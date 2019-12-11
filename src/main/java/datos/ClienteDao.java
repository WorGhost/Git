package datos;

import dominio.Cliente;
import java.util.List;


public interface ClienteDao {
    
    public List<Cliente> findAllClientes();
    
    public Cliente findClienteById(Cliente cliente);
    
    public Cliente findClienteByEmail(Cliente cliente);
    
    public void insertCliente(Cliente cliente);
    
    public void updateCliente(Cliente cliente);
    
    public void deleteCliente(Cliente cliente);
    
    public List<Cliente> listar();
    
    
}
