package Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import Graphics.Sprite;
import Graphics.SpriteSheet;

public class DesertHouseTile extends Tile {
    private final Sprite grassSprite;
    private  final Sprite desertHouseSprite;

    public DesertHouseTile(SpriteSheet spriteSheet, Rect mapLocationRect, int idxNormalHousePart) {
        super(mapLocationRect);
        grassSprite = spriteSheet.getGrassSprite();
        desertHouseSprite = spriteSheet.getDesertHouseSpriteArray()[idxNormalHousePart];
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        desertHouseSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
