package cn.jestar.statepagedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.jestar.statepage.IState;
import cn.jestar.statepage.StatePageView;

public class XmlActivity extends AppCompatActivity implements View.OnClickListener {

    private StatePageView mStatePageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        mStatePageView = (StatePageView) findViewById(R.id.state_page);
        mStatePageView.addState(new NoNetState(this)).initView();

    }

    @Override
    public void onClick(View v) {
        mStatePageView.setState(IState.STATE_DEFAULT);
    }

    @Override
    public void onBackPressed() {
        if (mStatePageView.getState() == IState.STATE_DEFAULT) {
            super.onBackPressed();
        } else {
            mStatePageView.setState(IState.STATE_DEFAULT);
        }
    }

    public void onBtnClick(View v) {
        int state = IState.STATE_DEFAULT;
        switch (v.getId()) {
            case R.id.btn_loading:
                state = IState.STATE_LOADING;
                break;
            case R.id.btn_error:
                state = IState.STATE_ERROR;
                break;
            case R.id.btn_out_of_net:
                state = IState.STATE_OUT_NET;
                break;
        }
        mStatePageView.setState(state);
    }
}
