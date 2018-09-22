package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.jokeactivity.JokeActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;

import static com.udacity.jokeactivity.JokeActivity.JOKE_KEY;

class EndPointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {


    private String mResult;

    private WeakReference<ProgressBar> mProgressBar;
    private WeakReference<Context> mContext;

    EndPointAsyncTask(Context context, ProgressBar progressBar) {
        this.mContext = new WeakReference<>(context);
        this.mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.get().setVisibility(View.VISIBLE);
        }
    }

    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... params) {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
        MyApi myApiService = builder.build();


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (mProgressBar != null) {
            mProgressBar.get().setVisibility(View.GONE);
        }
        if (result != null && result.length() > 0) {
            mResult = result;
            startJokeDisplayActivity();
        }
    }

    private void startJokeDisplayActivity() {
        Intent intent = new Intent(mContext.get(), JokeActivity.class);
        intent.putExtra(JOKE_KEY, mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.get().startActivity(intent);
    }

}