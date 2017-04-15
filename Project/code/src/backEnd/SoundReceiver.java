package backEnd;

import javax.sound.sampled.*;

class SoundReceiver extends UDPReceiver implements Runnable {

    private SourceDataLine line;
    private Thread thread;
    private boolean isStart;

    public SoundReceiver(String groupAddress,
                         int port,
                         int bufferSize) {
        super(groupAddress, port, bufferSize);
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
        }
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
