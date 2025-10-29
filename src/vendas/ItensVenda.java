package vendas;

    // Iniciamos as variaveis, construtor e getter/setters
public class ItensVenda {
    private final int id;
    private String nome;
    private int quantidade;
    private double preco;

    public ItensVenda(int id, String nome, int quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Faz o subtotal pela quantidade de itens vezes o preco
    public double getSubTotal() {
        return quantidade * preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
