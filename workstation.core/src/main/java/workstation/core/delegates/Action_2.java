package workstation.core.delegates;

/**
 * Created by WangXiaoJia on 2016/6/4.
 */
@FunctionalInterface
public interface Action_2<TIn1, TIn2> {
    void execute(TIn1 in1, TIn2 in2);
}
