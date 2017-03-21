package cn.jestar.statepage;

import android.support.annotation.LayoutRes;

/**
 * 默认的状态实现类
 * Created by jestar on 17-3-9.
 */

public class DefaultStateImpl extends AbsIStateImpl {

    public DefaultStateImpl(int state, @LayoutRes int layout) {
        super(state, layout);
    }

    @Override
    protected void handleView() {

    }
}
