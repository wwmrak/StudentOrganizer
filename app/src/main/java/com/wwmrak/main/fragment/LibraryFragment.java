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
import com.wwmrak.main.library.DownloadActivity;

public class LibraryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] libraryArray = {"Download eboook"};

        View rootView = inflater.inflate(R.layout.fragment_library, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.item_textview, libraryArray);
        ListView listView = (ListView) rootView.findViewById(R.id.library_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) startActivity(new Intent(getActivity(), DownloadActivity.class));
            }
        });

        return rootView;
    }
}
