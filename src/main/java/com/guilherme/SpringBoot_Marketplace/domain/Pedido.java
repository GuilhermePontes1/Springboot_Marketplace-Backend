package com.guilherme.SpringBoot_Marketplace.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
    public class Pedido implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        private Date instante;

        // pedido pode ver cliente
        @OneToOne(cascade=CascadeType.ALL, mappedBy = "pedido")
       /*(cascade=CascadeType.ALL) É Necessário para não ocorrer erro de entidade intransiente quando for salva,
	sempre que a entidade precisar ter o mesmo id da outra classe(nesse casos pedido) e por estar em 1 para 1
	ou seja peculiaridade do JPA */
        private Pagamento pagamento;


        @ManyToOne
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;

        @ManyToOne
        @JoinColumn(name = "endereco_de_entrega_id")
        private Endereco enderecoDeEntrega;

        @OneToMany(mappedBy = "id.pedido")
        private Set<ItemPedido> itens = new HashSet<>();

        public Pedido () {

        }

        public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
            super();
            this.id = id;
            this.instante = instante;
            this.cliente = cliente;
            this.enderecoDeEntrega = enderecoDeEntrega;
        }
    public double getValorTotal() {
            double soma = 0.0;
            for (ItemPedido ip : itens) {
                soma = soma + ip.getSubTotal();
            }
            return  soma;
        }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public Integer getId() {
            return id;
        }

        public Date getInstante() {
            return instante;
        }

        public Pagamento getPagamento() {
            return pagamento;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public Endereco getEnderecoDeEntrega() {
            return enderecoDeEntrega;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setInstante(Date instante) {
            this.instante = instante;
        }

        public void setPagamento(Pagamento pagamento) {
            this.pagamento = pagamento;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
            this.enderecoDeEntrega = enderecoDeEntrega;
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
            Pedido other = (Pedido) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }


    }


