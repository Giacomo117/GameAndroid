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

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled= false;
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
        spriteArray[0] = getSpriteByIndex(10, 27);
        spriteArray[1] = getSpriteByIndex(10, 28);
        spriteArray[2] = getSpriteByIndex(10, 29);
        spriteArray[3] = getSpriteByIndex(10, 30);
        return spriteArray;
    }

    public Sprite[] getNormalHouseSpriteArray(){
        Sprite[] spriteArray = new Sprite[6];
        spriteArray[0] = getSpriteByIndex(8, 4);//top-left
        spriteArray[1] = getSpriteByIndex(8, 5);//top
        spriteArray[2] = getSpriteByIndex(8, 6);//top-right
        spriteArray[3] = getSpriteByIndex(9, 4);//bottom-left
        spriteArray[4] = getSpriteByIndex(9, 5);//bottom
        spriteArray[5] = getSpriteByIndex(9, 6);//bottom-right
        return spriteArray;
    }

    public Sprite[] getDesertHouseSpriteArray(){
        Sprite[] spriteArray = new Sprite[6];
        spriteArray[0] = getSpriteByIndex(12, 7);//top-left
        spriteArray[1] = getSpriteByIndex(12, 8);//top
        spriteArray[2] = getSpriteByIndex(12, 9);//top-right
        spriteArray[3] = getSpriteByIndex(13, 7);//bottom-left
        spriteArray[4] = getSpriteByIndex(13, 8);//bottom
        spriteArray[5] = getSpriteByIndex(13, 9);//bottom-right
        return spriteArray;
    }

    public Sprite[] getLakeSpriteArray(){
        Sprite[] spriteArray = new Sprite[20];
        int cont = 0;
        for(int i = 2; i < 7; i++){
            for(int j = 9; j < 13; j++){
                spriteArray[cont] = getSpriteByIndex(i, j);
                cont++;
            }
        }
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