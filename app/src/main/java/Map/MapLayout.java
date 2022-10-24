package Map;

import static java.security.AccessController.getContext;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import GameObject.Border;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 128;
    public static final int TILE_HEIGHT_PIXELS = 128;
    public static final int NUMBER_OF_ROW_TILES = 68;
    public static final int NUMBER_OF_COLUMN_TILES = 68;
    public static final int NUMBER_OF_PLAYABLE_ROW_TILES = 60;
    public static final int NUMBER_OF_PLAYABLE_COLUMN_TILES = 60;

    public static final int WATER_TILE = 0;
    public static final int LAVA_TILE = 1;
    public static final int GROUND_TILE = 2;
    public static final int GRASS_TILE = 3;
    public static final int TREE_TILE = 4;
    public static final int TOP_FENCE_TILE = 5;
    public static final int BOTTOM_FENCE_TILE = 6;
    public static final int LEFT_FENCE_TILE = 7;
    public static final int RIGHT_FENCE_TILE = 8;
    public static final int TOP_RIGHT_CORNER_TILE = 9;
    public static final int TOP_LEFT_CORNER_TILE = 10;
    public static final int BOTTOM_RIGHT_CORNER_TILE = 11;
    public static final int BOTTOM_LEFT_CORNER_TILE = 12;
    public static final int NORMAL_HOUSE_TILE_0 = 13;
    public static final int NORMAL_HOUSE_TILE_1 = 14;
    public static final int NORMAL_HOUSE_TILE_2 = 15;
    public static final int NORMAL_HOUSE_TILE_3 = 16;
    public static final int NORMAL_HOUSE_TILE_4 = 17;
    public static final int NORMAL_HOUSE_TILE_5 = 18;
    public static final int DESERT_HOUSE_TILE_0 = 19;
    public static final int DESERT_HOUSE_TILE_1 = 20;
    public static final int DESERT_HOUSE_TILE_2 = 21;
    public static final int DESERT_HOUSE_TILE_3 = 22;
    public static final int DESERT_HOUSE_TILE_4 = 23;
    public static final int DESERT_HOUSE_TILE_5 = 24;
    public static final int LAKE_TILE = 25;

    private int [][] layout;
    public static List<Border> border = new ArrayList<Border>();
    private final Context context;

    public MapLayout(Context context) {
        this.context = context;
        initializeLayout();
    }

    public int[][] getLayout() {
        return layout;
    }

    private void initializeLayout() {//qui inizializzo, dando l'indice in sprite_sheetprova di un determinato background, cosa disegnare nella mappa
        layout = new int[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];    //dovrei scrivere 60 linee x 60 colonne, che due maroni
        for(int idxRow = 0; idxRow < NUMBER_OF_ROW_TILES; idxRow++){
            for(int idxCol = 0; idxCol < NUMBER_OF_COLUMN_TILES; idxCol++){
                boolean top = (idxRow == NUMBER_OF_ROW_TILES - NUMBER_OF_PLAYABLE_ROW_TILES);
                boolean bottom = (idxRow == NUMBER_OF_PLAYABLE_ROW_TILES - 1);
                boolean left = (idxCol == NUMBER_OF_COLUMN_TILES - NUMBER_OF_PLAYABLE_COLUMN_TILES);
                boolean right = (idxCol == NUMBER_OF_PLAYABLE_COLUMN_TILES - 1);

                //la base è tutta erba
                layout[idxRow][idxCol] = GRASS_TILE;

                //nul bordo dell'area giocabile metto la recinzione
                if(top && left){
                    layout[idxRow][idxCol] = TOP_LEFT_CORNER_TILE;
                    //il comando seguente era l'idea per rendere tutte le strutture grafiche dei cerci in modo da avere collisioni anche con le strutture
                    //sarebbe da riguardare e da mettere dappertutto:

                    // border.add(new Border(context, 17170445, TILE_WIDTH_PIXELS * (idxRow + 1), TILE_HEIGHT_PIXELS * (idxCol + 1), 64));
                } else if(top && right){
                    layout[idxRow][idxCol] = TOP_RIGHT_CORNER_TILE;
                } else if(bottom && left){
                    layout[idxRow][idxCol] = BOTTOM_LEFT_CORNER_TILE;
                } else if(bottom && right){
                    layout[idxRow][idxCol] = BOTTOM_RIGHT_CORNER_TILE;
                } else if(top){
                    layout[idxRow][idxCol] = TOP_FENCE_TILE;
                } else if(bottom){
                    layout[idxRow][idxCol] = BOTTOM_FENCE_TILE;
                } else if(left) {
                    layout[idxRow][idxCol] = LEFT_FENCE_TILE;
                } else if(right) {
                    layout[idxRow][idxCol] = RIGHT_FENCE_TILE;
                }

                //oltre la recinzione metto alberi, così non si vede sfondo nero
                if(idxRow < NUMBER_OF_ROW_TILES - NUMBER_OF_PLAYABLE_ROW_TILES || idxRow > NUMBER_OF_PLAYABLE_ROW_TILES - 1){
                    layout[idxRow][idxCol] = TREE_TILE;
                }
                if(idxCol < NUMBER_OF_COLUMN_TILES - NUMBER_OF_PLAYABLE_COLUMN_TILES || idxCol > NUMBER_OF_PLAYABLE_COLUMN_TILES - 1){
                    layout[idxRow][idxCol] = TREE_TILE;
                }
            }
        }
        drawStructure(37, 48, "normal");
        drawStructure(37, 54, "normal");
        drawStructure(40, 46, "normal");
        drawStructure(40, 52, "normal");
        drawStructure(43, 49, "normal");

        drawStructure(10, 20, "desert");
        drawStructure(10, 26, "desert");
        drawStructure(13, 18, "desert");
        drawStructure(13, 24, "desert");
        drawStructure(16, 21, "desert");

        drawStructure(50, 10, "lake");
    }

    private void drawStructure(int idxRow, int idxCol, String typeOfStructure){
        switch (typeOfStructure){
            case "normal":
                layout[idxRow][idxCol] = NORMAL_HOUSE_TILE_0;
                layout[idxRow][idxCol + 1] = NORMAL_HOUSE_TILE_1;
                layout[idxRow][idxCol + 2] = NORMAL_HOUSE_TILE_2;
                layout[idxRow + 1][idxCol] = NORMAL_HOUSE_TILE_3;
                layout[idxRow + 1][idxCol + 1] = NORMAL_HOUSE_TILE_4;
                layout[idxRow + 1][idxCol + 2] = NORMAL_HOUSE_TILE_5;
                break;
            case "desert":
                layout[idxRow][idxCol] = DESERT_HOUSE_TILE_0;
                layout[idxRow][idxCol + 1] = DESERT_HOUSE_TILE_1;
                layout[idxRow][idxCol + 2] = DESERT_HOUSE_TILE_2;
                layout[idxRow + 1][idxCol] = DESERT_HOUSE_TILE_3;
                layout[idxRow + 1][idxCol + 1] = DESERT_HOUSE_TILE_4;
                layout[idxRow + 1][idxCol + 2] = DESERT_HOUSE_TILE_5;
                /*for(int i = idxRow; i < idxRow + 2; i++){
                    for(int j = idxCol; j < idxCol + 3; j++){
                        border.add(new Border(context, 17170445, TILE_WIDTH_PIXELS * (i + 1), TILE_HEIGHT_PIXELS * (j + 1), 64));
                    }
                }*/
                break;
            case "lake":
                for(int i = idxRow; i < idxRow + 5; i++){
                    for(int j = idxCol; j < idxCol + 4; j++){
                        layout[i][j] = LAKE_TILE;
                    }
                }
                break;
            default:
                break;
        }
    }
}