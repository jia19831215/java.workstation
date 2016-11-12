package workstation.core.delegates;

/**
 * Created by WangXiaoJia on 2016/6/6.
 */
@FunctionalInterface
public interface Action_4<TIn1, TIn2, TIn3, TIn4> {
    void execute(TIn1 in1, TIn2 in2, TIn3 in3, TIn4 in4);
}
