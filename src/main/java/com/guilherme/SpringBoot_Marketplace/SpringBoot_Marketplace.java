package com.guilherme.SpringBoot_Marketplace;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilherme.SpringBoot_Marketplace.domain.Categoria;
import com.guilherme.SpringBoot_Marketplace.domain.Cidade;
import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import com.guilherme.SpringBoot_Marketplace.domain.Endereco;
import com.guilherme.SpringBoot_Marketplace.domain.Estado;
import com.guilherme.SpringBoot_Marketplace.domain.Produto;
import com.guilherme.SpringBoot_Marketplace.domain.enums.TipoCliente;
import com.guilherme.SpringBoot_Marketplace.repositories.CategoriaRepository;
import com.guilherme.SpringBoot_Marketplace.repositories.CidadeRepository;
import com.guilherme.SpringBoot_Marketplace.repositories.ClienteRepository;
import com.guilherme.SpringBoot_Marketplace.repositories.EnderecoRepository;
import com.guilherme.SpringBoot_Marketplace.repositories.EstadoRepository;
import com.guilherme.SpringBoot_Marketplace.repositories.ProdutoRepository;

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

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot_Marketplace.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Aqui realizamos as associações das categorias com os produtos de mão dupla,
		// um reconhecendo o outro
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 100.00);
		Produto p3 = new Produto(null, "Mouse", 800.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
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
		

	}

}
