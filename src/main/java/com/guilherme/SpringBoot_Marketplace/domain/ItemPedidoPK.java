package com.guilherme.SpringBoot_Marketplace.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // -> indica subtipo
public class ItemPedidoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    // CHAVE PRIMARIA DO ITEM PEDIDDO

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedidoPK)) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return getPedido().equals(that.getPedido()) && getProduto().equals(that.getProduto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPedido(), getProduto());
    }
}
