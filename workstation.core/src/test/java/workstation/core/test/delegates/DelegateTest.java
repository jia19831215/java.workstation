package workstation.core.test.delegates;

import org.junit.Assert;
import org.junit.Test;
import workstation.core.delegates.Action_1;
import workstation.core.delegates.Func_1;
import workstation.core.delegates.Func_2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by WangXiaoJia on 2016/6/4.
 */

public class DelegateTest {

    // shift + ctrl + F10 -> create test run configuration
    @Test
    public void TestAction_1() {

        List<String> input = new ArrayList<String>();

        Action_1<List<String>> action = (s) -> {
            s.add("snake");
        };

        action.execute(input);

        Assert.assertEquals(1, input.size());
    }

    @Test
    public void TestFunc_1() {
        String input = "Input";

        Func_1<String, String> func = (s) -> s + s;

        Assert.assertEquals(input + input, func.execute(input));
    }

    @Test
    public void TestFunc_2() {
        String input_1 = "Input1";
        String input_2 = "Input2";

        Func_2<String, String , String> func = (s1, s2) -> s1 + s2;

        Assert.assertEquals(input_1 + input_2, func.execute(input_1, input_2));
    }
}
