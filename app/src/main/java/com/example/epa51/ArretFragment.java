package com.example.epa51;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ArretFragment extends Fragment {

    public ListView listOfArrets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        Bundle id=getArguments();
        String idPointage = id.getString("idPointage");




        View view =inflater.inflate(R.layout.arrets_fragment,container,false);
        listOfArrets=view.findViewById(R.id.arretfrahmentList);

        ArretPointage_db db=new ArretPointage_db(getActivity());
        List<EtasArret_Model> arrets = db.getArret(Integer.parseInt(idPointage));




        PointageArretAdapter pointageArretAdapter=new PointageArretAdapter(getActivity(),arrets);
        listOfArrets.setAdapter(pointageArretAdapter);



        return view;
    }
}
