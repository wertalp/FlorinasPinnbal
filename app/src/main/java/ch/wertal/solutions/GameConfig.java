package ch.wertal.solutions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameConfig {

    public static final int ANZ_ROW  = 5;
    public static final int ANZ_COLS = 7;
    public static final int CANVAS_MARGIN = 40;
    public static final int BEE_SIZE = 150;
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_TOP_MARGIN = 500;
    private static int anzBeatles = 3;
    public static Bee[][] bees = new Bee[ANZ_ROW][ANZ_ROW];


    public static Context appcontext ;
    private static Bitmap bee ;


    public static Bitmap getBeeBitmap(int sizex, int sizey) {

        try {
            bee = BitmapFactory.decodeResource(appcontext.getResources(),R.drawable.honeybee) ;
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
        return Bitmap.createScaledBitmap(bee,sizex,sizey, false);
    }

    public static Bee[][] loadBees() {

        try {
           for (int row = 0 ; row < bees.length; row++){

               for (int col = 0; col < bees[row].length; col++){
                   bees[row][col] = createBee(row,col) ;
               }
           }
           return  bees ;
         } catch (Exception exception){

        }
        return null;
    }


    private static Bee createBee(int row, int col) {
        Bee bee = null ;
        try {

            int itemWith = CANVAS_WIDTH/ANZ_COLS;
            int columne = col ;

            int posX = itemWith * columne + BEE_SIZE + CANVAS_MARGIN;
            int posY = CANVAS_TOP_MARGIN + (BEE_SIZE * row) ;
            bee = new Bee(GameConfig.getBeeBitmap(150,150),posX,posY,appcontext);
            row++;
            col++;
            return bee ;
        }
        catch (Exception e){

        }

        return null ;
    }

    public static Context getAppcontext() {
        return appcontext;
    }

    public static void setAppcontext(Context appcontext) {
        GameConfig.appcontext = appcontext;
    }
}
