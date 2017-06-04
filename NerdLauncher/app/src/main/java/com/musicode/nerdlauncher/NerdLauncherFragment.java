package com.musicode.nerdlauncher;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class NerdLauncherFragment extends Fragment {

    private static final String TAG = "NerdLauncherFragment";
    private RecyclerView mRecyclerView;

    public NerdLauncherFragment() {
        // Required empty public constructor
    }

    public static NerdLauncherFragment newInstance() {
        return new NerdLauncherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nerd_launcher, container, false);
        mRecyclerView = (RecyclerView) view;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();

        return view;
    }

    private void setupAdapter() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);

        Collections.sort(
                activities,
                new Comparator<ResolveInfo>() {
                    @Override
                    public int compare(ResolveInfo a, ResolveInfo b) {
                        PackageManager pm = getActivity().getPackageManager();
                        return String.CASE_INSENSITIVE_ORDER.compare(
                                a.loadLabel(pm).toString(),
                                b.loadLabel(pm).toString()
                        );
                    }
                }
        );

        mRecyclerView.setAdapter(new Adapter(activities));

        Log.i(TAG, "Found" + activities.size() + " activities.");

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextView;
        private ResolveInfo mActivity;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view;
            mTextView.setOnClickListener(this);
        }

        public void bind(ResolveInfo activity) {
            mActivity = activity;
            mTextView.setText(activity.loadLabel(getActivity().getPackageManager()));
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName(mActivity.activityInfo.packageName, mActivity.activityInfo.name);
            startActivity(intent);
        }
    }


    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<ResolveInfo> mList;

        public Adapter(List<ResolveInfo> list) {
            mList = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }



}
