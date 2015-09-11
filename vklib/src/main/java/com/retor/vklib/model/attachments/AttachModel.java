package com.retor.vklib.model.attachments;

import android.os.Parcelable;

/**
 * Created by retor on 01.09.2015.
 */
public abstract class AttachModel implements Parcelable {

    public AttachModel() {
    }

    public abstract String getType();
}
