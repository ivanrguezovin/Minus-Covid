package es.uniovi.eii.minus_covid.util;

public class ComunidadDto {

    public String nombre;

    //totales
    public int total_casos_acumulado; //today_confirmed
    public int total_curados_acumulado; //today_recovered
    public int total_infectados_acumulado; //today_open_cases
    public int total_fallecidos_acumulado; // today_deaths
    public int total_uci_acumulado; //today_intensive_care

    //incrementos
    public int nuevos_casos; //today_new_confirmed
    public int nuevos_curados; //today_new_recovered
    public int nuevos_fallecidos; //today_new_deaths
    public int nuevos_uci; //today_new_invensive_care
    
}
