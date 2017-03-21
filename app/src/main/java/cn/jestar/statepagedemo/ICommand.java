package cn.jestar.statepagedemo;

/**
 * Created by 花京院 on 2017/3/20.
 */

public interface ICommand {
    void execute();

    void cancel();

    void retry();
}
