package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.model.Cart;
import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    private ImageView logo;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Init the cart object
        Common.cart = new Cart();

        logo = (ImageView) findViewById(R.id.imageViewsplash);
        tv = (TextView) findViewById (R.id.tvINTI);
        Animation myanim = AnimationUtils.loadAnimation(SplashScreen.this,
                R.anim.set);
        Animation mytvanim = AnimationUtils.loadAnimation(SplashScreen.this,
                R.anim.alpha);
        logo.startAnimation(myanim);
        tv.startAnimation(mytvanim);
        final Intent aftersplash = new Intent(SplashScreen.this,
                MainActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(aftersplash);
                    finish();
                }
            }
        };
        timer.start();
    }


}

