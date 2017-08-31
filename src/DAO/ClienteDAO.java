/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Mercadoria;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ClienteDAO {

    Cliente cli;

    public boolean salvar(Cliente cli) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //executeupdate = insert,update, delete
            //query = select
            if (cli.getId() == 0) {

                String sql = "INSERT INTO cliente VALUES ("
                        + "DEFAULT," + "'" + cli.getCidade().getId() + "',"
                        + "'" + cli.getRazaoSocial() + "',"
                        + "'" + cli.getTipoCadastro() + "',"
                        + "'" + cli.getCpfCnpj() + "',"
                        + "'" + cli.getEndereco() + "',"
                        + "'" + cli.getTelefone() + "',"
                        + "'" + cli.getAtivo() + "'"
                        + ")";
                //System.out.println(sql);
                int resultado = st.executeUpdate(sql);
            } else {
                String sql = "UPDATE cliente set id_cidade='" + cli.getCidade().getId()
                        + "', razao_social ='" + cli.getRazaoSocial()
                        + "', tipo_cadastro ='" + cli.getTipoCadastro()
                        + "', cpf_cnpj ='" + cli.getCpfCnpj()
                        + "', endereco ='" + cli.getEndereco()
                        + "', telefone ='" + cli.getTelefone()
                        + "', ativo ='" + cli.getAtivo()
                        + "' where id =" + cli.getId();

                int resultado = st.executeUpdate(sql);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro salvar Cliente = " + e);
        }
        return false;
    }

    public ArrayList<Cliente> consultar(Cliente cli) {
        this.cli = cli;
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "";
        if (cli.getId() == 0) {

            if (cli.getAtivo() == 'T' || cli.getAtivo() == 'F') {
                
                sql = "select c.id id_cliente,c.id_cidade,c.razao_social,c.cpf_cnpj,c.endereco, c.telefone,c.ativo,c.tipo_cadastro,"
                        + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                        + "from cliente c, cidade cid "
                        + "where (cpf_cnpj ilike '" + cli.getCpfCnpj() + "%' or"
                        + " razao_social ilike '" + cli.getRazaoSocial() + "%')"
                        + " and cid.id = c.id_cidade and c.ativo ='" + cli.getAtivo() + "' order by c.razao_social";
              //  System.out.println("consulta T ou F..."+sql);

                //consulta cadastrado
            } else if (cli.getRazaoSocial().equals("") && cli.getCpfCnpj() != "" && cli.getAtivo() == ' ') {
                sql = "select c.id id_cliente,c.id_cidade,c.razao_social,c.cpf_cnpj,c.endereco, c.telefone,c.ativo,c.tipo_cadastro,"
                        + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                        + "from cliente c, cidade cid "
                        + "where cpf_cnpj = '" + cli.getCpfCnpj() + "'"
                        + " and cid.id = c.id_cidade"
                        + " and c.id > 1   order by c.razao_social";
             //   System.out.println("consulta cadastrado..."+sql);

            }else if (cli.getRazaoSocial().equals("") && cli.getCpfCnpj() != "" && cli.getAtivo()== 'V') {
                sql = "select c.id id_cliente,c.id_cidade,c.razao_social,c.cpf_cnpj,c.endereco, c.telefone,c.ativo,c.tipo_cadastro,"
                        + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                        + "from cliente c, cidade cid "
                        + "where cpf_cnpj = '" + cli.getCpfCnpj() + "'"
                        + " and cid.id = c.id_cidade"
                        + " and c.id > 1 and c.ativo = 'T'  order by c.razao_social";
            }else {
                sql = "select c.id id_cliente,c.id_cidade,c.razao_social,c.cpf_cnpj,c.endereco, c.telefone,c.ativo,c.tipo_cadastro,"
                        + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                        + "from cliente c, cidade cid "
                        + "where (cpf_cnpj ilike '" + cli.getCpfCnpj() + "%' or"
                        + " razao_social ilike '" + cli.getRazaoSocial() + "%')"
                        + " and cid.id = c.id_cidade order by c.razao_social";
             //   System.out.println(sql);
            }
            
            

            // id cliente for > 0
        } else {
           

            sql = "select c.id id_cliente,c.id_cidade,c.razao_social,c.cpf_cnpj,c.endereco, c.telefone,c.ativo,c.tipo_cadastro,"
                    + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                    + "from cliente c, cidade cid "
                    + "where (cpf_cnpj ilike '" + cli.getCpfCnpj() + "%' or"
                    + " razao_social ilike '" + cli.getRazaoSocial() + "%') "
                    + "and cid.id = c.id_cidade and c.id =" + cli.getId() + " order by c.razao_social";
         //   System.out.println(sql);
          
        }

        //faz consulta e adiciona os valores para o array...
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
           // System.out.println(sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cidade cids = new Cidade();
                Cliente cliente = new Cliente(cids);
                cliente.setId(resultado.getInt("id_cliente"));
                cliente.setRazaoSocial(resultado.getString("razao_social"));
                cliente.setCpfCnpj(String.valueOf(resultado.getString("cpf_cnpj")));
                cids.setDescricao(resultado.getString("descricao"));
                cids.setId(resultado.getInt("id_cid"));
                cliente.setCidade(cids);
                cliente.getCidade().getDescricao();
                if (resultado.getString("telefone").equals("null")) {
                    
                    cliente.setTelefone("");
                } else {
                    cliente.setTelefone(resultado.getString("telefone"));
                }
                cliente.setTipoCadastro((resultado.getString("tipo_cadastro").charAt(0)));
                if (resultado.getString("endereco").equals("null")) {
                    cliente.setEndereco("");
                } else {
                    cliente.setEndereco(String.valueOf(resultado.getString("endereco")));
                }
                cliente.setAtivo(resultado.getString("ativo").charAt(0));
                clientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Cliente " + e);
        }

        return clientes;
    }

}
