package apihp2.apihp2.interfaces;

import java.util.List;

import apihp2.apihp2.entidades.Moneda;

public interface IMonedaServicio {

    List<Moneda> listar();

    Moneda buscarPorId(long id);

    Moneda guardar(Moneda moneda);   // sirve tanto para crear como para actualizar

    void eliminar(long id);
}
