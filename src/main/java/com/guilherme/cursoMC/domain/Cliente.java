package com.guilherme.cursoMC.domain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guilherme.cursoMC.domain.enums.TipoCliente;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name ="telefone")
	private Set<String> telefones = new HashSet<>(); /*
														 * optei por criar uma lista de conjunto, já que telefone é
														 * totalmente dependente de Cliente, logo já evitamos um
														 * problema que seria a repetição já que estou uma lista de
														 * conjuntos
														 */

	public Cliente() {

	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {              
		super();
		this.id = id;
		this.nome = nome;                        
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();	// Macete feito para transformar o tipo em NUMEROS no caso Integer, logo pro
									// mundo externoo que fica é o nome, já no interno fica seu ID por isso ta sendo declarado como
									// I	nteger!
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
		return TipoCliente.toEnum(tipo); // Macete feito para transformar o tipo em NUMEROS no caso Integer, logo pro
											// mundo externoo que fica é o nome, já no interno fica seu ID!
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
		this.tipo = tipo.getCod(); // Macete feito para transformar o tipo em NUMEROS no caso Integer, logo pro
									// mundo externo
									// o que fica é o nome, já no interno fica seu ID!
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
