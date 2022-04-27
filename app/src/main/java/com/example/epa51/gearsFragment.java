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

public class gearsFragment extends Fragment {

    public ListView listOfGear;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle id=getArguments();
        String idPointage = id.getString("idPointage");
        Toast.makeText(getActivity(),idPointage,Toast.LENGTH_SHORT).show();


        View view =inflater.inflate(R.layout.gear_fragment,container,false);
        listOfGear=view.findViewById(R.id.gearfrahmentList);

        Gears_db db=new Gears_db(getActivity());
        GearPointage_db GPdb=new GearPointage_db(getActivity());
        List<Integer> gearUsed = GPdb.getGearUsed(Integer.parseInt(idPointage));

        List<Gear_Model> gears=new ArrayList<>();
        for (int i=0;i<gearUsed.size();i++)
        {
        gears.add(db.getGearInfo(gearUsed.get(i)));
        }

        GearAdapter gearAdapter=new GearAdapter(getContext(),gears);
        listOfGear.setAdapter(gearAdapter);

        return view;
    }


}
