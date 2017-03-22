package cn.jestar.statepage;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;


/**
 * 自定义控件,用与切换状态.
 * Created by jestar on 17-3-9.
 */

public class StatePageView extends FrameLayout {
    private SparseArray<IState> mStateSparseArray;
    private IState mCurrentState;

    public StatePageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mStateSparseArray = new SparseArray<>(4);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatePageView);
        int loadingLayout = typedArray.getResourceId(R.styleable.StatePageView_state_loading, 0);
        int errorLayout = typedArray.getResourceId(R.styleable.StatePageView_state_error, 0);
        typedArray.recycle();
        mCurrentState = new DefaultState();
        loadingLayout = loadingLayout == 0 ? R.layout.layout_default_state_loading : loadingLayout;
        addState(getState(IState.STATE_LOADING, loadingLayout)).addState(mCurrentState);
        if (errorLayout != 0) {
            addState(getState(IState.STATE_ERROR, errorLayout));
        }
    }

    public StatePageView(@NonNull Context context) {
        this(context, null);
    }

    @NonNull
    private IState getState(int state, @LayoutRes int layoutId) {
        return new DefaultStateImpl(state, layoutId);
    }

    public StatePageView addState(IState IState) {
        mStateSparseArray.put(IState.getState(), IState);
        return this;
    }

    public void initView() {
        int size = mStateSparseArray.size();
        for (int i = 0; i < size; i++) {
            IState state = mStateSparseArray.valueAt(i);
            state.initView(this);
        }
    }

    public int getState() {
        return mCurrentState.getState();
    }

    public void setState(int state) {
        mCurrentState.hide();
        IState selectState = mStateSparseArray.get(state);
        if (selectState != null) {
            mCurrentState = selectState;
            mCurrentState.show();
        }
    }

}
