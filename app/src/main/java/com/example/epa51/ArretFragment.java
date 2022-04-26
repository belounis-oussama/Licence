package com.example.epa51;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ArretFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        Bundle id=getArguments();
        String idPointage = id.getString("idPointage");
        Toast.makeText(getActivity(),idPointage,Toast.LENGTH_SHORT).show();
        





        return inflater.inflate(R.layout.arrets_fragment,container,false);
    }
}
