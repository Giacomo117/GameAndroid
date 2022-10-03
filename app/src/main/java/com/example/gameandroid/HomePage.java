package com.example.gameandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import Graphics.Sprite;
import Graphics.SpriteSheet;

public class HomePage extends SurfaceView implements SurfaceHolder.Callback{
    private final Context context;
    private HomePageLoop homePageLoop;
    private MainActivity.STATE State;
    private Sprite sprite;

    public HomePage(Context context){
        super(context);
        this.context=context;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);  //così possiamo usare il touch input

        //SpriteSheet spriteSheet = new SpriteSheet(context);
        //sprite = spriteSheet.getMenuSprite();
        homePageLoop = new HomePageLoop(this, surfaceHolder);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        //MainActivity.getInstanceActivity().setContentView(R.layout.activity_main);
        String text = "Siamo nel menu";
        float x = 800;
        float y = 200;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
        //sprite.drawMenu(canvas, 0, 0);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        //preso da internet per risolvere un bug di crash applicazione dopo aver messo in pausa il gioco su dispositivi con android 9+
        if (homePageLoop.getState().equals(Thread.State.TERMINATED)){
            homePageLoop=new HomePageLoop(this,surfaceHolder);
        }
        homePageLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("Game.java","surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Game.java","surfaceDestroyed()");
    }

    //per gestire i vari tocchi touchsullo schermo
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                State = MainActivity.STATE.GAME;
                //System.out.println("stai cliccando lo schermo");
                MainActivity.getInstanceActivity().checkAndRunState(State);
                break;
        }
        return super.onTouchEvent(event);
    }
}
