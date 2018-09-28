package com.udacity.gradle.builditbigger;


import android.test.AndroidTestRunner;


import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EmptyStringTest extends AndroidTestRunner implements EndPointAsyncTask.AsyncResponse {

    String Joke;

    @SuppressWarnings("unchecked")
    @Test
    public void checkForEmptyString() {
        String result = null;

        EndPointAsyncTask endpointsAsyncTask = new EndPointAsyncTask(this);
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

    @Override
    public void processFinish(String output) {
        this.Joke = output;
    }
}