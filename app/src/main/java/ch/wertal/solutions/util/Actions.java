package ch.wertal.solutions.util;

import android.graphics.Rect;
import android.widget.Toast;

import ch.wertal.solutions.Ball;
import ch.wertal.solutions.Paddle;

public class Actions {

    public static boolean detectCollisions(Paddle paddle , Ball ball){

        Rect shapeball   = ball.bounds()  ;
        Rect shapePaddle = paddle.bounds() ;

        if(shapeball.intersect(shapePaddle))
        {
          return true;

        }
        else {
            return false;
        }

    }
}
