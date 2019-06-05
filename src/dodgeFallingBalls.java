import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.logging.Level;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class dodgeFallingBalls extends Application {
    public void start(Stage dfbStage){
        Pane pane = new Pane();

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        pane.getChildren().add(imageView);
        imageView.fitHeightProperty().bind(pane.heightProperty().divide(5));
        imageView.fitWidthProperty().bind(pane.widthProperty().divide(10));
        imageView.setX(600);
        imageView.setY(560);

        pane.setOnKeyPressed(e->{
            if (e.getCode()== RIGHT) {
                if (imageView.getX() + imageView.getFitWidth()<pane.getWidth()){
                    imageView.setX(imageView.getX() + 5);
                }
            }
            else if(e.getCode()== LEFT){
                if (imageView.getX() >0){
                    imageView.setX(imageView.getX()-5);
                }
            }
        });

        Scene scene = new Scene(pane, 1500,700);
        dfbStage.setTitle("Dodge Falling Balls");
        dfbStage.setScene(scene);
        dfbStage.show();

        pane.requestFocus();
        }
}
class BallPane extends Pane {
    public final double radius = 10;
    private double x = (int)(25), y= radius;
    private double dx=0, dy=5;
    private Circle circle = new Circle(x,y,radius);
    private Timeline animation;

    public BallPane(){
        circle.setFill(Color.BLACK);
        getChildren().add(circle);

        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e-> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void play(){
        animation.play();
    }

    public void pause(){
        animation.pause();
    }

    public void increaseSpeed(){
        animation.setRate(animation.getRate()+.1);
    }

    public void decreaseSpeed(){
        animation.setRate(animation.getRate() >0 ? animation.getRate()-.1:0);
    }

    public DoubleProperty rateProperty(){
        return animation.rateProperty();
    }

    protected void moveBall(){
        if(y>getHeight()-radius){
            getChildren().remove(circle);
        }
        y-=dy;
        circle.setCenterY(y);
    }
}

