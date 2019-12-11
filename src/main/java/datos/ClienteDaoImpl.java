package datos;

import dominio.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless 
public class ClienteDaoImpl implements ClienteDao{
    
    @PersistenceContext(unitName="SgaPU")
    EntityManager em;

    @Override
    public List<Cliente> findAllClientes() {
        return em.createNamedQuery("Cliente.findAll").getResultList();
    }

    public Cliente findClienteById(Cliente cliente) {
        return em.find(Cliente.class, cliente.getIdCliente());
    }

    @Override
    public Cliente findClienteByEmail(Cliente cliente) {
        Query query = em.createQuery("from Cliente p where p.email =: email");
        query.setParameter("email", cliente.getEmail());
        return (Cliente) query.getSingleResult();
    }

    @Override
    public void insertCliente(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        em.remove(em.merge(cliente));
    }

    public List<Cliente> listar() {
       return em.createNamedQuery("Cliente.findAll").getResultList();
    }

   
    
}
