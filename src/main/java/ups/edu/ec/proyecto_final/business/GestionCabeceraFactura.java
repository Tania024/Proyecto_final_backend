package ups.edu.ec.proyecto_final.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.proyecto_final.dao.CabeceraFacturaDAO;
import ups.edu.ec.proyecto_final.model.CabeceraFactura;

@Stateless
public class GestionCabeceraFactura {

	@Inject
    private CabeceraFacturaDAO daoCabeceraFactura;

    public void guardarFactura(CabeceraFactura cabecerafactura) throws Exception {
        // Validaciones básicas
        if (cabecerafactura.getCab_fecha() == null) {
            throw new Exception("Fecha de la factura requerida");
        }
        if (cabecerafactura.getCab_total() <= 0) {
            throw new Exception("Total de la factura debe ser mayor que cero");
        }

        CabeceraFactura fac = daoCabeceraFactura.read(cabecerafactura.getCab_codigo());
        if (fac != null) {
            //fac.getDetalles().clear();
            //fac.getDetalles().addAll(cabecerafactura.getDetalles());
            fac.setCab_total(cabecerafactura.getCab_total());
            fac.setCab_fecha(cabecerafactura.getCab_fecha());
            daoCabeceraFactura.update(fac);
        } else {
            daoCabeceraFactura.insert(cabecerafactura);
        }
    }

    public void actualizarFactura(CabeceraFactura cabecerafactura) throws Exception {
        // Validaciones básicas
        if (cabecerafactura.getCab_fecha() == null) {
            throw new Exception("Fecha de la factura requerida");
        }
        if (cabecerafactura.getCab_total() <= 0) {
            throw new Exception("Total de la factura debe ser mayor que cero");
        }

        CabeceraFactura fac = daoCabeceraFactura.read(cabecerafactura.getCab_codigo());
        if (fac != null) {
            daoCabeceraFactura.update(cabecerafactura);
        } else {
            throw new Exception("Factura no existe");
        }
    }

    public CabeceraFactura getFacturaPorCodigo(int cab_codigo) throws Exception {
        return daoCabeceraFactura.read(cab_codigo);
    }

    public void borrarFactura(int cab_codigo) {
        daoCabeceraFactura.remove(cab_codigo);
    }

    public List<CabeceraFactura> getFacturas() {
        return daoCabeceraFactura.getAll();
    }
}
