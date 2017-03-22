package cn.jestar.statepagedemo;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import cn.jestar.statepage.StatePageView;

public abstract class AbsLoadMoreActivityActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mRefreshLayout;
    protected StatePageView mStatePageView;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initStagePageView();
        mStatePageView.initView();
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        initRecyclerView();
    }

    @LayoutRes
    protected abstract int getLayout();

    private void initRecyclerView() {
        mRefreshLayout.setOnRefreshListener(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading) {
                    int visibleItemCount = recyclerView.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                    if (totalItemCount - visibleItemCount <= firstVisibleItem) {
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    protected abstract void loadMore();

    abstract void initStagePageView();

}
