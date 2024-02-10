package ups.edu.ec.proyecto_final.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class CabeceraFactura implements Serializable {

    @Id
    private int cab_codigo;
    private Date cab_fecha;
    private double cab_subtotal;
    private double cab_iva;
    private double cab_total;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "cabeceraFactura")
    private List<DetalleFactura> detalles;

    public int getCab_codigo() {
        return cab_codigo;
    }

    public void setCab_codigo(int cab_codigo) {
        this.cab_codigo = cab_codigo;
    }

    public Date getCab_fecha() {
        return cab_fecha;
    }

    public void setCab_fecha(Date cab_fecha) {
        this.cab_fecha = cab_fecha;
    }

    public double getCab_subtotal() {
        return cab_subtotal;
    }

    public void setCab_subtotal(double cab_subtotal) {
        this.cab_subtotal = cab_subtotal;
    }

    public double getCab_iva() {
        return cab_iva;
    }

    public void setCab_iva(double cab_iva) {
        this.cab_iva = cab_iva;
    }

    public double getCab_total() {
        return cab_total;
    }

    public void setCab_total(double cab_total) {
        this.cab_total = cab_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /*public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
        detalles.forEach(detalle -> detalle.setCabeceraFactura(this));
    }

    public void agregarDetalle(DetalleFactura detalle) {
        this.detalles.add(detalle);
        detalle.setCabeceraFactura(this);
    }*/

    @Override
    public String toString() {
        return "CabeceraFactura [cab_codigo=" + cab_codigo + ", cab_fecha=" + cab_fecha + ", cab_subtotal="
                + cab_subtotal + ", cab_iva=" + cab_iva + ", cab_total=" + cab_total + "]";
    }
}
