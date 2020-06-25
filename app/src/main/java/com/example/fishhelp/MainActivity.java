package com.example.fishhelp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import com.example.fishhelp.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        NavController navController = new NavController(this);

        final FloatingActionButton fab = findViewById(R.id.fab);
        final FloatingActionButton add = findViewById(R.id.add);
        final FloatingActionButton search = findViewById(R.id.search);

        final Animation show_add = AnimationUtils.loadAnimation(getApplication(), R.anim.show_add);
        final Animation hide_add = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_add);
        final Animation show_search = AnimationUtils.loadAnimation(getApplication(), R.anim.show_search);
        final Animation hide_search = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_search);
        final OvershootInterpolator interpolator = new OvershootInterpolator();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                if (flag) {
                    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) add.getLayoutParams();
                    layoutParams1.bottomMargin += (int) (add.getHeight() * 1.3);
                    add.setLayoutParams(layoutParams1);
                    add.startAnimation(show_add);
                    add.setClickable(true);

                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) search.getLayoutParams();
                    layoutParams2.bottomMargin += (int) (search.getHeight() * 2.6);
                    search.setLayoutParams(layoutParams2);
                    search.startAnimation(show_search);
                    search.setClickable(true);

                    ViewCompat.animate(fab).
                            rotation(-180f).
                            withLayer().
                            setDuration(500).
                            setInterpolator(interpolator).
                            start();

                    flag = false;
                } else {
                    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) add.getLayoutParams();
                    layoutParams1.bottomMargin -= (int) (add.getHeight() * 1.3);
                    add.setLayoutParams(layoutParams1);
                    add.startAnimation(hide_add);
                    add.setClickable(false);

                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) search.getLayoutParams();
                    layoutParams2.bottomMargin -= (int) (search.getHeight() * 2.6);
                    search.setLayoutParams(layoutParams2);
                    search.startAnimation(hide_search);
                    search.setClickable(false);

                    ViewCompat.animate(fab).
                            rotation(0f).
                            withLayer().
                            setDuration(500).
                            setInterpolator(interpolator).
                            start();

                    flag = true;
                }
            }
        });

        add.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.goto_addFragment));
        search.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.goto_searchFragment));
    }
}