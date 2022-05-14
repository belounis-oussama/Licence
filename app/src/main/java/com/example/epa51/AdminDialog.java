package com.example.epa51;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AdminDialog extends AppCompatDialogFragment {


    EditText passwordField;


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

                Admin_db admin_db =new Admin_db(getActivity());
                String lastAdminPassword = admin_db.getLastAdminPassword();

                if (lastAdminPassword != null)
                {



                if (passwordField.getText().toString().equals(lastAdminPassword))
                {


                    Intent intent=new Intent(getActivity(),admin_dashboard.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(),"mot de passe incorrect",Toast.LENGTH_SHORT).show();
                }

                }

                else
                {

                    if (passwordField.getText().toString().equals("EPA"))
                    {


                        Intent intent=new Intent(getActivity(),admin_dashboard.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"mot de passe incorrect",Toast.LENGTH_SHORT).show();
                    }
                }

                }





        });


        return builder.create();
    }
}
