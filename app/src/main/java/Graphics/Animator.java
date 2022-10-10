package Graphics;

import android.graphics.Canvas;

import com.example.gameandroid.GameDisplay;

import GameObject.Enemy;
import GameObject.Player;
import GameObject.PlayerState;

public class Animator {
    private Sprite[] playerSpriteArray;
    private int idxNotMovingFrame = 0;
    private int idxMovingFrame = 0;
    private int updateBeforeNextMoveFrame;
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5;

    public Animator(Sprite[] playerSpriteArray){
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()){    //switch sullo stato preso da playerState preso da player
            case NOT_MOVING:
                drawPlayerFrame(canvas, gameDisplay, player, playerSpriteArray[idxNotMovingFrame]);   //se non mi sto muovendo, disegno lo sprite che ho caricato sull'array in posizione 0
                break;
            case STARTED_MOVING:    //
                updateBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                drawPlayerFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            case IS_MOVING:
                updateBeforeNextMoveFrame--;
                if(updateBeforeNextMoveFrame == 0){
                    updateBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleIdxMovingFrame();
                }
                drawPlayerFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            default:
                break;
        }
    }

    public void drawEnemy(Canvas canvas, GameDisplay gameDisplay, Enemy enemy) {
        //idxNotMovingFrame = 0;
        updateBeforeNextMoveFrame--;
        if(updateBeforeNextMoveFrame == 0){
            updateBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
            toggleIdxMovingFrame();
        }
        drawEnemyFrame(canvas, gameDisplay, enemy, playerSpriteArray[idxMovingFrame]);

    }

    private void toggleIdxMovingFrame() {
        if(idxMovingFrame == 0){
            idxMovingFrame = 1;
        } else if(idxMovingFrame == 1){
            idxMovingFrame = 2;
        } else if(idxMovingFrame == 2){
            idxMovingFrame = 3;
        } else if(idxMovingFrame == 3){
            idxMovingFrame = 0;
        }
    }

    public void drawPlayerFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite){
        sprite.draw(
                canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()) -  sprite.getWidth()/2, // essendo l'immagine 64x64, bastava fare - 32
                (int)gameDisplay.gameToDisplayCoordinatesY(player.getPositionY()) - sprite.getHeight()/2
        );
    }

    public void drawEnemyFrame(Canvas canvas, GameDisplay gameDisplay, Enemy enemy, Sprite sprite){
        sprite.draw(
                canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(enemy.getPositionX()) -  sprite.getWidth()/2, // essendo l'immagine 64x64, bastava fare - 32
                (int)gameDisplay.gameToDisplayCoordinatesY(enemy.getPositionY()) - sprite.getHeight()/2
        );
    }
}
