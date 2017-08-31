/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Mileto
 */
public class FormaPagamento {

    private int id;
    private String descricao = "";
    private char formaAvista;
    private char ativo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char descricao) {
        this.ativo = descricao;
    }

    public char getFormaAvista() {
        return formaAvista;
    }

    public void setFormaAvista(char formaAvista) {
        this.formaAvista = formaAvista;
    }

}
