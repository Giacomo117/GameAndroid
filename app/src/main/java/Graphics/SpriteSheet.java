package Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.gameandroid.R;

public class SpriteSheet {

    private Bitmap bitmap; //un modo per far capire al computer come comprendere una mappa

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled= false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheetprova,bitmapOptions); //modo per importare una foto dentro al gioco

    }
    public Sprite getPlayerSprite(){
        return new Sprite(this, new Rect(0,0,64,64)); //questa funzione ritorna la Sprite ( immagine )
        // e la classe Rect serve ritagliare dall'immagine png che vogliamo utilizzare la vera immagine dell'omino o quel che è
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
