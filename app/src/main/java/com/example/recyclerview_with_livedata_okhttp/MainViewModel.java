/** ViewModel就是專門給View呈現資料用，這邊是給mutable live data用的
 * 做getter and setter */
package com.example.recyclerview_with_livedata_okhttp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel{

    MutableLiveData<List<Post>> userLiveData;

    public MainViewModel() {
        userLiveData = new MutableLiveData<>();
    }
    public MutableLiveData<List<Post>> getUserMutableLiveData(){
        return userLiveData;
    }
    public void setPosts(List<Post> posts){
        userLiveData.postValue(posts);
    }

}
