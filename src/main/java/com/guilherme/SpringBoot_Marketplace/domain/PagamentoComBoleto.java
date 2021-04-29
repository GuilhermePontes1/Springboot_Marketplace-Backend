package com.guilherme.SpringBoot_Marketplace.domain;

import com.guilherme.SpringBoot_Marketplace.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	
	public PagamentoComBoleto() {
	
	}
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);

		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataPagamento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
