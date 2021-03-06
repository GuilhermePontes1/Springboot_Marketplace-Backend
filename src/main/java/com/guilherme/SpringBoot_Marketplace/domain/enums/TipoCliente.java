package com.guilherme.SpringBoot_Marketplace.domain.enums;

public enum TipoCliente {
	PESSSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {    /* Feito para se ter controle total do código atribuido ao controle
													 feito por enumeração para evitar problemas futuros de implementação
													 de novos enums.
													 Basciamente ele varre o tipo enum em busca do código informado 
													 para fazer a comparação se o identificador é aquele mesmo */
		if (cod == null) {
			return null;
		}
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("id invalido"+ cod); 
	}
}
