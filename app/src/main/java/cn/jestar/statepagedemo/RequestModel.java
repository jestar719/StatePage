package cn.jestar.statepagedemo;

/**
 * Created by 花京院 on 2017/3/20.
 */

public class RequestModel {

    private final boolean isNew;
    private final String mTab;
    private int mPage;


    public RequestModel(String tab, boolean isNew) {
        this.isNew = isNew;
        mTab = tab;
    }

    public int getPage() {
        return mPage;
    }

    public RequestModel setPage(int page) {
        if (isNew) {
            page = 0;
        }
        mPage = ++page;
        return this;
    }

    public boolean isNew() {
        return isNew;
    }

    public String getTab() {
        return mTab;
    }
}
