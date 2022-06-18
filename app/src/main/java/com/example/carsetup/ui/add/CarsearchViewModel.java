package com.example.carsetup.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CarsearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CarsearchViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}