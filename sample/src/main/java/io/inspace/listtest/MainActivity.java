package io.inspace.listtest;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.inspace.fragmentaslistheader.HeaderFragmentManager;


public class MainActivity extends AppCompatActivity  {

    private DefaultHeaderFragment mDefaultHeaderFragment;
    private DefaultListFragment mListFragment;
    private View mHeaderFragmentContainer;
    private HeaderFragmentManager mHeaderFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDefaultHeaderFragment = new DefaultHeaderFragment();
        mListFragment = new DefaultListFragment();
        mHeaderFragmentContainer = findViewById(R.id.fragment_header);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_header, mDefaultHeaderFragment);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_list, mListFragment);
        ft.commit();

        mHeaderFragmentManager = new HeaderFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mHeaderFragmentManager.init(this, mDefaultHeaderFragment, mListFragment);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_expand) {
            if(mHeaderFragmentManager.getHeaderHeight() != mHeaderFragmentManager.getListHeaderHeight()) {
                mHeaderFragmentManager.setListHeaderHeight(mHeaderFragmentManager.getHeaderHeight());
            } else {
                mHeaderFragmentManager.setListHeaderHeight(0f);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
