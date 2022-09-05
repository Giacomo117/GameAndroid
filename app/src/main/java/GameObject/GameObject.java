package GameObject;

import android.graphics.Canvas;

//questa classe è la classe madre di nemici, player, ecc, e ha dentro tutte le cose in comune

public abstract class GameObject {
   public double positionX;
    public double positionY;
    public double velocityX=0;
    public double velocityY=0;
    public double directionX=1;
    public double directionY=0;

    public GameObject(double positionX, double positionY)
    {
        this.positionX=positionX;
        this.positionY=positionY;
    }

    protected static double getDistanceBetweenObj(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
                Math.pow(obj2.getPositionX()- obj1.getPositionX(), 2) +
                Math.pow(obj2.getPositionY()- obj1.getPositionY(), 2)
        );
    }

    public abstract void draw(Canvas canvas);   // draw e update sono diversi e quindi sono astratti, perchè ogni classe specifica li implementerà
    public abstract void update();

    protected double getPositionX(){
        return positionX;
    }

    protected double getPositionY(){
        return positionY;
    }

    protected double getDirectionX() {
        return directionX;
    }

    protected double getDirectionY() {
        return directionY;
    }
}
