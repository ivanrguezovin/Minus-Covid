package es.uniovi.eii.minus_covid.util;

import android.os.Parcel;
import android.os.Parcelable;

public class ComunidadDto implements Parcelable {

    public String nombre;
    public String id;

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
    public int nuevos_uci; //today_new_intensive_care

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
        this.total_casos_acumulado = in.readInt();
        this.total_curados_acumulado = in.readInt();
        this.total_infectados_acumulado = in.readInt();
        this.total_fallecidos_acumulado = in.readInt();
        this.total_uci_acumulado = in.readInt();
        this.nuevos_casos = in.readInt();
        this.nuevos_curados = in.readInt();
        this.nuevos_fallecidos = in.readInt();
        this.nuevos_uci = in.readInt();
    }

    public ComunidadDto(String nombre, int total_casos_acumulado, int total_curados_acumulado, int total_infectados_acumulado,
                        int total_fallecidos_acumulado, int total_uci_acumulado, int nuevos_casos, int nuevos_curados,
                        int nuevos_fallecidos, int nuevos_uci) {
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

    public ComunidadDto(int total_casos_acumulado, int total_curados_acumulado, int total_infectados_acumulado,
                        int total_fallecidos_acumulado, int total_uci_acumulado, int nuevos_casos,
                        int nuevos_curados, int nuevos_fallecidos, int nuevos_uci) {
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
        dest.writeInt(total_casos_acumulado);
        dest.writeInt(total_curados_acumulado);
        dest.writeInt(total_infectados_acumulado);
        dest.writeInt(total_fallecidos_acumulado);
        dest.writeInt(total_uci_acumulado);
        dest.writeInt(nuevos_casos);
        dest.writeInt(nuevos_curados);
        dest.writeInt(nuevos_fallecidos);
        dest.writeInt(nuevos_uci);
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
