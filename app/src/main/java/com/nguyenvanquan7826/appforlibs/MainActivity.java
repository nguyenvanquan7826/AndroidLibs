package com.nguyenvanquan7826.appforlibs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.nguyenvanquan7826.appbase.log.LogTextView;
import com.nguyenvanquan7826.appbase.log.LogView;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layoutFragment, fragment).commit();
        }

        LogView logViewNomal = new LogTextView(findViewById(R.id.tvLogNomal));
        logViewNomal.show(R.string.app_name, LogView.LOG_LEVEL_NOMAL);
        LogView logViewWraning = new LogTextView(findViewById(R.id.tvLogWraning));
        logViewWraning.show(R.string.app_name, LogView.LOG_LEVEL_WARNING);
        LogView logViewError = new LogTextView(findViewById(R.id.tvLogError));
        logViewError.show(R.string.app_name, LogView.LOG_LEVEL_ERROR);
    }

    @Override
    public void onListItemClick(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RecyclerListFragment();
                break;

            case 1:
                fragment = new RecyclerGridFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
