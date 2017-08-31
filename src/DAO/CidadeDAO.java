/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import entidade.Cidade;
import entidade.FormaPagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * 
 *
 * @author pc05
 */
public class CidadeDAO {

    Cidade cid;

    public boolean salvar(Cidade cidade) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //executeupdate = insert,update, delete
            //query = select

            if (cidade.getId() == 0) {

                String sql = "INSERT INTO cidade VALUES ("
                        + "DEFAULT," + "'" + cidade.getDescricao() + "',"
                        + "'" + cidade.getAtivo() + "'"
                        + ")";
                int resultado = st.executeUpdate(sql);
            } else {
                String sql = "UPDATE cidade set descricao='" + cidade.getDescricao()
                        + "', ativo ='" + cidade.getAtivo()
                        + "' where id =" + cidade.getId();

                int resultado = st.executeUpdate(sql);

            }
            return true;
        } catch (Exception e) {

            System.out.println("Erro salvar Cidade = " + e);
        }
        return false;
    }

    public ArrayList<Cidade> consultar(Cidade cid) {
        this.cid = cid;
        ArrayList<Cidade> cidades = new ArrayList<>();

        if (cid.getAtivo() == 'T' || cid.getAtivo() == 'F') {
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from  cidade "
                        + "where descricao ilike '" + cid.getDescricao() + "%'"
                        + "and ativo = '" + cid.getAtivo() + "' order by id";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Cidade cidade = new Cidade();
                    cidade.setId(resultado.getInt("id"));
                    cidade.setDescricao(resultado.getString("descricao"));
                    cidade.setAtivo(resultado.getString("ativo").charAt(0));
                    cidades.add(cidade);
                }
            } catch (Exception e) {
                System.out.println("Erro ao consultar cidades " + e);
            }
        }else {

            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from  cidade "
                        + "where  descricao ilike '" + cid.getDescricao() + "%' order by id";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Cidade cidade = new Cidade();
                    cidade.setId(resultado.getInt("id"));
                    cidade.setDescricao(resultado.getString("descricao"));
                    cidade.setAtivo(resultado.getString("ativo").charAt(0));
                    cidades.add(cidade);
                }
            } catch (Exception e) {
                System.out.println("Erro ao consultar cidades " + e);
//                System.out.println("Erro ao consultar cidades " + e);
            }

        }
        return cidades;

    }


}
