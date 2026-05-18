package apidefifa.apidefifa.interfaces;

import java.util.List;

import apidefifa.apidefifa.entidades.Pais;

public interface IPaisServicio {

    List<Pais> listar();

    Pais buscarPorId(long id);

    Pais guardar(Pais pais);     // crea o actualiza según si el id existe o no

    void eliminar(long id);
}
