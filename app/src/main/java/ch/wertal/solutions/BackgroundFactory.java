package ch.wertal.solutions;

import android.content.Context;

public class BackgroundFactory extends Thread implements Runnable {

  MainActivity mainActivity ;
  Context context ;
    public BackgroundFactory(Context context, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.context      = context ;
    }

    @Override
    public void run() {
        super.run();
        try {
                Thread.sleep(1000);
                mainActivity.startGame();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
