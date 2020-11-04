package com.example.simplificar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class IntroActivity extends AppCompatActivity {
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler1 = new Handler(), handler2 = new Handler(), handler3 = new Handler();
        imgLogo = (ImageView) findViewById(R.id.imgLogo);

        // tarefa postergada por 3500 milissegundos
        handler1.postDelayed(new Runnable() {
            public void run() {
                Intent it = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(it);
                finish();
            }
        }, 3500);

        // tarefa postergada por 500 milissegundos
        handler2.postDelayed(new Runnable() {
            public void run() {
                imgLogo.setVisibility(View.VISIBLE);
                //animação 03
                YoYo.with(Techniques.FadeIn)
                        .duration(1700)
                        .repeat(0)
                        .playOn(findViewById(R.id.imgLogo));

            }
        }, 500);
    }
}