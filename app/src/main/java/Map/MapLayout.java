package Map;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 128;
    public static final int TILE_HEIGHT_PIXELS = 128;
    public static final int NUMBER_OF_ROW_TILES = 60;
    public static final int NUMBER_OF_COLUMN_TILES = 60;

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

    private int [][] layout;

    public MapLayout() {
        initializeLayout();
    }

    public int[][] getLayout() {
        return layout;
    }

    private void initializeLayout() {//qui inizializzo, dando l'indice in sprite_sheetprova di un determinato background, cosa disegnare nella mappa
        layout = new int[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];    //dovrei scrivere 60 linee x 60 colonne, che due maroni
        for(int idxRow = 0; idxRow < NUMBER_OF_ROW_TILES; idxRow++){
            for(int idxCol = 0; idxCol < NUMBER_OF_COLUMN_TILES; idxCol++){
                boolean top = (idxRow == 0);
                boolean bottom = (idxRow == NUMBER_OF_ROW_TILES - 1);
                boolean left = (idxCol == 0);
                boolean right = (idxCol == NUMBER_OF_COLUMN_TILES - 1);

                layout[idxRow][idxCol] = GRASS_TILE;

                if(top && left){
                    layout[idxRow][idxCol] = TOP_LEFT_CORNER_TILE;
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
            }
        }
    }
}
