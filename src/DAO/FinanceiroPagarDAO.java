/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import apoio.Formatacao;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Faturamento;
import entidade.FaturamentoItem;
import entidade.FinanceiroPagar;
import entidade.FormaPagamento;
import entidade.FinanceiroReceber;
import entidade.Fornecedor;
import entidade.Mercadoria;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tela.JdgPedidoVenda;

/**
 *
 * @author Mileto
 */
public class FinanceiroPagarDAO {

    ArrayList<FinanceiroPagar> formasPagas;
    FinanceiroPagar formaPagamentoPagas;

    public boolean salvar(FinanceiroPagar formaPagamentoPagas) {
        this.formaPagamentoPagas = formaPagamentoPagas;
        if (this.formaPagamentoPagas.getId() == 0) {
            try {

                Statement st = ConexaoBD.getInstance().getConnection().createStatement();

                //for (int i = 0; i < formasPagas.size(); i++) {
                String sql = "";
                if (formaPagamentoPagas.getDataPagamento() == null) {

                    sql = "INSERT INTO financeiro_pagar VALUES ("
                            + "DEFAULT,"
                            + "" + formaPagamentoPagas.getFornecedor().getId() + ","
                            + "" + formaPagamentoPagas.getFormaPagamento().getId() + ","
                            + "'" + formaPagamentoPagas.getNumeroTitulo() + "',"
                            + "'" + formaPagamentoPagas.getDataEmissao() + "',"
                            + "'" + formaPagamentoPagas.getVencimento() + "',"
                            + "null,"
                            + "'" + formaPagamentoPagas.getValorProvisorio() + "',"
                            + formaPagamentoPagas.getValor() + ","
                            + formaPagamentoPagas.getValorPago() + ","
                            + "'" + formaPagamentoPagas.getQuitado() + "',"
                            + "'" + formaPagamentoPagas.getAtivo() + "'"
                            + ")";
                } else {

                    sql = "INSERT INTO financeiro_pagar VALUES ("
                            + "DEFAULT,"
                            + "" + formaPagamentoPagas.getFornecedor().getId() + ","
                            + "" + formaPagamentoPagas.getFormaPagamento().getId() + ","
                            + "'" + formaPagamentoPagas.getNumeroTitulo() + "',"
                            + "'" + formaPagamentoPagas.getDataEmissao() + "',"
                            + "'" + formaPagamentoPagas.getVencimento() + "',"
                            + "'" + formaPagamentoPagas.getDataPagamento() + "',"
                            + "'" + formaPagamentoPagas.getValorProvisorio() + "',"
                            + formaPagamentoPagas.getValor() + ","
                            + formaPagamentoPagas.getValorPago() + ","
                            + "'" + formaPagamentoPagas.getQuitado() + "',"
                            + "'" + formaPagamentoPagas.getAtivo() + "'"
                            + ")";
                }
                //System.out.println(sql);
                int resultado = st.executeUpdate(sql);
                //}

                return true;
            } catch (Exception e) {
                Logger.getLogger(JdgPedidoVenda.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Erro cadastrar título = " + e);
            }
            return true;
        } else {
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "";
                if (formaPagamentoPagas.getDataPagamento() != "" && formaPagamentoPagas.getValor() == 0 && formaPagamentoPagas.getValorPago() == 0 && formaPagamentoPagas.getAtivo()!= 'F') {
                    
                    sql = "update financeiro_pagar set valor_pago = " + formaPagamentoPagas.getValorPago() + ","
                            + " quitado ='" + formaPagamentoPagas.getQuitado() + "',"
                            + " data_pagamento = '" + formaPagamentoPagas.getDataPagamento() + "',"
                            + " ativo = '" + formaPagamentoPagas.getAtivo() + "'"
                            + " where id = " + formaPagamentoPagas.getId();

                   // System.out.println(sql);
                    int resultado = st.executeUpdate(sql);
                    return true;
                } else if (formaPagamentoPagas.getValor() == 0 && formaPagamentoPagas.getValorPago() > 0 && formaPagamentoPagas.getAtivo()!= 'F') {
                    sql = "update financeiro_pagar set "
                            
                            + " quitado ='" + formaPagamentoPagas.getQuitado() + "',"
                            + "valor_pago = " + formaPagamentoPagas.getValor()
                            + " where id = " + formaPagamentoPagas.getId();
                    int resultado = st.executeUpdate(sql);
                   // System.out.println(sql);
                    return true;
                }else if ( formaPagamentoPagas.getValor() > 0 && formaPagamentoPagas.getValorPago() == 0 && formaPagamentoPagas.getAtivo()!= 'F') {
                    sql = "update financeiro_pagar set "
                            
                            + "valor_titulo = " + formaPagamentoPagas.getValor()
                            + " where id = " + formaPagamentoPagas.getId();
                    int resultado = st.executeUpdate(sql);
                    //System.out.println(sql);
                    return true;
                }else if ( formaPagamentoPagas.getValor() > 0 && formaPagamentoPagas.getValorPago() > 0 && formaPagamentoPagas.getAtivo()!= 'F') {
                    sql = "update financeiro_pagar set "
                            + "valor_pago = " + formaPagamentoPagas.getValorPago() + ","
                            
                            + " quitado ='" + formaPagamentoPagas.getQuitado() + "',"
                            + "valor_titulo = " + formaPagamentoPagas.getValor()
                            + " where id = " + formaPagamentoPagas.getId();
                    int resultado = st.executeUpdate(sql);
                  //  System.out.println(sql);
                    return true;
                }else if (formaPagamentoPagas.getAtivo()== 'F') {
                    sql = "update financeiro_pagar set ativo = 'F'"
                            + "where id = "+formaPagamentoPagas.getId();
                    int resultado = st.executeUpdate(sql);
                  //  System.out.println(sql);
                    return true;
                }

            } catch (Exception e) {
                Logger.getLogger(JdgPedidoVenda.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }

        }
        return false;
    }

    public ArrayList<FinanceiroPagar> consultar(FinanceiroPagar formaPagamentoPagas, int tipoFiltro) {
        this.formaPagamentoPagas = formaPagamentoPagas;
        String sql = "";
        if ((formaPagamentoPagas.getDataInicio() == null && formaPagamentoPagas.getDataFim() == null)
                && (formaPagamentoPagas.getQuitado() == 'T' || formaPagamentoPagas.getQuitado() == 'F')) {

            sql = "select f.id idfinanceiro,f.id_fornecedor,f.numero_titulo,f.data_emissao,"
                    + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio, "
                    + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                    + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                    + "fp.descricao "
                    + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                    + "where c.id = f.id_fornecedor"
                    + " and fp.id = f.id_forma_pagamento "
                    + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                    + "and f.ativo = 'T' "
                    + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                    + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
        } else if ((formaPagamentoPagas.getDataInicio() != null && formaPagamentoPagas.getDataFim() != null)
                && (formaPagamentoPagas.getQuitado() == 'T' || formaPagamentoPagas.getQuitado() == 'F')) {
            if (tipoFiltro == 0) {
                sql = "select f.id idfinanceiro,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                        + "fp.descricao "
                        + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                        + "where c.id = f.id_fornecedor"
                        + " and fp.id = f.id_forma_pagamento "
                        + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                        + "and f.data_emissao >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_emissao <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 1) {
                sql = "select f.id idfinanceiro,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                        + "fp.descricao "
                        + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                        + "where c.id = f.id_fornecedor"
                        + " and fp.id = f.id_forma_pagamento "
                        + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                        + "and f.data_vencimento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_vencimento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 2) {
                sql = "select f.id idfinanceiro,f.id_fornecedor,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                        + "fp.descricao "
                        + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                        + "where c.id = f.id_fornecedor"
                        + " and fp.id = f.id_forma_pagamento "
                        + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                        + "and f.data_pagamento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_pagamento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            }
        } else if ((formaPagamentoPagas.getDataInicio() == null && formaPagamentoPagas.getDataFim() == null)
                && (formaPagamentoPagas.getQuitado() == 'A')) {

            sql = "select f.id idfinanceiro,f.id_fornecedor,f.numero_titulo,f.data_emissao,"
                    + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio, "
                    + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                    + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                    + "fp.descricao "
                    + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                    + "where c.id = f.id_fornecedor"
                    + " and fp.id = f.id_forma_pagamento "
                    + "and f.ativo = 'T' "
                    + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                    + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";

        } else if ((formaPagamentoPagas.getDataInicio() != null && formaPagamentoPagas.getDataFim() != null)
                && (formaPagamentoPagas.getQuitado() == 'A')) {
            if (tipoFiltro == 0) {
                sql = "select f.id idfinanceiro,f.id_fornecedor,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio, "
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                        + "fp.descricao "
                        + " from financeiro_pagar f, cliente c,forma_pagamento fp "
                        + "where c.id = f.id_fornecedor"
                        + " and fp.id = f.id_forma_pagamento "
                        + "and f.data_emissao >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_emissao <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 1) {
                sql = "select f.id idfinanceiro,f.id_fornecedor,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio, "
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                        + "fp.descricao "
                        + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                        + "where c.id = f.id_fornecedor"
                        + " and fp.id = f.id_forma_pagamento "
                        + "and f.data_vencimento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_vencimento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 2) {
                sql = "select f.id idfinanceiro,f.id_fornecedor,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio ,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                        + "fp.descricao "
                        + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                        + "where c.id = f.id_fornecedor"
                        + " and fp.id = f.id_forma_pagamento "
                        + "and f.data_pagamento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_pagamento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getFornecedor().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            }

        }
        ArrayList<FinanceiroPagar> formas = new ArrayList<>();
        //faz consulta e adiciona os valores para o array...
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
           // System.out.println(sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                FinanceiroPagar forma = new FinanceiroPagar();
                //FaturamentoItem fatItem = new FaturamentoItem();
                Cidade cid = new Cidade();
                Fornecedor forn = new Fornecedor(cid);
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setDescricao(resultado.getString("descricao"));
                forma.setFormaPagamento(formaPagamento);
                forma.setId(resultado.getInt("idfinanceiro"));
                forma.setNumeroTitulo(resultado.getString("numero_titulo"));
                forma.setDataEmissao(Formatacao.ajustaDataDMAHora(resultado.getString("data_emissao")));
                forma.setVencimento(Formatacao.ajustaDataDMA(resultado.getString("data_vencimento")));
                forma.setValor(resultado.getDouble("valor_titulo"));
                forma.setValorProvisorio(resultado.getDouble("valor_provisorio"));

                if (resultado.getString("data_pagamento") == null) {
                    forma.setDataPagamento("");
                } else {

                    forma.setDataPagamento(Formatacao.ajustaDataDMA(resultado.getString("data_pagamento")));
                }
                forma.setValorPago(resultado.getDouble("valor_pago"));
                forma.setQuitado(resultado.getString("quitado").charAt(0));
                forma.setAtivo(resultado.getString("ativofinanceiro").charAt(0));
                forn.setId(resultado.getInt("id_fornecedor"));
                forn.setRazaoSocial(resultado.getString("razao_social"));
                forn.setCpfCnpj(resultado.getString("cpf_cnpj"));
                forn.setEndereco(resultado.getString("endereco"));
                forma.setFornecedor(forn);

                formas.add(forma);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Título " + e);
        }

        return formas;
    }

    public ArrayList<FinanceiroPagar> consultar(FinanceiroPagar formaPagamentoPagas) {
        this.formaPagamentoPagas = formaPagamentoPagas;
        String sql = "";

        sql = "select f.id idfinanceiro,f.id_fornecedor,f.numero_titulo,f.data_emissao,"
                + "f.data_vencimento,f.data_pagamento,f.valor_titulo,f.valor_provisorio, "
                + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idfornecedor,"
                + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativofornecedor,"
                + "fp.descricao "
                
                + " from financeiro_pagar f, fornecedor c,forma_pagamento fp "
                
                + "where c.id = f.id_fornecedor"
                + " and fp.id = f.id_forma_pagamento "
                + "and f.ativo = 'T' "
                + "and f.numero_titulo = '" + formaPagamentoPagas.getNumeroTitulo()+ "' ";
                

        ArrayList<FinanceiroPagar> formas = new ArrayList<>();
        //faz consulta e adiciona os valores para o array...
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
           // System.out.println(sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                FinanceiroPagar forma = new FinanceiroPagar();                
                Cidade cid = new Cidade();
                Fornecedor forn = new Fornecedor(cid);
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setDescricao(resultado.getString("descricao"));
                forma.setFormaPagamento(formaPagamento);
                forma.setId(resultado.getInt("idfinanceiro"));
                forma.setNumeroTitulo(resultado.getString("numero_titulo"));
                forma.setDataEmissao(Formatacao.ajustaDataDMAHora(resultado.getString("data_emissao")));
                forma.setVencimento(Formatacao.ajustaDataDMA(resultado.getString("data_vencimento")));
                forma.setValor(resultado.getDouble("valor_titulo"));
                forma.setValorProvisorio(resultado.getDouble("valor_provisorio"));

                formas.add(forma);

            }

        } catch (Exception e) {

        }

        return formas;
    }

}
