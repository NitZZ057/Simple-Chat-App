package com.ChatApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Chat extends JFrame {
    ServerSocket server;
    Socket socket;

    static boolean clicked = false;

    DataInputStream din ;
    DataOutputStream out ;

    static String peer,sendMsg,receiveMsg;
    JLabel l1,l2;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel line = new JPanel();
    JPanel line1 = new JPanel();
    JPanel line2 = new JPanel();
    JPanel line3 = new JPanel();
    JTextField t = new JTextField();

    Box vertical = Box.createVerticalBox();

    BufferedImage pic1 = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\Screen3.png"));
    JLabel lb1 = new JLabel(new ImageIcon(pic1));

    BufferedImage pic2 = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\profil22.png"));
    JLabel lb2 = new JLabel(new ImageIcon(pic2));

    BufferedImage pic3 = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\back11.png"));
    JLabel lb3 = new JLabel(new ImageIcon(pic3));

    BufferedImage pic4 = ImageIO.read(new File("E:\\E users\\nitesh\\src\\com\\ChatApp\\send5.png"));
    JLabel lb4 = new JLabel(new ImageIcon(pic4));

    Chat(String peer) throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBounds(630,70, 400, 600);
        this.setVisible(true);
        Chat.peer = peer;

        p1.setLayout(null);
        p1.setPreferredSize(new Dimension(50, 80));
        p1.setBackground(new Color(7, 94, 84));
        add(p1, BorderLayout.NORTH);
        lb1.setBounds(7, 28, 28, 20);
        lb2.setBounds(45, 20, 60, 60);
        lb3.setBounds(200, 25, 195, 50);
        p1.add(lb1);
        p1.add(lb2);
        p1.add(lb3);

        lb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        p2.setLayout(new BorderLayout());
        p2.setPreferredSize(new Dimension(500, 100));
        add(p2, BorderLayout.CENTER);

        line.setLayout(null);
        line.setPreferredSize(new Dimension(50, 5));
        line.setBackground(new Color(255, 255, 255));
        p2.add(line, BorderLayout.NORTH);

        line1.setLayout(null);
        line1.setPreferredSize(new Dimension(5, 5));
        line1.setBackground(new Color(255, 255, 255));
        p2.add(line1, BorderLayout.EAST);

        line2.setLayout(null);
        line2.setPreferredSize(new Dimension(50, 5));
        line2.setBackground(new Color(255, 255, 255));
        p2.add(line2, BorderLayout.SOUTH);

        line3.setLayout(null);
        line3.setPreferredSize(new Dimension(5, 5));
        line3.setBackground(new Color(255, 255, 255));
        p2.add(line3, BorderLayout.WEST);

        p4.setLayout(new BorderLayout());
        p4.setPreferredSize(new Dimension(50, 30));
        p4.setBackground(new Color(64, 64, 64));
        add(p4, BorderLayout.SOUTH);
        t.setFont(new Font("Helvetica", Font.PLAIN, 17));
        p4.add(t);

        p3.setLayout(new BorderLayout());
        p3.setPreferredSize(new Dimension(70, 30));
        p3.setBackground(new Color(64, 64, 64));
        lb4.setBounds(0, 0, 40, 10);
        p3.add(lb4);
        p4.add(p3, BorderLayout.EAST);

        l1 = new JLabel("online");
        l1.setFont(new Font("ARIAL", Font.BOLD, 10));
        l1.setBounds(112, 45, 90, 30);
        l1.setForeground(new Color(255, 255, 255));
        p1.add(l1);
        l2 = new JLabel(peer);
        l2.setFont(new Font("ARIAL", Font.BOLD, 17));
        l2.setBounds(110, 30, 90, 30);
        l2.setForeground(new Color(255, 255, 255));
        p1.add(l2);


        server = new ServerSocket(Main1.port);
            Runnable t3 = () -> {
                try {
                    socket = server.accept();
                        din = new DataInputStream(socket.getInputStream());
                        out = new DataOutputStream(socket.getOutputStream());

                        startReading();

                        lb4.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                sendMsg = t.getText();
                                startWriting(sendMsg);
                                Chat2.click = true;

                            }
                        });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            };
            new Thread(t3).start();

        }



    public void startReading(){
        Runnable r1 = ()->{
            try {
                while (true) {
                        receiveMsg = din.readUTF();
                        JPanel panel = formatLabel1(receiveMsg);
                        JPanel left = new JPanel(new BorderLayout());
                        left.add(panel, BorderLayout.LINE_START);
                        vertical.add(left);
                        repaint();
                        invalidate();
                        validate();
                        clicked = false;


                }


            }catch (Exception e){
                e.printStackTrace();
            }
        };
        new Thread(r1).start();
    }

    public void startWriting(String msg){
        Runnable r2 = ()->{
            try {
                    out.writeUTF(msg);
                    out.flush();
                    JPanel pan = formatLabel(msg);
                    JPanel right = new JPanel(new BorderLayout());
                    right.add(pan,BorderLayout.LINE_END);
                    vertical.add(right);
                    vertical.add(Box.createVerticalStrut(15));
                    p2.add(vertical,BorderLayout.PAGE_START);

                    t.setText("");
                    repaint();
                    invalidate();
                    validate();

            }catch (Exception e){
                e.printStackTrace();
            }
        };
        new Thread(r2).start();
    }

    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style = \"width: 110px\">"+out+"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(7,94,94));
        output.setForeground(new Color(255,255,255));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,15));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }

    public static JPanel formatLabel1(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style = \"width: 110px\">"+out+"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(64,64,64));
        output.setForeground(new Color(255,255,255));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,15));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }

    public static void main(String[] args) throws IOException {

    }
}
