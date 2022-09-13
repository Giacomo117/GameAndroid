package GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.gameandroid.GameDisplay;
import com.example.gameandroid.R;

public class HealthBar {
    private Player player;
    int width, height,margin; //il tutto in pixel --- margin serve per la distanza tra bordo esterno della barra e contenuto
    private Paint borderPaint;
    private Paint healthPaint;

    public HealthBar(Context context,Player player) {
        this.player=player;
        this.width= 90;
        this.height= 12;
        this.margin=2;

        //creo il bordo

        this.borderPaint= new Paint();
        int borderColor= ContextCompat.getColor(context, R.color.white);
        borderPaint.setColor(borderColor);

        //creo l'interno
        this.healthPaint= new Paint();
        int healthColor= ContextCompat.getColor(context, R.color.healthBar);
        healthPaint.setColor(healthColor);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        float x= (float) player.getPositionX(); //li prendo in float perchè canvas.drawRect accetta parametri in float
        float y= (float) player.getPositionY();
        float distanceToPlayer= 55;
        float healtPointPercentage= (float) player.getHealthPoints()/player.MAX_HEALTH_POINTS;

        //dobbiamo disegnare il bordo e poi il contenuto interno della barra

        //bordo

        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft= x - width/2;
        borderRight = x+ width/2;
        borderBottom= y + distanceToPlayer;
        borderTop= borderBottom - height;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayCoordinatesX(borderLeft),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(borderRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderBottom),
                borderPaint);

        //contenuto interno

        float healthLeft,healthTop,healthRight,healthBottom, healthWidth, healthHeight;
        healthWidth= width - 2*margin;
        healthHeight= height - 2*margin;
        healthLeft= borderLeft + margin;
        healthRight= healthLeft + healthWidth*healtPointPercentage;
        healthBottom= borderBottom - margin;
        healthTop= healthBottom - healthHeight;

        canvas.drawRect(
                //utilizzo la classe gameDisplay perchè, insieme al player, la barra vita deve rimanere centrata nello schermo
                (float) gameDisplay.gameToDisplayCoordinatesX(healthLeft),
                (float) gameDisplay.gameToDisplayCoordinatesY(healthTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(healthRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(healthBottom),
                healthPaint);


    }
}
