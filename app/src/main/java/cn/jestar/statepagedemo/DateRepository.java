package cn.jestar.statepagedemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 花京院 on 2017/3/20.
 */

class DateRepository {
    private static final int ITEM_COUNT = 10;

    public Observable<List<String>> getDate(RequestModel request) {
        return Observable.just(request).subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .map(new Function<RequestModel, List<String>>() {
                    @Override
                    public List<String> apply(@NonNull RequestModel request) throws Exception {
                        List<String> list = new ArrayList<>(ITEM_COUNT);
                        for (int i = 1; i <= ITEM_COUNT; i++) {
                            list.add(String.format("PAGE %s-%s", request.getTab(), request.getPage() * 10 + i));
                        }
                        return list;
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
