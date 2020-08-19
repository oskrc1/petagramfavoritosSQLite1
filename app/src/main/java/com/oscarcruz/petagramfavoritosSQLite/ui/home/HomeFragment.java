package com.oscarcruz.petagramfavoritosSQLite.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oscarcruz.petagramfavoritosSQLite.GatoAdapter;
import com.oscarcruz.petagramfavoritosSQLite.GatoItem;
import com.oscarcruz.petagramfavoritosSQLite.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<GatoItem> gatoItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new GatoAdapter(gatoItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        gatoItems.add(new GatoItem(R.drawable.cat1, "Snow","0","0"));
        gatoItems.add(new GatoItem(R.drawable.cat2, "Tiger","1","0"));
        gatoItems.add(new GatoItem(R.drawable.cat3, "Copito","2","0"));
        gatoItems.add(new GatoItem(R.drawable.cat4, "Sunny","3","0"));
        gatoItems.add(new GatoItem(R.drawable.cat5, "Anubis","4","0"));
        gatoItems.add(new GatoItem(R.drawable.cat6, "Tizón","5","0"));
        gatoItems.add(new GatoItem(R.drawable.cat7, "Bonnie","6","0"));


        return root;
    }
}