package vendas.produtos;
import java.util.List;

public interface IProdutoService {

    void cadastrarProduto(Produto produto);

    Produto buscarProdutoPorId(int id);

    List<Produto> listarTodosProdutos();

    void adicionarEstoque(int id, int quantidade);

    void darBaixaEstoque(int id, int quantidade); // <-- MUDANÇA AQUI
}