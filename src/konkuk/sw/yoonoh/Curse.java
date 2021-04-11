package konkuk.sw.yoonoh;

import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Curse {

   // savingText
   private ArrayList<String> User1SavingTextLine = new ArrayList<String>();
   private ArrayList<String> User2SavingTextLine = new ArrayList<String>();
   private ArrayList<String> AllSavingTextLine = new ArrayList<String>();
   private ArrayList<String> AllCurse = new ArrayList<String>();

   // savingCurse
   private HashMap<Integer, String> Curse = new HashMap<Integer, String>();

   // user
   private String User1, User2;
   private boolean User1Check, User2Check;
   private int User1ChatNum, User2ChatNum;

   // temp
   private String temp, temp2, temp3, temp4, temp5;

   // CheckCurseNum
   private int[] User1CurseNum;
   private int[] User2CurseNum;

   // file Lines;
   private int countLines;

   // curse
   ArrayList<Integer> CurseNum1 = new ArrayList<Integer>();
   ArrayList<String> CurseName1 = new ArrayList<String>();
   ArrayList<Integer> CurseNum2 = new ArrayList<Integer>();
   ArrayList<String> CurseName2 = new ArrayList<String>();

   Curse() {
      try {
         countLines = countLines("Curse.txt");
      } catch (IOException e1) {
         // TODO 자동 생성된 catch 블록
         e1.printStackTrace();
      }

      User1CurseNum = new int[countLines];
      User2CurseNum = new int[countLines];

      try {
         Scanner scan2 = new Scanner(new File("Curse.txt"), "UTF-8");
         while (scan2.hasNextLine()) {
            String key = scan2.nextLine();
            AllCurse.add(key);
            int hash = getKey(key);

            if (key.equals(Curse.get(hash))) {
            }

            else
               Curse.put(hash, key);
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   // hash값 가져오기
   public int getKey(String key) {
      char[] ch = key.toCharArray();
      int hash = 0;

      for (int i = 0; i < key.length(); i++) {
         hash = key.hashCode() % 3319; // hash값 저장
      }

      return hash;
   }

   // 욕설 분석하고 어떤 사용자가 얼마나 욕을 했는지 나타냄
   public void searchWord(ArrayList<String> tmp, String name) {
      if (name.equals(User1)) {
         CurseNum1.clear();
         CurseName1.clear();
         int cnt = 0;
         for (int i = 0; i < tmp.size(); i++) {
            String road = tmp.get(i);
            String[] trim = road.split(":");
            StringTokenizer temp = new StringTokenizer(trim[1], " ");

            if (temp.hasMoreTokens()) {
               while (temp.hasMoreTokens()) {
                  String sav = temp.nextToken();

                  for (int j = 0; j < AllCurse.size(); j++) {
                     if (sav.contains(AllCurse.get(j))) {
                        String v = AllCurse.get(j);
                        if (cnt != 0) {
                           for (int k = 0; k < cnt; k++) {
                              if (v.equals(CurseName1.get(k))) {
                                 CurseNum1.set(k, (CurseNum1.get(k) + 1));
                                 CurseName1.set(k, v);
                                 cnt--;
                                 break;
                              }

                              else {
                                 CurseName1.add(cnt, v);
                                 CurseNum1.add(cnt, 1);
                              }
                           }
                        }

                        else {
                           CurseName1.add(cnt, v);
                           CurseNum1.add(cnt, 1);
                        }
                        cnt++;
                     }
                  }
               }
            }
         }

         for (int k = 0; k < cnt; k++) {
            AllSavingTextLine.add(name + " 님이 " + CurseName1.get(k) + " 이라는 욕설을 " + CurseNum1.get(k) + "번 사용함.\n");
         }
      } else if (name.equals(User2)) {
         CurseName2.clear();
         CurseNum2.clear();
         int cnt = 0;
         for (int i = 0; i < tmp.size(); i++) {
            String road = tmp.get(i);
            String[] trim = road.split(":");
            StringTokenizer temp = new StringTokenizer(trim[1], " ");

            if (temp.hasMoreTokens()) {
               while (temp.hasMoreTokens()) {
                  String sav = temp.nextToken();

                  for (int j = 0; j < AllCurse.size(); j++) {
                     if (sav.contains(AllCurse.get(j))) {
                        String v = AllCurse.get(j);
                        if (cnt != 0) {
                           for (int k = 0; k < cnt; k++) {
                              if (v.equals(CurseName2.get(k))) {
                                 CurseNum2.set(k, (CurseNum2.get(k) + 1));
                                 CurseName2.set(k, v);
                                 cnt--;
                                 break;
                              }

                              else {
                                 CurseName2.add(cnt, v);
                                 CurseNum2.add(cnt, 1);
                              }
                           }
                        }

                        else {
                           CurseName2.add(cnt, v);
                           CurseNum2.add(cnt, 1);
                        }
                        cnt++;
                     }
                  }
               }
            }
         }

         for (int k = 0; k < cnt; k++) {
            AllSavingTextLine.add(name + " 님이 " + CurseName2.get(k) + " 이라는 욕설을 " + CurseNum2.get(k) + "번 사용함.\n");
         }
      }
   }

   // 파일 줄 수 세기
   public static int countLines(String filename) throws IOException {
      InputStream is = new BufferedInputStream(new FileInputStream(filename));
      try {
         byte[] c = new byte[1024];
         int count = 0;
         int readChars = 0;
         boolean empty = true;
         while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
               if (c[i] == '\n') {
                  ++count;
               }
            }
         }
         return (count == 0 && !empty) ? 1 : count;
      } finally {
         is.close();
      }
   }

   // 파일 경로 찾아서 가져오기, 대화 분석
   public void start(String path) {

      User1Check = false;
      User2Check = false;

      User1ChatNum = 0;
      User2ChatNum = 0;

      User1 = null;
      User2 = null;

      User1SavingTextLine.clear();
      User2SavingTextLine.clear();
      AllSavingTextLine.clear();

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
      searchWord(User1SavingTextLine, User1);
      searchWord(User2SavingTextLine, User2);
      AllSavingTextLine.add(User1 + "님이 " + User1ChatNum + "번 이야기함\n");
      AllSavingTextLine.add(User2 + "님이 " + User2ChatNum + "번 이야기함");
   }

   // 사용자가 얼마나 욕설을 했는지 가져오기
   public ArrayList<String> printText() {
      for (int j = 0; j < AllSavingTextLine.size(); j++) {
         return AllSavingTextLine;
      }
      return null;
   }

   public void setGraph() {
      Graphs a = new Graphs(CurseName1, CurseNum1, User1);
      Graphs b = new Graphs(CurseName2, CurseNum2, User2);
   }
}