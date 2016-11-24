package com.monday8am.realmboilerplate.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Spinner;

import com.monday8am.realmboilerplate.R;
import com.monday8am.realmboilerplate.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.refresh_view) SwipeRefreshLayout refreshView;
    @BindView(R.id.list_view) ListView listView;
    @BindView(R.id.progressbar) MaterialProgressBar progressBar;
    @BindView(R.id.spinner) Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @OnItemClick(R.id.list_view) void onItemSelected(int position) {
        presenter.listItemSelected(position);
    }

}
