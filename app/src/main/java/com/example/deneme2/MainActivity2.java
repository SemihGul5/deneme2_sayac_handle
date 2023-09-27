package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView textView,textView3,textViewTimer;
    Runnable runnable;
    Handler handler;
    int number;
    Button buttonBaslat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textViewTimer=findViewById(R.id.textViewTimer);
        buttonBaslat=findViewById(R.id.buttonBaslat);
        number=0;


        //diğer sayfadan veri almak için;
        Intent intent = getIntent();
        String userName=intent.getStringExtra("userName");
        textView.setText(userName);

        //Timer
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textView3.setText("Kalan: "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity2.this,"Süre Bitti!",Toast.LENGTH_LONG).show();
            }
        }.start();


    }
    public void goTo1nd(View view){
        Intent intent= new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    //runnable (kronometre) örnek
    public void timerStart(View view){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                textViewTimer.setText(String.valueOf(number));
                number++;
                handler.postDelayed(this,1000);//ne kadar gecikme olacak. burada 1 saniye
            }
        };
        handler.post(runnable);//burayı unutma!
        buttonBaslat.setEnabled(false);

    }
    public void timerStop(View view){
        buttonBaslat.setEnabled(true);
        handler.removeCallbacks(runnable);//runnable durdurma kodu!!!
        textViewTimer.setText(String.valueOf(number));
        number=0;

    }
}