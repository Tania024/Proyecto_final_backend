package ups.edu.ec.proyecto_final.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.proyecto_final.dao.ClienteDAO;
import ups.edu.ec.proyecto_final.model.Cliente;

@Stateless
public class GestionCliente {
	@Inject
    private ClienteDAO daoCliente;

    public void guardarClientes(Cliente cliente) {
        Cliente cli = daoCliente.getClientePorUsuario(cliente.getCli_cedula());
        
        if (cli != null) {
            // Cliente existente, actualizamos
            cli.setCli_nombre(cliente.getCli_nombre());
            cli.setCli_apellido(cliente.getCli_apellido());
            cli.setCli_direccion(cliente.getCli_direccion());
            cli.setCli_telefono(cliente.getCli_telefono());
            cli.setCli_estado(cliente.getCli_estado());
            cli.setCli_usuario(cliente.getCli_usuario());
            cli.setCli_contrasena(cliente.getCli_contrasena());
            daoCliente.update(cli);
        } else {
            // Nuevo cliente, insertamos
            cliente.setCli_estado("inactivo");
            daoCliente.insert(cliente);
        }
    }


    public void actualizarCliente(Cliente cliente) {
        Cliente cli = daoCliente.getClientePorUsuario(cliente.getCli_cedula());
        if (cli != null) {
            // Cliente existente, actualizamos
            daoCliente.update(cliente);
        } else {
            throw new RuntimeException("Cliente no existe");
        }
    }

    public Cliente getPorUsuario(String cli_usuario) {
        return daoCliente.getClientePorUsuario(cli_usuario);
    }

    public void borrarCliente(int cli_codigo) {
        daoCliente.remove(cli_codigo);
    }


    public List<Cliente> getClientes() {
        return daoCliente.getAll();
    }
    
}
