package com.ChatApp;

import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class User extends JFrame  {

    static JLabel[] l1 = new JLabel[10];
    static JLabel[] l = new JLabel[10];
    static JPanel p1 = new JPanel();
    static JPanel p2 = new JPanel();
    static JPanel p3 = new JPanel();
    static JPanel p4 = new JPanel();
    static String Me ;


    BufferedImage pic = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\Wim1.jpg"));
    JLabel lb = new JLabel(new ImageIcon(pic));

    BufferedImage pic1 = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\Screen3.png"));
    JLabel lb1 = new JLabel(new ImageIcon(pic1));

    BufferedImage pic3 = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\back11.png"));
    JLabel lb3 = new JLabel(new ImageIcon(pic3));

    User() throws IOException {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBounds(20,70,400,600);
        this.setVisible(true);

        Main1.users.add(Main1.Uname.getText());
        Me = Main1.Uname.getText();
        Main1.Uname.setText("");
        Main1.g = false;
        p1.setLayout(null);
        p1.setPreferredSize(new Dimension(50,80));
        p1.setBackground(new Color(7,94,84));
        add(p1,BorderLayout.NORTH);
        lb1.setBounds(7,28,28,20);
        lb3.setBounds(200,25,195,50);
        p1.add(lb1);
        p1.add(lb3);

        lb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        p2.setLayout(null);
        p2.setPreferredSize(new Dimension(500,100));
        add(p2,BorderLayout.CENTER);

        p3.setLayout(new BorderLayout());
        p3.setPreferredSize(new Dimension(230,80));
        p3.setBackground(new Color(255,255,255));
        add(p3,BorderLayout.EAST);
        p3.add(lb);


        p4.setLayout(new BorderLayout());
        p4.setPreferredSize(new Dimension(50,30));
        p4.setBackground(new Color(64,64,64));
        add(p4,BorderLayout.SOUTH);

        int y=13;
        for(int i=0;i<Main1.users.size();i++){

            l[i] = new JLabel();
            l1[i] = new JLabel("Online");
            l[i].setText(Main1.users.get(i));

            l[i].setForeground(new Color(0,255,0));
            l1[i].setForeground(new Color(0,255,0));

            l[i].setFont(new Font("ARIAL", Font.BOLD,15));
            l1[i].setFont(new Font("ARIAL", Font.BOLD,15));

            l[i].setBounds(60,y,60,15);
            l1[i].setBounds(5,y,70,15);

            p2.add(l1[i]);
            p2.add(l[i]);

            int finalI = i;
            l[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Runnable t2 = () ->{
                        try {
                            if(Main1.f){
                                Chat c1 =new Chat(l[finalI].getText());
                                Main1.f = false;
                            }else {
                                Chat2 c2 =new Chat2(l[finalI].getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    };
                    new Thread(t2).start();
                }

            });
            y+=20;
        }

    }

    public static void main(String[] args) {

    }
}


