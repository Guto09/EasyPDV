/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import entidade.Cidade;
import entidade.Usuario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class UsuarioDAO {

    private Usuario user;

    public void consultar(Usuario user) {
        this.user = user;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "select * from  usuario";
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {

                user.setId(resultado.getInt("id"));
                user.setUsuario(resultado.getString("login"));
                user.setSenha(resultado.getString("senha"));

            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar Usuarios " + e);
        }

    }

    public boolean salvar(Usuario user) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "update usuario set senha ='" + user.getSenha()+"'";
            //System.out.println(sql);
            int resultado = st.executeUpdate(sql);
            
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Usuario " + e);

        }
        return false;

    }
}
