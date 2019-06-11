import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class DodgeFallingBalls3 extends Application {
    private int countt =0;
    private int temp =1;
    public void start(Stage stage){
        stage.setTitle("Dodge Falling Balls");

        Pane pane = new Pane();
        Scene scene = new Scene(pane,1500,700);

        int x = (int)(Math.random()*(pane.getWidth()-60));
        TraversingCircle ball = new TraversingCircle(x+30, 0);
        pane.getChildren().add(ball);

        ArrayList<TraversingCircle> ballList = new ArrayList<>();
        ballList.add(ball);
        for (int i=1; i<100; i++){
            int y = (int)(Math.random()*(pane.getWidth()-60));
            TraversingCircle ball2 = new TraversingCircle(y+30,0);
            ballList.add(ball2);
        }
        Text score = new Text(String.format("%d", countt));
        pane.getChildren().add(score);
        score.setFill(Color.PURPLE);
        score.setStroke(Color.PURPLE);
        score.setFont(Font.font("Comic Sans", 32));
        score.setX(10);
        score.setY(35);

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        pane.getChildren().add(imageView);
        imageView.fitHeightProperty().bind(pane.heightProperty().divide(15));
        imageView.fitWidthProperty().bind(pane.widthProperty().divide(30));
        imageView.setX(pane.getWidth()/2 -150);
        imageView.setY(pane.getHeight()-50);
        Rectangle hitBox = new Rectangle();
        hitBox.heightProperty().bind(pane.heightProperty().divide(15));
        hitBox.widthProperty().bind(pane.widthProperty().divide(30));
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.PURPLE);
        hitBox.setX(pane.getWidth()/2-150);
        hitBox.setY(pane.getHeight()-50);
        pane.getChildren().add(hitBox);
        pane.setOnKeyPressed(e->{
            if (e.getCode()== RIGHT) {
                if (imageView.getX() + imageView.getFitWidth()<pane.getWidth()){
                    imageView.setX(imageView.getX() + 15);
                    hitBox.setX(imageView.getX());
                }
            }
            else if(e.getCode()== LEFT){
                if (imageView.getX() >0){
                    imageView.setX(imageView.getX()-15);
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
                stage.setOnCloseRequest(e ->{
                    this.stop();
                });
                if (ball.getCenterX()+30>=imageView.getX()&& ball.getCenterX()-30<=imageView.getFitWidth()+imageView.getX()){
                    if(ball.getCenterY()+30>=imageView.getY() && ball.getCenterY()-30<=imageView.getY()+imageView.getFitHeight()){
                        this.stop();
                        Text gameOver = new Text("Game Over");
                        gameOver.setFill(Color.RED);
                        gameOver.setStroke(Color.RED);
                        pane.getChildren().add(gameOver);
                        gameOver.setFont(Font.font("Comic Sans", 72));
                        gameOver.setX(500);
                        gameOver.setY(350);
                        String musicFile = "waluigi_wah.mp3";
                        Media sound = new Media(new File(musicFile).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.setVolume(100);
                        mediaPlayer.play();
                    }
                }
                temp = 1;
                if (countt ==0){
                    if (ball.getCenterY()+30>=700) {
                        pane.getChildren().add(ballList.get(1));
                        countt++;
                        score.setText(String.format("%d", countt));
                    }
                }
                while (temp<= countt){
                    TraversingCircle ball = ballList.get(temp);

                    ball.setCenterY(ball.getCenterY()+5);
                    ball.setCenterX(ball.getCenterX()+ball.horizVelocity);

                    if (ball.getCenterX()+ 30>=pane.getWidth() || ball.getCenterX()-30<=0) {
                        ball.horizVelocity *= -1;
                    }

                    if (ball.getCenterX()+30>= imageView.getX() && ball.getCenterX()-30<= imageView.getFitWidth()+ imageView.getX()){
                        if(ball.getCenterY()+30>=imageView.getY() && ball.getCenterY()-30<=imageView.getY()+ imageView.getFitHeight()){
                            this.stop();
                            Text gameOver = new Text("Game Over");
                            gameOver.setFill(Color.RED);
                            gameOver.setStroke(Color.RED);
                            pane.getChildren().add(gameOver);
                            gameOver.setFont(Font.font("Comic Sans", 72));
                            gameOver.setX(500);
                            gameOver.setY(300);
                            String musicFile = "waluigi_wah.mp3";
                            Media sound = new Media(new File(musicFile).toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.setVolume(100);
                            mediaPlayer.play();
                        }
                    }
                    if(ballList.get(countt).getCenterY()+30>=pane.getHeight()){
                        ballList.get(countt).setCenterY(0);
                        pane.getChildren().add(ballList.get(countt+1));
                        countt++;
                        score.setText(String.format("%d", countt));
                    }
                    if (ballList.get(temp).getCenterY()+30>=pane.getHeight()){
                        ballList.get(temp).setCenterY(0);
                    }
                    temp++;
                }
            }
        }.start();
        pane.requestFocus();
    }
}