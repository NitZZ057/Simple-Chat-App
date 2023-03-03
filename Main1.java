package com.ChatApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class Main1 extends JFrame implements ActionListener {

    public static JTextField Uname = new JTextField();
    JButton signIn = new JButton("Log In");
    static boolean f = true;
    static boolean g = true;
    static int port = 7777;
    static ArrayList<String> users = new ArrayList<>();

    BufferedImage pic = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\Wim.jpg"));
    JLabel lb = new JLabel(new ImageIcon(pic));


    Main1() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(900,100,300,250);
        this.setVisible(true);

        lb.setBounds(0,0,300,250);
        add(lb);

        Uname.setBounds(65,50,170,30);
        Uname.setFont(new Font("ARIAL", Font.BOLD,17));
        Uname.setHorizontalAlignment(SwingConstants.CENTER);
        add(Uname);

        signIn.setBounds(100,100,90,30);
        signIn.setBackground(new Color(0,170,0));
        signIn.setForeground(new Color(255,255,255));
        signIn.setFocusable(false);
        signIn.addActionListener(this);
        add(signIn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            User user = new User();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        Main1 m = new Main1();
    }
}





