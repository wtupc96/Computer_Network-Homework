package backEnd;

public class Chat {
    private final SoundReceiver soundReceiver;
    private final SoundSender soundSender;

    public Chat(String targetPeer) {
        int indexOfPort = targetPeer.indexOf(':');
        soundSender = new SoundSender("localhost", 9999, 1024);
        soundReceiver = new SoundReceiver(targetPeer.substring(0, indexOfPort),
                10000, 1024);
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
