package vendas.produtos;

public class Produto {
    private String id;
    private String nome;
    private double precoUnitario;
    private int quantidadeEmEstoque;

    public Produto(String id, String nome, double precoUnitario, int quantidadeEmEstoque) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
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

    //remover item de estoque
    public boolean removerEstoque(int quantidade){
        if (quantidade > 0 && this.quantidadeEmEstoque >= quantidade){
            this.quantidadeEmEstoque -= quantidade;
            return true;
        }
        return false;
    }
}