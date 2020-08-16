package com.lwinfo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lwinfo.cursomc.domain.Categoria;
import com.lwinfo.cursomc.domain.Cidade;
import com.lwinfo.cursomc.domain.Cliente;
import com.lwinfo.cursomc.domain.Endereco;
import com.lwinfo.cursomc.domain.Estado;
import com.lwinfo.cursomc.domain.Pagamento;
import com.lwinfo.cursomc.domain.PagamentoComBoleto;
import com.lwinfo.cursomc.domain.PagamentoComCartao;
import com.lwinfo.cursomc.domain.Pedido;
import com.lwinfo.cursomc.domain.Produto;
import com.lwinfo.cursomc.domain.enums.EstadoPagamento;
import com.lwinfo.cursomc.domain.enums.TipoCliente;
import com.lwinfo.cursomc.repositories.CategoriaRepository;
import com.lwinfo.cursomc.repositories.CidadeRepository;
import com.lwinfo.cursomc.repositories.ClienteRepository;
import com.lwinfo.cursomc.repositories.EnderecoRepository;
import com.lwinfo.cursomc.repositories.EstadoRepository;
import com.lwinfo.cursomc.repositories.PagamentoRepository;
import com.lwinfo.cursomc.repositories.PedidoRepository;
import com.lwinfo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Rio de Janeiro", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Leandro Wakim", "leandrowakim@gmail.com", "98754262704", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("21967241571","21971700093"));
		
		Endereco e1 = new Endereco(null, "Rua Lopo Diniz", "155", null, "Vicente de Carvalho", "21371-080", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Madame pommery", "1260", "Apto 22", "Vila Uupes", "08615-090", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
				
	}	
}