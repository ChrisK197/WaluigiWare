import javafx.application.Application;
import javafx.scene.Scene;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Memory extends Application {

    @Override
    public void start(Stage s) {
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
        imageList.add(wah);
        imageList.add(wah2);
        imageList.add(mario);
        imageList.add(mario2);
        imageList.add(luigi);
        imageList.add(luigi2);
        imageList.add(wario);
        imageList.add(wario2);
        HashMap<String, CardClass> map = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            int use = (int) (Math.random() * imageList.size());
            CardClass c1 = new CardClass(imageList.get(use));
            c1.getCard().fitWidthProperty().bind(pane.widthProperty().divide(2));
            c1.getCard().fitHeightProperty().bind(pane.heightProperty().divide(4));
            imageList.remove(use);
            c1.getCard().setOnMouseClicked(e -> pushImage(c1, map));
            pane.add(c1.getCard(), i % 2, i / 2);


            map.put(String.format("%d", i), c1);

        }

        pane.setGridLinesVisible(true);
        pane.setHgap(2);
        pane.setVgap(2);

        /*
        pane.setOnMouseClicked(e ->{
            if (pane.getChildren().isEmpty()){
                Text text = new Text("You Win");
                text.xProperty().bind(pane.widthProperty().divide(2));
                text.yProperty().bind(pane.heightProperty().divide(2));
                text.setFill(Color.RED);
                text.setFont(Font.font("Comic Sans", 72));
                pane.add(text, 0, 0);
            }

        });
*/


        Scene scene = new Scene(pane, 200, 400);
        s.setScene(scene);
        s.setTitle("Matching");
        s.show();

        pane.requestFocus();
    }


    public void pushImage(CardClass c1, HashMap<String, CardClass> map) {
        c1.getCard().setImage(c1.getFront());
        c1.setFaceUp(true);
        for (int f = 0; f < 7; f++) {
            for (int j = f + 1; j < 8; j++) {
                if (map.get(String.format("%d", f)).bothUp(map.get(String.format("%d", j)))) {
                    if (map.get(String.format("%d", f)).sameCard(map.get(String.format("%d", j)))) {
                        map.get(String.format("%d", f)).setFaceUp(false);
                        map.get(String.format("%d", j)).setFaceUp(false);
                    } else {
                        map.get(String.format("%d", f)).unflip();
                        map.get(String.format("%d", j)).unflip();
                    }
                }
            }
        }

    }
}



