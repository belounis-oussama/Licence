package com.example.epa51;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AdminDialog extends AppCompatDialogFragment {


    EditText passwordField;

    private String TITLE_COLOR = "#000080";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.password_admin,null);

        passwordField=view.findViewById(R.id.password_admin);


        builder.setView(view)
                .setTitle("Veuillez entrer le mot de passe d'administration" +
                        "" +
                        "" )
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })

        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        return builder.create();
    }
}
