import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) throws IOException, MessagingException {
        String pass="********";
        String myEmail="********";
        String targetEmail="************";
       // 1) Установка соединения с почтовым сервером, через который будет отправлено письмо
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "false");
        props.setProperty("mail.smtp.host", "smtp.mail.ru");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.password", pass);
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.mail.ru");
        props.setProperty("mail.user", myEmail);
        props.setProperty("mail.password", pass);

        //2) Cоздаём сообщение
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication  getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                myEmail,pass );
                    }
                });
        MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(targetEmail));
        message.setSubject("Отправляю с Idea");
        message.setText("Привет! Я пишу с IDEA ");

        System.out.println("Начинается транзакция");


        //3) Отправка

        Transport.send(message);

        System.out.println("Сообщение отправлено");
    }
}