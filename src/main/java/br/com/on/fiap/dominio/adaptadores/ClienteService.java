package br.com.on.fiap.dominio.adaptadores;

import br.com.on.fiap.dominio.Cliente;
import br.com.on.fiap.dominio.portas.interfaces.IClienteService;

public class ClienteService implements IClienteService {
    /**
     * @param Id
     * @return
     */
    @Override
    public Cliente findById(Long Id) {
        return null;
    }

    /**
     * @param cpf
     * @return
     */
    @Override
    public Cliente findByCpf(String cpf) {
        return null;
    }
}
