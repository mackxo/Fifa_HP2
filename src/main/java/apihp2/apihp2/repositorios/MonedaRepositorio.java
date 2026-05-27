package apihp2.apihp2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apihp2.apihp2.entidades.Moneda;

@Repository
public interface MonedaRepositorio extends JpaRepository<Moneda, Long> {

    // Spring traduce este nombre al SQL: SELECT * FROM moneda WHERE Sigla = ?
    // Útil para buscar por código ISO (ej: "COP", "USD", "EUR")
    Moneda findBySigla(String sigla);
}
