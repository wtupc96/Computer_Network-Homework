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

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (true) {
                                    ChattingRoom.getjChattingRoomTextField().append(new Date().toString() + "\n" + textMessageSender.receiveMessage() + "\n\n");
                                }
                            }
                        }).start();
                    } catch (IOException e1) {
                        System.out.println("hh");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                textMessageReceiver = new TextMessageReceiver();
                                flag = false;
                                while (true) {
                                    ChattingRoom.getjChattingRoomTextField().append(new Date().toString() + "\n" + textMessageReceiver.receiveMessage() + "\n\n");
                                }
                            }
                        }).start();
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
