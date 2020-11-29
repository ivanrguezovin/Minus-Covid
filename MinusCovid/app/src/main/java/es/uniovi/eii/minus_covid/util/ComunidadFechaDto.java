package es.uniovi.eii.minus_covid.util;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ComunidadFechaDto implements Parcelable {

    public List<ComunidadDto> listaFechas;

    public ComunidadFechaDto() {
    }

    public ComunidadFechaDto(List<ComunidadDto> lista) {
        this.listaFechas=lista;
    }

    public ComunidadFechaDto(Parcel in) {
        listaFechas = new ArrayList<ComunidadDto>();
        in.readList(listaFechas, ComunidadDto.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listaFechas);

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
