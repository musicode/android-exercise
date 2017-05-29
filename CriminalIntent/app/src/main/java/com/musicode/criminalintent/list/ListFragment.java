package com.musicode.criminalintent.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.musicode.criminalintent.R;
import com.musicode.criminalintent.model.Crime;
import com.musicode.criminalintent.model.CrimeLab;

import java.util.List;

public class ListFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;

    public ListFragment() {

    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ListAdapter(getActivity(), CrimeLab.getInstance().getAll());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        List<Crime> list = CrimeLab.getInstance().getAll();
        mAdapter.notifyDataSetChanged();
    }

}
