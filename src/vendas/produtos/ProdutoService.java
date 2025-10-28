package vendas.produtos;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void cadastrarProduto(Produto produto) {
        // Testes de validação (Dados incompletos / inválidos)
        if (produto == null) {
            throw new IllegalArgumentException("Não é possível cadastrar um produto nulo.");
        }
        // Validações de dados já são feitas no construtor do Produto.
        // Apenas checamos a duplicidade de ID.

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
            // MUDANÇA AQUI: Lança exceção (Teste: Produto Inexistente)
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
        // buscarProdutoPorId já lança exceção se não encontrar (Produto Inexistente)
        Produto produto = buscarProdutoPorId(id);

        // adicionarEstoque no Produto já lança exceção se quantidade for <= 0 (Estoque negativo/inválido)
        produto.adicionarEstoque(quantidade);
    }

    @Override
    public void darBaixaEstoque(int id, int quantidade) {
        // buscarProdutoPorId já lança exceção se não encontrar (Produto Inexistente)
        Produto produto = buscarProdutoPorId(id);

        // removerEstoque no Produto já lança exceção para:
        // 1. Quantidade zerada ou negativa
        // 2. Quantidade superior ao estoque
        produto.removerEstoque(quantidade);
    }
}