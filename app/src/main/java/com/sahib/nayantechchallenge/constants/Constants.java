package com.sahib.nayantechchallenge.constants;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sahib.nayantechchallenge.R;

public class Constants {

    private static String BASE_URL = "https://api.github.com/";

    public static String getBASE_URL() {
        return BASE_URL;
    }

    public static DisplayImageOptions IMG_OPTIONS = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.ic_image_error)
            .showImageOnFail(R.drawable.ic_image_error)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public static final String KEY_INTENT_REPO = "KEY_INTENT_REPO";
}
