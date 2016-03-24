package com.michaelcavalli.jokeactivity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityMainFragment extends Fragment {

    public JokeActivityMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke_activity_main, container, false);

        // retrieve the joke from the intent extra
        Intent intent = getActivity().getIntent();
        String myJoke = intent.getStringExtra(JokeActivityMain.JOKE_KEY);

        // find the textview and set it's text to the joke
        TextView jtView = (TextView) root.findViewById(R.id.jokeTextView);
        jtView.setText(myJoke);

        return root;
    }
}
