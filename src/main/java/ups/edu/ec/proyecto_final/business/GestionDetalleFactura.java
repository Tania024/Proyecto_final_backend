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

	        if (detalleFactura.getDet_codigo() > 0) {
	            // Si el código es mayor que cero, se intenta actualizar
	            DetalleFactura df = daoDetalleFactura.read(detalleFactura.getDet_codigo());
	            if (df != null) {
	                df.setDet_cantidad(detalleFactura.getDet_cantidad());
	                df.setDet_precio(detalleFactura.getDet_precio());
	                // Asegurarse de establecer la relación con CabeceraFactura y Producto
	                //df.setCabeceraFactura(detalleFactura.getCabeceraFactura());
	                df.setProducto(detalleFactura.getProducto());
	                df.setCliente(detalleFactura.getCliente());
	                daoDetalleFactura.update(df);
	                return;
	            }
	        }

	        // Si el código es cero o no se encontró el detalle, se inserta uno nuevo
	        daoDetalleFactura.insert(detalleFactura);
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

	    public List<DetalleFactura> obtenerDetallesFacturaPorCliente(int cliCodigo) {
	        try {
	            // Implementa la lógica para obtener los detalles de factura por cliente
	            return daoDetalleFactura.obtenerDetallesFacturaPorCliente(cliCodigo);
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Puedes manejar la excepción de una manera específica para tu aplicación.
	            return null;
	        }
	    }
	    
	    public void borrarDetalleFactura(int det_codigo) {
	        daoDetalleFactura.remove(det_codigo);
	    }

	    public List<DetalleFactura> getDetallesFactura() {
	        return daoDetalleFactura.getAll();
	    }
}
