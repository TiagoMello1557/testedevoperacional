import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// SIMULANDO BANCO DE DADOS
		Usuario usuarioLogado = null;
		List<Produto> carrinho = new ArrayList<>();
		List<Venda> vendas = new ArrayList<>();
		List<Cliente> clientes = new ArrayList<>();
		List<Empresa> empresas = new ArrayList<>();
		List<Produto> produtos = new ArrayList<>();
		List<Usuario> usuarios = new ArrayList<>();

		iniciarBancoDeDados(clientes, empresas, produtos, usuarios);

		executarLoop(usuarios, clientes, empresas, produtos, carrinho, vendas, usuarioLogado);
	}

	private static Usuario autenticarUsuario(Scanner scanner, List<Usuario> usuarios) {
		Usuario usuarioLogado;
		System.out.println("Escreva seu usuario e senha:");
		System.out.print("Usuario: ");
		String usuario = scanner.next();
		System.out.print("Senha: ");
		String senha = scanner.next();

		List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(usuario))
				.toList();

		//verifica se existe o usuario no banco
		if (usuariosSearch.isEmpty()) {
			System.out.println("Usuario não encontrado");
			return null;
        }

		usuarioLogado = usuariosSearch.get(0);

		//verifica se a senha esta certa
		if (!usuarioLogado.getSenha().equals(senha)){
			System.out.println("Senha incorreta");
			return null;
		}

		return usuarioLogado;
	}

	private static void iniciarBancoDeDados(
			List<Cliente> clientes, List<Empresa> empresas, List<Produto> produtos, List<Usuario> usuarios
	) {
		//cadastrando empresas
		empresas.add(new Empresa(1, "SafeWay Padaria", "53239160000154", 0.05, 0.0));
		empresas.add(new Empresa(2, "Level Varejo", "30021423000159", 0.15, 0.0));
		empresas.add(new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0));

		//cadastrando produtos
		produtos.add(new Produto(1, "Pão Frances", 5, 3.50, empresas.get(0)));
		produtos.add(new Produto(2, "Coturno", 10, 400.0, empresas.get(1)));
		produtos.add(new Produto(3, "Jaqueta Jeans", 15, 150.0, empresas.get(1)));
		produtos.add(new Produto(4, "Calça Sarja", 15, 150.0, empresas.get(1)));
		produtos.add(new Produto(5, "Prato feito - Frango", 10, 25.0, empresas.get(2)));
		produtos.add(new Produto(6, "Prato feito - Carne", 10, 25.0, empresas.get(2)));
		produtos.add(new Produto(7, "Suco Natural", 30, 10.0, empresas.get(2)));
		produtos.add(new Produto(8, "Sonho", 5, 8.50, empresas.get(0)));
		produtos.add(new Produto(9, "Croissant", 7, 6.50, empresas.get(0)));
		produtos.add(new Produto(10, "Chá Gelado", 4, 5.50, empresas.get(0)));

		//cadastrando clientes
		clientes.add(new Cliente("07221134049", "Allan da Silva", "cliente", 20));
		clientes.add(new Cliente("72840700050", "Samuel da Silva", "cliente2", 24));
		clientes.add(new Cliente("00000000000", "admin", "admin", 0));

		//cadastrando usuarios
		usuarios.add(new Usuario("admin", "1234", null, null));
		usuarios.add(new Usuario("empresa", "1234", null, empresas.get(0)));
		usuarios.add(new Usuario("cliente", "1234", clientes.get(0), null));
		usuarios.add(new Usuario("cliente2", "1234", clientes.get(1), null));
		usuarios.add(new Usuario("empresa2", "1234", null, empresas.get(1)));
		usuarios.add(new Usuario("empresa3", "1234", null, empresas.get(2)));

	}

	private static void executarLoop(
			List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas, Usuario usuarioLogado
	) {
		Scanner scanner = new Scanner(System.in);

		//autenticação de usuario
		if (usuarioLogado == null)
			usuarioLogado = autenticarUsuario(scanner, usuarios);

		// Ações baseadas em tipo de usuario
		if (usuarioLogado != null) {
			if (usuarioLogado.IsEmpresa()) {
				acoesDeEmpresa(usuarioLogado, empresas, produtos, carrinho, clientes, vendas, usuarios, scanner);
			} else {
				acoesCliente(usuarioLogado, empresas, produtos, carrinho, clientes, vendas, usuarios, scanner);
			}
		}

		scanner.close();
	}

	private static void acoesDeEmpresa(
			Usuario usuarioLogado, List<Empresa> empresas, List<Produto> produtos,List<Produto> carrinho,
			List<Cliente> clientes, List<Venda> vendas, List<Usuario> usuarios, Scanner scanner
	) {
		System.out.println("Escolha uma opção:");
		System.out.println("1 - Listar vendas");
		System.out.println("2 - Ver produtos");
		System.out.println("0 - Deslogar");

		int escolha = scanner.nextInt();

		switch (escolha) {
			case 1:
				listarVendas(usuarios ,clientes, empresas, carrinho, produtos, vendas, usuarioLogado);
				break;
			case 2:
				verProdutos(usuarios ,clientes, empresas, carrinho, produtos, vendas, usuarioLogado);
				break;
			case 0:
				deslogar(usuarios ,clientes, empresas, carrinho, produtos, vendas, usuarioLogado);
				break;
			default:
				System.out.println("Escolha invalida");
				break;
		}
	}

	private static void acoesCliente(
			Usuario usuarioLogado, List<Empresa> empresas, List<Produto> produtos,List<Produto> carrinho,
			List<Cliente> clientes, List<Venda> vendas, List<Usuario> usuarios, Scanner scanner
	) {
		System.out.println("Escolha uma opção:");
		System.out.println("1 - Realizar compras");
		System.out.println("2 - Ver compreas");
		System.out.println("0 - Deslogar");

		int escolha = scanner.nextInt();

		switch (escolha) {
			case 1:
				realizarCompras(usuarios, usuarioLogado ,empresas, produtos, carrinho, clientes, vendas, scanner);
				break;
			case 2:
				verCompras(usuarios, usuarioLogado ,empresas, produtos, carrinho, clientes, vendas);
				break;
			case 0:
				deslogar(usuarios ,clientes, empresas, carrinho, produtos, vendas, usuarioLogado);
				break;
			default:
				System.out.println("Escolha invalida");
				break;
		}
	}
	private static void listarVendas(
			List<Usuario> usuarios , List<Cliente> clientes, List<Empresa> empresas, List<Produto> produtos,
			List<Produto> carrinho, List<Venda> vendas, Usuario usuarioLogado
	) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("VENDAS EFETUADAS");
		vendas.forEach(venda -> {
			if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
				System.out.println("************************************************************");
				System.out.println("Venda de código: " + venda.getCodigo() + " no CPF "
						+ venda.getCliente().getCpf() + ": ");
				venda.getItens().forEach(x -> {
					System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
				});
				System.out.println("Total Venda: R$" + venda.getValor());
				System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
				System.out.println("Total Líquido  para empresa"
						+ (venda.getValor() - venda.getComissaoSistema()));
				System.out.println("************************************************************");
			}

		});
		System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
		System.out.println("************************************************************");

		executarLoop(usuarios, clientes, empresas, produtos, carrinho, vendas, usuarioLogado);

	}

	private static void verProdutos(
			List<Usuario> usuarios , List<Cliente> clientes, List<Empresa> empresas, List<Produto> produtos,
			List<Produto> carrinho, List<Venda> vendas, Usuario usuarioLogado
	) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("MEUS PRODUTOS");
		produtos.forEach(produto -> {
			if (produto.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
				System.out.println("************************************************************");
				System.out.println("Código: " + produto.getId());
				System.out.println("Produto: " + produto.getNome());
				System.out.println("Quantidade em estoque: " + produto.getQuantidade());
				System.out.println("Valor: R$" + produto.getPreco());
				System.out.println("************************************************************");
			}

		});
		System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
		System.out.println("************************************************************");

		executarLoop(usuarios, clientes, empresas, produtos, carrinho, vendas, usuarioLogado);

	}

	private static void realizarCompras(
			List<Usuario> usuarios, Usuario usuarioLogado, List<Empresa> empresas, List<Produto> produtos,
			List<Produto> carrinho, List<Cliente> clientes, List<Venda> vendas, Scanner sc
	) {
		System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
		empresas.forEach(x -> {
			System.out.println(x.getId() + " - " + x.getNome());
		});
		Integer escolhaEmpresa = sc.nextInt();
		int escolhaProduto = -1;
		do {
			System.out.println("Escolha os seus produtos: ");
			produtos.forEach(x -> {
				if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
					System.out.println(x.getId() + " - " + x.getNome());
				}
			});
			System.out.println("0 - Finalizar compra");
			escolhaProduto = sc.nextInt();
			for (Produto produtoSearch : produtos) {
				if (produtoSearch.getId().equals(escolhaProduto) && produtoSearch.getEmpresa().getId().equals(escolhaEmpresa))
					carrinho.add(produtoSearch);
			}
		} while (escolhaProduto != 0);
		System.out.println("************************************************************");
		System.out.println("Resumo da compra: ");
		carrinho.forEach(x -> {
			if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
				System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
			}
		});
		Empresa empresaEscolhida = empresas.stream().filter(x -> x.getId().equals(escolhaEmpresa))
				.toList().get(0);
		Cliente clienteLogado = clientes.stream()
				.filter(x -> x.getUsername().equals(usuarioLogado.getUsername()))
				.toList().get(0);
		Venda venda = criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
		System.out.println("Total: R$" + venda.getValor());
		System.out.println("************************************************************");
		carrinho.clear();

		executarLoop(usuarios, clientes, empresas, produtos, carrinho, vendas, usuarioLogado);
	}

	private static void verCompras(
			List<Usuario> usuarios, Usuario usuarioLogado, List<Empresa> empresas, List<Produto> produtos,
			List<Produto> carrinho, List<Cliente> clientes, List<Venda> vendas
	) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("COMPRAS EFETUADAS");
		vendas.forEach(venda -> {
			if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
				System.out.println("************************************************************");
				System.out.println("Compra de código: " + venda.getCodigo() + " na empresa "
						+ venda.getEmpresa().getNome() + ": ");
				venda.getItens().forEach(x -> {
					System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
				});
				System.out.println("Total: R$" + venda.getValor());
				System.out.println("************************************************************");
			}

		});

		executarLoop(usuarios, clientes, empresas, produtos, carrinho, vendas, usuarioLogado);
	}
	private static void deslogar(
			List<Usuario> usuarios , List<Cliente> clientes, List<Empresa> empresas, List<Produto> carrinhos,
			List<Produto> produtos, List<Venda> vendas, Usuario usuarioLogado
	) {
		System.out.println("Deslogado com sucesso");
		usuarioLogado = null;
		executarLoop(usuarios, clientes, empresas, produtos, carrinhos, vendas, usuarioLogado);
		// Terminate the program
	}

	public static Venda criarVenda(List<Produto> carrinho, Empresa empresa, Cliente cliente, List<Venda> vendas) {
		Double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
		Double comissaoSistema = total * empresa.getTaxa();
		int idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCodigo() + 1;
		Venda venda = new Venda(idVenda, carrinho.stream().toList(), total, comissaoSistema, empresa, cliente);
		empresa.setSaldo(empresa.getSaldo() + total - comissaoSistema);
		vendas.add(venda);
		return venda;
	}
}
