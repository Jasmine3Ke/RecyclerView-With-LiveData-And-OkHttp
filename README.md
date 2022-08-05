# RecyclerView with LiveData and OkHttp

[ ] 把最終demo影片變成GIF放上來

## Introduce
An Android project to indicate the price change of cryptocurrency.

<img src="/screenshots/FinalDemo.png" width="25%" height="25%" >

## Features
* Using the **RecyclerView** widget.
* Implementation of **LinearLayoutManager** for vertical scrolling.
* **Custom adapter** for RecyclerView.
* **ProgressBar** while loading page and reset the data.

## Quick Setup (Basic Usage)
### 1. Using Gradle (build.gradle):
Please replace `okhttp-bom:4.9.0` and `gson:2.9.0` with the latest version:  [OkHttp](https://square.github.io/okhttp/#releases) and [Gson](https://square.github.io/okhttp/#releases)
```
dependencies {
    ...
    //OkHttp
    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    //GSON
    implementation 'com.google.code.gson:gson:2.9.0'
}
```

### 2. Edit manifests (AndroidMenifest.xml):
```
<?xml version="1.0" encoding="utf-8"?>
<manifest 
  ...
  <uses-permission android:name="android.permission.INTERNET" />
  ...
</manifest>
```

## References
* [碼農日常-『Android studio』以okHttp第三方庫取得網路資料(POST、GET、WebSocket)](https://thumbb13555.pixnet.net/blog/post/325387050-okhttp)
* [碼農日常-『Android studio』進度條、轉圈圈(Progress)之基礎用法](https://thumbb13555.pixnet.net/blog/post/327961623-progressbar)
* [LiveData Overview](https://developer.android.com/topic/libraries/architecture/livedata)
* [Android in Kotlin: MVVM 架構分享 —— LiveData Observe 簡單示範](https://ithelp.ithome.com.tw/articles/10249679)
* [Create dynamic lists with RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)

## Contact
Feel free to reach me at any time.
<pre><code>Email: meirenke4@gmail.com
LinkedIn: https://www.linkedin.com/in/mei-ren-ke-7136641a5/ </code></pre>
