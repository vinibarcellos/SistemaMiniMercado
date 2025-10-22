package vendas.produtos;
import java.util.List;

public interface IProdutoService {
    void cadastrarProduto(Produto produto);

    Produto buscarProdutoPorId(String id);
    //listar os produtos disponiveis
    List<Produto> listarTodosProdutos();

    // adicionar produtos no estoque
    void adicionarEstoque(String id, int quantidade);
    boolean darBaixaEstoque(String id, int quantidade);
}
