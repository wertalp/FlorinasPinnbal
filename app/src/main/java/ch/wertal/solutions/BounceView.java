package ch.wertal.solutions;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathDashPathEffect;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class BounceView extends View implements SensorEventListener {

    int startPosX, startPosY ;
    boolean initial = true ;
    Context appcontext = null ;
    boolean mInitiialize ;

    public List<Ball> balls = new ArrayList<>();
    Paddle paddle ;
    boolean running = true ;
    MediaPlayer mp;
    float mlastX;
    float mlastY;
    float mlastZ;
    private final float NOISE = (float) 2.0 ;

    TextView tvX ;
    TextView tvY ;
    TextView tvZ ;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public BounceView(Context context) {
        super(context);
    }

    public BounceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //paddle = new Paddle(startPosX/2,startPosY-320,startPosX/2+300,startPosY-300,Color.YELLOW);
        paddle = new Paddle(500,1600,800,1620,Color.YELLOW);
        mp  = MediaPlayer.create(context, R.raw.bounce);
        appcontext = context;
        appcontext.getApplicationContext();
        createBalls();
        // ball = findViewById(R.id.ball) ;

    }

    public Context getAppcontext() {
        return appcontext;
    }

    public void setAppcontext(Context appcontext) {
        this.appcontext = appcontext;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        startPosY = bottom;
        startPosX = right;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("Punkte:"+ Ball.getScore() , 50, 400, getTextPaint());
        canvas.drawText("X:"+ mlastX , 700, 600, getTextPaint());
        canvas.drawText("Y:"+ mlastY , 700, 700, getTextPaint());
        canvas.drawText("Z:"+ mlastZ , 700, 800, getTextPaint());

        canvas.drawText("Wertal Software Solution @2020:"+ Ball.getScore() , 40, 1800, getsmallTextPaint());

        paddle.draw(canvas);

            for (Ball ball : balls){
                ball.move(canvas);
                canvas.drawOval(ball.oval, ball.paint);
                // canvas.drawRect(100,120,220,1100,paddle.paint);
                initial=false;
            }
        invalidate();
}

    public void createBalls(){
     //   balls.add(new Ball(200,30,140,Color.GREEN));
        balls.add(new Ball(450,230,60,Color.RED,mp));

    };

    public Paddle getPaddle(){
        return paddle;
    }

    public void movepaddle() throws InterruptedException {
        paddle.moveleft(2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //paddle.move();
        invalidate();
        return false;
    }

    private Paint getTextPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.RED);
        paint.setTextSize(60);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        return paint ;
    }
    private Paint getsmallTextPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(Color.BLACK);
        paint.setTextSize(45);
        return paint ;
    }


    public void onSensorChanged1(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if (mInitiialize) {
            mlastX  = x ;
            mlastY  = y ;
            mlastZ  = z ;

            tvX.setText(("0.00"));
            tvY.setText(("0.00"));
            tvZ.setText(("0.00"));
            // mInitiialize = false ;

        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && ! mInitiialize) {

            float deltaX = Math.abs(mlastX - x);
            float deltaY = Math.abs(mlastY - y);
            float deltaZ = Math.abs(mlastZ - z);
            if (deltaX < NOISE) deltaX = (float) 0.0;
            if (deltaY < NOISE) deltaY = (float) 0.0;
            if (deltaZ < NOISE) deltaZ = (float) 0.0;
            mlastX = x;
            mlastY = y;
            mlastZ = z;
            Toast.makeText(getAppcontext(),"HOI"+ mlastX,Toast.LENGTH_SHORT).show();
            tvX.setText(Float.toString(deltaX));
            tvY.setText(Float.toString(deltaY));
            tvZ.setText(Float.toString(deltaZ));
        }}

    @Override
    public void onSensorChanged(SensorEvent event) {
       // Toast.makeText(getAppcontext(),"HOI"+ mlastX,Toast.LENGTH_SHORT).show();
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && ! mInitiialize) {
            float xSensor = event.values[0];
            float ySensor = event.values[1];
            float zSensor = event.values[2];

            float deltaX = Math.abs(mlastX - xSensor);
            float deltaY = Math.abs(mlastY - ySensor);
            float deltaZ = Math.abs(mlastZ - zSensor);
            if (deltaX < NOISE) deltaX = (float) 0.0;
            if (deltaY < NOISE) deltaY = (float) 0.0;
            if (deltaZ < NOISE) deltaZ = (float) 0.0;
            mlastX = xSensor;
            mlastY = ySensor;
            mlastZ = zSensor;

                if (xSensor > 0) {
                    try {
                    paddle.moveleft(mlastX);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
            if (xSensor < 0) {
                try {
                    paddle.moveRight(mlastX);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
