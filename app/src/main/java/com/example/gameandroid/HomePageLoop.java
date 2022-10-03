package com.example.gameandroid;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class HomePageLoop extends Thread{
    private static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private final com.example.gameandroid.HomePage homePage;
    private boolean isRunning1 =false;
    private final SurfaceHolder surfaceHolder;

    public HomePageLoop(HomePage homePage, SurfaceHolder surfaceHolder) {
        this.homePage = homePage;
        this.surfaceHolder = surfaceHolder;
    }

    public void startLoop() {
        isRunning1 =true;

        //con start faccio partire run
        start();
    }

    public void run() {
        super.run();
        Canvas canvas; //è un'area nera rettangolare dove ci si può disegnare

        int updateCount=0;
        int frameCount=0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        startTime=System.currentTimeMillis(); //imposto il tempo di partenza quando faccio partire il loop

        //Full Game Loop while the app is running
        while(isRunning1){

            //fasi:

            //update e render game

            try{
                canvas=surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    homePage.draw(canvas);
                    updateCount++;
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
                frameCount++;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            //pause Game Loop to not exceed target UPS
            elapsedTime=System.currentTimeMillis() - startTime; //tempo trascorso
            sleepTime= (long) (updateCount*UPS_PERIOD - elapsedTime);
            if (sleepTime>0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
