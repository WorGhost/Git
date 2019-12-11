package mx.com.gm.sga.servicio;

import dominio.Cliente;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClienteService {
      public List<Cliente> listarClientes();
    
    public Cliente encontrarClientePorId(Cliente cliente);
    
    public Cliente encontrarClientePorEmail(Cliente cliente);
    
    public void registrarCliente(Cliente cliente);
    
    public void modificarCliente(Cliente cliente);
    
    public void eliminarCliente(Cliente cliente);
    
}
