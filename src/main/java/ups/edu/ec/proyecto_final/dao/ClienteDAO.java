package ups.edu.ec.proyecto_final.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ups.edu.ec.proyecto_final.model.Cliente;

@Stateless
public class ClienteDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void insert(Cliente cliente) {
        try {
            em.persist(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
        }
    }

    public void update(Cliente cliente) {
        try {
            em.merge(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
        }
    }

    public void remove(int cli_codigo) {
        try {
            Cliente cliente = em.find(Cliente.class, cli_codigo);
            em.remove(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
        }
    }

    public Cliente read(int cli_codigo) {
        try {
            return em.find(Cliente.class, cli_codigo);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }

    public List<Cliente> getAll() {
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }

    public Cliente getClientePorCedula(String cli_cedula) {
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.cli_cedula = :cedula", Cliente.class);
            query.setParameter("cedula", cli_cedula);
            List<Cliente> clientes = query.getResultList();
            return clientes.isEmpty() ? null : clientes.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }
}
