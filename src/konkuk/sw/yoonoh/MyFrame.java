package konkuk.sw.yoonoh;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFrame extends JFrame implements ActionListener {
   // icon
   private ImageIcon mainLogo;
   private ImageIcon panelLogo;

   private Curse curse;
   private Slang slang;
   private SendEMail email;

   // FileWriter
   private FileWriter reader;

   // FileChooser
   private JFileChooser fileChooser;

   // CardLayout
   private CardLayout card;

   // container
   private Container mainContainer;

   // Panel
   private JPanel showLogoPanel;
   private JPanel mainMenuPanel;
   private JPanel curseAnalysisPanel; // 욕설분석 패널
   private JPanel slangAnalysisPanel; // 야민정음 분석 패널
   private JPanel addWordPanel;
   private JPanel showCursePanel;
   private JPanel showSlangPanel;

   // Button
   private JButton curseAnalysisButton;
   private JButton slangAnalysisButton;
   private JButton addWordButton;
   private JButton homeButton;
   private JButton sendWordButton;
   private JButton nextButton;
   private JButton analysisButton1;
   private JButton analysisButton2;
   private JButton FileOpenButton1;
   private JButton FileOpenButton2;
   private JButton exitButton;

   // TextField
   private JTextField sendWord;
   private JTextArea slangText;
   private JTextArea curseText;

   // label
   private JLabel sendLabel;
   private JLabel sendExplain[];
   private JLabel curseExplain[];
   private JLabel slangExplain[];
   private JLabel Developer;

   // Font
   private Font font1Bold;
   private Font font1Bold2;
   private Font font1Bold3;
   private Font font1Plane;

   // scrollPane
   private JScrollPane scrollPane1;
   private JScrollPane scrollPane2;

   // screenSize
   private int screenWidth;
   private int screenHeight;

   public MyFrame() {

      curse = new Curse();
      slang = new Slang();
      card = new CardLayout();

      try {
         reader = new FileWriter("C:\\savingJAVA\\test.txt", true);
      } catch (IOException e) {
         // TODO 자동 생성된 catch 블록
         e.printStackTrace();
      }

      // 아이콘설정
      Toolkit kit = Toolkit.getDefaultToolkit();
      Dimension screenSize = kit.getScreenSize();
      screenHeight = screenSize.height;
      screenWidth = screenSize.width;

      font1Bold = new Font("배달의민족 주아", Font.BOLD, 20);
      font1Bold2 = new Font("배달의민족 주아", Font.BOLD, 13);
      font1Bold3 = new Font("배달의민족 주아", Font.BOLD, 25);
      font1Plane = new Font("배달의민족 주아", Font.PLAIN, 20);

      setTitle("Language Korrection");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료
      Image img = kit.getImage("images\\icon.png"); // 경로
      this.setIconImage(img);
      mainContainer = this.getContentPane();
      setSize(1050, 630);
      init();

      showLogoPanel.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            card.show(mainContainer, "2");
         }
      });
      setVisible(true);
   }

   public void CreatePanel() {

      mainLogo = new ImageIcon("images\\Main.png");
      panelLogo = new ImageIcon("images\\mainLogo.png");
      mainContainer.setLayout(card);

      // 첫 화면 로고
      showLogoPanel = new JPanel(null) {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(mainLogo.getImage(), 0, 0, d.width, d.height, null);
            super.paintComponents(g);
         }
      };

      // 메인 메뉴
      mainMenuPanel = new JPanel(null) {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(panelLogo.getImage(), 0, 0, d.width, d.height, null);
            super.paintComponents(g);
         }
      };

      // 욕설 분석
      curseAnalysisPanel = new JPanel(null) {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(panelLogo.getImage(), 0, 0, d.width, d.height, null);
            super.paintComponents(g);
         }
      };

      // 야민정음 번역
      slangAnalysisPanel = new JPanel(null) {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(panelLogo.getImage(), 0, 0, d.width, d.height, null);
            super.paintComponents(g);
         }
      };

      // 단어 추가
      addWordPanel = new JPanel(null) {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(panelLogo.getImage(), 0, 0, d.width, d.height, null);
            super.paintComponents(g);
         }
      };

      // 욕설 그래프
      showCursePanel = new JPanel(null) {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(panelLogo.getImage(), 0, 0, d.width, d.height, null);
            super.paintComponents(g);
         }
      };
   }

   public void addPanelOnContainer() {
      mainContainer.add("1", showLogoPanel);
      mainContainer.add("2", mainMenuPanel);
      mainContainer.add("3", curseAnalysisPanel);
      mainContainer.add("4", slangAnalysisPanel);
      mainContainer.add("5", addWordPanel);
      mainContainer.add("6", showCursePanel);
   }

   public void init() {
      CreatePanel();
      CreateButton();
      addPanelOnContainer();
      addTextField();
      addLabel();
   }

   // JLabel 생성
   public void addLabel() {

      // 보내기 설명
      sendExplain = new JLabel[3];
      sendExplain[0] = new JLabel("1. 추가하고자하는 욕설 또는 야민정음을 적는다.");
      sendExplain[0].setFont(font1Bold3);
      sendExplain[0].setSize(800, 200);
      sendExplain[0].setLocation(270, 110);
      sendExplain[1] = new JLabel("2. 야민정음의 경우 원래 단어도 같이 적는다.");
      sendExplain[1].setFont(font1Bold3);
      sendExplain[1].setSize(800, 200);
      sendExplain[1].setLocation(270, 150);
      sendExplain[2] = new JLabel("3. 보내기 버튼을 누른다.");
      sendExplain[2].setFont(font1Bold3);
      sendExplain[2].setSize(800, 200);
      sendExplain[2].setLocation(270, 190);

      sendLabel = new JLabel("추가할 단어  : ");
      sendLabel.setFont(font1Bold);
      sendLabel.setSize(150, 30);
      sendLabel.setLocation(370, 400);

      // 욕설 분석 설명
      curseExplain = new JLabel[3];
      curseExplain[0] = new JLabel("1. 파일 열기버튼을 눌러서 대화내용을 불러온다.");
      curseExplain[0].setFont(font1Bold3);
      curseExplain[0].setSize(470, 300);
      curseExplain[0].setLocation(70, 90);

      curseExplain[1] = new JLabel("2. 분석 버튼을 누른다.");
      curseExplain[1].setFont(font1Bold3);
      curseExplain[1].setSize(470, 300);
      curseExplain[1].setLocation(70, 130);

      curseExplain[2] = new JLabel("3. 다음 버튼을 누른다.");
      curseExplain[2].setFont(font1Bold3);
      curseExplain[2].setSize(470, 300);
      curseExplain[2].setLocation(70, 170);

      // 야민정음 번역 설명
      slangExplain = new JLabel[3];
      slangExplain[0] = new JLabel("1. 파일 열기버튼을 눌러서 대화내용을 불러온다.");
      slangExplain[0].setFont(font1Bold3);
      slangExplain[0].setSize(470, 300);
      slangExplain[0].setLocation(70, 90);

      slangExplain[1] = new JLabel("2. 분석 버튼을 누른다.");
      slangExplain[1].setFont(font1Bold3);
      slangExplain[1].setSize(470, 300);
      slangExplain[1].setLocation(70, 130);

      slangExplain[2] = new JLabel("3. 다음 버튼을 누른다.");
      slangExplain[2].setFont(font1Bold3);
      slangExplain[2].setSize(470, 300);
      slangExplain[2].setLocation(70, 170);

      addWordPanel.add(sendLabel);
      addWordPanel.add(sendExplain[0]);
      addWordPanel.add(sendExplain[1]);
      addWordPanel.add(sendExplain[2]);
      curseAnalysisPanel.add(curseExplain[0]);
      curseAnalysisPanel.add(curseExplain[1]);
      curseAnalysisPanel.add(curseExplain[2]);
      slangAnalysisPanel.add(slangExplain[0]);
      slangAnalysisPanel.add(slangExplain[1]);
      slangAnalysisPanel.add(slangExplain[2]);
   }

   // JTextField와 JTextArea 생성
   public void addTextField() {

      // 욕설 분석
      curseText = new JTextArea();
      curseText.setSize(400, 350);
      curseText.setLocation(550, 50);
      curseText.setEditable(false);

      // 추가할 단어
      sendWord = new JTextField(50);
      sendWord.setSize(100, 35);
      sendWord.setLocation(510, 400);

      // 야민정음 번역
      slangText = new JTextArea();
      slangText.setSize(400, 350);
      slangText.setLocation(550, 50);
      slangText.setEditable(false);

      addWordPanel.add(sendWord);
      curseAnalysisPanel.add(curseText);
      slangAnalysisPanel.add(slangText);
   }

   // JTextArea에 출력
   public void addLogCurse(ArrayList<String> arrayList) {
      curseText.append(arrayList + "\n");
      curseText.setCaretPosition(curseText.getDocument().getLength());
   }

   public void addLogSlang(ArrayList<String> arrayList) {
      slangText.append(arrayList + "\n");
      slangText.setCaretPosition(slangText.getDocument().getLength());
   }

   public void CreateButton() {

      ImageIcon curseIcon = new ImageIcon("images\\curse.png");
      ImageIcon slangIcon = new ImageIcon("images\\slang.png");
      ImageIcon addwordIcon = new ImageIcon("images\\addWord.png");

      // 욕설 분석 버튼
      curseAnalysisButton = new JButton() {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(curseIcon.getImage(), 0, 0, d.width, d.height, null);
            setOpaque(false);
            super.paintComponents(g);
         }
      };
      curseAnalysisButton.setBounds(85, 100, 250, 350);
      curseAnalysisButton.addActionListener(this);
      curseAnalysisButton.setFont(font1Bold);
      curseAnalysisButton.setBorderPainted(false);
      mainMenuPanel.add(curseAnalysisButton);

      // 야민정음 분석 버튼
      slangAnalysisButton = new JButton() {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(slangIcon.getImage(), 0, 0, d.width, d.height, null);
            setOpaque(false);
            super.paintComponents(g);
         }
      };
      slangAnalysisButton.setBounds(395, 100, 250, 350);
      slangAnalysisButton.addActionListener(this);
      slangAnalysisButton.setFont(font1Bold);
      slangAnalysisButton.setBorderPainted(false);
      mainMenuPanel.add(slangAnalysisButton);

      // 단어 추가 버튼
      addWordButton = new JButton() {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(addwordIcon.getImage(), 0, 0, d.width, d.height, null);
            setOpaque(false);
            super.paintComponents(g);
         }
      };
      addWordButton.setBounds(705, 100, 250, 350);
      addWordButton.addActionListener(this);
      addWordButton.setFont(font1Bold);
      addWordButton.setBorderPainted(false);
      mainMenuPanel.add(addWordButton);

      // 홈버튼
      homeButton = new JButton("Home");
      homeButton.setSize(70, 30);
      homeButton.setLocation(465, 490);
      homeButton.addActionListener(this);
      homeButton.setFont(font1Bold2);

      // 보내기 버튼
      sendWordButton = new JButton("보내기");
      sendWordButton.setSize(90, 30);
      sendWordButton.setLocation(455, 450);
      sendWordButton.addActionListener(this);
      sendWordButton.setFont(font1Bold);
      addWordPanel.add(sendWordButton);

      // 다음 버튼
      nextButton = new JButton("다음");
      nextButton.setSize(90, 30);
      nextButton.setLocation(455, 440);
      nextButton.addActionListener(this);
      nextButton.setFont(font1Bold);

      // 분석 버튼
      analysisButton1 = new JButton("분석");
      analysisButton1.setSize(90, 50);
      analysisButton1.setLocation(780, 450);
      analysisButton1.addActionListener(this);
      analysisButton1.setFont(font1Bold);

      analysisButton2 = new JButton("분석");
      analysisButton2.setSize(90, 50);
      analysisButton2.setLocation(780, 450);
      analysisButton2.addActionListener(this);
      analysisButton2.setFont(font1Bold);

      // 파일 열기 버튼
      FileOpenButton1 = new JButton("파일 열기");
      FileOpenButton1.setSize(120, 50);
      FileOpenButton1.setLocation(650, 450);
      FileOpenButton1.addActionListener(new OpenActionListener1());
      FileOpenButton1.setFont(font1Bold);

      FileOpenButton2 = new JButton("파일 열기");
      FileOpenButton2.setSize(120, 50);
      FileOpenButton2.setLocation(650, 450);
      FileOpenButton2.addActionListener(new OpenActionListener2());
      FileOpenButton2.setFont(font1Bold);

      // 종료 버튼
      exitButton = new JButton("종료");
      exitButton.setBounds(400, 500, 200, 60);
      exitButton.addActionListener(this);
      exitButton.setFont(font1Bold);
      mainMenuPanel.add(exitButton);
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      // 홈 버튼
      if (e.getSource() == homeButton) {
         homeButton.setLocation(465, 495);
         setSize(1050, 630);
         card.show(mainContainer, "2");
      }

      // 욕설 분석 버튼
      else if (e.getSource() == curseAnalysisButton) {
         card.show(mainContainer, "3");
         curseAnalysisPanel.add(nextButton);
         curseAnalysisPanel.add(homeButton);
         curseAnalysisPanel.add(analysisButton1);
         curseAnalysisPanel.add(FileOpenButton1);
      }

      // 야민정음 번역 버튼
      else if (e.getSource() == slangAnalysisButton) {
         card.show(mainContainer, "4");
         slangAnalysisPanel.add(homeButton);
         slangAnalysisPanel.add(analysisButton2);
         slangAnalysisPanel.add(FileOpenButton2);
      }

      // 단어 추가 버튼
      else if (e.getSource() == addWordButton) {
         card.show(mainContainer, "5");
         addWordPanel.add(homeButton);
      }

      // 보내기 버튼
      else if (e.getSource() == sendWordButton) {
         try {
            String tempStr = sendWord.getText();
            reader.write(tempStr + "\n");
            sendWord.setText(null);
            email = new SendEMail(tempStr);
         } catch (IOException e1) {
            // TODO 자동 생성된 catch 블록
            e1.printStackTrace();
         }
      }

      // 다음 버튼
      else if (e.getSource() == nextButton) {
         card.show(mainContainer, "6");
         showCursePanel.add(homeButton);
         curse.setGraph();
      }

      // 분석 버튼
      else if (e.getSource() == analysisButton1) {
         addLogCurse(curse.printText());
         scrollPane1 = new JScrollPane(curseText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
               JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollPane1.setBounds(550, 50, 400, 350);
         curseAnalysisPanel.add(scrollPane1);
      }

      else if (e.getSource() == analysisButton2) {
         addLogSlang(slang.printText());
         scrollPane2 = new JScrollPane(slangText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
               JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollPane2.setBounds(550, 50, 400, 350);
         slangAnalysisPanel.add(scrollPane2);
      }

      // 종료시 팝업 창
      else if (e.getSource() == exitButton) {

         ImageIcon ExitIcon = new ImageIcon("images\\exit.png");

         int Exit = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료", JOptionPane.YES_OPTION,
               JOptionPane.NO_OPTION, ExitIcon);

         if (Exit == JOptionPane.YES_OPTION) {
            try {
               reader.close();
            } catch (IOException e1) {
               // TODO 자동 생성된 catch 블록
               e1.printStackTrace();
            }
            System.exit(0);
         }

         else {
            card.show(mainContainer, "2");
         }
      }
   }

   // 욕설 파일 열기
   class OpenActionListener1 implements ActionListener {
      OpenActionListener1() {
         fileChooser = new JFileChooser();
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO 자동 생성된 메소드 스텁
         FileNameExtensionFilter filter = new FileNameExtensionFilter("text 파일", "txt");
         fileChooser.setFileFilter(filter);
         int ret = fileChooser.showOpenDialog(curseAnalysisPanel);
         if (ret != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "파일을 선택하셔야 분석이 가능합니다.", " Warning", JOptionPane.WARNING_MESSAGE);
            return;
         } else {
            String path = fileChooser.getSelectedFile().getPath();
            curse.start(path);
         }
      }
   }

   // 야민정음 파일 열기
   class OpenActionListener2 implements ActionListener {
      OpenActionListener2() {
         fileChooser = new JFileChooser();
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO 자동 생성된 메소드 스텁
         FileNameExtensionFilter filter = new FileNameExtensionFilter("text 파일", "txt");
         fileChooser.setFileFilter(filter);
         int ret = fileChooser.showOpenDialog(curseAnalysisPanel);
         if (ret != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "파일을 선택하셔야 분석이 가능합니다.", " Warning", JOptionPane.WARNING_MESSAGE);
            return;
         } else {
            String path = fileChooser.getSelectedFile().getPath();
            slang.start(path);
         }
      }
   }
}