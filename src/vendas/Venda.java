package vendas;

import vendas.clientes.Cliente;
import java.time.LocalDateTime;
import java.util.ArrayList;

    // Criamos a classe Venda

public class Venda {

    private static int contadorId = 0;

    private final int id;
    private final LocalDateTime dataHora;
    private Cliente cliente;
    private final ArrayList<ItensVenda> itens;
    private double desconto;

    public Venda(Cliente cliente) {
        this.id = ++contadorId;
        this.cliente = cliente;


        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
        this.desconto = 0.0;
    }

    public void adicionarItem(ItensVenda item) {
        this.itens.add(item);
    }

    public double getSubTotal() {
        double subtotal = 0.0;
        for (ItensVenda item : itens) {
            subtotal += item.getSubTotal();
        }
        return subtotal;
    }

    public double getValorTotal() {
        return getSubTotal() - this.desconto;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItensVenda> getItens() {
        return itens;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
}