package GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.gameandroid.GameDisplay;
import com.example.gameandroid.GameLoop;
import com.example.gameandroid.Joystick;
import com.example.gameandroid.R;

import Graphics.Animator;
import Graphics.Sprite;


//il player è il personaggio principale, ed è l'unico che viene mosso attraverso un joystick.
//la classe player è estensione di circle, che è estensione di GameObject
public class Player extends Circle{

    public static final int MAX_HEALTH_POINTS = 10;
    private final Joystick joystick;
    private double radius;
    private Paint paint;
    public static final double SPEED_PIXEL_PER_SEC = 900.0 ;
    public static double MAX_SPEED= SPEED_PIXEL_PER_SEC / GameLoop.MAX_UPS;
    public static int FAR_BORDER = 128 * 60;
    public static int NEAR_BORDER = 128 * 8;
    public static int EXCESS_PIXELS = 64;
    private HealthBar healthBar;
    private int healthPoints;
    private Animator animator;
    private PlayerState playerState;


    //COSTRUTTORE
    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Animator animator){
        super(context, ContextCompat.getColor(context, R.color.purple_200), positionX, positionY,radius); //in questo modo recuperiamo i dati del costruttore in GameObject
        this.joystick= joystick;
        this.healthBar= new HealthBar(context, this);
        this.healthPoints=MAX_HEALTH_POINTS;
        this.animator=animator;
        this.playerState = new PlayerState(this);
    }

//path finding *
    public void update() {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY= joystick.getActuatorY()*MAX_SPEED;
        positionX+=velocityX;
        positionY+=velocityY;

        if(positionX > (FAR_BORDER - EXCESS_PIXELS)){
            positionX = (FAR_BORDER - EXCESS_PIXELS);
        }
        if(positionX < (NEAR_BORDER + EXCESS_PIXELS)){
            positionX = NEAR_BORDER + EXCESS_PIXELS;
        }
        if(positionY > (FAR_BORDER - EXCESS_PIXELS)){
            positionY = (FAR_BORDER - EXCESS_PIXELS);
        }
        if(positionY < (NEAR_BORDER + EXCESS_PIXELS)){
            positionY = NEAR_BORDER + EXCESS_PIXELS;
        }

        //aggiorno la direzione
        if (velocityX != 0 || velocityY != 0) {

            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

        playerState.update();
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX=positionX;
        this.positionY=positionY;
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay){
        animator.draw(canvas, gameDisplay, this);
        //super.draw(canvas,gameDisplay); //NON VA BENE perchè noi vogliamo utilizzare un immagine e non CircleDraw


        healthBar.draw(canvas,gameDisplay);

    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints >=0){
            this.healthPoints=healthPoints;
        }
    }

    public PlayerState getPlayerState(){
        return playerState;
    }
}


