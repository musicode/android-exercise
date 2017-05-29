package com.musicode.criminalintent.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.musicode.criminalintent.R;
import com.musicode.criminalintent.detail.DetailActivity;
import com.musicode.criminalintent.model.Crime;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Crime> mList;

    public ListAdapter(Context context, List<Crime> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
            mLayoutInflater.inflate(R.layout.list_row, null)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Crime mCrime;
        public TextView mTitleView;
        public TextView mDateView;
        public CheckBox mSolvedCheckBox;

        public ViewHolder(View view) {
            super(view);
            mTitleView =  (TextView) view.findViewById(R.id.title_text);
            mDateView =  (TextView) view.findViewById(R.id.date_text);
            mSolvedCheckBox = (CheckBox) view.findViewById(R.id.solved_checkbox);
            view.setOnClickListener(this);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleView.setText(crime.getTitle());
            mDateView.setText(crime.getDate().toString());
            mSolvedCheckBox.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = DetailActivity.newIntent(context, mCrime.getId());
            context.startActivity(intent);
        }
    }
}
