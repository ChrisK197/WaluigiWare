import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FindGuy extends Application {


    @Override
    public void start(Stage s){
        Pane pane = new Pane();

        Image wah = new Image("Waluigi.png");
        ImageView wahwah = new ImageView(wah);



        Scene ss = new Scene(pane);
        s.setScene(ss);
        s.setTitle("Find Waluigi");
        s.show();
    }
}
