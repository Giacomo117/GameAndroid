package Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import Graphics.Sprite;
import Graphics.SpriteSheet;

public class LakeTile extends Tile {
    private final Sprite grassSprite;
    private  final Sprite lakeSprite;

    public LakeTile(SpriteSheet spriteSheet, Rect mapLocationRect, int idxLakePart) {
        super(mapLocationRect);
        grassSprite = spriteSheet.getGrassSprite();
        lakeSprite = spriteSheet.getLakeSpriteArray()[idxLakePart];
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        lakeSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
