package es.uniovi.eii.minus_covid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import es.uniovi.eii.minus_covid.ui.about.AboutFragment;
import es.uniovi.eii.minus_covid.ui.general.GeneralFragment;
import es.uniovi.eii.minus_covid.ui.home.HomeFragment;
import es.uniovi.eii.minus_covid.ui.mapa.MapFragment;

public class MainActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,R.id.nav_general,
                R.id.nav_map, R.id.nav_salir)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.popBackStack();
                    int id = menuItem.getItemId();
                    if (id == R.id.nav_salir) {
                        AlertDialog dialogo = new AlertDialog
                                .Builder(this)
                                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setMessage("¿Deseas salir de la aplicación?").create();
                        dialogo.show();
                    }else if (id == R.id.nav_about) {
                        fragmentManager.beginTransaction().replace(R.id.container_main, new AboutFragment()).commit();
                    }else if (id == R.id.nav_home) {
                        fragmentManager.beginTransaction().replace(R.id.container_main, new HomeFragment()).commit();
                    }else if (id == R.id.nav_general) {
                        fragmentManager.beginTransaction().replace(R.id.container_main, new GeneralFragment()).commit();
                    }else if (id == R.id.nav_map) {
                        fragmentManager.beginTransaction().replace(R.id.container_main, new MapFragment()).commit();
                    }
                    DrawerLayout drawerA = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawerA.closeDrawer(GravityCompat.START);
                    return true;
                }
        );

        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);


    }
    /*
    private boolean hayInternet() {
        try {
            Process process = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int valor = process.waitFor();
            return (valor == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean hayConexionAInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected());
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}