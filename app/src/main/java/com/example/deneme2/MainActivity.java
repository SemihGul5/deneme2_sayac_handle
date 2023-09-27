package com.example.deneme2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;
    EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editTextNumber);
        textView=findViewById(R.id.textView);
        nameText=findViewById(R.id.editNameText);

        sharedPreferences=this.getSharedPreferences("com.example.myapplication1", Context.MODE_PRIVATE);
        int storedAge=sharedPreferences.getInt("storedAge",0);
        if(storedAge==0){
            textView.setText("Your Age: ");
        }
        else{
            textView.setText("Your Age: "+storedAge);
        }
        // buradaki MainActivity.this yerine this de kullanabilirdim çünkü direk class'ıma referans alabilir burada
        //yada getApplicationContext() kullanıp genel tanımlayabilirdim

        Toast.makeText(MainActivity.this,"Uygulama Başlatıldı",Toast.LENGTH_LONG);
    }

    public void save(View view){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("KAYDET");
        alert.setMessage("Emin misin?");
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!editText.getText().toString().matches("")) {
                    int userAge = Integer.parseInt(editText.getText().toString());
                    textView.setText("Your Age: " + userAge);
                    sharedPreferences.edit().putInt("storedAge", userAge).apply();

                    Toast.makeText(MainActivity.this, "Başarılı", Toast.LENGTH_LONG).show();
                }
            }
        });

        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // burada this çalışmaz. ya MainActivity.this yada getApplicationContext() olmalıdır.
                Toast.makeText(MainActivity.this,"Başarısız",Toast.LENGTH_LONG).show();
            }
        });
        alert.show();

    }



    public void delete(View view){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("Sil");
        alert.setMessage("Emin misin?");
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int storedData;
                storedData=sharedPreferences.getInt("storedAge",0);
                if(storedData!=0){
                    sharedPreferences.edit().remove("storedAge").apply();
                    textView.setText("Your Age: ");
                    Toast.makeText(MainActivity.this,"Başarılı",Toast.LENGTH_LONG).show();
                }
            }
        });
        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Başarısız",Toast.LENGTH_LONG).show();
            }
        });

        alert.show();


    }

    public void goTo2nd(View view){

        String userName= nameText.getText().toString();

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("userName",userName);

        startActivity(intent);
    }
}


