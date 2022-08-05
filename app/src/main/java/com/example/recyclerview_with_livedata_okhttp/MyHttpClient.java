/** 做網路請求 */
package com.example.recyclerview_with_livedata_okhttp;

import android.os.StrictMode;

import androidx.annotation.NonNull;

import com.example.recyclerview_with_livedata_okhttp.variables.GlobalVal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyHttpClient {

    GlobalVal globalVal;

    public void sendGet(MainViewModel viewModel){
        /** 建立連線 */
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        /** 設置傳送需求 */
        Request request = new Request.Builder()
                .url(globalVal.getURL())
                .build();

        /** 設置回傳，將request封裝成call */
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                List<Post> list = new Gson().fromJson(response.body().string(), new TypeToken<List<Post>>(){}.getType());
                viewModel.setPosts(list);
            }
        });
    }

    public String sendPost() throws IOException{
        /** 建立連線 */
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        /** 設置傳送所需夾帶的內容 */
        FormBody formBody = new FormBody.Builder()
                .add("userId", "1")
                .add("id", "1")
                .add("title", "Test okHttp")
                .build();

        /** 設置傳送需求 */
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .post(formBody)
                .build();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }
}
