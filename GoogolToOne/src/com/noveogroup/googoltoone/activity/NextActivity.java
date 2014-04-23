package com.noveogroup.googoltoone.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.fragment.QueryFragment;

public class NextActivity extends FragmentActivity {

    public boolean ifFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Testing. Remove this Activity when no more needed

        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_with_fragment);

        if(savedInstanceState != null) {
            return;
        }

        QueryFragment queryFragment = new QueryFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, queryFragment).commit();

      }

    public void Fuction() {

    }

}
