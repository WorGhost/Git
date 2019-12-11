package mx.com.gm.sga.servicio;

import dominio.Cliente;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClienteServiceRemote {
    
    public List<Cliente> listarCliente();
    
    public Cliente encontrarClientePorId(Cliente cliente);
    
    public Cliente encontrarClientePorEmail(Cliente cliente);
    
    public void registrarCliente(Cliente cliente);
    
    public void modificarCliente(Cliente cliente);
    
    public void eliminarCliente(Cliente cliente);
    
}
