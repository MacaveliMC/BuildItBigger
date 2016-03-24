package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.michaelcavalli.jokeactivity.JokeActivityMain;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements callBackTask {

    private String myJoke;
    private EndpointsAsyncTask myTask;
    public ProgressBar progress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Set up progress bar
        progress = (ProgressBar) root.findViewById(R.id.progressBar);
        progress.setVisibility(View.GONE);

        // set up joke button click listener
        Button jokeButton = (Button) root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJokeActivity();
            }
        });

        return root;
    }

    /**
     * Tells the asynctask to grab a new joke, and shows the progress indicator
     */
    public void startJokeActivity() {
        myTask = new EndpointsAsyncTask();
        myTask.myCallBack = this;
        progress.setVisibility(View.VISIBLE);
        myTask.execute(new Pair<Context, String>(getActivity(), "Manfred"));
    }

    /**
     * Sets the intent for the joke activity and starts it.  Removes the progress indicator as well.
     */
    public void gotData() {
        Intent intent = new Intent(getActivity(), JokeActivityMain.class);
        intent.putExtra(JokeActivityMain.JOKE_KEY, myJoke);
        progress.setVisibility(View.GONE);
        startActivity(intent);
    }

    /**
     * the callback the asynctask uses to let the activity know it has the joke
     */
    @Override
    public void myCallBack(String myJ) {
        if (myJ != null) {
            myJoke = myJ;
            gotData();
        }
    }
}
