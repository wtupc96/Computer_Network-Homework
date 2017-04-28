package frontEnd;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wtupc96 on 2017/4/10.
 */
class MainFrame extends JFrame implements Runnable {
    private MainFrame() {
        SwingUtilities.invokeLater(this);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void run() {
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Computer Network: 10005");
        this.setLocationRelativeTo(null);
        this.setSize(300, 600);
        this.setResizable(false);

        setJMenuBar(new TopMenu());
        add(new ChattingRoom(), BorderLayout.CENTER);
        add(new MultiFuncMenu(), BorderLayout.SOUTH);
    }
}
