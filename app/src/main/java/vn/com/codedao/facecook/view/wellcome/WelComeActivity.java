package vn.com.codedao.facecook.view.wellcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import vn.com.codedao.facecook.view.home.MainActivity;
import vn.com.codedao.facecook.R;

public class WelComeActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        delayToHomeSreen();
    }

    private void delayToHomeSreen() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e(TAG, "run() called" + e);
                } finally {
                    Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();
    }
}