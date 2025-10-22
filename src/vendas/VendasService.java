package vendas;

import java.util.HashMap;
import java.util.Map;

public class VendasService implements IVendasService{
    private final Map<Integer, Venda> vendas;

    public VendasService(Map<Integer, Venda> vendas) {
        this.vendas = new HashMap<>();
    }

    @Override
    public void registrarVenda(Venda venda) {
        this.vendas.put(venda.getId(), venda);
    }
}
