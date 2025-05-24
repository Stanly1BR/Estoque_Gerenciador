# Sistema Básico de Controle de Estoque de Produtos

## 1. Visão Geral do Sistema

Este sistema foi desenvolvido para controlar de forma básica o estoque de produtos[cite: 1]. Ele oferece as seguintes ações:

* **Cadastrar Novos Produtos:** O usuário tem a opção de adicionar produtos ao estoque, informando nome, código, preço (valor) e a quantidade[cite: 2].
* **Simular Vendas:** O sistema possibilita registrar a saída de produtos, o que diminui a quantidade do item no estoque[cite: 3]. A venda pode acontecer buscando o produto pelo nome ou código, ambos pré-cadastrados[cite: 4].
* **Listar Produtos:** É possível ver todos os produtos registrados no estoque, com todos os detalhes (nome, código, valor e quantidade disponível)[cite: 5].
* **Excluir Produtos:** Os produtos podem ser retirados do estoque utilizando o código deles como identificador[cite: 6].

### 1.1. Estrutura do Sistema

A estrutura do sistema é formada pelas seguintes classes[cite: 7]:

* **`Produto.java`**: Representa o produto, guardando as características e verificando se os dados inseridos são válidos ao criar um novo produto[cite: 7, 8].
* **`Estoque.java`**: Administra uma lista de produtos, contendo a lógica para adicionar, remover, procurar e mudar a quantidade de produtos[cite: 8].
* **`Menu.java`**: É responsável pela comunicação com o usuário, mostrando as opções, recebendo os dados e organizando as chamadas para as funções do estoque[cite: 9].
* **Classes de Exceção Criadas**[cite: 10]:
    * `ProdutoInvalidoException.java`
    * `ProdutoNaoEncontradoException.java`
    * `EstoqueInsuficienteException.java`
    Usadas para tratar erros específicos[cite: 10].

## 2. Tipos de Exceções Tratadas e Por Quê

O sistema trata exceções para assegurar seu funcionamento correto e avisar o usuário caso algo dê errado[cite: 11]. As exceções tratadas são:

* **`ProdutoInvalidoException`**:
    * **Motivo:** É muito importante que os dados dos produtos estejam corretos desde o início[cite: 11]. Se alguém tentar cadastrar um produto com dados errados, como nome em branco, código zero ou negativo, ou valor zero[cite: 12]. Assim, evitamos produtos com dados inválidos no sistema[cite: 13].
    * **Onde surge:** Na classe `Produto`, quando um novo produto é criado[cite: 13].
    * **Onde é resolvido:** No `Menu.java`, quando alguém tenta criar um produto novo[cite: 14].

* **`ProdutoNaoEncontradoException`**:
    * **Motivo:** Para várias ações, como vender, ver a quantidade ou mudar algo, o produto precisa existir no estoque[cite: 15]. Se o produto não for achado, esse erro avisa que não dá para continuar[cite: 16].
    * **Onde surge:** Nos métodos `quantidade(String nome)`, `buscarPeloCodigo(int codigo)`, `diminuirQuantidadeInt()` e `diminuirQuantidadeString()` da classe `Estoque`[cite: 17].
    * **Onde é resolvido:** No `Menu.java`, quando o sistema tenta vender algo e também quando busca a quantidade antes de fazer algo[cite: 18].

* **`EstoqueInsuficienteException`**:
    * **Motivo:** Para que o estoque esteja sempre certo, o sistema não pode deixar vender mais do que tem[cite: 19]. Esse erro impede que isso aconteça[cite: 20].
    * **Onde surge:** Nos métodos `diminuirQuantidadeInt()` e `diminuirQuantidadeString()` da classe `Estoque`, se a quantidade que querem vender for maior que o que tem[cite: 20].
    * **Onde é resolvido:** No `Menu.java`, quando o usuário diz quanto quer comprar[cite: 21].

* **`IllegalArgumentException` (erro padrão do Java)**:
    * **Motivo:** Usado para checar se os dados passados para os métodos estão corretos, quando não são erros específicos[cite: 22]. Aqui, garante que a quantidade a ser vendida seja um número positivo[cite: 23].
    * **Onde surge:** Nos métodos `diminuirQuantidadeInt()` e `diminuirQuantidadeString()` da classe `Estoque`, se a quantidade for zero ou menos[cite: 24].
    * **Onde é resolvido:** No `Menu.java`, junto com o erro `EstoqueInsuficienteException`, quando tentam vender algo[cite: 25].

### 3. Como Foi Feito o Tratamento (Código e Lógica):

O tratamento de exceções no sistema segue o padrão de lançamento (`throw`) e captura (`try-catch`)[cite: 26]:

