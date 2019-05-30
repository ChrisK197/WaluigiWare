import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
                imageView.setX(imageView.getX() + 5);
            }
            else if(e.getCode()== LEFT){
                imageView.setX(imageView.getX()-5);
            }
        });

        Scene scene = new Scene(pane, 1500,700);
        dfbStage.setTitle("Dodge Falling Balls");
        dfbStage.setScene(scene);
        dfbStage.show();
        }
}
