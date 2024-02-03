package ups.edu.ec.proyecto_final.services;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.proyecto_final.business.GestionProducto;
import ups.edu.ec.proyecto_final.model.Producto;

@Path("productos")
public class ProductoServices {

    @Inject
    private GestionProducto gProducto;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Producto producto) {
        try {
            Producto existingProducto = gProducto.getPorCodigo(producto.getPro_codigo());
            
            if (existingProducto != null) {
                // Producto existente, actualizar
                existingProducto.setPro_nombre(producto.getPro_nombre());
                existingProducto.setPro_stock(producto.getPro_stock());
                existingProducto.setPro_imagen(producto.getPro_imagen());
                existingProducto.setPro_precio(producto.getPro_precio());
                existingProducto.setPro_iva(producto.getPro_iva());
                gProducto.actualizarProducto(existingProducto);
            } else {
                // Nuevo producto, guardar
                gProducto.guardarProducto(producto);
            }
            
            return Response.ok(producto).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Producto producto) {
        try {
            gProducto.actualizarProducto(producto);
            return Response.ok(producto).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int pro_codigo) {
        try {
            gProducto.borrarProducto(pro_codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("codigo") int pro_codigo) {
        try {
            Producto prod = gProducto.getPorCodigo(pro_codigo);
            return Response.ok(prod).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Producto no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer2(@PathParam("codigo") int pro_codigo) {
        try {
            Producto prod = gProducto.getPorCodigo(pro_codigo);
            return Response.ok(prod).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Producto no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getProductos() {
        List<Producto> productos = gProducto.getProductos();
        if (productos.size() > 0)
            return Response.ok(productos).build();

        ErrorMessage error = new ErrorMessage(6, "No se registran productos");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
