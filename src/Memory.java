import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Memory extends Application {

    @Override
    public void start(Stage s){
        GridPane pane = new GridPane();
        Image wah = new Image("Waluigi.png");
        Image wah2 = new Image("Waluigi.png");

        Image mario = new Image("mario.jpg");
        Image mario2 = new Image("mario.jpg");

        Image luigi = new Image("luigi.jpg");
        Image luigi2 = new Image("luigi.jpg");

        Image wario = new Image("wario.jpg");
        Image wario2 = new Image("wario.jpg");

        ArrayList<Image> imageList = new ArrayList<>();
        imageList.add(wah);imageList.add(wah2);imageList.add(mario);imageList.add(mario2);
        imageList.add(luigi);imageList.add(luigi2);imageList.add(wario);imageList.add(wario2);

        for (int i =0; i < 8; i++) {
            int use = (int) (Math.random() * imageList.size());
            CardClass c1 = new CardClass(imageList.get(use));
            c1.getCard().fitWidthProperty().bind(pane.widthProperty().divide(2));
            c1.getCard().fitHeightProperty().bind(pane.heightProperty().divide(4));
            imageList.remove(use);
            pane.add(c1.getCard(), i % 2, i/2);

        }



        pane.setGridLinesVisible(true);
        pane.setHgap(2);
        pane.setVgap(2);







        Scene scene = new Scene(pane, 200, 400);
        s.setScene(scene);
        s.setTitle("Matching");
        s.show();

        pane.requestFocus();
    }
}


