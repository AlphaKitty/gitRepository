package cn.edu.sdau.forum.config;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author 张友谅
 */
public class MailUtils {

    public static final String HOST = "smtp.qq.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 25;
    public static final String FROM = "372219506@qq.com";// 发件人的email
    public static final String PWD = "zyl1814372211";// 发件人密码

    @SuppressWarnings("static-access")
    public static void send(String toEmail, String content,String subject) {
        try {
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            Properties prop = new Properties();
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.host", "smtp.qq.com");
            prop.setProperty("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", "25");
            prop.setProperty("mail.debug", "true");
            prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            prop.setProperty("mail.smtp.socketFactory.fallback", "false");
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            Authenticator authenticator = new PopAuthenticator("372219506@qq.com", "zyl1814372211");
            // 创建会话
            Session session = Session.getInstance(prop, authenticator);
            // 填写信封写信
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("372219506@qq.com"));
            InternetAddress[] address = { new InternetAddress(toEmail) };
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setContent(content, "text/html;charset=utf-8");
            // 验证用户名密码发送邮件
            Transport transport = session.getTransport();
            transport.send(msg);
           
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
