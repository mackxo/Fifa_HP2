package apidefifa.apidefifa.repositorios;

/*
 * REPOSITORIO: PaisRepositorio
 * --------------------------------------------------------------
 * Interfaz que gestiona el acceso a la tabla "pais".
 *
 * Hereda de JpaRepository los métodos CRUD estándar:
 *   - findAll()      → lista todos los países
 *   - findById(id)   → busca por clave primaria
 *   - save(pais)     → inserta o actualiza un registro
 *   - deleteById(id) → elimina por clave primaria
 *
 * Se agregan búsquedas por código ISO alfa2 y alfa3, que Spring
 * implementa automáticamente por convención de nombre de método.
 * --------------------------------------------------------------
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apidefifa.apidefifa.entidades.Pais;

@Repository
public interface PaisRepositorio extends JpaRepository<Pais, Long> {

    // Spring genera: SELECT * FROM pais WHERE CodigoAlfa2 = ?
    // Ej: buscar por "CO" (Colombia)
    Pais findByCodigoAlfa2(String codigoAlfa2);

    // Spring genera: SELECT * FROM pais WHERE CodigoAlfa3 = ?
    // Ej: buscar por "COL" (Colombia)
    Pais findByCodigoAlfa3(String codigoAlfa3);
}
