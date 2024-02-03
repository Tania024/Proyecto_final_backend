package ups.edu.ec.proyecto_final.services;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.proyecto_final.business.GestionDetalleFactura;
import ups.edu.ec.proyecto_final.model.DetalleFactura;

@Path("detalleFacturas")
public class DetalleFacturaServices {

    @Inject
    private GestionDetalleFactura gDetalleFactura;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(DetalleFactura detalleFactura) {
        try {
            DetalleFactura existingDetalle = gDetalleFactura.getDetalleFacturaPorCodigo(detalleFactura.getDet_codigo());

            if (existingDetalle != null) {
                // Detalle existente, actualizar
                existingDetalle.setDet_cantidad(detalleFactura.getDet_cantidad());
                existingDetalle.setDet_precio(detalleFactura.getDet_precio());
                gDetalleFactura.actualizarDetalleFactura(existingDetalle);
            } else {
                // Nuevo detalle, guardar
                gDetalleFactura.guardarDetalleFactura(detalleFactura);
            }

            return Response.ok(detalleFactura).build();
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
    public Response actualizar(DetalleFactura detalleFactura) {
        try {
            gDetalleFactura.actualizarDetalleFactura(detalleFactura);
            return Response.ok(detalleFactura).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int det_codigo) {
        try {
            gDetalleFactura.borrarDetalleFactura(det_codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("codigo") int det_codigo) {
        try {
            DetalleFactura detalleFactura = gDetalleFactura.getDetalleFacturaPorCodigo(det_codigo);
            return Response.ok(detalleFactura).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "DetalleFactura no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleFacturas() {
        List<DetalleFactura> detallesFactura = gDetalleFactura.getDetallesFactura();
        if (detallesFactura.size() > 0)
            return Response.ok(detallesFactura).build();

        ErrorMessage error = new ErrorMessage(6, "No se registran detalles de factura");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
