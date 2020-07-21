package ch.wertal.solutions;

public class GameThread extends Thread {
  BounceView bounceView;

    public GameThread(BounceView bounceView) {
    super();
    this.bounceView = bounceView;
    }

    @Override
    public void run() {
        super.run();
    }
}
