package br.com.improving.carrinho;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.*;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {
	private final Map<Produto, Item> itens;
    private BigDecimal valorTotal;
    
    public CarrinhoCompras() {
        this.itens = new LinkedHashMap<>();
        this.valorTotal = BigDecimal.ZERO;
    }

    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
    	if (produto == null || valorUnitario == null || quantidade <= 0) {
            throw new IllegalArgumentException("Produto, valor unitário e quantidade devem ser informados.");
        }

        Item item = itens.get(produto);
        if (item == null) {
            item = new Item(produto, valorUnitario, quantidade);
            itens.put(produto, item);
        } else {
            item.setQuantidade(item.getQuantidade() + quantidade);

            if (valorUnitario.compareTo(item.getValorUnitario()) != 0) {
                item.setValorUnitario(valorUnitario);
            }
        }

        valorTotal = valorTotal.add(valorUnitario.multiply(new BigDecimal(quantidade)));
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
    	 if (produto == null) {
             throw new IllegalArgumentException("Produto deve ser informado.");
         }

         Item item = itens.get(produto);
         if (item != null) {
             valorTotal = valorTotal.subtract(item.getValorTotal());
             itens.remove(produto);
             return true;
         }

         return false;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
    	if (posicaoItem < 0 || posicaoItem >= itens.size()) {
            return false;
        }
        Item itemRemovido = ((List<Item>) itens).remove(posicaoItem);
        if (itemRemovido == null) {
            return false;
        }
        return true;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
    	return valorTotal;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
    	System.out.println("Items: " +  itens);
    	 return itens.values();
    }
}