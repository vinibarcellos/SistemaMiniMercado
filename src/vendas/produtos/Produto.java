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

    // Getters para que outras classes leiam o valor do atributo privado
    // Setters para que outras classes alterem o valor do atributo privado
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
        if (quantidadeEmEstoque >= 0); //impede que coloque um valor negativo em estoque
        return this.quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque >= 0) {
            this.quantidadeEmEstoque = quantidadeEmEstoque;
        }
    }
    //adicionar item em estoque
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
