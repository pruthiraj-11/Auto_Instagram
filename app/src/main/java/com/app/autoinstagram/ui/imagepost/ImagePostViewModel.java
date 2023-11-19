package com.app.autoinstagram.ui.imagepost;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImagePostViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ImagePostViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}