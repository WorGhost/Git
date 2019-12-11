package mx.com.gm.sga.servicio;

import datos.ClienteDao;
import dominio.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


public class ClienteServiceImpl implements ClienteServiceRemote, ClienteService{

    @Inject
    private ClienteDao clienteDao;
    
    @Override
    public List<Cliente> listarClientes() {
       return clienteDao.findAllClientes();
    }

    @Override
    public Cliente encontrarClientePorId(Cliente cliente) {
        return clienteDao.findClienteById(cliente);
    }

    @Override
    public Cliente encontrarClientePorEmail(Cliente cliente) {
        return clienteDao.findClienteByEmail(cliente);
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        clienteDao.insertCliente(cliente);
    }

    @Override
    public void modificarCliente(Cliente cliente) {
        clienteDao.updateCliente(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteDao.deleteCliente(cliente);
    }

    @Override
    public List<Cliente> listarCliente() {
       return clienteDao.listar();
    }
}

