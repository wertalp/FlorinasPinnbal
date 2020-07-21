package ch.wertal.solutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main" ;
    ImageView ball ;
    ConstraintLayout layoutGame;
    Timer timer = new Timer();
    Thread thread1 = new Thread() ;
    boolean flg_start = false ;
    float x,y ;
    float screenWidth  =0;
    float screenHeigth =0;
    TextView tvX, tvY, tvZ ;
    Display display ;
    Point size = new Point();
    Rect r ;
    int[] ballcoordinates ;
    Paddle paddle ;
    ch.wertal.solutions.BounceView bounceView ;
    private final float NOISE = (float) 2.0 ;

    float mlastX, mlastY, mlastZ ;
    Sensor mSensor ;
    SensorManager mSensorManager ;
    long lastUpdate ;
    long periode = (long) 50000000F;
    boolean mInitiialize ;

    Context appcontext = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(bounceView);
        mInitiialize = true ;
        appcontext = getApplicationContext();

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
       /// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bindUI();


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        bounceView.setAppcontext(appcontext);
    }

    public void startGame(View view){
        Toast.makeText(this,"lets go DUDE",Toast.LENGTH_SHORT).show();
        bounceView.setRunning(true);
        bounceView.invalidate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);

        if(flg_start){
            tvX.setText(String.valueOf(x));
            tvY.setText(String.valueOf(y));
            tvZ.setText(String.valueOf(y));
        }
        return ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(bounceView,mSensor,SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }

    private void bindUI(){
      // ball = findViewById(R.id.ball) ;
       layoutGame = (ConstraintLayout) findViewById(R.id.layoutGame);


        bounceView = (ch.wertal.solutions.BounceView)   findViewById(R.id.bounceView);
        paddle = bounceView.getPaddle();
        mInitiialize = false ;

        bounceView.setOnTouchListener(new OnSwipeTouchListener(this) {

            public boolean onSwipeTop() {
                Toast.makeText(appcontext, "top", Toast.LENGTH_SHORT).show();
                return true;
            }
            public void onSwipeRight() throws InterruptedException {
                Toast.makeText(appcontext, "right", Toast.LENGTH_SHORT).show();
                paddle.setMovingR(true);
                paddle.moveRight(2);

            }
            public void onSwipeLeft() throws InterruptedException {
                paddle.setMovingL(true);
                Toast.makeText(appcontext, "left", Toast.LENGTH_SHORT).show();
                paddle.moveleft(2);

            }
            public boolean onSwipeBottom() {
                Toast.makeText(appcontext, "bottom", Toast.LENGTH_SHORT).show();
                return true;
            }});
   }




}