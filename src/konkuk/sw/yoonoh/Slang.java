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

            temp[0] = temp[0].trim(); // 야민정음
            temp[1] = temp[1].trim(); // 원래단어

            slang.put(temp[0], temp[1]); // 저장
            slang.put(temp[1], temp[0]);
         }
         scan.close();
      } catch (FileNotFoundException e) {
         // TODO 자동 생성된 catch 블록
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
            if (str.contains("[") && str.contains("]") && (str.contains("[오전") || str.contains("[오후"))) {

               temp3 = "";
               temp5 = "";

               StringTokenizer a = new StringTokenizer(str, "]");

               if (a.hasMoreTokens())
                  temp = a.nextToken(); // 1차분리 [이름
               else
                  continue;

               if (a.hasMoreTokens())
                  temp2 = a.nextToken(); // 2차분리 공백공백[시간
               else
                  continue;

               while (a.hasMoreTokens()) {
                  temp3 += a.nextToken(" ") + " ";
               }

               StringTokenizer b = new StringTokenizer(temp, "[");

               temp4 = b.nextToken(); // 순수 이름

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
                  User1ChatNum++; // 유저 1이 말한 총 대화 수
               }

               else if (temp4.equals(User2)) {
                  User2SavingTextLine.add(temp4 + ":" + temp5);
                  User2ChatNum++; // 유저 2가 말한 총 대화 수
               }
            }
         }
         scan.close();
      } catch (FileNotFoundException e) {
         // TODO 자동 생성된 catch 블록
         e.printStackTrace();
      }

      searchSlang(User1SavingTextLine, User1);
      searchSlang(User2SavingTextLine, User2);
      AllSave.add(User1 + "님이 " + User1ChatNum + "번 이야기함\n");
      AllSave.add(User2 + "님이 " + User2ChatNum + "번 이야기함");
   }

   // text 출력
   public ArrayList<String> printText() {
      for (int j = 0; j < AllSave.size(); j++) {
         return AllSave;
      }
      return null;
   }

   // 야민정음 번역 함수
   public void searchSlang(ArrayList<String> temp, String user) {

      for (int i = 0; i < temp.size(); i++) {
         String talk = temp.get(i);
         String[] word = talk.split(":");
         word[1].trim();

         StringTokenizer token = new StringTokenizer(word[1], " ");

         while (token.hasMoreTokens()) {
            String slangKey = slang.get(token.nextToken());

            if (slangKey != null) {
               AllSave.add(user + " : " + word[1] + " → " + slangKey + "\n");
            }
         }
      }
   }
}