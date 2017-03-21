package cn.jestar.statepage;

import android.support.annotation.LayoutRes;

/**
 * Created by jestar on 17-3-9.
 */

public class DefaultStateImpl extends AbsIStateImpl {
    private int mState;
    private int mLayout;

    public DefaultStateImpl(int state, @LayoutRes int layout) {
        mState = state;
        mLayout = layout;
    }


    @Override
    protected int getLayoutId() {
        return mLayout;
    }

    @Override
    public int getState() {
        return mState;
    }
}
