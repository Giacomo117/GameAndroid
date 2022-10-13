package Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import Graphics.Sprite;
import Graphics.SpriteSheet;

public class FenceTile extends Tile {

    private final Sprite grassSprite;
    private final Sprite fenceSprite;
    private final String position;

    public FenceTile(SpriteSheet spriteSheet, Rect mapLocationRect, String position) {
        super(mapLocationRect);
        this.position = position;
        grassSprite = spriteSheet.getGrassSprite();
        fenceSprite = spriteSheet.getFenceSprite(position);
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        fenceSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
