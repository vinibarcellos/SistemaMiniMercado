package vendas.produtos;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService implements IProdutoService {

    private final List<Produto> repositorioDeProdutos;

    public ProdutoService() {
        this.repositorioDeProdutos = new ArrayList<>();
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        Produto produtoExistente = buscarProdutoPorId(produto.getId());

        if (produtoExistente == null) {
            this.repositorioDeProdutos.add(produto);
            System.out.println("Produto cadastrado: " + produto.getNome());
        } else {
            System.out.println("Erro: Produto com ID " + produto.getId() + " já existe.");
        }
    }

    @Override
    public Produto buscarProdutoPorId(int id) {
        for (Produto p : this.repositorioDeProdutos) {

            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Produto> listarTodosProdutos() {
        return new ArrayList<>(this.repositorioDeProdutos);
    }

    @Override
    public void adicionarEstoque(int id, int quantidade) {
        Produto produto = buscarProdutoPorId(id);

        if (produto != null) {
            produto.adicionarEstoque(quantidade);
            System.out.println("Estoque atualizado: " + produto.getNome() + " | Qtd: " + produto.getQuantidadeEmEstoque());
        } else {
            System.out.println("Erro: Produto com ID " + id + " não encontrado para adicionar estoque.");
        }
    }

    @Override
    public boolean darBaixaEstoque(int id, int quantidade) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            boolean sucesso = produto.removerEstoque(quantidade);

            if (sucesso) {
                System.out.println("Baixa no estoque: " + produto.getNome() + " | Restante: " + produto.getQuantidadeEmEstoque());
                return true;
            } else {
                System.out.println("Erro: Estoque insuficiente para " + produto.getNome());
                return false;
            }
        } else {
            System.out.println("Erro: Produto com ID " + id + " não encontrado para dar baixa.");
            return false;
        }
    }
}