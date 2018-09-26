package com.udacity.jokeactivity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);



        TextView jokeDisplay_tv = findViewById(R.id.jokeDisplay);

        if (getIntent() != null){
            jokeDisplay_tv.setText(getIntent().getStringExtra(JOKE_KEY));
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }
}
