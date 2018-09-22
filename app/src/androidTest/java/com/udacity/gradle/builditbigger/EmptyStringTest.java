package com.udacity.gradle.builditbigger;


import android.test.AndroidTestRunner;


import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.assertNotNull;

public class EmptyStringTest extends AndroidTestRunner {

    @SuppressWarnings("unchecked")
    @Test
    public void checkForEmptyString() {
        String result = null;
        EndPointAsyncTask endpointsAsyncTask = new EndPointAsyncTask(getContext(), null);
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}