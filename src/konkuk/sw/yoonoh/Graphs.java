package konkuk.sw.yoonoh;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Graphs extends Frame {
   // �׷��� Ÿ���� ���� ���
   private int graphStyle;

   private Font font1Bold;
   private Font font1Bold2;
   private Font font1Plane;

   // �������� �ּҰ��� �ִ밪�� ���Ѵ�.
   private double min, max;

   // �����͸� �����Ѵ�.
   private int[] data;
   private int[] top3 = new int[3];

   private String[] strData;
   private String[] strTop3 = new String[3];
   private String user;

   private BufferedImage backImg;

   // �׷����� ���Ǵ� ����.
   Color gridColor = new Color(0, 150, 150);
   Color dataColor = new Color(234, 24, 212);

   public Graphs(ArrayList<String> a, ArrayList<Integer> b, String c) {
      // ������ �ݱ� �̺�Ʈ ó��
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent we) {
            setVisible(false);
            dispose();
         }
      });

      // resize event ó��
      addComponentListener(new ComponentAdapter() {
         public void componentResized(ComponentEvent e) {
            repaint();
         }
      });

      font1Bold = new Font("����ǹ��� �־�", Font.BOLD, 20);
      font1Bold2 = new Font("����ǹ��� �־�", Font.BOLD, 13);
      font1Plane = new Font("����ǹ��� �־�", Font.PLAIN, 20);
      try {
         backImg = ImageIO.read(new File("images\\background.png"));
      } catch (IOException e1) {
         // TODO �ڵ� ������ catch ���
         e1.printStackTrace();
      }

      user = c;
      strData = new String[a.size()];
      data = new int[b.size()];
      int[] rank = new int[b.size()];

      for (int i = 0; i < b.size(); i++) {
         data[i] = b.get(i);
         strData[i] = a.get(i);
      }

      // ���� ���� Ƚ�� ������ ����
      for (int i = data.length - 1; i > 0; i--) {
         for (int j = 0; j < i; j++) {
            if (data[i] <= data[j])
               rank[i]++;
            else
               rank[j]++;
         }
      }

      // � �弳�� �Ͽ������� �弳 Ƚ�� ������ ����
      for (int i = 0; i < data.length; i++) {
         if (rank[i] == 0) {
            strTop3[0] = strData[i];
            top3[0] = data[i];
         } else if (rank[i] == 1) {
            strTop3[1] = strData[i];
            top3[1] = data[i];
         } else if (rank[i] == 2) {
            strTop3[2] = strData[i];
            top3[2] = data[i];
         }
      }

      min = 0;
      max = top3[0];
      setSize(1050, 630);
      setVisible(true);
   }

   // �׷��� �׸���
   public void paint(Graphics g) {
      Random r = new Random();

      g.clearRect(0, 0, getWidth(), getHeight());
      g.drawImage(backImg, 0, 0, null);
      g.setFont(font1Bold);
      g.drawString(user + "", 900 - user.length(), 100);

      g.drawLine(100, 550, 1000, 550);
      for (int cnt = 0; cnt < 3; cnt++) {
         g.drawString(((int) (max / 2) * cnt) + "", 50, (int) (550 - 250 * cnt));
      }

      g.drawLine(100, 20, 100, 550);
      for (int i = 0; i < 3; i++) {
         g.drawString(strTop3[i] + "", 100 + (300 * i + 200), 600);
      }

      for (int i = 0; i < 3; i++) {
         // ���� �� ����
         g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
         // ���� �׸���
         g.fillRect((300 * i + 285), (int) (550 - ((top3[i] / max) * 550)), 50,
               550 - (int) (550 - ((top3[i] / max) * 550)));
      }
   }
}