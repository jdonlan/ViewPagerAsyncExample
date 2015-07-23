package com.mobiquity.demo.viewpagerasyncdemo;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobiquity.demo.viewpagerasyncdemo.fragments.CategoryFragment;
import com.mobiquity.demo.viewpagerasyncdemo.objects.ActivityListener;


public class MainActivity extends Activity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        ((Button) findViewById(R.id.b_getfacts)).setOnClickListener(bUserNumberListener);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            String category = "";
            switch(position){
                case 0:
                    category = "math";
                    break;
                case 1:
                    category = "trivia";
                    break;
                case 2:
                    category = "date";
                    break;
                case 3:
                    category = "year";
                    break;
            }
            return CategoryFragment.newInstance(category);
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

    private void updateFragment(Fragment _fragment){
        if(_fragment instanceof ActivityListener) {
            EditText userNumberField = (EditText) findViewById(R.id.et_number);
            ((ActivityListener) _fragment).updateNumber(userNumberField.getText().toString());
        }
    }

    private View.OnClickListener bUserNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Fragment previousFragment = getFragmentManager().findFragmentByTag("android:switcher:" + mViewPager.getId() + ":" + (mViewPager.getCurrentItem()-1));
            Fragment nextFragment = getFragmentManager().findFragmentByTag("android:switcher:" + mViewPager.getId() + ":" + (mViewPager.getCurrentItem()+1));
            Fragment currentFragment = getFragmentManager().findFragmentByTag("android:switcher:" + mViewPager.getId() + ":" + mViewPager.getCurrentItem());

            if(previousFragment != null) updateFragment(previousFragment);
            if(nextFragment != null) updateFragment(nextFragment);
            updateFragment(currentFragment);

        }
    };

}
