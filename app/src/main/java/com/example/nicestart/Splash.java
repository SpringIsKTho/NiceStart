package com.example.nicestart;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView mSea = findViewById(R.id.backView);

        Glide.with(this)
                .load(getDrawable(R.drawable.loginimage))
                //.load(R.drawable.splashimage)
                //.load("https://images.unsplash.com/photo-1489424731084-a5d8b219a5bb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1974&q=80")
                .transition(DrawableTransitionOptions.withCrossFade(100))
                //.centerCrop()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.transparent)))
                .into(mSea);

        ImageView wave = findViewById(R.id.logo);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.blink);
        Animation myanim2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        wave.startAnimation(myanim);
        wave.startAnimation(myanim2); //Animaciones de la carpeta anim, alterar en caso de que se pida.

        openApp();
    }

    private void openApp(){
        Handler handler = new Handler(); //Handler hace que se espere antes de abrir la otra aplicacion.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { //run para abrir el login.
                Intent intent = new Intent(Splash.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 5000);
    }
}