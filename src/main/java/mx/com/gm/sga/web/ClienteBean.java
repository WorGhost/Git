package mx.com.gm.sga.web;

import datos.ClienteDaoImpl;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.gm.sga.servicio.ClienteService;
import org.primefaces.event.RowEditEvent;

@Named("clienteBean")
@RequestScoped
@WebServlet("/ServletControlador")
public class ClienteBean {
    
    @Inject
    private ClienteService clienteService;
    
    private Cliente clienteSeleccionada;
    
    List<Cliente> cliente;
    
    public ClienteBean(){
        
    }
    
    @PostConstruct 
    public void inicializar(){
    
        cliente = clienteService.listarClientes();
        clienteSeleccionada = new Cliente( );
    }
    
    public void editListener(RowEditEvent event){
        Cliente cliente = (Cliente) event.getObject();
        clienteService.modificarCliente(cliente);
    }

    public Cliente getClienteSeleccionada() {
        return clienteSeleccionada;
    }

    public void setClienteSeleccionada(Cliente clienteSeleccionada) {
        this.clienteSeleccionada = clienteSeleccionada;
    }

    public List<Cliente> getClientes() {
        return cliente;
    }

    public void setClientes(List<Cliente> clientes ) {
        this.cliente = clientes;
    }
    
    public void agregarCliente(){
        this.clienteService.registrarCliente(clienteSeleccionada);
        this.cliente.add(clienteSeleccionada);
        this.clienteSeleccionada= null;
    }
    
    public void eliminarCliente(){
        this.clienteService.eliminarCliente(clienteSeleccionada);
        this.cliente.remove(this.clienteSeleccionada);
        this.clienteSeleccionada= null;
    }
    
    public void reiniciarClienteSeleccionada (){
        this.clienteSeleccionada = new Cliente();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case  "insertar":
                    this.agregarCliente();
                    break;
                case "editar":
                    this.editListener((RowEditEvent) cliente);
                    break;
                case "eliminar":
                    this.eliminarCliente();
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoImpl().listar();
        System.out.println("clientes = " + clientes);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
}
