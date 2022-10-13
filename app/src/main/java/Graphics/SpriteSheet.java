package Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.gameandroid.R;

public class SpriteSheet {

    private static final int SPRITE_WIDTH_PIXELS = 128;
    private static final int SPRITE_HEIGHT_PIXELS = 128;
    private final Bitmap bitmapMappa;
    private Bitmap bitmap; //un modo per far capire al computer come comprendere una mappa

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled= false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheetprova,bitmapOptions); //modo per importare una foto dentro al gioco
        bitmapMappa = BitmapFactory.decodeResource(context.getResources(), R.drawable.spritesheet_completo128x128,bitmapOptions);
    }


    public Bitmap getBitmap() {
        return bitmapMappa;
    }


    public Sprite[] getPlayerSpriteArray(){
        Sprite[] spriteArray = new Sprite[4];
        spriteArray[0] = getSpriteByIndex(10, 35);
        spriteArray[1] = getSpriteByIndex(10, 36);
        spriteArray[2] = getSpriteByIndex(10, 37);
        spriteArray[3] = getSpriteByIndex(10, 38);
        return spriteArray;
    }

    public Sprite[] getEnemySpriteArray(){
        Sprite[] spriteArray = new Sprite[4];
        spriteArray[0] = getSpriteByIndex(10, 31);
        spriteArray[1] = getSpriteByIndex(10, 32);
        spriteArray[2] = getSpriteByIndex(10, 33);
        spriteArray[3] = getSpriteByIndex(10, 34);
        return spriteArray;
    }

    //per i prossimi passaggi avrei potuto fare come ho fatto in getPlayerSpriteArray, andando a prendere lo sprite corretto indicando la sua posizione
    //in sprite_sheetprova, in realtà creo una nuova funzione per rendere più leggibile il codice e rendere più semplice la chiamata
    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }

    public Sprite getWaterSprite() {
        return getSpriteByIndex(5, 10);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(12, 23);
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(1, 3);
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1,0);
    }

    public Sprite getTreeSprite() {
        return getSpriteByIndex(0,1);
    }

    public Sprite getHorizontalFenceSprite() {
        return getSpriteByIndex(2,1);
    }

    public Sprite getVerticalFenceSprite() {
        return getSpriteByIndex(3,0);
    }

    public Sprite getFenceSprite(String position){
        switch(position){
            case "top":
                return getSpriteByIndex(2, 1);
            case "bottom":
                return getSpriteByIndex(4, 1);
            case "left":
                return getSpriteByIndex(3, 0);
            case "right":
                return getSpriteByIndex(3, 2);
            case "top_left":
                return getSpriteByIndex(2, 0);
            case "top_right":
                return getSpriteByIndex(2, 2);
            case "bottom_left":
                return getSpriteByIndex(4, 0);
            case "bottom_right":
                return getSpriteByIndex(4, 2);
            default:
                return null;
        }
    }
}