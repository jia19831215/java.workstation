package workstation.mail;

/**
 * Created by WangXiaoJia on 2016/6/15.
 */
public class MailException extends RuntimeException {

    public MailException() {
        this(null);
    }

    public MailException(Throwable inner){
        super("Mail Exception", inner);
    }
}
