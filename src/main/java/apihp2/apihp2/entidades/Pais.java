package apihp2.apihp2.entidades;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "pais")
public class Pais {

    /* ── Clave primaria autonumérica ── */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_pais")
    @GenericGenerator(name = "secuencia_pais", strategy = "increment")
    private long id;

    /* ── Campos de datos ── */
    @Column(name = "Pais", length = 50)
    private String nombre;

    @Column(name = "CodigoAlfa2", length = 5)
    private String codigoAlfa2;

    @Column(name = "CodigoAlfa3", length = 5)
    private String codigoAlfa3;

    /*
     * ── Relación muchos-a-uno con Moneda ──
     * Muchos países pueden usar la misma moneda (ej: el Euro lo usan
     * varios países). @JoinColumn indica el campo FK en la tabla pais.
     */
    @ManyToOne
    @JoinColumn(name = "IdMoneda", referencedColumnName = "Id")
    private Moneda moneda;

    @Column(name = "Mapa")      // BLOB → byte[] en Java
    private byte[] mapa;

    @Column(name = "Bandera")   // BLOB → byte[] en Java
    private byte[] bandera;

    /* ── Constructores ── */
    public Pais() {}

    // Constructor sin imágenes (el más usado al crear desde JSON)
    public Pais(long id, String nombre, String codigoAlfa2, String codigoAlfa3, Moneda moneda) {
        this.id = id;
        this.nombre = nombre;
        this.codigoAlfa2 = codigoAlfa2;
        this.codigoAlfa3 = codigoAlfa3;
        this.moneda = moneda;
    }

    /* ── Getters y Setters ── */
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigoAlfa2() { return codigoAlfa2; }
    public void setCodigoAlfa2(String codigoAlfa2) { this.codigoAlfa2 = codigoAlfa2; }

    public String getCodigoAlfa3() { return codigoAlfa3; }
    public void setCodigoAlfa3(String codigoAlfa3) { this.codigoAlfa3 = codigoAlfa3; }

    public Moneda getMoneda() { return moneda; }
    public void setMoneda(Moneda moneda) { this.moneda = moneda; }

    public byte[] getMapa() { return mapa; }
    public void setMapa(byte[] mapa) { this.mapa = mapa; }

    public byte[] getBandera() { return bandera; }
    public void setBandera(byte[] bandera) { this.bandera = bandera; }
}
