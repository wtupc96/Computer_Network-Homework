package frontEnd;

import backEnd.Chat;
import backEnd.TextMessageReceiver;
import backEnd.TextMessageSender;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;

import static frontEnd.MultiFuncMenu.*;

/**
 * Created by wtupc96 on 2017/4/10.
 */
public class TopMenu extends JMenuBar {
    private static final JMenuItem jBreakMenuItem = new JMenuItem("连接");
    private static final JMenuItem jQuitMenuItem = new JMenuItem("退出");
    private static Chat chat;
    private static TextMessageSender textMessageSender;
    private static TextMessageReceiver textMessageReceiver;
    private static String input = "";
    private static boolean flag = false;
    private static Thread sendThread;
    private static Thread receiveThread;

    static {
        jQuitMenuItem.addActionListener(e -> {
            if (JOptionPane.
                    showConfirmDialog(null, "你确定退出吗?", "退出", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        });

        jBreakMenuItem.addActionListener(e -> {
            if (jBreakMenuItem.getText().equals("连接")) {
                input = JOptionPane.showInputDialog("请输入你想要连接的IP地址和端口号：");
                if (input != null &&
                        input.matches("^(\\d{1,3}\\.){3}\\d{1,3}:\\d{1,10}$")) {
                    chat = new Chat(input);

                    try {
                        textMessageSender = new TextMessageSender(input);
                        flag = true;

                        sendThread = new Thread(new Runnable() {
                            String message;

                            @Override
                            public void run() {
                                while (true) {
                                    if ((message = textMessageSender.receiveMessage()) == null) {
                                        break;
                                    }
                                    ChattingRoom.getjChattingRoomTextField().append(new Date().toString() + "   " + input + "说：\n" + message + "\n\n");
                                }
                                jBreakMenuItem.doClick();
                                sendThread.interrupt();
                            }
                        });

                        sendThread.start();
                    } catch (IOException e1) {
                        receiveThread = new Thread(new Runnable() {
                            String message;

                            @Override
                            public void run() {
                                textMessageReceiver = new TextMessageReceiver();
                                flag = false;
                                while (true) {
                                    if ((message = textMessageReceiver.receiveMessage()) == null) {
                                        break;
                                    }
                                    ChattingRoom.getjChattingRoomTextField().append(new Date().toString() + "   " + input + "说：\n" + message + "\n\n");
                                }
                                jBreakMenuItem.doClick();
                                receiveThread.interrupt();
                            }
                        });

                        receiveThread.start();
                    }

                    getjTalkButton().setEnabled(true);
                    getjVideoButton().setEnabled(true);
                    getjMessageTextArea().setEnabled(true);

                    jBreakMenuItem.setText("断开连接");
                } else {
                    JOptionPane.showMessageDialog(null, "请输入合法的IP地址和端口号！");
                }
            } else {
                chat.soundStop();

                JButton jTalkButton = getjTalkButton();
                JButton jVideoButton = getjVideoButton();
                jTalkButton.setEnabled(false);
                jTalkButton.setText("开始语音");
                jVideoButton.setEnabled(false);
                jVideoButton.setText("开始视频");
                getjMessageTextArea().setEnabled(false);

                jBreakMenuItem.setText("连接");
            }
        });
    }

    TopMenu() {
        this.add(jBreakMenuItem);
        this.add(jQuitMenuItem);
    }

    public static boolean getFlag() {
        return flag;
    }

    public static Chat getChat() {
        return chat;
    }

    public static TextMessageSender getTextMessageSender() {
        return textMessageSender;
    }

    public static TextMessageReceiver getTextMessageReceiver() {
        return textMessageReceiver;
    }
}
