package com.bawei.dian;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bawei.dian.Function.Quanzi;
import com.bawei.dian.Function.DingDan;
import com.bawei.dian.Function.Mine;
import com.bawei.dian.Function.GouWuChe;
import com.bawei.dian.Function.ShouYe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.main_pager);
        radioGroup = findViewById(R.id.main_group);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        initMian();
    }

    private void initMian() {
        final ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(new ShouYe());
        list.add(new Quanzi());
        list.add(new GouWuChe());
        list.add(new DingDan());
        list.add(new Mine());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_button1:

                        pager.setCurrentItem(0);
                        break;

                    case R.id.main_button2:

                        pager.setCurrentItem(1);
                        break;

                    case R.id.main_button3:

                        pager.setCurrentItem(2);
                        break;
                    case R.id.main_button4:

                        pager.setCurrentItem(3);
                        break;
                    case R.id.main_button5:

                        pager.setCurrentItem(4);
                        break;
                }
            }
        });
    }
}
