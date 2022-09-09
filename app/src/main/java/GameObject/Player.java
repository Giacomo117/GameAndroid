package GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.gameandroid.GameLoop;
import com.example.gameandroid.Joystick;
import com.example.gameandroid.R;


//il player è il personaggio principale, ed è l'unico che viene mosso attraverso un joystick.
//la classe player è estensione di circle, che è estensione di GameObject
public class Player extends Circle{

    public static final int MAX_HEALTH_POINTS = 10;
    private final Joystick joystick;
    private double radius;
    private Paint paint;
    public static final double SPEED_PIXEL_PER_SEC = 900.0 ;
    public static final double MAX_SPEED= SPEED_PIXEL_PER_SEC / GameLoop.MAX_UPS;
    private HealthBar healthBar;
    private int healthPoints;


    //COSTRUTTORE
    public Player(Context context,Joystick joystick, double positionX, double positionY, double radius){
        super(context, ContextCompat.getColor(context, R.color.purple_200), positionX, positionY,radius); //in questo modo recuperiamo i dati del costruttore in GameObject
        this.joystick= joystick;
        this.healthBar= new HealthBar(context, this);
        this.healthPoints=MAX_HEALTH_POINTS;
    }



    public void update() {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY= joystick.getActuatorY()*MAX_SPEED;
        positionX+=velocityX;
        positionY+=velocityY;


        //aggiorno la direzione
        if (velocityX != 0 || velocityY != 0) {

            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }


    }

    public void setPosition(double positionX, double positionY) {
        this.positionX=positionX;
        this.positionY=positionY;
    }


    public void draw(Canvas canvas){
        super.draw(canvas);
        healthBar.draw(canvas);

    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints >=0){
            this.healthPoints=healthPoints;
        }
    }
}


