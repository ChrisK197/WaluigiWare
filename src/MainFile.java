import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFile extends Application{
    protected Text texttt = new Text("ADD ME");
    protected Text textt = new Text("TO SMASH");

    protected int dx;
    protected int dy;

    @Override
    public void start(Stage ps){

        GridPane mainPane = new GridPane();
        Pane imagePane = new Pane();
        Image image = new Image("Menu - Copy.png");
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(imagePane.widthProperty());
        imageView.fitHeightProperty().bind(imagePane.heightProperty());
        imagePane.getChildren().add(imageView);
        imagePane.setOnMousePressed(e ->{
            imageView.setImage(new Image("smash.jpg"));
            texttt.setFill(Color.PURPLE);
            texttt.setFont(Font.font("Comic Sans", 72));
            texttt.setX(0);
            texttt.setY(100);
            imagePane.getChildren().add(texttt);

            textt.setFill(Color.PURPLE);
            textt.setFont(Font.font("Comic Sans", 72));
            textt.setX(0);
            textt.setY(200);
            imagePane.getChildren().add(textt);

        });
        imagePane.setOnMouseReleased(e ->{
            imageView.setImage(image);
            imagePane.getChildren().remove(texttt);
            imagePane.getChildren().remove(textt);
        });
        mainPane.add(imagePane, 0,0);
        GridPane selectPane = new GridPane();
        mainPane.add(selectPane,0,1);
        Button b1 = new Button("Find Waluigi");
        b1.setPrefSize(120, 10);
        selectPane.add(b1, 0, 0);
        b1.setOnMouseClicked(e -> findGuy(new Stage()));
        Button b2 = new Button("Memory Matching");
        b2.setPrefSize(120, 10);
        selectPane.add(b2, 1, 0);
        b2.setOnMouseClicked(e -> {
            count = 0;
            memory(new Stage());
        });
        Button b3 = new Button("Deflector");
        b3.setPrefSize(120, 10);
        selectPane.add(b3, 2, 0);
        b3.setOnMouseClicked(e -> deflector(new Stage()));



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
            Button b = new Button("Return to select screen");
            b.setPrefSize(150, 10);
            b.layoutXProperty().bind(text.xProperty());
            b.layoutYProperty().bind(text.yProperty().add(50));
            b.setOnMouseClicked(f -> s.hide());
            /*
            Button b2 = new Button("Play Again");
            b2.layoutXProperty().bind(text.xProperty());
            b2.layoutYProperty().bind(text.yProperty().add(60));
            b2.setOnMouseClicked(f -> findGuy(s));
            ADD b2
             */
            pane.getChildren().addAll(b);
        });


        Scene ss = new Scene(pane, 4000, 4000);
        s.setScene(ss);
        s.setTitle("Find Waluigi");
        s.show();
    }
    public int count = 0;

    public void memory(Stage s) {
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
        ArrayList<String> llist = new ArrayList<>();
        llist.add("0");
        llist.add("0");
        llist.add("1");
        llist.add("1");
        llist.add("2");
        llist.add("2");
        llist.add("3");
        llist.add("3");
        HashMap<String, CardClass> map = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            int use = (int) (Math.random() * imageList.size());
            String code = llist.get(use);
            CardClass c1 = new CardClass(imageList.get(use), code);
            c1.getCard().fitWidthProperty().bind(pane.widthProperty().divide(2));
            c1.getCard().fitHeightProperty().bind(pane.heightProperty().divide(4));
            imageList.remove(use);
            llist.remove(use);
            c1.getCard().setOnMouseClicked(e -> {
                c1.getCard().setImage(c1.getFront());
                c1.setFaceUp(true);
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(500),
                        ae -> pushImage(c1, map, pane)));
                timeline.play();

            });
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

    public void deflector (Stage ps) {
        dx = 5;
        dy = 5;

        Image loser = new Image("Waluigi5.png");
        ImageView imageView2 = new ImageView(loser);
        Text t = new Text("WAAAA! You lose!");

        Circle shield = new Circle(150);
        Circle circle = new Circle(50);

        Pane p = new Pane();

        if (p.getChildren().contains(shield)) {
            p.getChildren().remove(shield);
        }
        if (p.getChildren().contains(circle)) {
            p.getChildren().remove(circle);
        }

        shield.centerXProperty().bind(p.widthProperty().divide(2).add(10));
        shield.centerYProperty().bind(p.heightProperty().divide(2));
        shield.setStroke(Color.RED);
        shield.setStrokeWidth(5);
        shield.setFill(Color.WHITE);
        p.getChildren().add(shield);

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        p.getChildren().add(imageView);
        imageView.xProperty().bind(p.widthProperty().divide(2).subtract(waluigi.getWidth()/2));
        imageView.yProperty().bind(p.heightProperty().divide(2).subtract(waluigi.getHeight()/2));

        if ((int)(Math.random()*4) == 0) {
            circle.setCenterX(80);
            circle.setCenterY((int)((Math.random()*670)+80));
        }
        else if ((int)(Math.random()*4) == 1) {
            circle.setCenterX(670);
            circle.setCenterY((int)((Math.random()*670)+80));
        }
        else if ((int)(Math.random()*4) == 2) {
            circle.setCenterX((int)((Math.random()*670)+80));
            circle.setCenterY(80);
        }
        else {
            circle.setCenterX((int)((Math.random()*670)+80));
            circle.setCenterY(670);
        }
        circle.setFill(Color.PURPLE);
        p.getChildren().add(circle);
        circle.setOnMousePressed(e -> {
            if ((int)(Math.random()*4) == 0) {
                circle.setCenterX(80);
                circle.setCenterY((int)((Math.random()*670)+80));
            }
            else if ((int)(Math.random()*4) == 1) {
                circle.setCenterX(670);
                circle.setCenterY((int)((Math.random()*670)+80));
            }
            else if ((int)(Math.random()*4) == 2) {
                circle.setCenterX((int)((Math.random()*670)+80));
                circle.setCenterY(80);
            }
            else {
                circle.setCenterX((int)((Math.random()*670)+80));
                circle.setCenterY(670);
            }
            dx *= 1.25;
            dy *= 1.25;
        });
        Timeline animation = new Timeline();
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(50), e -> {
            if (circle.getCenterX()<circle.getRadius() || circle.getCenterX()>p.getWidth() - circle.getRadius()) {
                dx *= -1;
            }
            if (circle.getCenterY()<circle.getRadius()|| circle.getCenterY()>p.getHeight() - circle.getRadius()) {
                dy *= -1;
            }

            if (Math.pow(circle.getCenterX()-shield.getCenterX(), 2) + Math.pow(circle.getCenterY()-shield.getCenterY(), 2) < Math.pow(circle.getRadius()+shield.getRadius(), 2)) {
                circle.setFill(Color.RED);
                imageView2.setX(0);
                imageView2.setY(0);
                t.setFill(Color.PURPLE);
                t.setFont(Font.font("Comic Sans", 50));
                t.setX(300);
                t.setY(100);
                p.getChildren().add(imageView2);
                p.getChildren().add(t);
                animation.stop();
            }
            circle.setCenterX(circle.getCenterX() + dx);
            circle.setCenterY(circle.getCenterY() + dy);
        }));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        Scene scene = new Scene(p, 1000, 750);
        ps.setTitle("Deflector");
        ps.setScene(scene);
        ps.show();
    }


    public void pushImage(CardClass c1, HashMap<String, CardClass> map, GridPane pane) {
        for (int f = 0; f < 7; f++) {
            for (int j = f + 1; j < 8; j++) {
                if (map.get(String.format("%d", f)).bothUp(map.get(String.format("%d", j)))) {
                    if (map.get(String.format("%d", f)).sameCard(map.get(String.format("%d", j)))) {
                        map.get(String.format("%d", f)).setFaceUp(false);
                        map.get(String.format("%d", j)).setFaceUp(false);
                        map.get(String.format("%d", f)).inactive();
                        map.get(String.format("%d", j)).inactive();
                        count += 2;
                    } else {
                        map.get(String.format("%d", f)).unflip();
                        map.get(String.format("%d", j)).unflip();
                        map.get(String.format("%d", f)).setFaceUp(false);
                        map.get(String.format("%d", j)).setFaceUp(false);
                    }
                }
            }
        }
        if (count>= 8){
            Text text = new Text("You");
            text.xProperty().bind(pane.widthProperty().divide(2));
            text.yProperty().bind(pane.heightProperty().divide(2));
            text.setFill(Color.RED);
            text.setFont(Font.font("Comic Sans", 72));
            pane.add(text, 0, 0);

            Text text2 = new Text("Win");
            text2.xProperty().bind(pane.widthProperty().divide(2));
            text2.yProperty().bind(pane.heightProperty().divide(2));
            text2.setFill(Color.RED);
            text2.setFont(Font.font("Comic Sans", 72));
            pane.add(text2, 0, 1);
        }
    }


}
