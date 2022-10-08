package Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.gameandroid.R;

public class SpriteSheet {

    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private final Bitmap bitmapMappa;
    private final Bitmap bitmapGoblin;
    private final Bitmap bitmapIceMonster;
    private final Bitmap bitmapDevil;
    private final Bitmap bitmapSnake;
    private Bitmap bitmap; //un modo per far capire al computer come comprendere una mappa

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled= false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheetprova,bitmapOptions); //modo per importare una foto dentro al gioco
        bitmapMappa = BitmapFactory.decodeResource(context.getResources(), R.drawable.spritesheet2,bitmapOptions);
        bitmapGoblin = BitmapFactory.decodeResource(context.getResources(), R.drawable.goblin,bitmapOptions);
        bitmapIceMonster = BitmapFactory.decodeResource(context.getResources(), R.drawable.ice_rock_monster,bitmapOptions);
        bitmapDevil = BitmapFactory.decodeResource(context.getResources(), R.drawable.devil,bitmapOptions);
        bitmapSnake = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake,bitmapOptions);
    }
    public Sprite[] getPlayerSpriteArray(){
        //return new Sprite(this, new Rect(0,0,64,64)); questa funzione ritorna la Sprite ( immagine )
        // e la classe Rect serve ritagliare dall'immagine png che vogliamo utilizzare la vera immagine dell'omino o quel che è
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0] = new Sprite(this, new Rect(0*64,0,1*64,64));
        spriteArray[1] = new Sprite(this, new Rect(1*64,0,2*64,64));
        spriteArray[2] = new Sprite(this, new Rect(2*64,0,3*64,64));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
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
        return getSpriteByIndex(1, 0);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(1, 1);
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(1, 2);
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1,3);
    }

    public Sprite getTreeSprite() {
        return getSpriteByIndex(1,4);
    }
}
