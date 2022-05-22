package com.example.epa51;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.button.MaterialButton;

public class FinishedDialog  extends AppCompatDialogFragment {

    MaterialButton confirmer;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.pointage_finished,null);

        confirmer=view.findViewById(R.id.confimefinishpointage);


        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),login.class);
                startActivity(intent);
                getActivity().finish();



            }
        });

        builder.setView(view);





        return builder.create();
    }
}
