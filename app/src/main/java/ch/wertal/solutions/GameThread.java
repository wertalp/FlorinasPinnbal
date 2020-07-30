package ch.wertal.solutions;

import android.view.SurfaceHolder;

public class GameThread extends Thread {

  SurfaceHolder holder        = null ;
  BounceView    bounceView    = null ;
  Boolean       isRunning     = false ;

  public GameThread(SurfaceHolder holder, BounceView bounceView) {
    super();
    this.holder     = holder ;
    this.bounceView = bounceView;

  }

  @Override
    public void run() {
        super.run();

        while (isRunning){

        }


    }
}
