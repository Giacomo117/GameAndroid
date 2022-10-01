package Graphics;

import android.graphics.Canvas;

import com.example.gameandroid.GameDisplay;

import GameObject.Player;
import GameObject.PlayerState;

public class Animator {
    private Sprite[] playerSpriteArray;
    private int idxNotMovingFrame = 0;
    private int idxMovingFrame = 1;
    private int updateBeforeNextMoveFrame;
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5;

    public Animator(Sprite[] playerSpriteArray){
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()){    //switch sullo stato preso da playerState preso da player
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxNotMovingFrame]);   //se non mi sto muovendo, disegno lo sprite che ho caricato sull'array in posizione 0
                break;
            case STARTED_MOVING:    //
                updateBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            case IS_MOVING:
                updateBeforeNextMoveFrame--;
                if(updateBeforeNextMoveFrame == 0){
                    updateBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleIdxMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            default:
                break;
        }
    }

    private void toggleIdxMovingFrame() {
        if(idxMovingFrame == 1){
            idxMovingFrame = 2;
        } else {
            idxMovingFrame = 1;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite){
        sprite.draw(
                canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()) -  sprite.getWidth()/2, // essendo l'immagine 64x64, bastava fare - 32
                (int)gameDisplay.gameToDisplayCoordinatesY(player.getPositionY()) - sprite.getHeight()/2
        );
    }
}
