package vn.com.codedao.facecook.apiservice;

import android.util.Log;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bruce Wayne on 12/04/2018.
 */

public class ApiClient {
//    public static final String BASE_URL = "http://192.168.48.105/facecook/index.php/";
    public static final String BASE_URL = "http://vietanhlogistics.com/facecook/index.php/";
    public static final String TAG = "ApiClient";
    private static Retrofit retrofit = null;
    static HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.e(TAG, "\n" + message);
        }
    });

    public static Retrofit getClient() {
        if (retrofit==null) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            CookieHandler cookieHandler = new CookieManager();
            OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(logging)
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
