package ups.edu.ec.proyecto_final.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ups.edu.ec.proyecto_final.model.Cliente;
import ups.edu.ec.proyecto_final.model.DetalleFactura;
import ups.edu.ec.proyecto_final.model.Producto;

@Stateless
public class ProductoDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void insert(Producto producto) {
        try {
            em.persist(producto);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
        }
    }

    public void update(Producto producto) {
        try {
            em.merge(producto);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
        }
    }
    
    public void remove(int pro_codigo) {
        try {
        	Producto producto = em.find(Producto.class, pro_codigo);
            if (producto != null) {
                em.remove(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
        }
    }

    public Producto read(int pro_codigo) {
        try {
            return em.find(Producto.class, pro_codigo);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }

    public List<Producto> getAll() {
        try {
            TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }
}
