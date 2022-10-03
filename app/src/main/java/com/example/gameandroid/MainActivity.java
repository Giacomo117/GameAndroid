package com.example.gameandroid;

import android.animation.Animator;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/*Main*/
public class MainActivity extends AppCompatActivity {

    public static WeakReference<MainActivity> weakActivity;

    //creo una enum di stati (menu o gioco)
    public enum STATE{
        MENU,
        GAME
    }

    public static STATE State = STATE.MENU; //lo inizializzo a menu

    private HomePage homePage;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.main);

        //questo serve per poter mettere in pausa il gioco (utilizzando poi game.pause())
        //se non va bene questo modo per il menù, basta semplicemente togliere tutte le cose di weakactivity, cancellare check and run e mettere solo inizializzazione di game con setContentView
        weakActivity = new WeakReference<>(MainActivity.this);

        checkAndRunState(State);
    }

    public void checkAndRunState (STATE State){
        if(State == STATE.MENU){
            homePage=new HomePage(this);
            setContentView(homePage);
        } else if(State == STATE.GAME){
            game=new Game(this);
            setContentView(game);
        }
    }

    public static MainActivity getInstanceActivity(){
        return weakActivity.get();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        game.pause();
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

