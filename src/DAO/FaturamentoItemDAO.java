/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import entidade.Cidade;
import entidade.FaturamentoItem;
import entidade.Mercadoria;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class FaturamentoItemDAO {

    private FaturamentoItem fat;

    public ArrayList<FaturamentoItem> consultar(FaturamentoItem fat, ArrayList<FaturamentoItem> mercs) {
        this.fat = fat;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            for (int i = 0; i < mercs.size(); i++) {

                String sql = "select id from  mercadoria  where referencia = '"
                        + mercs.get(i).getMercadoria().getReferencia() + "'";
               // System.out.println("sql");
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    mercs.get(i).getMercadoria().setId(Integer.parseInt(resultado.getString("id")));

                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar faturamento item " + e);
        }
        return mercs;
    }

    public ArrayList<FaturamentoItem> consultar(FaturamentoItem fat) {
        this.fat = fat;
        String sql = "    select m.referencia, m.descricao,f.quantidade,f.valor_unitario,"
                + " f.desconto,f.valor_total"
                + "    from  faturamento_item f,mercadoria m "
                + "where m.id = f.id_mercadoria "
                + "and  f.id_faturamento = " + fat.getIdFaturamento();
        ArrayList<FaturamentoItem> mercs = new ArrayList<>();
        //faz consulta e adiciona os valores para o array...
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
           // System.out.println(sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Mercadoria merc = new Mercadoria();
                FaturamentoItem fatItem = new FaturamentoItem();
                merc.setReferencia(resultado.getString("referencia"));
                merc.setDescricao(resultado.getString("descricao"));
                fatItem.setQuantidade(resultado.getDouble("quantidade"));
                fatItem.setValorUnitario(resultado.getDouble("valor_unitario"));
                fatItem.setValorTotal(resultado.getDouble("valor_total"));
                fatItem.setDesconto(resultado.getDouble("desconto"));
                fatItem.setMercadoria(merc);
                mercs.add(fatItem);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar faturamento item " + e);
        }

        return mercs;
    }

}
