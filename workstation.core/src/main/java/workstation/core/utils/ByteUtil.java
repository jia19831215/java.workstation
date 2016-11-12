package workstation.core.utils;


import com.sun.istack.internal.NotNull;

/**
 * Created by WangXiaoJia on 2016/6/14.
 */
public class ByteUtil {

    /**
     * Byte 转 16 进制数据
     * @param input
     * @return
     * @throws IllegalArgumentException
     */
    public static String toHex(@NotNull byte[] input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("input null");
        }

        StringBuffer buffer = new StringBuffer();

        int value = 0;

        for (int i = 0; i < input.length; i++) {
            value = input[i];

            if (value < 0) {
                value += 256;
            }

            if (value < 16) {
                buffer.append("0");
            }

            buffer.append(Integer.toHexString(value));
        }

        return buffer.toString();
    }
}
