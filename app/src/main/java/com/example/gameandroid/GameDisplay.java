package com.example.gameandroid;

import android.graphics.Rect;

import GameObject.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;
    private final double displayCenterX;
    private final double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private final GameObject centerObject;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);
        this.centerObject = centerObject;

        displayCenterX= widthPixels/2.0;
        displayCenterY= heightPixels/2.0;
        update();
    }

    public void update(){
        gameCenterX=centerObject.getPositionX();
        gameCenterY=centerObject.getPositionY();
        gameToDisplayCoordinatesOffsetX= displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY=displayCenterY - gameCenterY;
    }



    public double gameToDisplayCoordinatesX(double positionX) {
        return positionX + gameToDisplayCoordinatesOffsetX;
    }
    public double gameToDisplayCoordinatesY(double positionY) {
        return positionY + gameToDisplayCoordinatesOffsetY;
    }

    public Rect getGameRect() {//mi serve per avere il rettangolo in vista del gioco
        return new Rect(
                (int) (gameCenterX - widthPixels/2),    //left
                (int) (gameCenterY - heightPixels/2),   //top
                (int) (gameCenterX + widthPixels/2),    //right
                (int) (gameCenterY + heightPixels/2)    //bottom
        );
    }
}
