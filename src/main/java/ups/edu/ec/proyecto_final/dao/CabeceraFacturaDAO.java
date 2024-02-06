package ups.edu.ec.proyecto_final.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.edu.ec.proyecto_final.model.CabeceraFactura;
import ups.edu.ec.proyecto_final.model.Cliente;

@Stateless
public class CabeceraFacturaDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void insert(CabeceraFactura cabeceraFactura) {
        em.persist(cabeceraFactura);
    }

    public void update(CabeceraFactura cabeceraFactura) {
        em.merge(cabeceraFactura);
    }

    public void remove(int cab_codigo) {
        CabeceraFactura cabeceraFactura = em.find(CabeceraFactura.class, cab_codigo);
        em.remove(cabeceraFactura);
    }

    public CabeceraFactura read(int cab_codigo) {
        return em.find(CabeceraFactura.class, cab_codigo);
    }

    public List<CabeceraFactura> getAll() {
        try {
            // Se asume que CabeceraFactura tiene una relación con Cliente y Cliente tiene un campo cli_estado.
            String jpql = "SELECT c FROM CabeceraFactura c JOIN c.cliente cl WHERE cl.cli_estado = 'Activo'";
            Query q = em.createQuery(jpql, CabeceraFactura.class);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }

    public CabeceraFactura getCabeceraFactura(int cab_codigo) {
        try {
            String jpql = "SELECT c FROM CabeceraFactura c WHERE c.cab_codigo = :cab_codigo";
            Query q = em.createQuery(jpql, CabeceraFactura.class);
            q.setParameter("cab_codigo", cab_codigo);
            List<CabeceraFactura> cabeceraFacturas = q.getResultList();
            return cabeceraFacturas.isEmpty() ? null : cabeceraFacturas.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar la excepción de una manera específica para tu aplicación.
            return null;
        }
    }
}
