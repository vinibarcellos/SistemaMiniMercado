package vendas;
import vendas.clientes.Cliente;
import vendas.clientes.Categoria;

    // Calcula o preco final junto com os descontos de fidelidade
public class DescontoFidelidade {

    public static double calcularPrecoFinal(Cliente cliente, double subtotal) {
        if (cliente == null || cliente.getCategoria() == null) {
            return subtotal;
        }

        Categoria categoriaDoCliente = cliente.getCategoria();
        double percentualDesconto = categoriaDoCliente.getDescontoPercentual();
        double valorDoDesconto = subtotal * percentualDesconto;
        return subtotal - valorDoDesconto;
    }
}