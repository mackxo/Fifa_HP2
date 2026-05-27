package apihp2.apihp2.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apihp2.apihp2.entidades.Pais;

@Repository
public interface PaisRepositorio extends JpaRepository<Pais, Long> {

    // Spring genera: SELECT * FROM pais WHERE CodigoAlfa2 = ?
    // Ej: buscar por "CO" (Colombia)
    Pais findByCodigoAlfa2(String codigoAlfa2);

    // Spring genera: SELECT * FROM pais WHERE CodigoAlfa3 = ?
    // Ej: buscar por "COL" (Colombia)
    Pais findByCodigoAlfa3(String codigoAlfa3);
}
