package vn.com.codedao.facecook.view.home;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.presenter.newfeed.PresenterLogicHandleNewFeed;
import vn.com.codedao.facecook.view.friend.FragmentFriend;
import vn.com.codedao.facecook.view.menu.FragmentMenu;
import vn.com.codedao.facecook.view.newfeed.FragmentNewFeed;
import vn.com.codedao.facecook.view.newfeed.PostAdapter;

public class Home extends AppCompatActivity {

    private PresenterLogicHandleNewFeed mPresenterLogicHandleHome;
    private PostAdapter mPostAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenfr, new FragmentNewFeed());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        BottomNavigationView bottomNavigationView =
                findViewById(R.id.naviBottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                transitFragment(new FragmentNewFeed());
                                break;
                            case R.id.action_schedules:
                                transitFragment(new FragmentFriend());
                                break;
                            case R.id.action_music:
                                transitFragment(new FragmentMenu());
                                break;

                        }
                        return true;
                    }
                });



    }

    public void transitFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenfr, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back 1 Lần nữa để thoát", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
