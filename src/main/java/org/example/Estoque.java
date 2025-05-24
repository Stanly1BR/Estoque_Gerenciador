package org.example;

import org.example.exceptions.EstoqueInsuficienteException;
import org.example.exceptions.ProdutoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Estoque{
    private final List<Produto> produtos;

    Estoque(){
        this.produtos = new ArrayList<>();
    }

    public boolean adicionarProduto(Produto produto){
        for(Produto p : this.produtos){
            if(p.getCodigo() == produto.getCodigo()){
                return false;
            }
            if(p.getNome().equals(produto.getNome())){
                return false;
            }
        }
        this.produtos.add(produto);
        return true;
    }

    public boolean removerProduto(int codigo){
        for(Produto p : this.produtos){
            if(p.getCodigo() == codigo){
                this.produtos.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean buscarPeloNome(String nome){
        for(Produto p : this.produtos){
            if(p.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }

    public int quantidade(String nome) throws ProdutoNaoEncontradoException {
        for(Produto p : this.produtos){
            if(p.getNome().equals(nome)){
                return p.getQuantidade();
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com nome '" + nome + "' não encontrado ao buscar quantidade.");
    }

    public int buscarPeloCodigo(int codigo){
        for(Produto p : this.produtos){
            if(p.getCodigo() == codigo){
                return p.getQuantidade();
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com código " + codigo + " não encontrado ao buscar quantidade.");
    }

    public void diminuirQuantidadeInt(int codigo, int quantidadeDesejada) throws ProdutoNaoEncontradoException, EstoqueInsuficienteException, IllegalArgumentException {
        Produto produtoAlvo = null;
        for(Produto p : this.produtos){
            if(p.getCodigo() == codigo){
                produtoAlvo = p;
                break;
            }
        }
        if(produtoAlvo == null){
            throw  new ProdutoNaoEncontradoException("Tentativa de venda com código de produto inexistente: " + codigo);
        }
        if(quantidadeDesejada <=0){
            throw new IllegalArgumentException("A quantidade para venda deve ser positiva.");
        }
        if(produtoAlvo.getQuantidade() < quantidadeDesejada) {
            throw new EstoqueInsuficienteException("Tentativa de venda com quantidade (" + quantidadeDesejada + ") maior que o estoque disponível (" + produtoAlvo.getQuantidade() + ") para o produto código " + codigo + " (" + produtoAlvo.getNome() + ").");
        }
        produtoAlvo.setQuantidade(quantidadeDesejada);
    }


    public void diminuirQuantidadeString(String nome, int quantidadeDesejada)throws ProdutoNaoEncontradoException, EstoqueInsuficienteException, IllegalArgumentException {
        Produto produtoAlvo = null;
        for(Produto p : this.produtos){
            if(p.getNome().equals(nome)){
                produtoAlvo = p;
                break;
            }
        }
        if(produtoAlvo == null){
            throw new ProdutoNaoEncontradoException("Tentativa de venda com nome de produto inexistente: " + nome);
        }
        if(quantidadeDesejada <=0){
            throw new IllegalArgumentException("A quantidade para venda deve ser negativa.");
        }
        if (produtoAlvo.getQuantidade() < quantidadeDesejada){
            throw new EstoqueInsuficienteException("Tentativa de venda com quantidaejada); // Chama Produto.setQuantidade que subtraide (" + quantidadeDesejada + ") maior que o estoque disponível (" + produtoAlvo.getQuantidade() + ") para o produto: " + produtoAlvo.getNome());
        }
        produtoAlvo.setQuantidade(quantidadeDesejada);
    }

    public void ListarProdutos(){
        if (produtos.isEmpty()){
            System.out.println("Estoque vazio:");
        } else{
            for(Produto p: produtos){
                System.out.println(p);
            }
        }
    }
}