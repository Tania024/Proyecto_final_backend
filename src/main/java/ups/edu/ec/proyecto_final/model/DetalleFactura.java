package ups.edu.ec.proyecto_final.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleFactura implements Serializable {
    
    @Id
    private int det_codigo;
    private double det_precio;
    private int det_cantidad;
    private double det_subtotal;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private CabeceraFactura cabeceraFactura;

    @ManyToOne
    private Cliente cliente;

    public int getDet_codigo() {
        return det_codigo;
    }

    public void setDet_codigo(int det_codigo) {
        this.det_codigo = det_codigo;
    }

    public double getDet_precio() {
        return det_precio;
    }

    public void setDet_precio(double det_precio) {
        this.det_precio = det_precio;
    }

    public int getDet_cantidad() {
        return det_cantidad;
    }

    public void setDet_cantidad(int det_cantidad) {
        this.det_cantidad = det_cantidad;
    }

    public double getDet_subtotal() {
        return det_subtotal;
    }

    public void setDet_subtotal(double det_subtotal) {
        this.det_subtotal = det_subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public CabeceraFactura getCabeceraFactura() {
        return cabeceraFactura;
    }

    public void setCabeceraFactura(CabeceraFactura cabeceraFactura) {
        this.cabeceraFactura = cabeceraFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "DetalleFactura [det_codigo=" + det_codigo + ", det_precio=" + det_precio + ", det_cantidad="
                + det_cantidad + ", det_subtotal=" + det_subtotal + "]";
    }
}
