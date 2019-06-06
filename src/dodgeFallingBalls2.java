import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class dodgeFallingBalls2 extends Application {
    private int countt =0;
    private int temp =1;
    public void start(Stage stage){
        stage.setTitle("Dodge Falling Balls");

        Pane pane = new Pane();
        Scene scene = new Scene(pane,1500,700);

        int x = (int)(Math.random()*(pane.getWidth()-60));
        Circle ball = new Circle(x+30, 0, 30);
        pane.getChildren().add(ball);

        ArrayList<Circle> ballList = new ArrayList<>();
        ballList.add(ball);
        for (int i=1; i<100; i++){
            int y = (int)(Math.random()*(pane.getWidth()-60));
            Circle ball2 = new Circle(y+30,0,30);
            ballList.add(ball2);
        }

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        pane.getChildren().add(imageView);
        imageView.fitHeightProperty().bind(pane.heightProperty().divide(5));
        imageView.fitWidthProperty().bind(pane.widthProperty().divide(10));
        imageView.setX(600);
        imageView.setY(560);
        Rectangle hitBox = new Rectangle();
        hitBox.heightProperty().bind(pane.heightProperty().divide(5));
        hitBox.widthProperty().bind(pane.widthProperty().divide(10));
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.PURPLE);
        hitBox.setX(600);
        hitBox.setY(560);
        pane.getChildren().add(hitBox);
        pane.setOnKeyPressed(e->{
            if (e.getCode()== RIGHT) {
                if (imageView.getX() + imageView.getFitWidth()<pane.getWidth()){
                    imageView.setX(imageView.getX() + 10);
                    hitBox.setX(imageView.getX());
                }
            }
            else if(e.getCode()== LEFT){
                if (imageView.getX() >0){
                    imageView.setX(imageView.getX()-10);
                    hitBox.setX(imageView.getX());
                }
            }
        });

        final long startNanoTIme = System.nanoTime();
        stage.setScene(scene);
        stage.show();
        new AnimationTimer(){
          public void handle(long currentNanoTime){
                ball.setCenterY(ball.getCenterY()+5);
                if (ball.getCenterX()+30>=imageView.getX()&& ball.getCenterX()-30<=imageView.getFitWidth()+imageView.getX()){
                    if(ball.getCenterY()+30>=560 && ball.getCenterY()-30<=imageView.getY()+imageView.getFitHeight()){
                        this.stop();
                        Text gameOver = new Text("Game Over");
                        gameOver.setFill(Color.RED);
                        gameOver.setStroke(Color.RED);
                        pane.getChildren().add(gameOver);
                        gameOver.setFont(Font.font("Comic Sans", 72));
                        gameOver.setX(500);
                        gameOver.setY(350);
                    }
                }
                temp = 1;
                if (countt ==0){
                    if (ball.getCenterY()+30>=700) {
                        pane.getChildren().add(ballList.get(1));
                        countt++;
                    }
                }
                while (temp<= countt){
                    ballList.get(temp).setCenterY(ballList.get(temp).getCenterY()+5);
                    if (ballList.get(temp).getCenterX()+30>= imageView.getX() && ballList.get(temp).getCenterX()-30<= imageView.getFitWidth()+ imageView.getX()){
                        if(ballList.get(temp).getCenterY()+30>=560 && ballList.get(temp).getCenterY()-30<=imageView.getY()+ imageView.getFitHeight()){
                            this.stop();
                            Text gameOver = new Text("Game Over");
                            gameOver.setFill(Color.RED);
                            gameOver.setStroke(Color.RED);
                            pane.getChildren().add(gameOver);
                            gameOver.setFont(Font.font("Comic Sans", 72));
                            gameOver.setX(500);
                            gameOver.setY(300);
                        }
                    }
                    if (ballList.get(temp).getCenterY()+30>=700){
                        ballList.get(temp).setCenterY(0);
                        pane.getChildren().add(ballList.get(temp+1));
                        countt++;
                    }
                    temp++;
                }
            }
        }.start();
        pane.requestFocus();
    }
}