import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFile extends Application{
    @Override
    public void start(Stage ps){

        GridPane mainPane = new GridPane();
        Pane imagePane = new Pane();
        Image image = new Image("Menu - Copy.png");
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(imagePane.widthProperty());
        imageView.fitHeightProperty().bind(imagePane.heightProperty());
        imagePane.getChildren().add(imageView);
        mainPane.add(imagePane, 0,0);
        GridPane selectPane = new GridPane();
        mainPane.add(selectPane,0,1);
        Button b1 = new Button("Test");
        selectPane.add(b1, 0, 0);





        Scene scene = new Scene(mainPane, 537, 400);
        ps.setScene(scene);
        ps.setTitle("Waluigi Ware");
        ps.show();

        mainPane.requestFocus();
    }
}
