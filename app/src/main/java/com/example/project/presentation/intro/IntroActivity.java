package com.example.project.presentation.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.project.data.AppPref;
import com.example.project.R;
import com.example.project.presentation.main.MainActivity;
import com.google.android.material.tabs.TabLayout;
import static com.example.project.R.color.colorFiolet;
import static com.example.project.R.color.colorlitePurpure;

public class IntroActivity extends AppCompatActivity {
    private Button intro_next, intro_skip;
    private ViewPager viewPager;
    TabLayout tabLayout;
    RelativeLayout relativeLayout;
    ImageView imageView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, true);

        intro_next = findViewById(R.id.intro_next);
        intro_skip = findViewById(R.id.intro_skip);
        imageView = findViewById(R.id.image_intro);
        relativeLayout=findViewById(R.id.relativeLayout);

        intro_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });
        intro_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPref.getInstance(IntroActivity.this).saveShown();
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        });

        viewPager = findViewById(R.id.intro_view_pager);
        viewPager.setAdapter(new IntroPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        intro_skip.setVisibility(View.VISIBLE);
                        intro_next.setVisibility(View.VISIBLE);
                        intro_next.setText("Next");
                        imageView.setImageResource(R.drawable.a);
                        relativeLayout.setBackgroundColor(colorFiolet);
                        break;
                    case 1:
                        intro_next.setText("Next");
                        intro_skip.setVisibility(View.VISIBLE);
                        imageView.getResources();
                        imageView.setImageResource(R.drawable.b);
                        relativeLayout.setBackgroundColor(colorlitePurpure);
                        break;
                    case 2:
                        intro_next.setText("Start");
                        intro_skip.setVisibility(View.GONE);
                        imageView.setImageResource(R.drawable.a);
                        relativeLayout.setBackgroundColor(colorFiolet);
                        break;
                }
            }
        });
    }
    private void onNextClick(){
        AppPref.getInstance(this).saveShown();

        if (viewPager.getCurrentItem() < 2) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() +1, true);
        } else {
            startActivity(new Intent(this,MainActivity.class));
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