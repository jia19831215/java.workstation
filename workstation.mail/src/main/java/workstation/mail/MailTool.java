package workstation.mail;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.function.Function;

/**
 * Created by WangXiaoJia on 2016/6/15.
 */
public class MailTool {

    private String smtp;
    private String user;
    private String password;
    private String from;
    private int port = 25;
    private boolean isTsl = false;

    public boolean isTsl() {
        return isTsl;
    }

    public void setIsTsl(boolean isTsl) {
        this.isTsl = isTsl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 发送邮件
     *
     * @param to 目标地址
     * @param cc 抄送地址
     * @param subject 主题��
     * @param content 内容
     * @param setup 设置����������
     * @throws MailException
     */
    public void send(@NotNull String[] to, @Nullable String[] cc, @NotNull String subject, @NotNull String content, @Nullable Function<Properties, Properties> setup) throws MailException, MessagingException {

        Transport transport = null;

        try {
            if (to == null || to.length == 0) {
                throw new IllegalArgumentException("to null or to length equals zero");
            }

            if (subject == null) {
                throw new IllegalArgumentException("subject null");
            }

            if (content == null) {
                throw new IllegalArgumentException("content null");
            }

            Properties properties = new Properties();

            properties.setProperty("mail.smtp.host", this.smtp);
            properties.setProperty("mail.smtp.port", String.valueOf(this.port));
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.smtp.auth", "true");

            if (setup != null) {
                properties = setup.apply(properties);
            }

            if (this.isTsl) {
                properties.setProperty("mail.smtp.starttls.enable", "true");
            }

            Session session = Session.getInstance(properties);

            session.setDebug(true);

            transport = session.getTransport();

            transport.connect(this.smtp, this.user, this.password);

            Message mail = this.createMail(session, to, cc, subject, content, null);

            transport.sendMessage(mail, mail.getAllRecipients());
        } catch (Exception ce) {
            throw new MailException(ce);
        } finally {
            if (transport != null) {
                transport.close();
            }
        }
    }

    /**
     * 发送邮件
     *
     * @param session
     * @param to
     * @param cc
     * @param subject
     * @param content
     * @param setup
     * @return
     * @throws MessagingException
     */
    private MimeMessage createMail(Session session, String[] to, String[] cc, String subject, String content, Function<MimeMessage, MimeMessage> setup) throws MessagingException {
        MimeMessage mail = new MimeMessage(session);

        mail.setFrom(new InternetAddress(this.from));

        for (String address : to) {
            mail.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        }

        if (cc != null && cc.length > 0) {
            for (String address : cc) {
                mail.setRecipient(Message.RecipientType.CC, new InternetAddress(address));
            }
        }

        mail.setSubject(subject);
        mail.setContent(content, "text/html;charset=UTF-8");
        mail.setSentDate(new Date());
        mail.saveChanges();

        if (setup != null) {
            mail = setup.apply(mail);
        }

        return mail;
    }
}
