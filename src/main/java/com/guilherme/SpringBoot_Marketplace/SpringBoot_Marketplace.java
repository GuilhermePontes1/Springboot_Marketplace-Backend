package com.guilherme.SpringBoot_Marketplace;

import com.guilherme.SpringBoot_Marketplace.domain.*;
import com.guilherme.SpringBoot_Marketplace.domain.enums.EstadoPagamento;
import com.guilherme.SpringBoot_Marketplace.domain.enums.TipoCliente;
import com.guilherme.SpringBoot_Marketplace.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringBoot_Marketplace implements CommandLineRunner {

	@Autowired // Instancia automáticamente assim que o código é executado
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
		SpringApplication.run(SpringBoot_Marketplace.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Aqui realizamos as associações das categorias com os produtos de mão dupla,
		// um reconhecendo o outro
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Higiene");
		Categoria cat4 = new Categoria(null, "Jardinagem");
		Categoria cat5 = new Categoria(null, "Geral");
		Categoria cat6 = new Categoria(null, "Alimentos");
		Categoria cat7 = new Categoria(null, "PET");



		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 100.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		
		Estado est1 = new Estado(null, "Bahia");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Salvador", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Marlene Pontes", "marlene@gmail.com", "724.487.335-20",
				TipoCliente.PESSSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("98111444", "88895414"));

		Endereco e1 = new Endereco(null, "Rua Jacaradá", "200", "apt 404", "Alphavile", "78559-217", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Paulista", "1800", "apt 8660", "Jardins", "57073-554", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
																// (MUITO IMPORTANTE), TEM UMA ORDEM A SER SEGUIDA E
																// RESPEITADA NA HORA DE SALVAR AS LISTAS!
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2021 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2021 19:35"), cli1, e2);
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2,sdf.parse("22/10/2021 00:00"),null);
		ped2.setPagamento(pagto2);

		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));

		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,100.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,80.00);

		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}



}
