package cn.jestar.statepagedemo;

import java.util.List;
import java.util.Random;

import cn.jestar.statepage.IState;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by 花京院 on 2017/3/20.
 */

public class StatePresentImpl implements StateContact.StatePresent {

    private StateContact.StateView mView;
    private int mCurrentPage;
    private DateRepository mDateRepository = new DateRepository();
    private ICommand mCommand;

    @Override
    public void bindView(StateContact.StateView view) {
        mView = view;
    }

    @Override
    public void retry() {
        mView.setState(IState.STATE_LOADING);
        mCommand.retry();
    }

    @Override
    public void cancelCommand() {
        mCommand.cancel();
    }

    @Override
    public void loadDate(RequestModel model) {
        mView.setState(IState.STATE_LOADING);
        mCommand = new DateCommandImpl(model.setPage(mCurrentPage), mDateRepository);
        mCommand.execute();
    }

    private class DateCommandImpl implements ICommand {

        private final RequestModel mRequestModel;
        private final DateRepository mDateRepository;
        private boolean isError = true;
        private Disposable mDisposable;
        private Consumer<List<String>> mDateConsumer = new Consumer<List<String>>() {
            @Override
            public void accept(@NonNull List<String> strings) throws Exception {
                mView.onGetDate(strings, mRequestModel.isNew());
                mCurrentPage = mRequestModel.getPage();
            }
        };
        ;
        private Consumer<Throwable> mErrorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                String message = throwable.getMessage();
                mView.setState(Integer.parseInt(message));
            }
        };

        DateCommandImpl(RequestModel requestModel, DateRepository dateRepository) {
            mRequestModel = requestModel;
            mDateRepository = dateRepository;
        }

        @Override
        public void execute() {
            boolean isOutOfNet = new Random().nextBoolean();
            if (isOutOfNet) {
                isError = false;
                Observable<List<String>> date = Observable.error(new RuntimeException(String.valueOf(IState.STATE_OUT_NET)));
                mDisposable = date.subscribe(mDateConsumer, mErrorConsumer);
            } else {
                getDate();
            }
        }

        @Override
        public void cancel() {
            mDisposable.dispose();
        }

        @Override
        public void retry() {
            getDate();
        }

        private void getDate() {
            mDisposable = mDateRepository.getDate(mRequestModel)
                    .map(new Function<List<String>, List<String>>() {
                        @Override
                        public List<String> apply(@NonNull List<String> strings) throws Exception {
                            if (isError) {
                                isError = false;
                            }
                            return strings;
                        }
                    })
                    .subscribe(mDateConsumer, mErrorConsumer);
        }
    }
}
