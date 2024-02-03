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

    public void guardarClientes(Cliente cliente) throws Exception {
        // Validaciones básicas
        if (cliente.getCli_cedula().length() != 10) {
            throw new Exception("Cédula incorrecta");
        }
        if (!"activo".equals(cliente.getCli_estado()) && !"inactivo".equals(cliente.getCli_estado())) {
            throw new Exception("Estado no válido");
        }
        if (!isValidPassword(cliente.getCli_contrasena())) {
            throw new Exception("Formato de contraseña incorrecto");
        }

        Cliente cli = daoCliente.getClientePorCedula(cliente.getCli_cedula());
        if (cli != null) {
            // Cliente existente, actualizamos
            if ("activo".equals(cli.getCli_estado())) {
                daoCliente.update(cliente);
            } else {
                throw new Exception("Cliente inactivo, no se puede actualizar");
            }
        } else {
            // Nuevo cliente, insertamos
            daoCliente.insert(cliente);
        }
    }

    public void actualizarCliente(Cliente cliente) throws Exception {
        // Validaciones básicas
        if (cliente.getCli_cedula().length() != 10) {
            throw new Exception("Cédula incorrecta");
        }
        if (!"activo".equals(cliente.getCli_estado()) && !"inactivo".equals(cliente.getCli_estado())) {
            throw new Exception("Estado no válido");
        }
        if (!isValidPassword(cliente.getCli_contrasena())) {
            throw new Exception("Formato de contraseña incorrecto");
        }

        Cliente cli = daoCliente.getClientePorCedula(cliente.getCli_cedula());
        if (cli != null) {
            // Cliente existente, actualizamos
            if ("activo".equals(cli.getCli_estado())) {
                daoCliente.update(cliente);
            } else {
                throw new Exception("Cliente inactivo, no se puede actualizar");
            }
        } else {
            throw new Exception("Cliente no existe");
        }
    }

    public Cliente getPorCodigo(String cli_cedula) throws Exception {
        // Validaciones básicas
        if (cli_cedula.length() != 10) {
            throw new Exception("Cédula incorrecta");
        }

        Cliente cliente = daoCliente.getClientePorCedula(cli_cedula);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado");
        }
        return cliente;
    }

    public void borrarCliente(int cli_codigo) {
        // No se realiza validación de existencia ya que el método remove del DAO
        // manejará el caso de un cliente inexistente.
        daoCliente.remove(cli_codigo);
    }

    public List<Cliente> getClientes() {
        return daoCliente.getAll();
    }

    private boolean isValidPassword(String password) {
        // Puedes implementar aquí tu lógica de validación de contraseña
        // Por ejemplo, longitud mínima, caracteres especiales, etc.
        return password.length() >= 8;
    }
}
