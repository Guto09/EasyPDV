/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Fornecedor;
import entidade.Mercadoria;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class FornecedorDAO {

    Fornecedor fornecedor;

    public boolean salvar(Fornecedor fornecedor) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //executeupdate = insert,update, delete
            //query = select
            if (fornecedor.getId() == 0) {

                String sql = "INSERT INTO fornecedor VALUES ("
                        + "DEFAULT," + "'" + fornecedor.getCidade().getId() + "',"
                        + "'" + fornecedor.getRazaoSocial() + "',"
                        + "'" + fornecedor.getTipoCadastro() + "',"
                        + "'" + fornecedor.getCpfCnpj() + "',"
                        + "'" + fornecedor.getEndereco() + "',"
                        + "'" + fornecedor.getTelefone() + "',"
                        + "'" + fornecedor.getAtivo() + "'"
                        + ")";
               // System.out.println(sql);
                int resultado = st.executeUpdate(sql);
            } else {
                String sql = "UPDATE fornecedor set id_cidade='" + fornecedor.getCidade().getId()
                        + "', razao_social ='" + fornecedor.getRazaoSocial()
                        + "', tipo_cadastro ='" + fornecedor.getTipoCadastro()
                        + "', cpf_cnpj ='" + fornecedor.getCpfCnpj()
                        + "', endereco ='" + fornecedor.getEndereco()
                        + "', telefone ='" + fornecedor.getTelefone()
                        + "', ativo ='" + fornecedor.getAtivo()
                        + "' where id =" + fornecedor.getId();

                int resultado = st.executeUpdate(sql);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro salvar Fornecedor = " + e);
        }
        return false;
    }

    public ArrayList<Fornecedor> consultar(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "";
        if (fornecedor.getId() == 0) {

            if (fornecedor.getAtivo() == 'T' || fornecedor.getAtivo() == 'F') {
                
                sql = "select f.id id_fornecedor,f.id_cidade,f.razao_social,f.cpf_cnpj,f.endereco, f.telefone,f.ativo,f.tipo_cadastro,"
                        + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                        + "from fornecedor f, cidade cid "
                        + "where (cpf_cnpj ilike '" + fornecedor.getCpfCnpj() + "%' or"
                        + " razao_social ilike '" + fornecedor.getRazaoSocial() + "%')"
                        + " and cid.id = f.id_cidade and f.ativo ='" + fornecedor.getAtivo() + "' order by f.razao_social";
              //  System.out.println("consulta T ou F..."+sql);

                //consulta cadastrado
            } else if (fornecedor.getRazaoSocial().equals("") && fornecedor.getCpfCnpj() != "" && fornecedor.getAtivo() == ' ') {
                sql = "select f.id id_fornecedor,f.id_cidade,f.razao_social,f.cpf_cnpj,f.endereco, f.telefone,f.ativo,f.tipo_cadastro,"
                        + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                        + "from fornecedor f, cidade cid "
                        + "where cpf_cnpj = '" + fornecedor.getCpfCnpj() + "'"
                        + " and cid.id = f.id_cidade"
                        + " order by f.razao_social";
              //  System.out.println("consulta cadastrado..."+sql);

            }else {
                sql = "select f.id id_fornecedor,f.id_cidade,f.razao_social,f.cpf_cnpj,c.endereco, f.telefone,f.ativo,f.tipo_cadastro,"
                        + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                        + "from fornecedor f, cidade cid "
                        + "where (cpf_cnpj ilike '" + fornecedor.getCpfCnpj() + "%' or"
                        + " razao_social ilike '" + fornecedor.getRazaoSocial() + "%')"
                        + " and cid.id = f.id_cidade order by f.razao_social";
             //   System.out.println(sql);
            }
            
            

            // id cliente for > 0
        } else {
           

            sql = "select f.id id_fornecedor,f.id_cidade,f.razao_social,f.cpf_cnpj,f.endereco, f.telefone,f.ativo,f.tipo_cadastro,"
                    + "cid.id id_cid, cid.descricao,cid.ativo ativo_cid "
                    + "from fornecedor f, cidade cid "
                    + "where (cpf_cnpj ilike '" + fornecedor.getCpfCnpj() + "%' or"
                    + " razao_social ilike '" + fornecedor.getRazaoSocial() + "%') "
                    + "and cid.id = f.id_cidade and f.id =" + fornecedor.getId() + " order by f.razao_social";
          //  System.out.println(sql);
        }

        //faz consulta e adiciona os valores para o array...
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //System.out.println(sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cidade cids = new Cidade();
                Fornecedor forn = new Fornecedor(cids);
                forn.setId(resultado.getInt("id_fornecedor"));
                forn.setRazaoSocial(resultado.getString("razao_social"));
                forn.setCpfCnpj(String.valueOf(resultado.getString("cpf_cnpj")));
                cids.setDescricao(resultado.getString("descricao"));
                cids.setId(resultado.getInt("id_cid"));
                forn.setCidade(cids);
                forn.getCidade().getDescricao();
                if (resultado.getString("telefone").equals("null")) {
                    
                    forn.setTelefone("");
                } else {
                    forn.setTelefone(resultado.getString("telefone"));
                }
                forn.setTipoCadastro((resultado.getString("tipo_cadastro").charAt(0)));
                if (resultado.getString("endereco").equals("null")) {
                    forn.setEndereco("");
                } else {
                    forn.setEndereco(String.valueOf(resultado.getString("endereco")));
                }
                forn.setAtivo(resultado.getString("ativo").charAt(0));
                fornecedores.add(forn);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Fornecedor " + e);
        }

        return fornecedores;
    }

}
