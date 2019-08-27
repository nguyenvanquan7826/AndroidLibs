package com.nguyenvanquan7826.appforlibs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layoutFragment, fragment)
                    .commit();
        }
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
