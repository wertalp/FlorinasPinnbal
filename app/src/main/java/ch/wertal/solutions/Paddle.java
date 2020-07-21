package ch.wertal.solutions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Paddle {
    public int[] direction = new int[]{1,1}; //direction modifier (-1,1)
    public float x,y,x1,y1;
    public int speed = 10;
    public Paint paint;
    public RectF rect;
    public int initalX, initialY ;
    boolean movingL, movingR = false ;
    int cornerRadius = 50;
    Paint fillPaint ;
    Paint strokePaint ;

    public boolean isMovingL() {
        return movingL;
    }

    public void setMovingL(boolean movingL) {
        this.movingL = movingL;
    }

    public boolean isMovingR() {
        return movingR;
    }

    public void setMovingR(boolean movingR) {
        this.movingR = movingR;
    }

    public Paddle(int x, int y, int x1, int y1, int color){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.paint = new Paint();
        this.paint.setColor(color);
        this.fillPaint = new Paint();
        this.strokePaint = new Paint();
        this.rect = new RectF(x,y,x1,y1);
        this.initPaints();
    }

    public void moveleft(float x) throws InterruptedException {
          if (this.x < 40){
              return;
          }
            this.x = this.x - x*speed;
            this.x1 = this.x1 - x*speed;
            this.rect = new RectF(this.x , 1900, x1 , 1870);

    }

    public void moveRight(float x) throws InterruptedException {
        if (this.x > 750){
            return;
        }
           this.x = this.x +Math.abs(x*speed) ;
           this.x1 = this.x1 + Math.abs(x*speed);
           this.rect = new RectF(this.x,1900,x1,1870);
       }

    public void moveUp(float x) throws InterruptedException {
        if (this.y < 0){
            return;
        }
        this.y = this.y +speed ;
        this.y1 = this.y1 +speed ;
        this.rect = new RectF(this.x,this.y,x1,this.y1+speed);
    }

    public void draw(Canvas canvas){
        canvas.drawRect(this.rect,this.paint);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, fillPaint);    // fill
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, strokePaint);

    }

    void initPaints() {

        // fill
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.YELLOW);

        // stroke
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(10);
    }

}
