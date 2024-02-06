package ups.edu.ec.proyecto_final.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente implements Serializable {
	 @Id
	    private int cli_codigo;
	    private String cli_cedula;
	    private String cli_nombre;
	    private String cli_apellido;
	    private String cli_direccion;
	    private String cli_telefono;
	    private String cli_estado;
	    private String cli_usuario;
	    private String cli_contrasena;

	    /*@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	    private List<CabeceraFactura> cabecerasFactura;

	    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	    private List<DetalleFactura> detalleFactura;*/

	   
	    
	    public int getCli_codigo() {
			return cli_codigo;
		}



		public void setCli_codigo(int cli_codigo) {
			this.cli_codigo = cli_codigo;
		}



		public String getCli_cedula() {
			return cli_cedula;
		}



		public void setCli_cedula(String cli_cedula) {
			this.cli_cedula = cli_cedula;
		}



		public String getCli_nombre() {
			return cli_nombre;
		}



		public void setCli_nombre(String cli_nombre) {
			this.cli_nombre = cli_nombre;
		}



		public String getCli_apellido() {
			return cli_apellido;
		}



		public void setCli_apellido(String cli_apellido) {
			this.cli_apellido = cli_apellido;
		}



		public String getCli_direccion() {
			return cli_direccion;
		}



		public void setCli_direccion(String cli_direccion) {
			this.cli_direccion = cli_direccion;
		}



		public String getCli_telefono() {
			return cli_telefono;
		}



		public void setCli_telefono(String cli_telefono) {
			this.cli_telefono = cli_telefono;
		}



		public String getCli_estado() {
			return cli_estado;
		}



		public void setCli_estado(String cli_estado) {
			this.cli_estado = cli_estado;
		}



		public String getCli_usuario() {
			return cli_usuario;
		}



		public void setCli_usuario(String cli_usuario) {
			this.cli_usuario = cli_usuario;
		}



		public String getCli_contrasena() {
			return cli_contrasena;
		}



		public void setCli_contrasena(String cli_contrasena) {
			this.cli_contrasena = cli_contrasena;
		}



		/*public List<CabeceraFactura> getCabecerasFactura() {
			return cabecerasFactura;
		}



		public void setCabecerasFactura(List<CabeceraFactura> cabecerasFactura) {
			this.cabecerasFactura = cabecerasFactura;
		}



		public List<DetalleFactura> getDetalleFactura() {
			return detalleFactura;
		}



		public void setdetalleFactura(List<DetalleFactura> detalleFactura) {
			this.detalleFactura = detalleFactura;
		}


		public Cliente() {
	        this.cabecerasFactura = new ArrayList<>();
	        this.detalleFactura = new ArrayList<>();
	    }


	    public void setDetalleFactura(List<DetalleFactura> detalleFactura) {
	        this.detalleFactura = detalleFactura;
	        detalleFactura.forEach(detalle -> detalle.setCliente(this));
	    }

	    public void agregarAldetalleFactura(DetalleFactura detalle) {
	        this.detalleFactura.add(detalle);
	        detalle.setCliente(this);
	    }*/

		@Override
	    public String toString() {
	        return "Cliente [cli_codigo=" + cli_codigo + ", cli_cedula=" + cli_cedula + ", cli_nombre=" + cli_nombre
	                + ", cli_apellido=" + cli_apellido + ", cli_direccion=" + cli_direccion + ", cli_telefono="
	                + cli_telefono + ", cli_estado=" + cli_estado + ", cli_usuario=" + cli_usuario + ", cli_contrasena="
	                + cli_contrasena + "]";
	    }
}
