package es.uniovi.eii.minus_covid.ui.mapa.datos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Comunidad Autónoma");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
