package ups.edu.ec.proyecto_final.business;

import java.util.Date;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import ups.edu.ec.proyecto_final.dao.CabeceraFacturaDAO;
import ups.edu.ec.proyecto_final.dao.ClienteDAO;
import ups.edu.ec.proyecto_final.dao.DetalleFacturaDAO;
import ups.edu.ec.proyecto_final.dao.ProductoDAO;
import ups.edu.ec.proyecto_final.model.CabeceraFactura;
import ups.edu.ec.proyecto_final.model.Cliente;
import ups.edu.ec.proyecto_final.model.DetalleFactura;
import ups.edu.ec.proyecto_final.model.Producto;


@Singleton
@Startup
public class GestionDatos {
	@Inject
	private ClienteDAO daoCliente;
	
	@Inject
	private DetalleFacturaDAO daoDetalleFactura;
	
	@Inject
	private ProductoDAO daoProducto;
	
	@Inject
	private CabeceraFacturaDAO daoCabeceraFactura;
	
	@PostConstruct
	public void init() {
		System.out.println("iniciando");
		
		Cliente cliente = new Cliente();
		cliente.setCli_codigo(1);
		cliente.setCli_cedula("0106717671");
		cliente.setCli_direccion("Monay");
		cliente.setCli_nombre("Tania");
		cliente.setCli_telefono("0993235895");
		cliente.setCli_apellido("Lojano");
		cliente.setCli_usuario("tlojano");
		cliente.setCli_contrasena("proyecto_final");
		cliente.setCli_estado("Activo");
		
		daoCliente.insert(cliente);
		
		Producto producto = new Producto();
		producto.setPro_codigo(1);
		producto.setPro_fecha_registro(null);
		producto.setPro_imagen("assets/CHOMPA.jpeg");
		producto.setPro_iva(12);
		producto.setPro_nombre("Chompa verde ");
		producto.setPro_precio(19.14);
		producto.setPro_stock(1);
		daoProducto.insert(producto);
		
		Producto producto1 = new Producto();
		producto1.setPro_codigo(2);
		producto1.setPro_fecha_registro(null);
		producto1.setPro_imagen("assets/CAMISETA.jpeg");
		producto1.setPro_iva(12);
		producto1.setPro_nombre("Camiseta de hombre para primavera");
		producto1.setPro_precio(23.99);
		producto1.setPro_stock(8);
		daoProducto.insert(producto1);
		
		Producto producto2 = new Producto();
		producto2.setPro_codigo(3);
		producto2.setPro_fecha_registro(null);
		producto2.setPro_imagen("assets/FALDA.jpeg");
		producto2.setPro_iva(12);
		producto2.setPro_nombre("Falda");
		producto2.setPro_precio(33.40);
		producto2.setPro_stock(3);
		daoProducto.insert(producto2);
		
		Producto producto3 = new Producto();
		producto3.setPro_codigo(4);
		producto3.setPro_fecha_registro(null);
		producto3.setPro_imagen("assets/VESTIDO.jpeg");
		producto3.setPro_iva(12);
		producto3.setPro_nombre("Vestido verde con tiritas para la playa");
		producto3.setPro_precio(40.12);
		producto3.setPro_stock(3);
		//producto.setDetallesFactura(null);
		daoProducto.insert(producto3);
		
		Producto producto4 = new Producto();
		producto4.setPro_codigo(5);
		producto4.setPro_fecha_registro(null);
		producto4.setPro_imagen("assets/SHORT.jpeg");
		producto4.setPro_iva(12);
		producto4.setPro_nombre("Short color azul marino");
		producto4.setPro_precio(4.12);
		producto4.setPro_stock(3);
		daoProducto.insert(producto4);
		
		Producto producto5 = new Producto();
		producto5.setPro_codigo(6);
		producto5.setPro_fecha_registro(null);
		producto5.setPro_imagen("assets/BOLERO.jpeg");
		producto5.setPro_iva(12);
		producto5.setPro_nombre("bolero blanco con fresitas");
		producto5.setPro_precio(15.99);
		producto5.setPro_stock(5);
		
		daoProducto.insert(producto5);
		
		
		
		CabeceraFactura cabecera = new CabeceraFactura();
		cabecera.setCab_codigo(1);
		cabecera.setCab_fecha(null);
		cabecera.setCab_iva(12);
		cabecera.setCab_subtotal(20);
		cabecera.setCab_total(23);
		cabecera.setCliente(cliente);
		//cabecera.setDetalles(null);
		
		daoCabeceraFactura.insert(cabecera);
		DetalleFactura detalle = new DetalleFactura();
		//detalle.setDet_codigo(1);
		detalle.setDet_cantidad(2);
		detalle.setDet_precio(13);
		detalle.setDet_subtotal(20);
		detalle.setProducto(producto);
		detalle.setCliente(cliente);
		//detalle.setCabeceraFactura(cabecera);
		
		daoDetalleFactura.insert(detalle);
		
		}
	}


