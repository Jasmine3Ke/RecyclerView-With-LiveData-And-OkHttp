/** 處理首頁按下"show cryptocurrency"鍵之後的activity */
package com.example.recyclerview_with_livedata_okhttp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    MainViewModel viewModel;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    MyHttpClient client = new MyHttpClient();
    CustomAdapter customAdapter = new CustomAdapter(); // recycler view的adapter
    Handler handler = new Handler(Looper.getMainLooper()); // 處理sendPost的網路請求

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        init();
    }

    public void init(){
        /** Initialize recycler view */
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(customAdapter);

        /** Initialize progress bar */
        progressBar = findViewById(R.id.pb);

        /** Initialize view model */
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, userListUpdateObserver); // .observe使viewModel註冊getUserMutableLiveData()

        progressBar.setVisibility(View.VISIBLE);
        client.sendGet(viewModel);
    }

    /** 只要observe到MutableLiveData改變，就進行onChanged()，重新setupRecyclerView() */
    private Observer<List<Post>> userListUpdateObserver = new Observer<List<Post>>() {
        @Override
        public void onChanged(List<Post> userArrayList) {
            customAdapter.setData(userArrayList); // 在新的data(userArrayList)出現時，重新設定customAdapter裡的data
            recyclerView.getAdapter().notifyDataSetChanged(); // 在adapter裡的data改變時，通知adapter data已經變了，會顯示新data
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    /** 按下reset鍵後，跑client.sendGet()再抓一次資料 */
    public void onClickReset(View view){
        progressBar.setVisibility(View.VISIBLE);
        client.sendGet(viewModel);
    }

    /** 按下back鍵後，回到首頁 */
    public void onClickBack(View view){
        startActivity(new Intent(RecyclerViewActivity.this, MainActivity.class));

        /** setup progress dialog */
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Back to menu");
        dialog.setMessage("Loading...");
        dialog.show();
    }

    /** 按下send POST鍵後，顯示response */
    public void onClickSendPost(View view){
        progressBar.setVisibility(View.VISIBLE);

        AlertDialog.Builder alertDialog =
                new AlertDialog.Builder(RecyclerViewActivity.this);

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    alertDialog.setMessage(client.sendPost());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                alertDialog.show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
