package com.example.gameandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

public class TitleScreen extends AppCompatActivity {

    private Game game;
    private static View view;
    public static WeakReference<TitleScreen> weakActivity;


    public enum STATE{
        MENU,
        GAME
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_title_screen);

        weakActivity = new WeakReference<>(TitleScreen.this);

    }
    public void checkAndRunState(TitleScreen.STATE State){
        if(State == TitleScreen.STATE.MENU){
            Intent titleScreen= new Intent(this, TitleScreen.class);
            startActivity(titleScreen);
        } else if(State == TitleScreen.STATE.GAME){
            game=new Game(this);
            setContentView(game);
        }
    }

    public void helpOnScreen(View view){
        Intent helpScreen= new Intent(this, HelpPage.class);
        startActivity(helpScreen);
    }

    public void finishGame (View view){
        finish();
    }
    public void gameOnScreen(View view ){
            game=new Game(this);
            setContentView(game);

    }



    public static TitleScreen getInstanceActivity(){
        return weakActivity.get();
    }

    public void gameOnScreen2(boolean b){
        // game=new Game(this);
        //while (Game.getGameFinished() == false){

        setContentView(view);
        // }
        //setContentView(view);

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