* **Na classe `Produto.java`**[cite: 27]:
    * O construtor checa se os dados estão corretos[cite: 27].
    * Se algo estiver errado (por exemplo, nome sem preenchimento, código ou valor zerado ou negativo), uma nova `ProdutoInvalidoException` é criada e enviada[cite: 28].
    * *Exemplo de trecho de código de `Produto.java`*:
        ```java
        // Exemplo simplificado baseado na descrição
        public Produto(String nome, int codigo, int valor, int quantidade) { // [cite: 29]
            if (nome == null || nome.trim().isEmpty()) { // [cite: 29]
                throw new ProdutoInvalidoException("O nome do produto não pode ser null"); // [cite: 29]
            }
            if (codigo <= 0) { // Ajustado com base em "código zero ou negativo" e "código não pode ser igual ou negativo"
                throw new ProdutoInvalidoException("O código não pode ser igual ou menor que zero"); // [cite: 29]
            }
            if (valor <= 0) { // [cite: 29]
                throw new ProdutoInvalidoException("O valor não pode ser igual ou menor que zero"); // [cite: 29]
            }
            // ...
        }
        ```

* **Na classe `Estoque.java`**:
    * Os métodos importantes (como procurar ou mudar a quantidade) verificam se tudo está ok antes de começar. Por exemplo, `diminuirQuantidadeInt` verifica se o produto existe, se a quantidade desejada é positiva e se há estoque suficiente. Se alguma dessas verificações falhar, a exceção apropriada (`ProdutoNaoEncontradoException`, `IllegalArgumentException`, `EstoqueInsuficienteException`) é lançada[cite: 29, 30].

* **Captura e Tratamento de Exceções (na classe `Menu.java`)**:
    * As chamadas das funções que podem enviar exceções ficam dentro de blocos `try`[cite: 30].
    * Depois do bloco `try`, um ou mais blocos `catch` são usados para cada tipo de exceção que precisa ser tratada[cite: 30].
    * O sistema mostra uma mensagem de erro clara para o usuário no console (`System.err.println(e.getMessage());`), explicando o que aconteceu e dando a chance de tentar de novo[cite: 31].
    * *Exemplos de trechos de código de `Menu.java` (ilustrativos com base nas imagens/texto fornecido)*:
        * Tratando `ProdutoInvalidoException` ao adicionar um produto:
            ```java
            // Exemplo simplificado
            try { // [cite: 33]
                Produto novoProduto = new Produto(nomeProduto, codigo, valor, quantidade); // [cite: 33]
                if (!estoque.adicionarProduto(novoProduto)) { // [cite: 33]
                    System.out.println("Produto ou código já cadastrado "); // [cite: 33]
                    System.out.println("Por favor, Verefique a Lista de produtos e tente novamente "); // [cite: 34]
                    estoque.listarProdutos(); // [cite: 34]
                } else {
                    System.out.println("Produto cadastrado com sucesso!"); // [cite: 34]
                }
            } catch (ProdutoInvalidoException e) { // [cite: 34]
                System.err.println("Erro ao cadastrar produto: " + e.getMessage()); // [cite: 34]
            }
            ```
        * Tratando exceções durante a venda por nome do produto:
            ```java
            // Exemplo simplificado
            try { // [cite: 34]
                // ... lógica para obter nome do produto e quantidade desejada ...
                if (!estoque.buscarPeloNome(nome)) { // [cite: 34] // Assumindo que buscarPeloNome verifica a existência
                    System.out.println("Estoque vazio"); // [cite: 34] // Modificado para clareza
                } else {
                    int quantidade = estoque.quantidade(nome); // [cite: 34] // Assumindo que 'quantidade' obtém o estoque disponível
                    // ... lógica para obter quantidadeDesejada ... [cite: 34]
                    estoque.diminuirQuantidadeString(nome, quantidadeDesejada); // [cite: 35]
                    System.out.println("Retirada foi realizada com sucesso!"); // [cite: 35]
                }
            } catch (ProdutoNaoEncontradoException e) { // [cite: 35]
                System.err.println("Erro: " + e.getMessage()); // [cite: 35]
            } catch (EstoqueInsuficienteException | IllegalArgumentException e) { // [cite: 35]
                System.err.println("Erro na venda: " + e.getMessage()); // [cite: 35]
            }
            ```
        * Tratando exceções durante a venda por código do produto:
             ```java
            // Exemplo simplificado
            try { // [cite: 36]
                // ... lógica para obter código do produto e quantidade desejada ...
                int quantidade = estoque.buscarPeloCodigo(codigo); // [cite: 36] // Assumindo que buscarPeloCodigo retorna quantidade ou lança exceção
                if (quantidade != 0) { // [cite: 36] // Ou verificar se o produto existe antes disso
                    // ... menuQuantidade e obter quantidadeDesejada ... [cite: 36]
                    estoque.diminuirQuantidadeInt(codigo, quantidadeDesejada); // [cite: 38]
                     System.out.println("Retirada foi realizada com sucesso!"); // [cite: 38]
                } else {
                    System.out.println("Estoque vazio"); // [cite: 38]
                }
            } catch (ProdutoNaoEncontradoException e) { // [cite: 39]
                System.err.println("Erro: " + e.getMessage()); // [cite: 39]
            } catch (EstoqueInsuficienteException | IllegalArgumentException e) { // [cite: 39]
                System.err.println("Erro na venda: " + e.getMessage()); // [cite: 39]
            }
            ```

## 4. Diferença Entre Exceções Verificadas e Não Verificadas (Aplicada ao Sistema) [cite: 40]

