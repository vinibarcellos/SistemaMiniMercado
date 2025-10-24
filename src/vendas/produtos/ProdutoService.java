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
        } else {
            System.out.println("[SERVIÇO PRODUTO] Erro: Produto com ID " + produto.getId() + " já existe.");
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
        } else {
            System.out.println("[SERVIÇO PRODUTO] Erro: Produto com ID " + id + " não encontrado para adicionar estoque.");
        }
    }

    @Override
    public boolean darBaixaEstoque(int id, int quantidade) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            boolean sucesso = produto.removerEstoque(quantidade);

            if (sucesso) {

                return true;
            } else {
                System.out.println("[SERVIÇO PRODUTO] Erro: Estoque insuficiente para " + produto.getNome());
                return false;
            }
        } else {
            System.out.println("[SERVIÇO PRODUTO] Erro: Produto com ID " + id + " não encontrado para dar baixa.");
            return false;
        }
    }
}