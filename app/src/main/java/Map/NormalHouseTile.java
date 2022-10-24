package Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import Graphics.Sprite;
import Graphics.SpriteSheet;

public class NormalHouseTile extends Tile {
    private final Sprite grassSprite;
    private  final Sprite normalHouseSprite;

    public NormalHouseTile(SpriteSheet spriteSheet, Rect mapLocationRect, int idxNormalHousePart) {
        super(mapLocationRect);
        grassSprite = spriteSheet.getGrassSprite();
        normalHouseSprite = spriteSheet.getNormalHouseSpriteArray()[idxNormalHousePart];
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        normalHouseSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
