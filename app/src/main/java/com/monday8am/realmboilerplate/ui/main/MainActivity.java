package com.monday8am.realmboilerplate.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.monday8am.realmboilerplate.R;
import com.monday8am.realmboilerplate.data.model.NYTimesStory;
import com.monday8am.realmboilerplate.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import timber.log.Timber;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.refresh_view) SwipeRefreshLayout mRefreshView;
    @BindView(R.id.list_view) ListView mListView;
    @BindView(R.id.progressbar) MaterialProgressBar mProgressBar;
    @BindView(R.id.spinner) Spinner mSpinner;

    private ArrayAdapter<NYTimesStory> mAdapter;
    @Inject MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAdapter = null;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMainPresenter.listItemSelected(position);
            }
        });
        mListView.setEmptyView(getLayoutInflater()
                .inflate(R.layout.common_emptylist, mListView, false));

        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMainPresenter.refreshList();
            }
        });
        mProgressBar.setVisibility(View.INVISIBLE);
        mMainPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    /**
     *  Methods called by MainViewPresenter
     */
    public void showList(List<NYTimesStory> items) {
        if (mAdapter == null) {
            mAdapter = new NewsListAdapter(MainActivity.this, items);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(items);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void configureToolbar(List<String> sections) {

        String[] sectionList = sections.toArray(new String[sections.size()]);
        final ArrayAdapter adapter = new ArrayAdapter<CharSequence>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, sectionList);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMainPresenter.titleSpinnerSectionSelected((String) adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void hideRefreshing() {
        mRefreshView.setRefreshing(false);
    }

    public void showNetworkLoading(Boolean networkInUse) {
        mProgressBar.setVisibility(networkInUse ? View.VISIBLE : View.INVISIBLE);
    }

    // ListView mAdapter class
    public static class NewsListAdapter extends ArrayAdapter<NYTimesStory> {

        private final LayoutInflater mInflater;
        @LayoutRes private final int mLayoutResource;

        @BindColor(android.R.color.darker_gray) int mReadColor;
        @BindColor(android.R.color.primary_text_light) int mUnreadColor;

        public NewsListAdapter(Context context, List<NYTimesStory> initialData) {
            super(context, android.R.layout.simple_list_item_1);
            setNotifyOnChange(false);
            addAll(initialData);
            mInflater = LayoutInflater.from(context);
            mLayoutResource = android.R.layout.simple_list_item_1;
        }

        @Override
        @NonNull  public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(mLayoutResource, parent, false);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            NYTimesStory story = getItem(position);
            Timber.d(story.shortUrl);
            holder.titleView.setText(story.title);
            holder.titleView.setTextColor(story.isRead ? mReadColor : mUnreadColor);
            return view;
        }

        static class ViewHolder {
            @BindView(android.R.id.text1) TextView titleView;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
