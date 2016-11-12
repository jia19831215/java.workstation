package workstation.core.delegates;

/**
 * Created by WangXiaoJia on 2016/6/6.
 */
@FunctionalInterface
public interface Func_2<TOut, TIn1, TIn2> {
    TOut execute(TIn1 in1, TIn2 in2);
}
