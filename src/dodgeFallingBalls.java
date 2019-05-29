import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class dodgeFallingBalls extends Application {
    public void start(Stage dfbStage){
        Pane pane = new Pane();

        Rectangle person = new Rectangle(200,200,20,20);
        pane.getChildren().add(person);

        Scene scene = new Scene(pane, 400,400);
        dfbStage.setTitle("Dodge Falling Balls");
        dfbStage.setScene(scene);
        dfbStage.show();
    }
}
