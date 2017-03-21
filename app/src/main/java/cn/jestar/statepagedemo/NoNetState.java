package cn.jestar.statepagedemo;

import android.view.View;

import cn.jestar.statepage.AbsIStateImpl;
import cn.jestar.statepage.IState;

/**
 * Created by 花京院 on 2017/3/20.
 */

public class NoNetState extends AbsIStateImpl {

    private final View.OnClickListener mListener;

    public NoNetState(View.OnClickListener listener) {
        super(IState.STATE_OUT_NET, R.layout.layout_state_out_net);
        mListener = listener;
    }

    @Override
    protected void handleView() {
        mView.findViewById(R.id.btn_retry).setOnClickListener(mListener);
    }
}
