package cn.jestar.statepage;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jestar on 17-3-9.
 */

public abstract class AbsIStateImpl implements IState {
    protected View mView;

    @Override
    public void initView(@NonNull ViewGroup parent) {
        Context context = parent.getContext();
        mView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        hide();
        handleView();
        parent.addView(mView);
    }

    protected void handleView() {
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void show() {
        mView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        mView.setVisibility(View.GONE);
    }

}
