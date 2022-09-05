package GameObject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.gameandroid.GameLoop;
import com.example.gameandroid.R;

public class Spell extends Circle{
    private final Player spelluser;

    public static final double SPEED_PIXEL_PER_SEC = 900.0 ;
    public static final double MAX_SPEED= SPEED_PIXEL_PER_SEC / GameLoop.MAX_UPS;


    public Spell(Context context, Player spelluser) {
        super(context,
                ContextCompat.getColor(context, R.color.teal_200),
                spelluser.getPositionX(),
                spelluser.getPositionY(), //positionY,
                10//radius
        );
        this.spelluser=spelluser;//?

    }


    public void update() {
        positionX+=velocityX;
        positionY+=velocityY;

        velocityX=spelluser.getDirectionX()*MAX_SPEED;
        velocityY=spelluser.getDirectionY()*MAX_SPEED;

        //aggiorno la direzione
        if (velocityX != 0 || velocityY != 0){
            //creo il vettore
            double distance =Utils.getDistanceBetweenPoints(0,0, velocityX, velocityY);
            directionX= velocityX/distance;
            directionY=velocityY/distance;
        }
    }
}
