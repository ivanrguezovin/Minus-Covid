package es.uniovi.eii.minus_covid.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.uniovi.eii.minus_covid.R;

public class AboutFragment extends Fragment {

    TextView fuente;
    TextView about;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        fuente = root.findViewById(R.id.fuente);
        fuente.setText("Narrativa.com\n" +
                "Ministerio de Sanidad\n" +
                "Dipartimento della Protezione Civile de Italia\n" +
                "Robert Koch Institute de Alemania\n" +
                "Santé publique France\n" +
                "Johns Hopkins University");
        about = root.findViewById(R.id.about);
        about.setText("Sobre nosotros");
        return root;
    }
}