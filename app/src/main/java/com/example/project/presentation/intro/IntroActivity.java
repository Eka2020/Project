package com.example.project.presentation.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.project.R;
import com.example.project.presentation.main.MainActivity;

public class IntroActivity extends AppCompatActivity {
    private Button intro_next, intro_skip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        intro_next = findViewById(R.id.intro_next);
        intro_skip = findViewById(R.id.intro_skip);

        intro_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });
        intro_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(IntroActivity.this);
                finish();
            }
        });

        viewPager = findViewById(R.id.intro_view_pager);
        viewPager.setAdapter(new IntroPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == IntroPagerAdapter.PAGES_COUNT-1) {
                    intro_next.setText("Start");
                    intro_skip.setVisibility(View.GONE);
                } else {
                    intro_next.setText("Next");
                    intro_skip.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    private void onNextClick(){
        if (viewPager.getCurrentItem() < IntroPagerAdapter.PAGES_COUNT-1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1, true);
        } else {
            MainActivity.start(IntroActivity.this);
            finish();
        }
    }

    public class IntroPagerAdapter extends FragmentPagerAdapter {
        public static final int PAGES_COUNT=3;
        public IntroPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return IntroFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }
}