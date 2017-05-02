package backEnd;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import javax.sound.sampled.*;

class SoundReceiver extends UDPReceiver implements Runnable {

    private SourceDataLine line;
    private Thread thread;
    private boolean isStart;

    public SoundReceiver() {
        super("localhost", 9527, 1024);
        AudioFormat format = new AudioFormat(8000, 16, 2, true, true);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, 10240);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (isStart && !thread.isInterrupted()) {
            byte[] data = super.receive();
            line.write(data, 0, data.length);

            // 进一步将音频保存到文件中
            saveToFile(data);
        }
    }

    private void saveToFile(byte[] data) {
        System.out.print(HexBin.encode(data));
    }

    public void start() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            line.start();
            isStart = true;
            contact();
            thread.start();
        }
    }

    public void stop() {
        thread.interrupt();
        line.stop();
        isStart = false;
        close();
    }
}
