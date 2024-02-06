package ups.edu.ec.proyecto_final.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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
import ups.edu.ec.proyecto_final.business.GestionCabeceraFactura;
import ups.edu.ec.proyecto_final.model.CabeceraFactura;

@Path("cabeceraFacturas")
@Named
@ApplicationScoped
public class CabeceraFacturaServices {

    @Inject
    private GestionCabeceraFactura gFactura;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(CabeceraFactura cabecerafactura) {
        try {
            gFactura.guardarFactura(cabecerafactura);
            return Response.ok(cabecerafactura).build();
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
    public Response actualizar(CabeceraFactura cabecerafactura) {
        try {
            gFactura.actualizarFactura(cabecerafactura);
            return Response.ok(cabecerafactura).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
	@Path("elim/{cab_codigo}")
    public String borrar(@PathParam("cab_codigo") int cab_codigo) {
        try {
            gFactura.borrarFactura(cab_codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cab_codigo}")
    public Response leer(@PathParam("cab_codigo") int cab_codigo) {
        try {
            CabeceraFactura fac = gFactura.getFacturaPorCodigo(cab_codigo);
            return Response.ok(fac).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Factura no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacturas() {
        List<CabeceraFactura> facturas = gFactura.getFacturas();
        if (facturas.size() > 0)
            return Response.ok(facturas).build();

        ErrorMessage error = new ErrorMessage(6, "No se registran facturas");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
