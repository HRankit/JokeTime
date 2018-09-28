package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

class EndPointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private final WeakReference<AsyncResponse> delegate;

    public interface AsyncResponse {
        void processFinish(String output);
    }


    EndPointAsyncTask(AsyncResponse s) {
        delegate = new WeakReference<>(s);

    }

    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... params) {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
        MyApi myApiService = builder.build();


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return null;
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

        final AsyncResponse listener = delegate.get();

        if (result != null && result.length() > 0) {
            if (listener != null) {
                listener.processFinish(result);
            }
//            delegate.processFinish(result);
        } else {
            if (listener != null) {
                listener.processFinish(null);
            }
        }
    }


}