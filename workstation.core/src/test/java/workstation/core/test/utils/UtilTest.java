package workstation.core.test.utils;

import org.junit.Assert;
import org.junit.Test;
import workstation.core.test.utils.testclass.Student;
import workstation.core.utils.DateUtil;
import workstation.core.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by WangXiaoJia on 2016/6/14.
 */
public class UtilTest {

    @Test
    public void testMd5() {

        String input = "Test Md5";

        String value = StringUtil.md5(input);

        Assert.assertEquals("4237739e921df2ae86602ee32daadc3a", value);
    }

    @Test
    public void testMd5ByBit16() {
        String input = "Test Md5";

        String value = StringUtil.md5ByBit16(input);

        Assert.assertEquals("921df2ae86602ee3", value);
    }

    @Test
    public void testDateFormat() {

        String pattern = "yyyy-MM-dd HH:mm:ss";
        String date = "2016-01-01 12:00:00";

        try {
            Date input = DateUtil.parase(date, pattern);

            String res = DateUtil.format(input, pattern);

            Assert.assertEquals(date, res);
        } catch (ParseException ce) {
            ce.printStackTrace();
        }
    }

    @Test
    public void testGetExceptionInfo() {
        Exception inner_1 = new Exception("inner_1");
        Exception inner_2 = new Exception("inner_2", inner_1);
        Exception out = new Exception("out", inner_2);

        String res = StringUtil.getStackTrace(out);

        System.out.println(res);
    }

    @Test
    public void testObjectToXml() {
        Student student = new Student();

        student.setId(1);
        student.setName("AKB48");
        student.setOld(17);

        String xml = null;

        try {
            xml = StringUtil.objectToXml(student);
        } catch (Exception ce) {
            Assert.fail("object to xml fail");
        }

        Student result = null;

        try {
            result = StringUtil.xmlToObject(xml, Student.class);
        } catch (Exception ce) {
            Assert.fail("xml to object fail");
        }

        Assert.assertEquals(student.getId(), result.getId());
        Assert.assertEquals(student.getName(), result.getName());
        Assert.assertEquals(student.getOld(), result.getOld());
    }

    @Test
    public void testUrlDecodeAndEncode() throws UnsupportedEncodingException {
        String url = "http://www.sony.co.jp?area=中国";

        String encode = StringUtil.urlEncode(url, null);

        String decode = StringUtil.urlDecode(encode, null);

        Assert.assertEquals(url, decode);
    }

    @Test
    public void testGuid() {
        System.out.println(StringUtil.newGuid());
    }
}
