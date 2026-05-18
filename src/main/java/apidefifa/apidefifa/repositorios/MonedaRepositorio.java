package apidefifa.apidefifa.repositorios;

/*
 * REPOSITORIO: MonedaRepositorio
 * --------------------------------------------------------------
 * Interfaz que gestiona el acceso a la tabla "moneda".
 *
 * Al extender JpaRepository<Moneda, Long>, Spring genera
 * automáticamente los métodos CRUD estándar:
 *   - findAll()        → lista todas las monedas
 *   - findById(id)     → busca por clave primaria
 *   - save(moneda)     → inserta o actualiza un registro
 *   - deleteById(id)   → elimina por clave primaria
 *
 * Además se declara un método de búsqueda por sigla que Spring
 * implementa automáticamente por convención de nombre.
 * --------------------------------------------------------------
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apidefifa.apidefifa.entidades.Moneda;

@Repository
public interface MonedaRepositorio extends JpaRepository<Moneda, Long> {

    // Spring traduce este nombre al SQL: SELECT * FROM moneda WHERE Sigla = ?
    // Útil para buscar por código ISO (ej: "COP", "USD", "EUR")
    Moneda findBySigla(String sigla);
}
