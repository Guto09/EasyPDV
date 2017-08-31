/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.sql.Date;

/**
 *
 * @author pc05
 */
public class Faturamento {

    private int id;
    private Cliente cliente;
    private FormaPagamento formaPagamento;
    private Empresa empresa;
    private String dataEmissao;
    private String dataEmissaoInicio;
    private String dataEmissaoFim;
    private char fase = '0';
    private double valorTotal;
    private double desconto;
    private double valorTotalLiquido;

    public double getValorTotalLiquido() {
        return valorTotalLiquido;
    }

    public void setValorTotalLiquido(double valorTotalLiquido) {
        this.valorTotalLiquido = valorTotalLiquido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public char getFase() {
        return fase;
    }

    public void setFase(char fase) {
        this.fase = fase;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getDataEmissaoInicio() {
        return dataEmissaoInicio;
    }

    public void setDataEmissaoInicio(String dataEmissaoInicio) {
        this.dataEmissaoInicio = dataEmissaoInicio;
    }

    public String getDataEmissaoFim() {
        return dataEmissaoFim;
    }

    public void setDataEmissaoFim(String dataEmissaoFim) {
        this.dataEmissaoFim = dataEmissaoFim;
    }

}
