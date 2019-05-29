import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Deflecter extends Application {
    public void start (Stage ps) {
        Pane p = new Pane();

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        p.getChildren().add(imageView);

        Scene scene = new Scene(p);
        ps.setTitle("Deflecter");
        ps.setScene(scene);
        ps.show();
    }
}
