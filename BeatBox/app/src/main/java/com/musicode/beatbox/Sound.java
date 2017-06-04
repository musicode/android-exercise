package com.musicode.beatbox;

public class Sound {

    private String mAssetPath;
    private String mName;
    private Integer mId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] parts = assetPath.split("/");
        String filename = parts[ parts.length - 1 ];
        mName = filename.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
