package com.guilherme.SpringBoot_Marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilherme.SpringBoot_Marketplace.domain.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
/* Quando se trabalha com Herança de produtos os resultados deles são organizados de 2 maneiras, uma em tabela unica(SINGLE.TABLE) outra
 * em tabela unitaria, a tabela unica ganho mais perfomace dependendo do service a ser feito, porém quando se tem diversas subclasses
 * se utiliza tabelas unicas para cada caso de compra(JOINED)*/
public abstract class  Pagamento implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        private Integer id;
        private Integer estado;

        @JsonIgnore// Pagamento não pode ver pedidos
        @OneToOne
        @JoinColumn(name = "pedido_id")
        @MapsId
        private Pedido pedido;

        public Pagamento() {
        }

        public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
            super();
            this.id = id;
            this.estado = (estado == null) ? null : estado.getCod();
            this.pedido = pedido;
        }
        public Integer getId() {
            return id;
        }

        public EstadoPagamento getEstado() {
            return EstadoPagamento.toEnum(estado);
        }

        public Pedido getPedido() {
            return pedido;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setEstado(EstadoPagamento estado) {
            this.estado = estado.getCod();
        }

        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
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
            Pagamento other = (Pagamento) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }


    }

