package Graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet=spriteSheet;
        this.rect=rect;
    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(x,y,x+getWidth(),y+getHeight()),  // così dico dove va disegnato sullo schermo ( prendendo x e y da Player o altro)
                null
        );
    }

    public int getHeight() {
        return rect.height();
    }
    public int getWidth() {
        return rect.width();
    }
}
