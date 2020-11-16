package es.uniovi.eii.minus_covid.util;

import android.os.Parcel;
import android.os.Parcelable;

public class ComunidadDto implements Parcelable {

    public String nombre;
    public String id;

    //totales
    public String total_casos_acumulado; //today_confirmed
    public String total_curados_acumulado; //today_recovered
    public String total_infectados_acumulado; //today_open_cases
    public String total_fallecidos_acumulado; // today_deaths
    public String total_uci_acumulado; //today_intensive_care

    //incrementos
    public String nuevos_casos; //today_new_confirmed
    public String nuevos_curados; //today_new_recovered
    public String nuevos_fallecidos; //today_new_deaths
    public String nuevos_uci; //today_new_intensive_care

    @Override
    public String toString() {
        return "ComunidadDto [nombre=" + nombre + ", total_casos_acumulado=" + total_casos_acumulado
                + ", total_curados_acumulado=" + total_curados_acumulado + ", total_infectados_acumulado="
                + total_infectados_acumulado + ", total_fallecidos_acumulado=" + total_fallecidos_acumulado
                + ", total_uci_acumulado=" + total_uci_acumulado + ", nuevos_casos=" + nuevos_casos
                + ", nuevos_curados=" + nuevos_curados + ", nuevos_fallecidos=" + nuevos_fallecidos + ", nuevos_uci="
                + nuevos_uci + "]";
    }

    public ComunidadDto(){}

    public ComunidadDto(Parcel in) {
        this.nombre = in.readString();
        this.total_casos_acumulado = in.readString();
        this.total_curados_acumulado = in.readString();
        this.total_infectados_acumulado = in.readString();
        this.total_fallecidos_acumulado = in.readString();
        this.total_uci_acumulado = in.readString();
        this.nuevos_casos = in.readString();
        this.nuevos_curados = in.readString();
        this.nuevos_fallecidos = in.readString();
        this.nuevos_uci = in.readString();
    }

    public ComunidadDto(String nombre, String total_casos_acumulado, String total_curados_acumulado, String total_infectados_acumulado,
                        String total_fallecidos_acumulado, String total_uci_acumulado, String nuevos_casos, String nuevos_curados,
                        String nuevos_fallecidos, String nuevos_uci) {
        this.nombre = nombre;
        this.total_casos_acumulado = total_casos_acumulado;
        this.total_curados_acumulado = total_curados_acumulado;
        this.total_infectados_acumulado = total_infectados_acumulado;
        this.total_fallecidos_acumulado = total_fallecidos_acumulado;
        this.total_uci_acumulado = total_uci_acumulado;
        this.nuevos_casos = nuevos_casos;
        this.nuevos_curados = nuevos_curados;
        this.nuevos_fallecidos = nuevos_fallecidos;
        this.nuevos_uci = nuevos_uci;
    }

    public ComunidadDto(String total_casos_acumulado, String total_curados_acumulado, String total_infectados_acumulado,
                        String total_fallecidos_acumulado, String total_uci_acumulado, String nuevos_casos,
                        String nuevos_curados, String nuevos_fallecidos, String nuevos_uci) {
        this.total_casos_acumulado = total_casos_acumulado;
        this.total_curados_acumulado = total_curados_acumulado;
        this.total_infectados_acumulado = total_infectados_acumulado;
        this.total_fallecidos_acumulado = total_fallecidos_acumulado;
        this.total_uci_acumulado = total_uci_acumulado;
        this.nuevos_casos = nuevos_casos;
        this.nuevos_curados = nuevos_curados;
        this.nuevos_fallecidos = nuevos_fallecidos;
        this.nuevos_uci = nuevos_uci;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(total_casos_acumulado);
        dest.writeString(total_curados_acumulado);
        dest.writeString(total_infectados_acumulado);
        dest.writeString(total_fallecidos_acumulado);
        dest.writeString(total_uci_acumulado);
        dest.writeString(nuevos_casos);
        dest.writeString(nuevos_curados);
        dest.writeString(nuevos_fallecidos);
        dest.writeString(nuevos_uci);
    }

    public static final Creator<ComunidadDto> CREATOR = new Creator<ComunidadDto>() {
        @Override
        public ComunidadDto createFromParcel(Parcel in) {
            return new ComunidadDto(in);
        }

        @Override
        public ComunidadDto[] newArray(int size) {
            return new ComunidadDto[size];
        }
    };
}
