import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Memory extends Application {

    @Override
    public void start(Stage s){
        GridPane pane = new GridPane();

        Image star = new Image("star.jpg");
        ImageView cardBack = new ImageView(star);
        cardBack.fitWidthProperty().bind(pane.widthProperty().divide(2));
        cardBack.fitHeightProperty().bind(pane.heightProperty().divide(4));


        pane.setGridLinesVisible(true);
        pane.setHgap(2);
        pane.setVgap(2);

        ImageView c2 = new ImageView(star);
        c2.fitWidthProperty().bind(pane.widthProperty().divide(2));
        c2.fitHeightProperty().bind(pane.heightProperty().divide(4));

        ImageView c3 = new ImageView(star);
        c3.fitWidthProperty().bind(pane.widthProperty().divide(2));
        c3.fitHeightProperty().bind(pane.heightProperty().divide(4));

        ImageView c4 = new ImageView(star);
        c4.fitWidthProperty().bind(pane.widthProperty().divide(2));
        c4.fitHeightProperty().bind(pane.heightProperty().divide(4));

        ImageView c5 = new ImageView(star);
        c5.fitWidthProperty().bind(pane.widthProperty().divide(2));
        c5.fitHeightProperty().bind(pane.heightProperty().divide(4));

        ImageView c6 = new ImageView(star);
        c6.fitWidthProperty().bind(pane.widthProperty().divide(2));
        c6.fitHeightProperty().bind(pane.heightProperty().divide(4));

        ImageView c7 = new ImageView(star);
        c7.fitWidthProperty().bind(pane.widthProperty().divide(2));
        c7.fitHeightProperty().bind(pane.heightProperty().divide(4));

        ImageView c8 = new ImageView(star);
        c8.fitWidthProperty().bind(pane.widthProperty().divide(2));
        c8.fitHeightProperty().bind(pane.heightProperty().divide(4));

        pane.addColumn(0, cardBack, c2, c3, c4);
        pane.addColumn(1, c5, c6, c7, c8);






        Scene scene = new Scene(pane, 200, 400);
        s.setScene(scene);
        s.setTitle("Matching");
        s.show();

        pane.requestFocus();
    }
}


