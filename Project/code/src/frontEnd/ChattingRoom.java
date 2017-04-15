package frontEnd;

import javax.swing.*;

/**
 * Created by wtupc96 on 2017/4/10.
 */
class ChattingRoom extends JPanel {
    private static final JTextArea jChattingRoomTextField = new JTextArea(23, 0);
    private static final JScrollPane jScrollPane = new JScrollPane(jChattingRoomTextField);

    static {
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jChattingRoomTextField.setLineWrap(true);
        jChattingRoomTextField.setSize(290, 400);
        jChattingRoomTextField.setEditable(false);
    }

    ChattingRoom() {
        add(jScrollPane);
    }

    public static JTextArea getjChattingRoomTextField() {
        return jChattingRoomTextField;
    }
}
