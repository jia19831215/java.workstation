package workstation.core.delegates;

/**
 * Created by WangXiaoJia on 2016/6/6.
 */
@FunctionalInterface
public interface Action_3<TIn1, TIn2, TIn3> {
    void execute(TIn1 in1, TIn2 in2, TIn3 in3);
}
