package vendas.produtos;
import java.util.ArrayList;
import java.util.List;

    // Implementa a interface
public class ProdutoService implements IProdutoService {

    private final List<Produto> repositorioDeProdutos;

    public ProdutoService() {
        this.repositorioDeProdutos = new ArrayList<>();
    }

    // Método privado para buscar sem lançar exceção (usado apenas internamente)
    private Produto buscarProdutoPorId_interno(int id) {
        for (Produto p : this.repositorioDeProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Tratamento de excecoes

    @Override
    public void cadastrarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Não é possível cadastrar um produto nulo.");
        }
        Produto produtoExistente = buscarProdutoPorId_interno(produto.getId());
        if (produtoExistente != null) {
            throw new RuntimeException("[SERVIÇO PRODUTO] Erro: Produto com ID " + produto.getId() + " já existe.");
        }

        this.repositorioDeProdutos.add(produto);
    }

    @Override
    public Produto buscarProdutoPorId(int id) {
        Produto p = buscarProdutoPorId_interno(id);
        if (p == null) {
            throw new RuntimeException("[SERVIÇO PRODUTO] Erro: Produto com ID " + id + " não encontrado.");
        }
        return p;
    }

    @Override
    public List<Produto> listarTodosProdutos() {
        return new ArrayList<>(this.repositorioDeProdutos);
    }

    @Override
    public void adicionarEstoque(int id, int quantidade) {
        Produto produto = buscarProdutoPorId(id);
        produto.adicionarEstoque(quantidade);
    }

    @Override
    public void darBaixaEstoque(int id, int quantidade) {
        Produto produto = buscarProdutoPorId(id);
        produto.removerEstoque(quantidade);
    }
}