package br.com.improving.carrinho;

import java.math.BigDecimal;

public class Main {
	public static void main(String[] args) {
        Produto p1 = new Produto(1L, "Camisa Polo");
        Produto p2 = new Produto(2L, "Calça Jeans");
        Produto p3 = new Produto(3L, "Tênis");
        
        Item i1 = new Item(p1, BigDecimal.valueOf(59.90), 2);
        Item i2 = new Item(p2, BigDecimal.valueOf(99.90), 1);
        Item i3 = new Item(p3, BigDecimal.valueOf(149.90), 1);
        
        CarrinhoComprasFactory factory = new CarrinhoComprasFactory();
        
        CarrinhoCompras carrinho1 = factory.criar("1234");
        carrinho1.adicionarItem(i1.getProduto(), i1.getValorUnitario(), i1.getQuantidade());
        carrinho1.adicionarItem(i2.getProduto(), i2.getValorUnitario(), i2.getQuantidade());
        carrinho1.adicionarItem(i3.getProduto(), i3.getValorUnitario(), i3.getQuantidade());
        
        CarrinhoCompras carrinho2 = factory.criar("5678");
        carrinho2.adicionarItem(i1.getProduto(), i1.getValorUnitario(), i1.getQuantidade());
        carrinho2.adicionarItem(i2.getProduto(), i2.getValorUnitario(), i2.getQuantidade());
        
        factory.adicionarCarrinho(carrinho1);
        factory.adicionarCarrinho(carrinho2);
        System.out.println("Valor Ticket Médio: " + factory.getValorTicketMedio());
    }
}
