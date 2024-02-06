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
import ups.edu.ec.proyecto_final.business.GestionCliente;
import ups.edu.ec.proyecto_final.model.Cliente;

@Path("clientes")
@Named
@ApplicationScoped
public class ClienteServices {

    @Inject
    private GestionCliente gCliente;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Cliente cliente) {
        try {
            gCliente.guardarClientes(cliente);
            return Response.ok(cliente).build();
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
    public Response actualizar(Cliente cliente) {
        try {
            gCliente.actualizarCliente(cliente);
            return Response.ok(cliente).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
	@Path("elim/{cli_codigo}")
    public Response borrar(@PathParam("cli_codigo") int cli_codigo) {
        try {
            gCliente.borrarCliente(cli_codigo);
            return Response.ok("OK").build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cli_usuario}")
    public Response leer(@PathParam("cli_usuario") String cli_usuario) {
        try {
            Cliente cli = gCliente.getPorUsuario(cli_usuario);
            if (cli != null) {
                return Response.ok(cli).build();
            } else {
                ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getClientes() {
        try {
            List<Cliente> clientes = gCliente.getClientes();
            if (!clientes.isEmpty()) {
                return Response.ok(clientes).build();
            } else {
                ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }
}
