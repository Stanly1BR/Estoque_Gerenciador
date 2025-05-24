package org.example;

import org.example.exceptions.ProdutoInvalidoException;

public class Produto {
    private final String nome;
    private final int codigo;
    private final int valor;
    private int quantidade;

    Produto(String nome, int codigo, int valor, int quantidade){
        if( nome == null || nome.trim().isEmpty()){
            throw new ProdutoInvalidoException("O nome do produto não pdoe ser null");
        }
        if(codigo <= 0){
            throw new ProdutoInvalidoException("O código não pode ser igual ou negativo");
        }
        if(valor <= 0){
            throw  new ProdutoInvalidoException("O Valor não pode ser igual ou menor que zero");
        }

        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int menosQuantidade) {
        this.quantidade = (this.quantidade - menosQuantidade);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", codigo=" + codigo +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}