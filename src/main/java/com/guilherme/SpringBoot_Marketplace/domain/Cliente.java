package com.guilherme.SpringBoot_Marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilherme.SpringBoot_Marketplace.domain.enums.Perfil;
import com.guilherme.SpringBoot_Marketplace.domain.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;

	private String cpfOuCnpj;
	private Integer tipo;

	@JsonIgnore
	private String senha;

	@OneToMany(mappedBy = "cliente" ,cascade = CascadeType.ALL	)// ex: se eu apagar os clientes apago junto seus endereços, isso se chama efeito "Cascata")
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER) //Quero garantir que sempre que buscar um cliente, automaticamente busque os perfis também!
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();


	@ElementCollection
	@CollectionTable(name ="TELEFONE")
	private Set<String> telefones = new HashSet<>(); /*
														 * optei por criar uma lista de conjunto, já que telefone é
														 * totalmente dependente de Cliente, logo já evitamos um
														 * problema que seria a repetição já que estou uma lista de
														 * conjuntos
														 */
	@JsonIgnore// pedidos de cliente não vão ser serializados
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();


	public Cliente() {

		addPerfil(Perfil.CLIENTE);

	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
		super();
		this.senha = senha;
		this.id = id;
		this.nome = nome;                        
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		addPerfil(Perfil.CLIENTE);
		this.tipo = (tipo == null) ? null : tipo.getCod();	/* Macete feito para transformar o tipo em NUMEROS, no caso Integer, logo pro
															  mundo externo oque fica é o nome, já no interno fica seu ID, por isso esta sendo declarado como
															  Integer!  */
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); // retorna os perfis do cliente convertidos para String já que são inicialmente Integers.
	}


	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());  // como o própio nome já diz, adiciona um perfil
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo); /* Macete feito para transformar o tipo em NUMEROS no caso Integer, logo pro
											 mundo externoo que fica é o nome, já no interno fica seu ID! */
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod(); /* Macete feito para transformar o tipo em NUMEROS no caso Integer, logo pro
									 mundo externo o que fica é o nome, já no interno fica seu ID! */

	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
