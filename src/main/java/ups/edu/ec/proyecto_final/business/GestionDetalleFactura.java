package ups.edu.ec.proyecto_final.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.proyecto_final.dao.DetalleFacturaDAO;
import ups.edu.ec.proyecto_final.model.DetalleFactura;

@Stateless
public class GestionDetalleFactura {

    @Inject
    private DetalleFacturaDAO daoDetalleFactura;

    public void guardarDetalleFactura(DetalleFactura detalleFactura) throws Exception {
        // Validaciones básicas
        if (detalleFactura.getDet_precio() <= 0) {
            throw new Exception("Precio debe ser mayor que cero");
        }
        if (detalleFactura.getDet_cantidad() <= 0) {
            throw new Exception("Cantidad debe ser mayor que cero");
        }

        DetalleFactura df = daoDetalleFactura.read(detalleFactura.getDet_codigo());
        if (df != null) {
            df.setDet_cantidad(detalleFactura.getDet_cantidad());
            df.setDet_precio(detalleFactura.getDet_precio());

            daoDetalleFactura.update(df);

        } else {
            daoDetalleFactura.insert(detalleFactura);
        }
    }

    public void actualizarDetalleFactura(DetalleFactura detalleFactura) throws Exception {
        // Validaciones básicas
        if (detalleFactura.getDet_precio() <= 0) {
            throw new Exception("Precio debe ser mayor que cero");
        }
        if (detalleFactura.getDet_cantidad() <= 0) {
            throw new Exception("Cantidad debe ser mayor que cero");
        }

        DetalleFactura df = daoDetalleFactura.read(detalleFactura.getDet_codigo());
        if (df != null) {
            daoDetalleFactura.update(detalleFactura);
        } else {
            throw new Exception("DetalleFactura no existe");
        }
    }

    public DetalleFactura getDetalleFacturaPorCodigo(int det_codigo) throws Exception {
        return daoDetalleFactura.read(det_codigo);
    }

    public void borrarDetalleFactura(int det_codigo) {
        daoDetalleFactura.remove(det_codigo);
    }

    public List<DetalleFactura> getDetallesFactura() {
        return daoDetalleFactura.getAll();
    }
}
