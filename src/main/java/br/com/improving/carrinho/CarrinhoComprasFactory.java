package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {
	private Map<String, CarrinhoCompras> carrinhos;
    private BigDecimal valorTotalCarrinhos;
    private int quantidadeCarrinhos;

	public CarrinhoComprasFactory() {
		carrinhos = new HashMap<>();
        valorTotalCarrinhos = BigDecimal.ZERO;
        quantidadeCarrinhos = 0;
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
    	if (carrinhos.containsKey(identificacaoCliente)) {
            return carrinhos.get(identificacaoCliente);
        } else {
            CarrinhoCompras novoCarrinho = new CarrinhoCompras();
            carrinhos.put(identificacaoCliente, novoCarrinho);
            return novoCarrinho;
        }
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
    	BigDecimal ticketMedio = BigDecimal.ZERO;
        if (quantidadeCarrinhos != 0) {
            ticketMedio = valorTotalCarrinhos.divide(BigDecimal.valueOf(quantidadeCarrinhos), 2, RoundingMode.HALF_UP);
        }
        return ticketMedio;
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
    	if (carrinhos.containsKey(identificacaoCliente)) {
            return this.removerCarrinho(identificacaoCliente);
        } else {
            return false;
        }
    }
    
    /**
     * Adiciona um carrinho de compras ao cálculo do ticket médio.
     *
     * @param carrinho
     */
    public void adicionarCarrinho(CarrinhoCompras carrinho) {
        valorTotalCarrinhos = valorTotalCarrinhos.add(carrinho.getValorTotal());
        quantidadeCarrinhos++;
    }
    
    public boolean removerCarrinho(String identificacaoCliente) {
        CarrinhoCompras carrinhoRemovido = carrinhos.remove(identificacaoCliente);
        if (carrinhoRemovido != null) {
            BigDecimal valorCarrinhoRemovido = carrinhoRemovido.getValorTotal();
            valorTotalCarrinhos = valorTotalCarrinhos.subtract(valorCarrinhoRemovido);
            quantidadeCarrinhos--;
            return true;
        }
        return false;
    }

}
