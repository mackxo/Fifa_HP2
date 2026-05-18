package apidefifa.apidefifa.controladores;

/*
 * CLASE AUXILIAR: FiltroPeriodoRequest
 * --------------------------------------------------------------
 * Representa el cuerpo (body) JSON que el cliente envía al
 * endpoint de búsqueda por periodo. Ejemplo de JSON esperado:
 *
 *   {
 *     "desde": "2018-01-01",
 *     "hasta": "2018-06-01",
 *     "sigla": "COP"
 *   }
 *
 * Spring deserializa automáticamente ese JSON en este objeto
 * cuando se usa @RequestBody en el controlador.
 * --------------------------------------------------------------
 */

public class FiltroPeriodoRequest {

    private String desde;   // fecha inicio en formato "yyyy-MM-dd"
    private String hasta;   // fecha fin   en formato "yyyy-MM-dd"
    private String sigla;   // código ISO de la moneda (ej: "COP")

    public FiltroPeriodoRequest() {}

    public String getDesde() { return desde; }
    public void setDesde(String desde) { this.desde = desde; }

    public String getHasta() { return hasta; }
    public void setHasta(String hasta) { this.hasta = hasta; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
}
