package ups.edu.ec.proyecto_final.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Producto implements Serializable {

    @Id
    private int pro_codigo;
    private Date pro_fecha_registro;
    private String pro_nombre;
    private int pro_stock;
    private String pro_imagen;
    private double pro_precio;
    private double pro_iva;

    /*@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleFactura> detallesFactura;*/

    public int getPro_codigo() {
        return pro_codigo;
    }

    public void setPro_codigo(int pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public Date getPro_fecha_registro() {
        return pro_fecha_registro;
    }

    public void setPro_fecha_registro(Date pro_fecha_registro) {
        this.pro_fecha_registro = pro_fecha_registro;
    }

    public String getPro_nombre() {
        return pro_nombre;
    }

    public void setPro_nombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public int getPro_stock() {
        return pro_stock;
    }

    public void setPro_stock(int pro_stock) {
        this.pro_stock = pro_stock;
    }

    public String getPro_imagen() {
        return pro_imagen;
    }

    public void setPro_imagen(String pro_imagen) {
        this.pro_imagen = pro_imagen;
    }

    public double getPro_precio() {
        return pro_precio;
    }

    public void setPro_precio(double pro_precio) {
        this.pro_precio = pro_precio;
    }

    public double getPro_iva() {
        return pro_iva;
    }

    public void setPro_iva(double pro_iva) {
        this.pro_iva = pro_iva;
    }

    /*public List<DetalleFactura> getDetallesFactura() {
        return detallesFactura;
    }

    public void setDetallesFactura(List<DetalleFactura> detallesFactura) {
        this.detallesFactura = detallesFactura;
        detallesFactura.forEach(detalle -> detalle.setProducto(this));
    }

    public void agregarDetalleFactura(DetalleFactura detalle) {
        this.detallesFactura.add(detalle);
        detalle.setProducto(this);
    }*/

    @Override
    public String toString() {
        return "Producto [pro_codigo=" + pro_codigo + ", pro_fecha_registro=" + pro_fecha_registro + ", pro_nombre="
                + pro_nombre + ", pro_stock=" + pro_stock + ", pro_imagen=" + pro_imagen
                + ", pro_precio=" + pro_precio + ", pro_iva=" + pro_iva + "]";
    }
}
