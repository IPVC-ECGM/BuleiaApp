package ecgm.app.buleia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.Transaction;

import ecgm.app.buleia.R;

public class SplashScreenComplete extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreencomplete);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar(). hide();


        lottieAnimationView = findViewById(R.id.lottie);
        lottieAnimationView.animate().setDuration(8500);

        View decorView = getWindow().getDecorView();

    //Esconder barra de navegação nativa do android
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        new Handler().postDelayed(new Runnable(){
           @Override
            public void run(){
               startActivity(new Intent(SplashScreenComplete.this, LoginActivity.class));
           }
        }, 8500);
    }
}
