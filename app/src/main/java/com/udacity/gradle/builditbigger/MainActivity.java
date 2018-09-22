package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.jokeactivity.JokeActivity;

import java.lang.ref.WeakReference;

import static com.udacity.jokeactivity.JokeActivity.JOKE_KEY;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //noinspection unchecked
        new EndPointAsyncTask(getApplicationContext(), mProgressBar).execute();
    }


    private void startJokeActivity(String result) {
        Intent i = new Intent (MainActivity.this, JokeActivity.class);
        i.putExtra(JOKE_KEY, result);
        startActivity(i);
    }

    private void showCustomToast(String joke) {
        Toast toast = Toast.makeText(this, joke, Toast.LENGTH_LONG);
        View v = toast.getView();
        v.getBackground().setColorFilter(getResources().getColor(R.color.toastBackground), PorterDuff.Mode.SRC_IN);
        TextView text = v.findViewById(android.R.id.message);
        text.setTextColor(getResources().getColor(R.color.background));
        text.setTextSize(38);
        text.setGravity(Gravity.CENTER_HORIZONTAL);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}
