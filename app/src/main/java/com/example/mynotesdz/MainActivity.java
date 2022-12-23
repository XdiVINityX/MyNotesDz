package com.example.mynotesdz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if(savedInstanceState == null){
            NotesRecycleFragment notesRecycleFragment = new NotesRecycleFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_recycle,notesRecycleFragment)
                    .commit();
        }*/


       if (savedInstanceState == null) {
            NotesFragment notesFragment = new NotesFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_list, notesFragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            MenuItem item = menu.findItem(R.id.menu_about_program);
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case  R.id.menu_about_program:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("")
                        .add(R.id.fragment_container_description, new AboutProgrammFragment())
                        .commit();

                return true;

            case R.id.menu_exit:
                showAlertDialogExit();
                return true;

            case R.id.menu_add:
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Тут будет добавление заметки", Toast.LENGTH_SHORT);
                toast.show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void showAlertDialogExit(){
       new  AlertDialog.Builder(this)
               .setTitle("Выход")
               .setMessage("Вы точно хотите выйти?")
               .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Вы вышли из приложения",Toast.LENGTH_LONG)
                              .show();

                       finish();

                   }
               })
               .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                   }
               })
               .setNeutralButton("Отмена",null)
               .show();


    }
}



