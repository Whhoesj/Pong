package nl.sjtek.smartmobile.pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class GameState {

    //screen width and height
    final int screenWidth = 640;
    final int screenHeight = 360;

    //The ball
    final int ballSize = 10;
    int ballX = 100;
    int ballY = 100;
    int ballVelocityX = 3;
    int ballVelocityY = 3;

    //The bats
    final int batLength = 75;
    final int batHeight = 10;
    final int batMargin = 10;

    int topBatX = (screenWidth / 2) - (batLength / 2);
    final int topBatY = batMargin;

    int bottomBatX = (screenWidth / 2) - (batLength / 2);
    final int bottomBatY = screenHeight - batHeight - batMargin;


    public GameState() {
    }

    //The update method
    public void update() {

        ballX += ballVelocityX;
        ballY += ballVelocityY;

        topBatX = ballX - batLength/2;

        //DEATH!
        if (ballY > screenHeight - (ballSize/2) || ballY < ballSize/2) {
            ballX = 100;
            ballY = 100;
        }    //Collisions with the sides

        if (ballX > screenWidth || ballX < 0)
            ballVelocityX *= -1;    //Collisions with the bats

        if (ballX > topBatX && ballX < topBatX + batLength && ballY < topBatY)
            ballVelocityY *= -1;  //Collisions with the bats

        if (ballX > bottomBatX && ballX < bottomBatX + batLength
                && ballY > bottomBatY)
            ballVelocityY *= -1;
    }

    public void changeBatSpeed(float batSpeed) {
        bottomBatX = (int) batSpeed;
    }


    public void draw(Canvas canvas, Paint paint) {

        //Clear the screen
        canvas.drawRGB(0, 0, 0);

        //set the colour
        paint.setARGB(200, 255, 255, 255);

        //draw the ball
        canvas.drawRect(new Rect(ballX, ballY, ballX + ballSize, ballY + ballSize),
                paint);

        //draw the bats
        canvas.drawRect(new Rect(topBatX, topBatY, topBatX + batLength,
                topBatY + batHeight), paint); //top bat
        canvas.drawRect(new Rect(bottomBatX, bottomBatY, bottomBatX + batLength,
                bottomBatY + batHeight), paint); //bottom bat

    }
}