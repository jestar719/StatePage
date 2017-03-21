package cn.jestar.statepagedemo;

import android.view.View;

import cn.jestar.statepage.DefaultStateImpl;
import cn.jestar.statepage.IState;

/**
 * Created by 花京院 on 2017/3/20.
 */

public class ErrorState extends DefaultStateImpl {

    private final View.OnClickListener mListener;

    public ErrorState(View.OnClickListener listener) {
        super(IState.STATE_ERROR, R.layout.layout_state_error);
        mListener = listener;
    }

    @Override
    protected void handleView() {
        super.handleView();
        mView.findViewById(R.id.btn_retry).setOnClickListener(mListener);
    }
}
