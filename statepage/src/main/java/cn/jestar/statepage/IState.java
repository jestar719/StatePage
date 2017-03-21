package cn.jestar.statepage;

import android.view.ViewGroup;

/**
 * Created by jestar on 17-3-9.
 */

public interface IState {
    int STATE_DEFAULT = 0xff0;
    int STATE_LOADING = 0xff1;
    int STATE_ERROR = 0xff2;
    int STATE_EMPTY = 0xff3;
    int STATE_OUT_NET = 0xff4;

    void initView(ViewGroup parent);

    void show();

    void hide();

    int getState();
}
