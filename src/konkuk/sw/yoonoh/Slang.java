package konkuk.sw.yoonoh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Slang {

   private HashMap<String, String> slang = new HashMap<String, String>();
   private ArrayList<String> User1SavingTextLine = new ArrayList<String>();
   private ArrayList<String> User2SavingTextLine = new ArrayList<String>();
   private ArrayList<String> AllSave = new ArrayList<String>();

   // user
   private String User1, User2;
   private boolean User1Check, User2Check;
   private int User1ChatNum, User2ChatNum;

   // temp
   private String temp, temp2, temp3, temp4, temp5;

   Slang() {
      try {
         Scanner scan = new Scanner(new File("Slang.txt"));
         while (scan.hasNextLine()) {
            String str = scan.nextLine();
            String[] temp = str.split(":");

            temp[0] = temp[0].trim(); // �߹�����
            temp[1] = temp[1].trim(); // �����ܾ�

            slang.put(temp[0], temp[1]); // ����
            slang.put(temp[1], temp[0]);
         }
         scan.close();
      } catch (FileNotFoundException e) {
         // TODO �ڵ� ������ catch ���
         e.printStackTrace();
      }
   }

   public void start(String path) {

      User1Check = false;
      User2Check = false;

      User1ChatNum = 0;
      User2ChatNum = 0;

      User1 = null;
      User2 = null;

      User1SavingTextLine.clear();
      User2SavingTextLine.clear();

      try {
         Scanner scan = new Scanner(new File(path), "UTF-8");
         while (scan.hasNextLine()) {
            String str = scan.nextLine();
            if (str.contains("[") && str.contains("]") && (str.contains("[����") || str.contains("[����"))) {

               temp3 = "";
               temp5 = "";

               StringTokenizer a = new StringTokenizer(str, "]");

               if (a.hasMoreTokens())
                  temp = a.nextToken(); // 1���и� [�̸�
               else
                  continue;

               if (a.hasMoreTokens())
                  temp2 = a.nextToken(); // 2���и� �������[�ð�
               else
                  continue;

               while (a.hasMoreTokens()) {
                  temp3 += a.nextToken(" ") + " ";
               }

               StringTokenizer b = new StringTokenizer(temp, "[");

               temp4 = b.nextToken(); // ���� �̸�

               StringTokenizer c = new StringTokenizer(temp3, "]");

               while (c.hasMoreElements()) {
                  temp5 += c.nextElement();
               }

               if (!User1Check) {
                  User1 = temp4;
                  User1Check = true;
               }

               else if (!User2Check && !User1.equals(temp4)) {
                  User2 = temp4;
                  User2Check = true;
               }

               if (temp4.equals(User1)) {
                  User1SavingTextLine.add(temp4 + ":" + temp5);
                  User1ChatNum++; // ���� 1�� ���� �� ��ȭ ��
               }

               else if (temp4.equals(User2)) {
                  User2SavingTextLine.add(temp4 + ":" + temp5);
                  User2ChatNum++; // ���� 2�� ���� �� ��ȭ ��
               }
            }
         }
         scan.close();
      } catch (FileNotFoundException e) {
         // TODO �ڵ� ������ catch ���
         e.printStackTrace();
      }

      searchSlang(User1SavingTextLine, User1);
      searchSlang(User2SavingTextLine, User2);
      AllSave.add(User1 + "���� " + User1ChatNum + "�� �̾߱���\n");
      AllSave.add(User2 + "���� " + User2ChatNum + "�� �̾߱���");
   }

   // text ���
   public ArrayList<String> printText() {
      for (int j = 0; j < AllSave.size(); j++) {
         return AllSave;
      }
      return null;
   }

   // �߹����� ���� �Լ�
   public void searchSlang(ArrayList<String> temp, String user) {

      for (int i = 0; i < temp.size(); i++) {
         String talk = temp.get(i);
         String[] word = talk.split(":");
         word[1].trim();

         StringTokenizer token = new StringTokenizer(word[1], " ");

         while (token.hasMoreTokens()) {
            String slangKey = slang.get(token.nextToken());

            if (slangKey != null) {
               AllSave.add(user + " : " + word[1] + " �� " + slangKey + "\n");
            }
         }
      }
   }
}