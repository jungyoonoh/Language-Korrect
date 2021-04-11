package konkuk.sw.yoonoh;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEMail {

   SendEMail(String str) {
      String host = "smtp.naver.com"; // 네이버용 프로토콜
      final String user = "sarangi70"; // 발신자 아이디
      final String password = "qkrtjdwns98"; // 발신자 비밀번호

      String to = "park98sj@gmail.com"; // 수신자 아이디

      Properties props = new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
         }
      });

      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // 메일 제목
         message.setSubject("단어 추가 피드백");

         // 추가할 단어 보내기
         message.setText(str);

         // 메일 보내기
         Transport.send(message);
         System.out.println("message sent successfully...");

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
}