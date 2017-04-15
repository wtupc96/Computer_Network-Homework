package frontEnd;

import backEnd.Chat;

import javax.swing.*;

import static frontEnd.MultiFuncMenu.getjMessageTextArea;
import static frontEnd.MultiFuncMenu.getjTalkButton;
import static frontEnd.MultiFuncMenu.getjVideoButton;

/**
 * Created by wtupc96 on 2017/4/10.
 */
public class TopMenu extends JMenuBar {
    private static final JMenuItem jBreakMenuItem = new JMenuItem("连接");
    private static final JMenuItem jQuitMenuItem = new JMenuItem("退出");

    public static Chat getChat() {
        return chat;
    }

    private static Chat chat;

    private static String input = "";

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
}
