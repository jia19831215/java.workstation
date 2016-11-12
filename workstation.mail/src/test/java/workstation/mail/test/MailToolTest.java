package workstation.mail.test;

import org.junit.Assert;
import org.junit.Test;
import workstation.mail.MailTool;

/**
 * Created by WangXiaoJia on 2016/6/15.
 */
public class MailToolTest {

    @Test
    public void testSimpleMail() {
        MailTool mailTool = new MailTool();

        mailTool.setFrom("jia19831215@msn.com");
        mailTool.setUser("jia19831215@msn.com");
        mailTool.setPassword("AKB48wanting");
        mailTool.setSmtp("smtp-mail.outlook.com");
        mailTool.setPort(587);
        mailTool.setIsTsl(true);

        try {
            mailTool.send(new String[]{"jia19831215@msn.com"}, null, "Test", "<h>Test</h><a href='http://www.sina.com'>link</a>", null);
        } catch (Exception ce) {
            System.out.println(ce.toString());
            Assert.fail("Send mail fail");
        }
    }
}
