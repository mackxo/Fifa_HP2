package apihp2.apihp2.interfaces;

import java.util.List;

import apihp2.apihp2.entidades.Pais;

public interface IPaisServicio {

    List<Pais> listar();

    Pais buscarPorId(long id);

    Pais guardar(Pais pais);     // crea o actualiza según si el id existe o no

    void eliminar(long id);
}
