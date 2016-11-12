package workstation.core.delegates;

/**
 * Created by WangXiaoJia on 2016/6/6.
 */
@FunctionalInterface
public interface Func_1<TOut, TIn> {
    TOut execute(TIn in);
}
