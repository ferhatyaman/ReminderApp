package com.example.reminderapp;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Event>> liveEventList;


}
