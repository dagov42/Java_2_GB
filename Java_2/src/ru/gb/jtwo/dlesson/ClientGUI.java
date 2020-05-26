package ru.gb.jtwo.dlesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("Dmitriy");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private final JLabel usersLabel = new JLabel("Members: ");
    private JMenuBar mainMenu = new JMenuBar();
    private final JMenu menuActions = new JMenu("Actions");
    private final JMenu menuAbout = new JMenu("About");
    private final JMenuItem menuAboutMessenger = new JMenuItem("About Chat");
    private final JMenuItem menuAddNewUser = new JMenuItem("Add New User");
    private final JMenuItem menuChangePassword = new JMenuItem("Change Your Password");
    private final JMenuItem menuFileSave = new JMenuItem("Save log");
    private final JMenuItem menuFileLoad = new JMenuItem("Load log");
    private final JMenuItem menuFileExit = new JMenuItem("Exit");


    private final JList<String> userList = new JList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat");

        setJMenuBar(mainMenu);
        mainMenu.add(menuActions);
        mainMenu.add(menuAbout);
        menuActions.add(menuAddNewUser);
        menuActions.add(menuChangePassword);
        menuActions.add(menuFileSave);
        menuActions.add(menuFileLoad);
        menuActions.addSeparator();
        menuActions.add(menuFileExit);
        menuAbout.add(menuAboutMessenger);

        menuAddNewUser.addActionListener(this::actionPerformed);
        menuChangePassword.addActionListener(this::actionPerformed);
        menuFileSave.addActionListener(this::actionPerformed);
        menuFileLoad.addActionListener(this::actionPerformed);
        menuFileExit.addActionListener(this::actionPerformed);
        menuAboutMessenger.addActionListener(this::actionPerformed);

        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUser = new JScrollPane(userList);
        String[] users = {"user1", "user2", "user3", "user4", "user5",
                "user_with_an_exceptionally_long_name_in_this_chat"};
        userList.setListData(users);
        scrollUser.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        btnSend.addActionListener(new SendMSG());

        add(scrollLog, BorderLayout.CENTER);
        add(scrollUser, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);

        tfMessage.getRootPane().setDefaultButton(btnSend);


        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } if (src == menuFileSave) {
            JFileChooser file = new JFileChooser();
            int choose = file.showDialog(null, "Save log into file");
            if (choose == JFileChooser.APPROVE_OPTION){
                File chooseFile = file.getSelectedFile();
                String data = log.getText();
                saveData(chooseFile, data);
            }
        } if (src == menuFileLoad) {
            JFileChooser file = new JFileChooser();
            int choose = file.showDialog(null, "Load log from file");
            if (choose == JFileChooser.APPROVE_OPTION){
                File chooseFile = file.getSelectedFile();
                log.setText(loadData(chooseFile));
            }
        } if (src == menuFileExit) {
            System.exit(0);
        }else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    private String loadData(File chooseFile) {
        StringBuilder response = new StringBuilder("");
        Scanner scanner = null;
        try {
            scanner = new Scanner(chooseFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            response.append(str).append("\n");
        }
        return response.toString();
    }

    private void saveData(File file, String data) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = String.format("Exception in thread \"%s\" %s: %s\n\t at %s",
                t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    public class SendMSG implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if (!tfMessage.getText().isEmpty()){
                String nickname = tfLogin.getText();
                if (nickname.isEmpty()) nickname = "Anonimus";
                log.append(nickname + ": " + tfMessage.getText() + "\n");
            }
            tfMessage.setText(" ");
            tfMessage.grabFocus();
        }
    }

}

