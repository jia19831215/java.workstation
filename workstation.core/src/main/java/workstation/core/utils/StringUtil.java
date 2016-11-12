package workstation.core.utils;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WangXiaoJia on 2016/6/12.
 */
public class StringUtil {

    /**
     * 获取异常堆栈信息
     * @param e 异常
     * @return 异常堆栈信息字符串
     */
    public static String getStackTrace(@NotNull Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            e.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

    /**
     * md5 加密 32位
     * @param input 被加密字符串
     * @return 加密字串
     * @throws IllegalArgumentException
     */
    public static String md5(@NotNull String input) throws IllegalArgumentException {

        if (input == null) {
            throw new IllegalArgumentException("input null");
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes());

            byte[] b = md.digest();

            return ByteUtil.toHex(b);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * md5 加密 16 位
     * @param input 被加密字符串
     * @return 加密字串
     * @throws IllegalArgumentException
     */
    public static String md5ByBit16(@NotNull String input) throws IllegalArgumentException {
        return md5(input).substring(8, 24);
    }

    /**
     * XML -> java 对象
     * @param xml xml字串
     * @param clazz 对象类型
     * @param <T> 返回对象类型
     * @return java 对象
     * @throws JAXBException
     */
    public static <T> T xmlToObject(@NotNull String xml, Class<T> clazz) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(clazz);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }

    /**
     * java 对象 -> xml
     * @param obj java 对象
     * @return xml字串
     * @throws JAXBException
     * @throws IOException
     */
    public static String objectToXml(@NotNull Object obj) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());

        Marshaller marshaller = context.createMarshaller();

        StringWriter sw = new StringWriter();

        marshaller.marshal(obj, sw);

        try {
            return sw.toString();
        } catch (Exception ce) {
            throw ce;
        } finally {
            sw.close();
        }
    }

    /**
     * url 解码
     * @param url url
     * @param enc 编码格式，不传默认 UTF-8
     * @return 解码字符串
     * @throws UnsupportedEncodingException
     */
    public static String urlDecode(@NotNull String url, @Nullable String enc) throws UnsupportedEncodingException {

        if (enc == null) {
            enc = "UTF-8";
        }

        return URLDecoder.decode(url, enc);
    }

    /**
     * url 编码
     * @param url url
     * @param enc 编码格式，不传默认 UTF-8
     * @return 编码字符串
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(@NotNull String url, @Nullable String enc) throws UnsupportedEncodingException {

        if (enc == null) {
            enc = "UTF-8";
        }

        return URLEncoder.encode(url, enc);
    }

    /**
     * 生成 GUID
     * @return GUID 字符串
     */
    public static String newGuid() {
        return java.util.UUID.randomUUID().toString();
    }
}
