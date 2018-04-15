package vn.com.codedao.facecook.view.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.com.codedao.facecook.R;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_layout);
        getSupportActionBar().hide();
    }
}
