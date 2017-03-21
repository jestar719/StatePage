package cn.jestar.statepagedemo;

import java.util.List;

/**
 * Created by 花京院 on 2017/3/20.
 */
public interface StateContact {

    interface StateView {

        void setState(int state);

        void onGetDate(List<String> strings, boolean isNew);
    }

    interface StatePresent {
        void bindView(StateView view);

        void retry();

        void cancelCommand();

        void loadDate(RequestModel requestModel);
    }
}
