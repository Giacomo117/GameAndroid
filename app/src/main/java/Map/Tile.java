package Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import Graphics.Sprite;
import Graphics.SpriteSheet;

public abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType{
        WATER_TILE,
        LAVA_TILE,
        GROUND_TILE,
        GRASS_TILE,
        TREE_TILE,
        TOP_FENCE_TILE,
        BOTTOM_FENCE_TILE,
        LEFT_FENCE_TILE,
        RIGHT_FENCE_TILE,
        TOP_RIGHT_CORNER_TILE,
        TOP_LEFT_CORNER_TILE,
        BOTTOM_RIGHT_CORNER_TILE,
        BOTTOM_LEFT_CORNER_TILE
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]){    //non posso usare l'index direttamente poichè non è TileType. Per ovviare al problema uso la funzione values() su TileType,
                                                    //che mi fornisce un array con tutti i valori dell'enum. Accedo così al TileType corretto usando il mio index.
            case WATER_TILE:
                return new WaterTile(spriteSheet, mapLocationRect);
            case LAVA_TILE:
                return new LavaTile(spriteSheet, mapLocationRect);
            case GROUND_TILE:
                return new GroundTile(spriteSheet, mapLocationRect);
            case GRASS_TILE:
                return new GrassTile(spriteSheet, mapLocationRect);
            case TREE_TILE:
                return new TreeTile(spriteSheet, mapLocationRect);
            case TOP_FENCE_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "top");
            case BOTTOM_FENCE_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "bottom");
            case LEFT_FENCE_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "left");
            case RIGHT_FENCE_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "right");
            case TOP_LEFT_CORNER_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "top_left");
            case TOP_RIGHT_CORNER_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "top_right");
            case BOTTOM_LEFT_CORNER_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "bottom_left");
            case BOTTOM_RIGHT_CORNER_TILE:
                return new FenceTile(spriteSheet, mapLocationRect, "bottom_right");
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
