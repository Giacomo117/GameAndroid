package GameObject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.gameandroid.GameDisplay;
import com.example.gameandroid.GameLoop;
import com.example.gameandroid.R;

import Graphics.Animator;

public class Enemy extends Circle{

    private static final double SPEED_PIXEL_PER_SEC = Player.SPEED_PIXEL_PER_SEC*0.6 ;
    private static final double MAX_SPEED= SPEED_PIXEL_PER_SEC / GameLoop.MAX_UPS;
    private static final double SPAWN_PER_MIN = 60;
    private static final double SPAWN_PER_SEC = SPAWN_PER_MIN/60.0;
    private static final double UPDATE_PER_SPAWN = GameLoop.MAX_UPS/SPAWN_PER_SEC;
    private static double timeUntilNextSpawn;


    private final Player player;
    private Animator animator;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius, Animator animator) {
        super(context, ContextCompat.getColor(context, R.color.magenta), positionX, positionY,radius);
        this.animator = animator;
        this.player=player;
    }

    //questo costruttore serve per far spawnare i nemici, e per farli spawnare random posX,posY devono essere scelti a caso
    public Enemy(Context context, Player player, Animator animator) {
        super(context,
              ContextCompat.getColor(context, R.color.magenta),
              Math.random()*1000, //positionX,
                Math.random()*1000, //positionY,
              30//radius
        );
        this.player=player;
        this.animator = animator;
    }

    public static boolean readyToSpawn() {
        //se il tempo è <= 0 ritorno true e aumento nuovamente il timer, se no false
        if(timeUntilNextSpawn <= 0){
            timeUntilNextSpawn+=UPDATE_PER_SPAWN;
            return true;
        }
        else
        {
            timeUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {
        //dobbiamo aggiornare la velocità del nemico in modo che sia sempre direzionata verso il personaggio

        //calcolo il vettore tra il player e il nemico (x,y)
        double distanceToPlayerX= player.getPositionX() - positionX;
        double distanceToPlayerY= player.getPositionY() - positionY;
        //calcolo la distanza
        double distanceToPlayer = GameObject.getDistanceBetweenObj(this, player);

        //calcolo la direzione
        double directionX= distanceToPlayerX/distanceToPlayer;
        double directionY= distanceToPlayerY/distanceToPlayer;

        //setto la velocità nella direzione trovata
        if (distanceToPlayer > 0){
            velocityX=directionX*MAX_SPEED;
            velocityY=directionY*MAX_SPEED;
        } else {
            velocityY=0;
            velocityX=0;
        }
        //aggiorno la posizione del nemico

        positionX += velocityX;
        positionY += velocityY;

    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        animator.drawEnemy(canvas, gameDisplay, this);
        //super.draw(canvas,gameDisplay); //NON VA BENE perchè noi vogliamo utilizzare un immagine e non CircleDraw

    }
}
