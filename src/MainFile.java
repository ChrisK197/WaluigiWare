import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
        Button b1 = new Button("Find Waluigi");
        b1.setPrefSize(100, 10);
        selectPane.add(b1, 0, 0);
        b1.setOnMouseClicked(e -> findGuy(new Stage()));





        Scene scene = new Scene(mainPane, 537, 400);
        ps.setScene(scene);
        ps.setTitle("Waluigi Ware");
        ps.show();

        mainPane.requestFocus();
    }

    private void findGuy(Stage s){
        Pane pane = new Pane();
        Rectangle rect = new Rectangle();
        rect.setFill(Color.BLACK);
        rect.widthProperty().bind(pane.widthProperty());
        rect.heightProperty().bind(pane.heightProperty());
        pane.getChildren().add(rect);
        Image wah = new Image("Waluigi.png");
        ImageView wahwah = new ImageView(wah);
        wahwah.setFitWidth(180/4);
        wahwah.setFitHeight(257/4);
        pane.getChildren().add(wahwah);
        Rectangle hide = new Rectangle();
        hide.setFill(Color.BLACK);
        hide.setStroke(Color.BLACK);
        hide.setWidth(wahwah.getFitWidth());
        hide.setHeight(wahwah.getFitHeight());
        pane.getChildren().add(hide);
        wahwah.xProperty().bind(pane.widthProperty().subtract(wah.widthProperty()).multiply(Math.random()));
        wahwah.yProperty().bind(pane.heightProperty().subtract(wah.heightProperty()).multiply(Math.random()));
        hide.yProperty().bind(wahwah.yProperty());
        hide.xProperty().bind(wahwah.xProperty());

        Circle c = new Circle();
        c.setFill(Color.TRANSPARENT);
        c.setStroke(Color.RED);
        c.setRadius(20);
        pane.getChildren().add(c);
        pane.setOnMouseMoved(e ->{
            c.setCenterX(e.getX());
            c.setCenterY(e.getY());
        });

        hide.setOnMouseEntered(e ->{
            pane.getChildren().remove(hide);
            Text text = new Text("You Win");
            text.xProperty().bind(pane.widthProperty().divide(2));
            text.yProperty().bind(pane.heightProperty().divide(2));
            text.setFill(Color.RED);
            text.setFont(Font.font("Comic Sans", 72));
            pane.getChildren().add(text);
        });


        Scene ss = new Scene(pane, 4000, 4000);
        s.setScene(ss);
        s.setTitle("Find Waluigi");
        s.show();
    }
}
