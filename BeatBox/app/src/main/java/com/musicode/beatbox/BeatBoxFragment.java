package com.musicode.beatbox;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeatBoxFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BeatBox mBeatBox;

    public BeatBoxFragment() {
        // Required empty public constructor
    }

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setAdapter(new BeatBoxAdapter(mBeatBox.getSounds()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    class SoundHolder extends RecyclerView.ViewHolder {

        private Button mButton;
        private Sound mSound;

        public SoundHolder(View view) {
            super(view);
            mButton = (Button) view.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBeatBox.play(mSound);
                }
            });
        }

        public void bind(Sound sound) {
            mSound = sound;
            mButton.setText(sound.getName());
        }

    }

    class BeatBoxAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public BeatBoxAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_sound, parent, false);
            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            holder.bind(mSounds.get(position));
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }

    }

}
