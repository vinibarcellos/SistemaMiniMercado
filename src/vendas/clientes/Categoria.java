package vendas.clientes;

    // Criamos o enum Categoria para classificar os clientes e seus respectivos descontos

public enum Categoria {
    CLIENTECOMUM(0.0),
    CLIENTEOURO(0.10),
    CLIENTEPLATINA(0.20);

    private final double descontoPercentual;

    Categoria(double descontoPercentual) {
        this.descontoPercentual = descontoPercentual;
    }

    public double getDescontoPercentual() {
        return descontoPercentual;
    }
}
