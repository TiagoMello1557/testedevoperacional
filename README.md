# testedevoperacional

Olá, recrutador(a). Primeiramente, muito obrigado pela oportunidade e atenção. Esse teste foi feito usando IntelliJ IDEA por ser o IDE que estou mais familiarizado. Agora vamos revisar minhas mudanças ao projeto.

1. Mudanças na estrutura

A primeira coisa que decidi fazer quando olhei e revisei o código foi reorganizar o código da classe Main em pequenos métodos para aprimorar a visibilidade, leitura e Manutenibilidade do projeto. Com isso em mente
criei novas funções separando a lógica do projeto em cada uma delas, e passando as listas feitas para simular um banco de dados de forma adequada sem que o código pare de funcionar normalmente.

2- Bugs e erros de regra de negócio

Após separar a lógica do código, decidi testar e debugar o mesmo para encontrar erros inesperados:

- A primeira coisa que notei foi que realizar funções com o usuário Admin iria inevitavelmente resultar em erros, já que o mesmo cai nas ações dos clientes, e ele não tem um Objeto Cliente espelhando admin.
 Como queria que o código não desse erro nenhum antes de fazer alterações definitivas, eu simplesmente adicionei um cliente extra para esse usuário;

- Depois disso percebi que ao fazer compras, se eu escolhesse um número que não esta na lista daquela loja, porem existe na lista de outra loja, o programa irá adicionar o preço daquele item
ao total da compra no resumo e irá adicionar o próprio produto na lista de compras. Para arrumar isso eu simplesmente reforcei uma condição que para adicionar o item ao carrinho, o mesmo deve conter o id da loja
da compra atual;

- Percebi também que o saldo da empresa não estava refletindo o valor correto após aplicação das taxas, ajustei isso no cálculo do saldo na função das vendas.

- Finalmente notei que o código desconecta o usuário sempre que completa qualquer ação, para arrumar isso eu passo o mesmo Objeto de usuário logado pela aplicação para manter o usuário logado até o cliente decidir
  desconectar de fato. Quando a função deslogar é chamada, elá transforma o Objeto de usuário logado em Null, assim quando loop for feito, a autenticação tera de ser feita novamente.

3. Coisas que não consegui implementar por conta do tempo

- Gostaria que os produtos a serem vendidos ficassem em uma ordem crescente e que o número não fosse refletido apenas no ID do item
- Atualmente o usuário de Admin tem a mesma função e permissões que um usuário cliente comum, o que vai contra a regra de negócio. Eu implementaria ações exclusivas para esse usuário que permitem que ele não só
faça compras, mas como também ver todas as compras, vendas e informações de usuário e empresas.

4 - Sugestões de Tecnologias

Obviamente para um projeto desse tipo, usar algo como Spring Boot seria uma boa escolha, pois com ele poderíamos mapear as classes com tabelas de um banco de dados verdadeiro, ou um banco de dados embarcados.
Além disso teríamos a opção de usar Templates para uma interface melhorada para o usuário. Com Spring poderiamos usar uma arquitetura orientada a serviços, com RESTful APIs, separando cada interfaço de tipo
de usuario em um endpoint diferente. Poderiamos usar Poderíamos também usar Lombok para reduzir códigos boilerplate como getters e setters.

Comentarios Finais

Apesar de ter tido dificuldades no início do teste, por conta de minha inexperiência com projetos Eclipse, e como importá-los para o IntelliJ, a experiência no geral foi muito interessante e divertida. Não consegui,
mostrar tudo que sou capaz de fazer como programado, porem acredito que tenha balanceado bem as prioridades com o tempo limite do teste.
Também quero agradecer muito pelo seu tempo e paciência ao revisar meu código. Espero ter a oportunidade de bater um papo sobre o teste, e discutir outras formas de resolver os problemas envolvidos.
