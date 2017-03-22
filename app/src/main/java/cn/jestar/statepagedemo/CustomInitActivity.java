package cn.jestar.statepagedemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.jestar.statepage.IState;
import cn.jestar.statepage.StatePageView;

public class CustomInitActivity extends AbsLoadMoreActivityActivity implements StateContact.StateView {
    private TextAdapter mAdapter;
    private StateContact.StatePresent mPresent;
    private String mCurrentTab;
    private String[] mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresent = new StatePresentImpl();
        mPresent.bindView(this);
        mAdapter = new TextAdapter();
        mRecyclerView.setAdapter(mAdapter);
        initTab();
    }

    private void initTab() {
        mTabs = getResources().getStringArray(R.array.tab_list);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_state);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentTab = mTabs[tab.getPosition()];
                mAdapter.setNew(null);
                loadDate(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < mTabs.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(mTabs[i]), i == 0);
        }
    }

    private void loadDate(boolean isNew) {
        mPresent.loadDate(new RequestModel(mCurrentTab, isNew));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_custom_init;
    }

    @Override
    void initStagePageView() {
        mStatePageView = new StatePageView(this);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.retry();
            }
        };
        mStatePageView.addState(new ErrorState(listener))
                .addState(new NoNetState(listener));
        ((ViewGroup) getWindow().getDecorView()).addView(mStatePageView);
    }

    @Override
    protected void loadMore() {
        loadDate(false);
    }

    @Override
    public void onRefresh() {
        loadDate(true);
    }

    @Override
    public void onBackPressed() {
        if (mStatePageView.getState() == IState.STATE_DEFAULT) {
            super.onBackPressed();
        } else {
            mPresent.cancelCommand();
            setState(IState.STATE_DEFAULT);
        }
    }

    @Override
    public void setState(int state) {
        mStatePageView.setState(state);
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetDate(List<String> strings, boolean isNew) {
        if (isNew) {
            mAdapter.setNew(strings);
        } else {
            mAdapter.loadMore(strings);
        }
        setState(IState.STATE_DEFAULT);
    }
}
