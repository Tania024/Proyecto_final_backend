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
import ups.edu.ec.proyecto_final.business.GestionCliente;
import ups.edu.ec.proyecto_final.model.Cliente;

@Path("clientes")
public class ClienteServices {

    @Inject
    private GestionCliente gCliente;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Cliente cliente) {
        try {
            Cliente existingCliente = gCliente.getPorCodigo(cliente.getCli_cedula());
            
            if (existingCliente != null) {
                // Cliente existente, actualizar
                existingCliente.setCli_nombre(cliente.getCli_nombre());
                existingCliente.setCli_apellido(cliente.getCli_apellido());
                existingCliente.setCli_direccion(cliente.getCli_direccion());
                existingCliente.setCli_telefono(cliente.getCli_telefono());
                existingCliente.setCli_estado(cliente.getCli_estado());
                existingCliente.setCli_usuario(cliente.getCli_usuario());
                existingCliente.setCli_contrasena(cliente.getCli_contrasena());
                gCliente.actualizarCliente(existingCliente);
            } else {
                // Nuevo cliente, guardar
                gCliente.guardarClientes(cliente);
            }
            
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
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int cli_codigo) {
        try {
            gCliente.borrarCliente(cli_codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("dni") String cli_cedula, @QueryParam("nombre") String cli_nombre) {
        try {
            System.out.println("cedula " +  cli_cedula + " nom=" + cli_nombre);
            Cliente cli = gCliente.getPorCodigo(cli_cedula);
            return Response.ok(cli).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("{dni}/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer2(@PathParam("dni") String cli_cedula, @PathParam("nombre") String cli_nombre) {
        try {
            System.out.println("cedula " +  cli_cedula + " nom=" + cli_nombre);
            Cliente cli = gCliente.getPorCodigo(cli_cedula);
            return Response.ok(cli).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getClientes() {
        List<Cliente> clientes = gCliente.getClientes();
        if (clientes.size() > 0)
            return Response.ok(clientes).build();

        ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
