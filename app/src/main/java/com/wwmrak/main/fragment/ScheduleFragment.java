package com.wwmrak.main.fragment;

/**
 * Created by wwmrak on 7/2/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wwmrak.studentorganizer.R;
import com.wwmrak.main.schedule.EventActivity;
import com.wwmrak.main.schedule.ViewScheduleActivity;

public class ScheduleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] libraryArray = {"Create event", "View schedule"};

        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.item_textview, libraryArray);
        ListView listView = (ListView) rootView.findViewById(R.id.library_list2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) startActivity(new Intent(getActivity(), EventActivity.class));
                if (position == 1)
                    startActivity(new Intent(getActivity(), ViewScheduleActivity.class));
            }
        });
        return rootView;
    }
}