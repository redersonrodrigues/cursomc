package com.rederson.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rederson.cursomc.domain.Categoria;
import com.rederson.cursomc.domain.Cidade;
import com.rederson.cursomc.domain.Cliente;
import com.rederson.cursomc.domain.Endereco;
import com.rederson.cursomc.domain.Estado;
import com.rederson.cursomc.domain.ItemPedido;
import com.rederson.cursomc.domain.Pagamento;
import com.rederson.cursomc.domain.PagamentoComBoleto;
import com.rederson.cursomc.domain.PagamentoComCartao;
import com.rederson.cursomc.domain.Pedido;
import com.rederson.cursomc.domain.Produto;
import com.rederson.cursomc.domain.enums.EstadoPagamento;
import com.rederson.cursomc.domain.enums.TipoCliente;
import com.rederson.cursomc.repositories.CategoriaRepository;
import com.rederson.cursomc.repositories.CidadeRepository;
import com.rederson.cursomc.repositories.ClienteRepository;
import com.rederson.cursomc.repositories.EnderecoRepository;
import com.rederson.cursomc.repositories.EstadoRepository;
import com.rederson.cursomc.repositories.ItemPedidoRepository;
import com.rederson.cursomc.repositories.PagamentoRepository;
import com.rederson.cursomc.repositories.PedidoRepository;
import com.rederson.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// instanciando dados
		// categorias
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		// produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		// estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		// cidades
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		// clientes
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		// endereços
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "28220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		// pedido
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		// pagamento
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pgto2);

		// itens Pedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 2, 800.00);

		// instanciando associações
		// Produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		// Categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		// Estados
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		// Clientes
		// > telefones
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		// > enderecos
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		// pedidos
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		// itens pedidos
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));



		// Persistindo dados
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
