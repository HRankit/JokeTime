package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.jokeactivity.JokeActivity;
import com.udacity.jokeprovider.ProvideJokes;

import java.io.IOException;

import static com.udacity.jokeactivity.JokeActivity.JOKE_KEY;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        new EndpointsAsyncTask().execute();
    }

    public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
        private MyApi myApiService = null;


        @Override
        protected String doInBackground(String... strings) {
            if(myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();
            }


            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            startJokeActivity(result);
//            showCustomToast(result);
        }
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
