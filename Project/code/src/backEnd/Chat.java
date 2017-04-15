package backEnd;

public class Chat {
    private final SoundReceiver soundReceiver;
    private final SoundSender soundSender;

    public Chat(String targetPeer) {
        int indexOfPort = targetPeer.indexOf(':');
        soundSender = new SoundSender("localhost", 10001, 1024);
        soundReceiver = new SoundReceiver(targetPeer.substring(0, indexOfPort),
                Integer.valueOf(targetPeer.substring(indexOfPort + 1)), 1024);
    }

    public void soundStart() {
        soundReceiver.start();
        soundSender.start();
    }

    public void soundStop() {
        soundReceiver.stop();
        soundSender.stop();
    }
}
