package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.widget.Button;

import com.udacity.gradle.builditbigger.MainActivity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private final String LOG_TAG = getClass().getSimpleName();
    private Activity application;
    EndpointsAsyncTask newTask;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        application = getActivity();
        newTask = new EndpointsAsyncTask();
    }

    /**
     * This test will make sure the value the async task retrieves is not null
     */
    @MediumTest
    public void testAsynctask(){
        String result = null;
        newTask = new EndpointsAsyncTask();
        newTask.execute(new Pair<Context, String>(application.getApplicationContext(), "String"));
        try {
            result = newTask.get();
        } catch(InterruptedException e){
            Log.v(LOG_TAG, "Interrupted Exception: ");
            e.printStackTrace();
        } catch (ExecutionException e){
            Log.v(LOG_TAG, "Execution Exception: ");
            e.printStackTrace();
        }
        assertNotNull(result);
    }

}