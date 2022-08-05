/** 2022.08.03 Mei-Ren Ke
 * Android Final Project:
 * [v] 從OkHTTP取得api -> 抓Binance api
 * [v] recycler view的外觀
 * [v] 用觀察者模式（Live Data）呈現UI
 * [v] 轉圈圈 #progress bar
 * [v] post to a website */

package com.example.recyclerview_with_livedata_okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** 按下"show cryptocurrency"鍵之後就換頁 */
    public void onClickShow(View view){
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));

        /** setup progress dialog */
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Crypto Currency");
        dialog.setMessage("Loading...");
        dialog.show();
    }

    /** 按下"轉圈圈"鍵之後跳dialog */
    public void onClickSpinner(View view){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Run!");
        dialog.show();
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                dialog.setMessage("已經： "+i);
                SystemClock.sleep(50);
            }
            dialog.dismiss();
        }).start();
    }
}