package Map;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    public static final int NUMBER_OF_ROW_TILES = 60;
    public static final int NUMBER_OF_COLUMN_TILES = 60;

    private int [][] layout;

    public MapLayout() {
        initializeLayout();
    }

    public int[][] getLayout() {
        return layout;
    }

    private void initializeLayout() {//qui inizializzo, dando l'indice in sprite_sheetprova di un determinato background, cosa disegnare nella mappa
        layout = new int[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];    //dovrei scrivere 60 linee x 60 colonne, che due maroni
        for(int i = 0; i < NUMBER_OF_ROW_TILES; i++){
            int cont = 0;
            for(int j = 0; j < NUMBER_OF_COLUMN_TILES; j++){
                layout[i][j] = 3;
                if(i % 2 == 0){
                    if(cont == 15){
                        layout[i][j] = 4;
                    }
                } else {
                    if(cont == 6){
                        layout[i][j] = 4;
                    }
                }

                if(i > 20 && i < 30){
                    if(j > 15 && j < 22){
                        layout[i][j] = 0;
                    }
                }
                cont++;
            }
        }
    }
}
