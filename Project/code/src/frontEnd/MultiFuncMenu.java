package frontEnd;

import backEnd.Chat;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Date;

import static frontEnd.ChattingRoom.getjChattingRoomTextField;
import static frontEnd.TopMenu.getChat;

/**
 * Created by wtupc96 on 2017/4/10.
 */
class MultiFuncMenu extends JPanel {
    private static final JTextArea jMessageTextArea = new JTextArea(10, 0);
    private static final JButton jTalkButton = new JButton("开始语音");
    private static final JButton jVideoButton = new JButton("开始视频");
    private static final JButton jSendButton = new JButton("发送");

    private static final GridLayout btnGridLayout = new GridLayout(1, 3, 3, 3);
    private static final JPanel jBtnPanel = new JPanel();
    private static final JScrollPane jScrollPane = new JScrollPane(jMessageTextArea);

    private static Chat chat;

    public static JTextArea getjMessageTextArea() {
        return jMessageTextArea;
    }

    public static JButton getjTalkButton() {
        return jTalkButton;
    }

    public static JButton getjVideoButton() {
        return jVideoButton;
    }

    static {
        jScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jMessageTextArea.setLineWrap(true);
        jSendButton.setEnabled(false);
        jTalkButton.setEnabled(false);
        jVideoButton.setEnabled(false);
        jMessageTextArea.setEnabled(false);
        jBtnPanel.setLayout(btnGridLayout);
        jBtnPanel.add(jTalkButton);
        jBtnPanel.add(jVideoButton);
        jBtnPanel.add(jSendButton);

        jMessageTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setJSendButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setJSendButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setJSendButtonState();
            }
        });

        jTalkButton.addActionListener(e -> {
            chat = getChat();

            if (jTalkButton.getText().equals("开始语音")) {
                chat.soundStart();
                jTalkButton.setText("结束语音");
            } else {
                chat.soundStop();
                jTalkButton.setText("开始语音");
            }
        });

        jSendButton.addActionListener(e -> {
            getjChattingRoomTextField().append(new Date().toString() + '\n' + jMessageTextArea.getText() + "\n\n");
            jMessageTextArea.setText("");
        });

        jVideoButton.addActionListener(e -> {
            // TODO: 2017/4/10
        });
    }

    MultiFuncMenu() {
        setLayout(new BorderLayout(2, 2));
        add(jScrollPane, BorderLayout.CENTER);
        add(jBtnPanel, BorderLayout.SOUTH);
    }

    private static void setJSendButtonState() {
        if (jMessageTextArea.getText().equals("")) {
            jSendButton.setEnabled(false);
        } else {
            jSendButton.setEnabled(true);
        }
    }
}
