package ups.edu.ec.proyecto_final.business;

import java.util.List;

import jakarta.inject.Inject;
import ups.edu.ec.proyecto_final.dao.ProductoDAO;
import ups.edu.ec.proyecto_final.model.Producto;

public class GestionProducto {

    @Inject
    private ProductoDAO daoProducto;

    public void guardarProducto(Producto producto) throws Exception {
        // Validaciones básicas
        if (producto.getPro_nombre() == null || producto.getPro_nombre().isEmpty()) {
            throw new Exception("Nombre del producto requerido");
        }
        if (producto.getPro_stock() < 0) {
            throw new Exception("Stock no puede ser negativo");
        }
        if (producto.getPro_precio() <= 0) {
            throw new Exception("Precio debe ser mayor que cero");
        }

        Producto prod = daoProducto.read(producto.getPro_codigo());
        if (prod != null) {
            daoProducto.update(producto);
        } else {
            daoProducto.insert(producto);
        }
    }

    public void actualizarProducto(Producto producto) throws Exception {
        // Validaciones básicas
        if (producto.getPro_nombre() == null || producto.getPro_nombre().isEmpty()) {
            throw new Exception("Nombre del producto requerido");
        }
        if (producto.getPro_stock() < 0) {
            throw new Exception("Stock no puede ser negativo");
        }
        if (producto.getPro_precio() <= 0) {
            throw new Exception("Precio debe ser mayor que cero");
        }

        Producto prod = daoProducto.read(producto.getPro_codigo());
        if (prod != null) {
            daoProducto.update(producto);
        } else {
            throw new Exception("Producto no existe");
        }
    }

    public Producto getPorCodigo(int pro_codigo) throws Exception {
        return daoProducto.read(pro_codigo);
    }

    public void borrarProducto(int pro_codigo) {
        daoProducto.remove(pro_codigo);
    }

    public List<Producto> getProductos() {
        return daoProducto.getAll();
    }
}
