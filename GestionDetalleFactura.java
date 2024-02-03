package ups.edu.ec.Borracho.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.Borracho.dao.DetalleFacturaDAO;
import ups.edu.ec.Borracho.model.DetalleFactura;

@Stateless
public class GestionDetalleFactura {
	@Inject
    private DetalleFacturaDAO daoDetalleFactura;

    public void guardarDetalleFactura(DetalleFactura detalleFactura) {
        DetalleFactura df = daoDetalleFactura.read(detalleFactura.getCodigo());
        if (df != null) {
        	df.setNombre(detalleFactura.getNombre());
        	df.setCantidad(detalleFactura.getCantidad());
        	df.setPrecio(detalleFactura.getPrecio());
        	
        	daoDetalleFactura.update(df);
        	
        }else {
        	daoDetalleFactura.insert(detalleFactura);
        }
    }

    public void actualizarDetalleFactura(DetalleFactura detalleFactura) throws Exception {
        DetalleFactura df = daoDetalleFactura.read(detalleFactura.getCodigo());
        if (df != null) {
            daoDetalleFactura.update(detalleFactura);
        } else {
            throw new Exception("DetalleFactura no existe");
        }
    }

    public DetalleFactura getDetalleFacturaPorCodigo(int codigo) throws Exception {
        return daoDetalleFactura.read(codigo);
    }

    public void borrarDetalleFactura(int codigo) {
        daoDetalleFactura.remove(codigo);
    }

    public List<DetalleFactura> getDetallesFactura() {
        return daoDetalleFactura.getAll();
    }

}
