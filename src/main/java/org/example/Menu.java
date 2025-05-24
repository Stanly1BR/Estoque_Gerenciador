package org.example;

import org.example.exceptions.EstoqueInsuficienteException;
import org.example.exceptions.ProdutoInvalidoException;
import org.example.exceptions.ProdutoNaoEncontradoException;

import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estoque estoque = new Estoque();

        int opcao;

        do{
            menu();
            opcao = sc.nextInt(); sc.nextLine();

            if(opcao == 1){
                System.out.println("Digite o nome do produto: ");
                String nomeProduto = sc.nextLine();

                System.out.println("Digite o código do produto: ");
                int codigo = sc.nextInt(); sc.nextLine();

                System.out.println("Digite o Valor do produto:");
                int valor = sc.nextInt(); sc.nextLine();

                System.out.println("Digite a quantidade do produto: ");
                int quantidade = sc.nextInt(); sc.nextLine();

                try {
                    Produto novoProduto = new Produto(nomeProduto, codigo, valor, quantidade);

                    if(!estoque.adicionarProduto(novoProduto)){
                        System.out.println("Produto ou código já cadastrado!");
                        System.out.println("_____________________________________________________________");
                        System.out.println("Por favor, Verefique a Lista de produtos e tente novamente!");
                        System.out.println("_____________________________________________________________");
                        estoque.ListarProdutos();
                    }else{
                        System.out.println("Produto cadastrado com sucesso!");
                    }

                }catch (ProdutoInvalidoException e){
                    System.err.println("Erro ao cadastrar produto: "+e.getMessage());
                }
                aperteEnter(sc);

            }else if (opcao == 2){
                menuVenda();
                int opcaoVenda = sc.nextInt(); sc.nextLine();

                if (opcaoVenda == 1){
                    System.out.println("Digite o nome do produto: ");
                    String nome = sc.nextLine();
                    try {
                        if (!estoque.buscarPeloNome(nome)) {
                            System.out.println("Estoque vazio");
                        } else {
                            int quantidade = estoque.quantidade(nome);

                            if (quantidade != 0) {
                                menuQuantidade(quantidade);
                                int quantidadeDesejada = sc.nextInt(); sc.nextLine();

                                if (quantidadeDesejada > quantidade) {
                                    System.out.println("Quantidade desejada fora de estoque!");
                                } else {
                                    System.out.println("Retirada foi realizada com sucesso!");
                                    System.out.println("Quantidade: " + quantidadeDesejada);
                                    estoque.diminuirQuantidadeString(nome, quantidadeDesejada);
                                }
                            }
                        }
                    }catch (ProdutoNaoEncontradoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }catch (EstoqueInsuficienteException | IllegalArgumentException e){
                        System.err.println("Erro na venda: " + e.getMessage());
                    }

                } else if (opcaoVenda == 2) {
                    System.out.println("Digite o código do produto: ");
                    int codigo = sc.nextInt(); sc.nextLine();

                    try {
                        int quantidade = estoque.buscarPeloCodigo(codigo);

                        if (quantidade != 0) {
                            menuQuantidade(quantidade);
                            int quantidadeDesejada = sc.nextInt();
                            sc.nextLine();

                            if (quantidadeDesejada > quantidade) {
                                System.out.println("Quantidade desejada fora de estoque!");
                            } else {
                                System.out.println("Retirada foi realizada com sucesso!");
                                System.out.println("Quantidade: " + quantidadeDesejada);
                                estoque.diminuirQuantidadeInt(codigo, quantidadeDesejada);
                            }
                        } else {
                            System.out.println("Estoque vazio");
                        }

                    }catch (ProdutoNaoEncontradoException e) {
                        System.err.println("Erro: " + e.getMessage());
                    } catch (EstoqueInsuficienteException | IllegalArgumentException e) {
                        System.err.println("Erro na venda: " + e.getMessage());
                    }
                }
                aperteEnter(sc);

            }else if (opcao ==3){
                System.out.println("Lista de produtos:");
                estoque.ListarProdutos();
                aperteEnter(sc);

            }else if (opcao ==4){
                System.out.println("Lista de produtos:");
                System.out.println("(Em caso de estoque vazio aperter (0)");
                estoque.ListarProdutos();
                System.out.println("Qual o código do produto que deseja excluir?: ");
                int codigo = sc.nextInt(); sc.nextLine();

                if(codigo != 0) {
                    if (!estoque.removerProduto(codigo)) {
                        System.out.println("Produto não encontrado");
                    } else {
                        System.out.println("Produto excluido com sucesso");
                    }
                }
                aperteEnter(sc);
            }
        }while(opcao != 0);
        sc.close();
    }

    private static void menu(){
        System.out.println("___________________________");
        System.out.println("-----------MENU------------");
        System.out.println("___________________________");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Vender Produto");
        System.out.println("3 - Listar Produto");
        System.out.println("4 - Excluir Produto");
        System.out.println("0 - Sair");
        System.out.println("___________________________");
        System.out.print("Qual a opção?: ");
    }
    private static void menuVenda(){
        System.out.println("_______________________________________");
        System.out.println("-----------Venda do produto------------");
        System.out.println("________________________________________");
        System.out.println("1 - Comprar pelo nomeProduto");
        System.out.println("2 - Comprar pelo codigoProduto");
        System.out.println("________________________________________");
        System.out.print("Qual a opção?: ");
    }

    private static void menuQuantidade(int quantidade){
        System.out.println("_______________________________________");
        System.out.println("Quantidade do produto: "+quantidade);
        System.out.println("________________________________________");
        System.out.print("Quantidade do produto que deseja comprar? : ");
    }

    private static void aperteEnter(Scanner sc){
        System.out.println("________________________________________");
        System.out.println("Aperte Enter para continuar...");
        sc.nextLine();
        System.out.println("________________________________________");
    }
}