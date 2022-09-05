package com.example.gameandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import GameObject.Circle;
import GameObject.Enemy;
import GameObject.Player;
import GameObject.Spell;

/**
 * Questo Game controlla tutti gli oggetti nel gioco ed è responsabile degli aggiornamenti dei dati e delle immagini nello schermo
 * */


class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final GameLoop gameLoop;
    private final Context context;
    private final Joystick joystick;

    //Voglio creare un array di nemici ( in modo che non ce ne sia solo uno)
    private List<Enemy> enemyList= new ArrayList<Enemy>();
    private List<Spell> spellList= new ArrayList<Spell>();
    private int joystickPointerId=0;
    private  int numberOfSpellToCast=0;


    public Game(Context context) {
        super(context);
        this.context=context;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);  //così possiamo usare il touch input


        gameLoop=new GameLoop(this,surfaceHolder); //creo un loop del gioco sul surfaceHolder che ho creato

        //inizializzo il joystick
        joystick=new Joystick(275,750,70,40);

        //inizializzo il player
        player=new Player(getContext(),joystick, 2*500,500,30);

        setFocusable(true); //sarebbe per permettere di dare il focus ad un componente, non so se serve, (forse si)



    }

    @Override
    public void surfaceCreated( SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    //per gestire i vari tocchi touchsullo schermo
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                if (joystick.getIsPressed()){
                    //stavamo già usando il joystick prima del touch
                    //quindi creo una spell
                    numberOfSpellToCast++;
                } else if (joystick.isPressed((double)event.getX(),(double)event.getY())) {
                    //se non stavamo usando il joystick e premo sul joystick, allora adesso lo attivo
                    joystickPointerId=event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    //il joystick non è usato e non lo stiamo premendo, quindi creo una spell
                    numberOfSpellToCast++;
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double)event.getX(),(double)event.getY());

                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())){

                    joystick.setIsPressed(false);
                    joystick.resetActuator();

                }
                return true;


        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed( SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUpdatePerSec(canvas);
        drawFramePerSec(canvas);
        joystick.draw(canvas);
        player.draw(canvas); //per disegnare il player

        for(Enemy enemy : enemyList){
            enemy.draw(canvas);
        }

        for (Spell spell : spellList){
            spell.draw(canvas);
        }

    }

    public void drawUpdatePerSec(Canvas canvas){
        String avrUPS= Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(45);
        canvas.drawText("UPS: " + avrUPS, 20, 90, paint);
    }

    public void drawFramePerSec(Canvas canvas){
        String avrFPS= Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(45);
        canvas.drawText("FPS: " + avrFPS, 20, 50, paint);
    }

    public void update() {
        joystick.update(); // joystick
        player.update(); // per aggiornare il personaggio

        //devo creare un update specifico per i nemici

        //creo un nemico se è tempo di crearlo
        if (Enemy.readyToSpawn()){
            enemyList.add(new Enemy(getContext(),player));
        }

        while(numberOfSpellToCast > 0){
            spellList.add(new Spell(getContext(), player));
            numberOfSpellToCast--;
        }

        //aggiorno lo stato di ogni nemico
        for (Enemy enemy : enemyList){
            enemy.update();
        }

        //aggiorno lo stato di ogni spell g
        for (Spell spell : spellList){
            spell.update();
        }


        //iteratore che guarda tutti i nemici e vede se si sono scontrati col player
        Iterator<Enemy> iteratorEnemy=enemyList.iterator();
        while(iteratorEnemy.hasNext()){
            Circle enemy= iteratorEnemy.next();
            if(Circle.isColliding(enemy,player)){
                //Elimino il nemico se ha colliso col player
                iteratorEnemy.remove();
                continue;
            }
            Iterator<Spell> iteratorSpell = spellList.iterator();
            while(iteratorSpell.hasNext()){
                Circle spell= iteratorSpell.next();
                //elimino le spell se vanno contro i nemici
                if (Circle.isColliding(spell, enemy)){
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }

        }
    }
}