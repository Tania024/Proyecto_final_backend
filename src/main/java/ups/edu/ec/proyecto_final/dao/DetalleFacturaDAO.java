package ups.edu.ec.proyecto_final.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.edu.ec.proyecto_final.model.Cliente;
import ups.edu.ec.proyecto_final.model.DetalleFactura;

@Stateless
public class DetalleFacturaDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void insert(DetalleFactura detallefactura) {
        em.persist(detallefactura);
    }

    public void update(DetalleFactura detallefactura) {
        em.merge(detallefactura);
    }

    public void remove(int det_codigo) {
        DetalleFactura detallefactura = em.find(DetalleFactura.class, det_codigo);
        em.remove(detallefactura);
    }

    public DetalleFactura read(int det_codigo) {
        return em.find(DetalleFactura.class, det_codigo);
    }

    public List<DetalleFactura> getAll() {
        try {
            // Se asume que DetalleFactura tiene una relación con Cliente y Cliente tiene un campo cli_estado.
            String jpql = "SELECT d FROM DetalleFactura d JOIN d.cliente c WHERE c.cli_estado = 'activo'";
            Query q = em.createQuery(jpql, DetalleFactura.class);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }

    public DetalleFactura getDetalleFactura(int det_codigo) {
        try {
            String jpql = "SELECT d FROM DetalleFactura d WHERE d.det_codigo = :codigo";
            Query q = em.createQuery(jpql, DetalleFactura.class);
            q.setParameter("codigo", det_codigo);
            List<DetalleFactura> detallefacturas = q.getResultList();
            return detallefacturas.isEmpty() ? null : detallefacturas.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }
}
