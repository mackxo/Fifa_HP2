package apidefifa.apidefifa.entidades;

/*
 * ENTIDAD: CambioMoneda
 * --------------------------------------------------------------
 * Representa la tabla "cambiomoneda" en la base de datos.
 * Registra el valor de una moneda en un momento específico,
 * permitiendo construir un historial de tasas de cambio.
 *
 * Campos de la tabla:
 *   - Id       → clave primaria autonumérica
 *   - IdMoneda → clave foránea hacia la tabla moneda
 *   - Fecha    → fecha y hora del registro (DATETIME en BD → LocalDateTime en Java)
 *   - Cambio   → valor o tasa de cambio registrada (FLOAT)
 * --------------------------------------------------------------
 */

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "cambiomoneda")
public class CambioMoneda {

    /* ── Clave primaria autonumérica ── */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_cambio")
    @GenericGenerator(name = "secuencia_cambio", strategy = "increment")
    private long id;

    /*
     * ── Relación muchos-a-uno con Moneda ──
     * Muchos registros de cambio pertenecen a una sola moneda.
     * @JoinColumn indica el campo FK en la tabla cambiomoneda.
     */
    @ManyToOne
    @JoinColumn(name = "IdMoneda", referencedColumnName = "Id")
    private Moneda moneda;

    // DATETIME en la BD → LocalDateTime en Java
    @Column(name = "Fecha")
    private LocalDateTime fecha;

    // El campo en la BD se llama "Cambio", pero en Java lo llamamos "valor"
    // para mayor claridad semántica
    @Column(name = "Cambio")
    private float valor;

    /* ── Constructores ── */
    public CambioMoneda() {}

    public CambioMoneda(long id, Moneda moneda, LocalDateTime fecha, float valor) {
        this.id = id;
        this.moneda = moneda;
        this.fecha = fecha;
        this.valor = valor;
    }

    /* ── Getters y Setters ── */
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Moneda getMoneda() { return moneda; }
    public void setMoneda(Moneda moneda) { this.moneda = moneda; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }
}
