package com.musicode.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {

    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds;
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSounds = new ArrayList<>();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            for (String filename : soundNames) {
                Sound sound = new Sound(SOUNDS_FOLDER + "/" + filename);
                load(sound);
                mSounds.add(sound);
            }
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        }
        catch (IOException e) {
            Log.e(TAG, "Could not list assets", e);
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        sound.setId(mSoundPool.load(afd, 1));
    }

    public void play(Sound sound) {
        Integer id = sound.getId();
        if (id == null) {
            return;
        }
        mSoundPool.play(id, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release() {
        mSoundPool.release();
    }

}
