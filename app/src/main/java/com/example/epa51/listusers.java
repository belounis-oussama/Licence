package com.example.epa51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

public class listusers extends AppCompatActivity {

    private ListView userslistview;
    ImageButton backbtn;
    public Toolbar menu;





    private void setUserAdapter() {

        User_db db=new User_db(listusers.this);


           UserAdapter userAdapter=new UserAdapter(getApplicationContext(),db.getall());

        userslistview.setAdapter(userAdapter);
        userslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(listusers.this,modify_delete_user.class);
                String name= userslistview.getAdapter().getItem(i).toString();

                String id = name.substring(name.indexOf("id=") + "id=".length(), name.indexOf(","));


                intent.putExtra("idkey",id);


                startActivity(intent);
            }
        });



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listusers);
        initWidget();
        setUserAdapter();



        backbtn=findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(listusers.this,admin_dashboard.class);
                startActivity(intent);
                finish();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_listuser,menu);
        MenuItem menuItem= menu.findItem(R.id.action_search);

        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type the user you want to find");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {



                User_db db=new User_db(listusers.this);


                UserAdapter userAdapter=new UserAdapter(getApplicationContext(),db.getall());
                //UserAdapter userAdapter=new UserAdapter(getApplicationContext(),User_Model.user_modelArrayList);
                userslistview.setAdapter(userAdapter);

                userAdapter.getFilter().filter(newText);



                return false;
            }
        });
        return true;
    }

    private void initWidget()
    {
        userslistview =findViewById(R.id.listView);
        menu=findViewById(R.id.action_search);

    }






    public void newuser(View view) {
        Intent newuserintent =new Intent(this,userDetails.class);
        startActivity(newuserintent);
    }
}