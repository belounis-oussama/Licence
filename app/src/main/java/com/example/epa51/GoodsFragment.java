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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends Fragment {

    public RecyclerView listOfGoods;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Bundle id=getArguments();
        String idPointage = id.getString("idPointage");
        Toast.makeText(getActivity(),idPointage,Toast.LENGTH_SHORT).show();


        View view =inflater.inflate(R.layout.goods_fragment,container,false);
        listOfGoods=view.findViewById(R.id.goodsfrahmentList);

        GoodsPointage_db db=new GoodsPointage_db(getActivity());
        List<Goods_Model> goods = db.getGood(Integer.parseInt(idPointage));


        //setting Adapter

        ArrayList<Goods_Model> listGoods=new ArrayList<>();
        listGoods.addAll(goods);


        pointageRecycleAdapter adapter = new pointageRecycleAdapter(listGoods);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        listOfGoods.setLayoutManager(layoutManager);
        listOfGoods.setItemAnimator(new DefaultItemAnimator());

        listOfGoods.setAdapter(adapter);


        return view;
    }
}
