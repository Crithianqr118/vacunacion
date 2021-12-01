package com.example.vacunacionshark.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.vacunacionshark.R;
import com.example.vacunacionshark.clases.EnlaceMenu;
import com.example.vacunacionshark.fragmentos.CarnetVacunacionFragment;
import com.example.vacunacionshark.fragmentos.ConfiguracionFragment;
import com.example.vacunacionshark.fragmentos.InicioFragment;
import com.example.vacunacionshark.fragmentos.UbicacionFragment;
import com.example.vacunacionshark.fragmentos.VacunasSugeridasFragment;

public class MenuActivity extends AppCompatActivity implements EnlaceMenu {
    Fragment[] fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fragments = new Fragment[5];
        fragments[0] = new InicioFragment();
        fragments[1] = new CarnetVacunacionFragment();
        fragments[2] = new VacunasSugeridasFragment();
        fragments[3] = new UbicacionFragment();
        fragments[4] = new ConfiguracionFragment();

    }

    @Override
    public void menu(int request_button) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rl_menu, fragments[request_button]);
        fragmentTransaction.commit();
    }
}