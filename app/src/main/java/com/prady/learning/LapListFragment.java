package com.prady.learning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class LapListFragment extends ListFragment {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lap_list,container , false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getListView();
        arrayList = new ArrayList<String>(Arrays.asList(new String[]{}));
        arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.simple_list_item,arrayList);
        listView.setAdapter(arrayAdapter);
        arrayList.add("    LAP TIME  ");
    }

    public void addLap(int minutes, int seconds, int millisecconds)
    {
        Log.i("LAP",""+minutes+" : "+seconds+" : "+millisecconds);
        arrayAdapter.add(""+minutes+" : "+seconds+" : "+millisecconds);

    }
}
