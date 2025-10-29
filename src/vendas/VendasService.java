package vendas;

import java.util.HashMap;
import java.util.Map;

    // A classe implementa a interface e sobreescreve seus metodos
public class VendasService implements IVendasService {

    private final Map<Integer, Venda> vendas;
    public VendasService() {
        this.vendas = new HashMap<>();
    }

    @Override
    public void registrarVenda(Venda venda) {
        this.vendas.put(venda.getId(), venda);
    }

}