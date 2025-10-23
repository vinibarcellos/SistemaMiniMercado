package vendas.produtos;

public class Produto {
    private final int id;
    private String nome;
    private double precoUnitario;
    private int quantidadeEmEstoque;

    public Produto(int id, String nome, double precoUnitario, int quantidadeEmEstoque) {
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
        this.nome = nome;
    }

    public int getId() {
        return id;
    }


    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario >= 0) {
            this.precoUnitario = precoUnitario;
        }
    }

    public int getQuantidadeEmEstoque() {
        return this.quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque >= 0) {
            this.quantidadeEmEstoque = quantidadeEmEstoque;
        }
    }

    public void adicionarEstoque(int quantidade){
        if (quantidade > 0){
            this.quantidadeEmEstoque += quantidade;
        }
    }

    public boolean removerEstoque(int quantidade){
        if (quantidade > 0 && this.quantidadeEmEstoque >= quantidade){
            this.quantidadeEmEstoque -= quantidade;
            return true;
        }
        return false;
    }
}