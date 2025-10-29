package vendas.produtos;

public class Produto {
    private final int id;
    private String nome;
    private double precoUnitario;
    private int quantidadeEmEstoque;

    public Produto(int id, String nome, double precoUnitario, int quantidadeEmEstoque) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio.");
        }
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo.");
        }
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Estoque inicial não pode ser negativo.");
        }

        this.id = id;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public int getId() {
        return id;
    }


    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo.");
        }
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEmEstoque() {
        return this.quantidadeEmEstoque;
    }

    // Tratamento de excecoes
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void adicionarEstoque(int quantidade){
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade a adicionar ao estoque deve ser positiva.");
        }
        this.quantidadeEmEstoque += quantidade;
    }

    public void removerEstoque(int quantidade){
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a remover do estoque deve ser positiva.");
        }
        if (this.quantidadeEmEstoque < quantidade){
            throw new RuntimeException("Estoque insuficiente. Tentativa de remover " + quantidade + ", mas há apenas " + this.quantidadeEmEstoque + ".");
        }
        this.quantidadeEmEstoque -= quantidade;
    }
}