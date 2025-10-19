package vendas;

import vendas.clientes.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venda {
    private final int id;
    private final LocalDateTime dataHora;
    private Cliente cliente;
    private final ArrayList<ItensVenda> itens;
    private double desconto;

    public Venda(int id, LocalDateTime dataHora, ArrayList<ItensVenda> itens, Cliente cliente, double desconto) {
        this.id = id;
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
        this.cliente = cliente;
        this.desconto = 0.0;
    }

    public void adicionarItem(ItensVenda item) {
        this.itens.add(item);
    }

    // valorTotal
    public double getValorTotal() {
        double subtotal = 0.0;
        for (ItensVenda item : itens) {
            subtotal += item.getSubTotal();
        }
        return subtotal - this.desconto;
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
