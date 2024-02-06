package ups.edu.ec.proyecto_final.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
            if (cliente != null) {
                em.remove(cliente);
            }
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
            String jpql = "SELECT c FROM Cliente c";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }

    public Cliente getClientePorUsuario(String cli_usuario) {
        try {
            String jpql = "SELECT c FROM Cliente c WHERE c.cli_usuario = :cli_usuario";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("cli_usuario", cli_usuario);  // Corregido el nombre del parámetro
            return query.getSingleResult(); // Utiliza getSingleResult() si esperas un solo resultado
        } catch (NoResultException e) {
            // Maneja el caso cuando no se encuentra ningún cliente con el usuario proporcionado
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }
}
