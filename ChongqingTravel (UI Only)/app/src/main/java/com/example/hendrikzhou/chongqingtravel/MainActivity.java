package com.example.hendrikzhou.chongqingtravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hendrikzhou.chongqingtravel.fragment.FindFragment;
import com.example.hendrikzhou.chongqingtravel.fragment.MainFragment;
import com.example.hendrikzhou.chongqingtravel.fragment.MeFragment;

public class MainActivity extends AppCompatActivity {

    protected LinearLayout mMenuMain;
    protected LinearLayout mMenuFind;
    protected LinearLayout mMenuMe;
    protected MainFragment mMainFragment = new MainFragment();
    protected FindFragment mFindFragment = new FindFragment();
    protected MeFragment mMeFragment = new MeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_content, mMainFragment)
                .add(R.id.container_content, mFindFragment).hide(mFindFragment)
                .add(R.id.container_content, mMeFragment).hide(mMeFragment)
                .commit();

    }

    public void initView() {
        mMenuMain = this.findViewById(R.id.menu_main);
        mMenuFind = this.findViewById(R.id.menu_find);
        mMenuMe = this.findViewById(R.id.menu_me);

        mMenuMain.setOnClickListener(new MenuOnClickListener());
        mMenuFind.setOnClickListener(new MenuOnClickListener());
        mMenuMe.setOnClickListener(new MenuOnClickListener());
    }

    class MenuOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.menu_main:
                    MainActivity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .show(mMainFragment)
                            .hide(mFindFragment)
                            .hide(mMeFragment)
                            .commit();
                    break;
                case R.id.menu_find:
                    MainActivity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .hide(mMainFragment)
                            .show(mFindFragment)
                            .hide(mMeFragment)
                            .commit();
                    break;
                case R.id.menu_me:
                    MainActivity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .hide(mMainFragment)
                            .hide(mFindFragment)
                            .show(mMeFragment)
                            .commit();
                    break;
            }
        }
    }
}
