package com.wwmrak.main.fragment;

/**
 * Created by wwmrak on 7/2/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wwmrak.studentorganizer.R;

public class SpaceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] scheduleArray = {"File upload to z-drive", "File download from z-drive"};

        View rootView = inflater.inflate(R.layout.fragment_space, container, false);
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.item_textview, scheduleArray);
        ListView listView = (ListView) rootView.findViewById(R.id.space_list);
        listView.setAdapter(adapter);

        return rootView;
    }
}
