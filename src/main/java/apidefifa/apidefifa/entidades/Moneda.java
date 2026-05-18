package apidefifa.apidefifa.entidades;

/*
 * ENTIDAD: Moneda
 * --------------------------------------------------------------
 * Representa la tabla "moneda" en la base de datos.
 * Una moneda puede estar asociada a muchos países y puede tener
 * muchos registros de cambio a lo largo del tiempo.
 *
 * Campos de la tabla:
 *   - Id       → clave primaria autonumérica
 *   - Moneda   → nombre completo (ej: "Peso colombiano")
 *   - Sigla    → código ISO de 3 letras (ej: "COP")
 *   - Simbolo  → símbolo gráfico (ej: "$")
 *   - Emisor   → entidad que la emite (ej: "Banco de la República")
 *   - Imagen   → imagen en binario (BLOB)
 * --------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "moneda")
public class Moneda {

    /* ── Clave primaria autonumérica ── */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_moneda")
    @GenericGenerator(name = "secuencia_moneda", strategy = "increment")
    private long id;

    /* ── Campos de datos ── */
    @Column(name = "Moneda", length = 100)
    private String nombre;

    @Column(name = "Sigla", length = 5)
    private String sigla;

    @Column(name = "Simbolo", length = 5)
    private String simbolo;

    @Column(name = "Emisor", length = 100)
    private String emisor;

    @Column(name = "Imagen")   // BLOB en la BD → byte[] en Java
    private byte[] imagen;

    /*
     * ── Relaciones uno-a-muchos ──
     * @JsonIgnore evita bucles infinitos al serializar a JSON:
     * sin él, Moneda serializa sus CambioMoneda, cada uno
     * vuelve a serializar su Moneda, y así infinitamente.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "moneda")
    private List<CambioMoneda> cambios = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "moneda")
    private List<Pais> paises = new ArrayList<>();

    /* ── Constructores ── */
    public Moneda() {}

    public Moneda(long id, String nombre, String sigla, String simbolo, String emisor) {
        this.id = id;
        this.nombre = nombre;
        this.sigla = sigla;
        this.simbolo = simbolo;
        this.emisor = emisor;
    }

    /* ── Getters y Setters ── */
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getSimbolo() { return simbolo; }
    public void setSimbolo(String simbolo) { this.simbolo = simbolo; }

    public String getEmisor() { return emisor; }
    public void setEmisor(String emisor) { this.emisor = emisor; }

    public byte[] getImagen() { return imagen; }
    public void setImagen(byte[] imagen) { this.imagen = imagen; }

    public List<CambioMoneda> getCambios() { return cambios; }
    public void setCambios(List<CambioMoneda> cambios) { this.cambios = cambios; }

    public List<Pais> getPaises() { return paises; }
    public void setPaises(List<Pais> paises) { this.paises = paises; }
}
