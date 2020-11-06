package es.uniovi.eii.minus_covid.ui.salir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.uniovi.eii.minus_covid.R;

public class ExitFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        AlertDialog dialogo = new AlertDialog
            .Builder(getActivity()) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Hicieron click en el botón positivo, así que la acción está confirmada
                    System.exit(0);
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Hicieron click en el botón negativo, no confirmaron
                    // Simplemente descartamos el diálogo
                    dialog.dismiss();
                }
            })
            .setMessage("¿Deseas salir de la aplicación?") // El mensaje
            .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!
        dialogo.show();
        return root;
    }
}
