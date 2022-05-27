package com.example.epa51;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class PointageBaics extends Fragment{
TextView navire,pointeur,date,mode_conditionement,nature,brigade,shift,quai;










    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle id=getArguments();
        String idPointage = id.getString("idPointage");

        Pointage_db db=new Pointage_db(getActivity());
        Pointage_Model pointageData = db.getPointageData(Integer.parseInt(idPointage));

        View view =inflater.inflate(R.layout.pointagebasics,container,false);
        navire=view.findViewById(R.id.navire_details);
        pointeur=view.findViewById(R.id.pointeur_details);
        date=view.findViewById(R.id.date_details);
        mode_conditionement=view.findViewById(R.id.mode_conditionement_details);
        nature=view.findViewById(R.id.nature_details);
        brigade=view.findViewById(R.id.brigade_details);
        shift=view.findViewById(R.id.shift_details);
        quai=view.findViewById(R.id.quai_details);





        navire.setText("Navire : "+pointageData.getNom_navire());
        pointeur.setText("pointeur : "+pointageData.getNom_pointeur());
        date.setText("date : "+pointageData.getDate());
        mode_conditionement.setText("mode_conditionement : "+pointageData.getMode_conditionnement());
        nature.setText("nature : "+pointageData.getNature_marchandise());
        brigade.setText("brigade : "+pointageData.getBrigade());
        shift.setText("shift : "+pointageData.getShift());
        quai.setText("quai : "+pointageData.getQuai());










        return view;










    }


}