A principal diferença entre exceções verificadas (checked) e não verificadas (unchecked) em Java reside em como o compilador as trata[cite: 40]:

* **Exceções Não Checadas (Unchecked Exceptions)**:
    * **O que são:** São exceções que derivam da classe `RuntimeException` ou `Error`[cite: 40]. O compilador Java não obriga que o desenvolvedor as declare na cláusula `throws` de um método, nem que as capture de forma obrigatória com `try-catch`[cite: 41].
    * **Exemplos Típicos:** `NullPointerException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException`[cite: 42].
    * **Quando Usar:** Normalmente indicam erros na lógica do programa (bugs) ou falhas irrecuperáveis do ambiente (`Error`)[cite: 42]. A ideia é que esses erros deveriam ser corrigidos no código[cite: 43, 44], ou são tão graves que o programa não consegue se recuperar de forma adequada[cite: 44].
    * **No Meu Sistema**[cite: 45]:
        * Exceções personalizadas (`ProdutoInvalidoException`, `ProdutoNaoEncontradoException`, `EstoqueInsuficienteException`) herdam de `RuntimeException`, sendo classificadas como não verificadas[cite: 45].
        * A `IllegalArgumentException`, lançada em `Estoque.java`, também é não verificada[cite: 46].
        * **Como Aplicar:** Ao definir suas exceções como não verificadas, o compilador não exige que você as coloque em blocos `try-catch` onde os métodos que as lançam são chamados[cite: 46]. No entanto, eu optei por capturá-las na classe `Menu` para dar feedback ao usuário e permitir que o programa continue (em vez de terminar de repente)[cite: 47]. Isso é comum para erros que, mesmo sendo `RuntimeException`, vêm de entradas do usuário ou situações que podem ser comunicadas[cite: 48].

* **Exceções Checadas (Checked Exceptions)**[cite: 49]:
    * **O que são:** São exceções que herdam da classe `Exception`, mas não de `RuntimeException`[cite: 49]. O compilador Java exige que elas sejam tratadas[cite: 50]. Isso significa que um método que lança uma exceção verificada (ou chama um método que a lança) deve[cite: 50]:
        * Declarar a exceção em sua cláusula `throws`[cite: 50].
        * Ou capturá-la usando um bloco `try-catch`[cite: 51].
    * **Exemplos Típicos:** `IOException`, `SQLException`, `FileNotFoundException`[cite: 51].
    * **Quando Usar:** Representam situações excepcionais que um programa bem escrito deve prever e das quais pode, possivelmente, se recuperar[cite: 52]. Geralmente estão ligadas a falhas de recursos externos (arquivos, rede, banco de dados)[cite: 53].

Optar por um tipo ou outro de exceção é algo que se define ao projetar o sistema[cite: 54]. Exceções não checadas costumam ser usadas quando o problema não deve ser tratado diretamente pelo código que usa a função, ou para evitar muitos blocos `try-catch` quando o erro indica uma falha no código ou condição inválida[cite: 55]. Exceções verificadas obrigam o programador a considerar o que fazer em caso de falha[cite: 56].

## 5. Qual a Razão de Adotar Exceções Sob Medida [cite: 57]

O uso de exceções personalizadas, como `ProdutoInvalidoException`, `ProdutoNaoEncontradoException` e `EstoqueInsuficienteException` em meu sistema, concede diversas vantagens[cite: 57]:

* **Nitidez e Semântica Exata:**
    * Denominações de exceção como `EstoqueInsuficienteException` revelam de imediato a natureza da falha, simplificando a leitura do código em comparação com uma exceção vaga como `RuntimeException("Estoque insuficiente")`[cite: 57]. Elas explicitam a questão no contexto da sua aplicação[cite: 58].

* **Tratamento Exclusivo e Detalhado:**
    * Possibilitam que você capture e lide com diferentes tipos de falha de maneiras distintas[cite: 58]. Por exemplo, a medida a ser tomada quando um `ProdutoNaoEncontradoException` emerge pode ser diferente da ação para um `ProdutoInvalidoException`[cite: 59].

* **Otimização da Depuração e Manutenção:**
    * Quando um erro surge, o stack trace mostrará o nome da sua exceção sob medida, facilitando a identificação da causa principal do problema[cite: 60]. Isso é muito mais útil do que um `NullPointerException` genérico com múltiplas causas[cite: 61].

* **Abstração e Encapsulamento:**
    * Exceções personalizadas podem encapsular informações adicionais sobre o erro, se preciso (embora nesse caso atual elas utilizem principalmente a mensagem da superclasse)[cite: 62]. Por exemplo, `EstoqueInsuficienteException` poderia, em teoria, carregar campos como `produtoId`, `quantidadeRequisitada`, `quantidadeDisponivel`[cite: 63].

* **Impedir o Desvio de Códigos de Retorno:**
    * Ao invés de métodos retornarem códigos de erro numéricos ou `null` para sinalizar falhas (o que pode ser ignorado ou mal interpretado), lançar uma exceção personalizada força o tratamento da falha ou a propagação consciente da exceção[cite: 64].
