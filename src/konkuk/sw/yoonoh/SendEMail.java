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
      String host = "smtp.naver.com"; // ���̹��� ��������
      final String user = "sarangi70"; // �߽��� ���̵�
      final String password = "qkrtjdwns98"; // �߽��� ��й�ȣ

      String to = "park98sj@gmail.com"; // ������ ���̵�

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

         // ���� ����
         message.setSubject("�ܾ� �߰� �ǵ��");

         // �߰��� �ܾ� ������
         message.setText(str);

         // ���� ������
         Transport.send(message);
         System.out.println("message sent successfully...");

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
}