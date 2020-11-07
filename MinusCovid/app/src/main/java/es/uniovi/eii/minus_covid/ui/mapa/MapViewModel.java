package es.uniovi.eii.minus_covid.ui.mapa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Seleccione la comunidad autónoma");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
