package workstation.core.delegates;

/**
 * Created by WangXiaoJia on 2016/6/4.
 */
@FunctionalInterface
public interface Action_1<TIn> {
    void execute(TIn in);
}
