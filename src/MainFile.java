import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class MainFile extends Application{
    private Text texttt = new Text("ADD ME");
    private Text textt = new Text("TO SMASH");

    private int dx;
    private int dy;
    private int total;
    private Text s;

    private int countt =0;
    private int temp =1;

    private Timeline musicLoop = new Timeline(new KeyFrame(Duration.millis(122000), e -> musicLoopAnimation()));

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
        b1.setPrefSize(537/4, 10);
        selectPane.add(b1, 0, 0);
        b1.setOnMouseClicked(e -> findGuyRules(new Stage()));
        Button b2 = new Button("Memory Matching");
        b2.setPrefSize(537/4, 10);
        selectPane.add(b2, 1, 0);
        b2.setOnMouseClicked(e -> {
            count = 0;
            memoryRules(new Stage());
        });
        Button b3 = new Button("Deflector");
        b3.setPrefSize(537/4, 10);
        selectPane.add(b3, 2, 0);
        b3.setOnMouseClicked(e -> deflectorRules(new Stage()));

        Button b4 = new Button("Dodge Falling Balls");
        b4.setPrefSize(537/4, 10);
        selectPane.add(b4, 3, 0);
        b4.setOnMouseClicked(e-> dodgeFallingBallsInstructions());

        Button b5 = new Button("Dodge Falling Balls2");
        b5.setPrefSize(537/4, 10);
        selectPane.add(b5, 0, 1);
        b5.setOnMouseClicked(e-> {
            countt = 0;
            dodgeFallingBalls2(new Stage());
            countt = 0;
        });

        String musicFile = "Wii_Sports.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(100);
        mediaPlayer.play();
        musicLoop.play();

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
    private int count = 0;

    private void memory(Stage s) {
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
                        ae -> pushImage(c1, map, pane, s)));
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

    private void deflector (Stage ps) {
        dx = 5;
        dy = 5;
        total = 0;

        Image loser = new Image("Waluigi5.png");
        ImageView imageView2 = new ImageView(loser);
        Text t = new Text("WAAAA! You lose!");

        s = new Text(Integer.toString(total));

        Circle shield = new Circle(150);
        Circle circle = new Circle(50);

        Pane p = new Pane();

        s.setFill(Color.PURPLE);
        s.setFont(Font.font("Comic Sans", 50));
        s.setX(100);
        s.setY(100);

        if (p.getChildren().contains(shield)) {
            p.getChildren().remove(shield);
        }
        if (p.getChildren().contains(circle)) {
            p.getChildren().remove(circle);
        }
        if (p.getChildren().contains(imageView2)) {
            p.getChildren().remove(imageView2);
        }
        if (p.getChildren().contains(t)) {
            p.getChildren().remove(t);
        }
        if (p.getChildren().contains(s)) {
            p.getChildren().remove(s);
        }

        shield.centerXProperty().bind(p.widthProperty().divide(2).add(10));
        shield.centerYProperty().bind(p.heightProperty().divide(2));
        shield.setStroke(Color.PURPLE);
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
            total++;
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
                String musicFile = "waluigi_wah.mp3";
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.setVolume(100);
                mediaPlayer.play();
                animation.stop();
            }
            circle.setCenterX(circle.getCenterX() + dx);
            circle.setCenterY(circle.getCenterY() + dy);
            s.setText(Integer.toString(total));
        }));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        p.getChildren().add(s);

        Scene scene = new Scene(p, 1000, 750);
        ps.setTitle("Deflector");
        ps.setScene(scene);
        ps.show();
    }


    private void pushImage(CardClass c1, HashMap<String, CardClass> map, GridPane pane, Stage s) {
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
            Stage tempp = new Stage();
            Pane pp = new Pane();
            Text text = new Text("You");
            text.setX(60);
            text.setY(100);
            text.setFill(Color.RED);
            text.setFont(Font.font("Comic Sans", 72));
            pp.getChildren().add(text);

            Text text2 = new Text("Win");
            text2.setX(60);
            text2.setY(200);
            text2.setFill(Color.RED);
            text2.setFont(Font.font("Comic Sans", 72));
            pp.getChildren().add(text2);

            Button bb = new Button("Return to menu");
            bb.setOnMouseClicked(e ->{
                tempp.hide();
                s.hide();
            });
            bb.setLayoutX(60);
            bb.setLayoutY(250);
            pp.getChildren().add(bb);

            Scene sss = new Scene(pp, 200, 300);
            tempp.setScene(sss);
            tempp.setTitle("You Won");
            tempp.show();
        }
    }

    private void dodgeFallingBallsInstructions(){
        Pane pane = new Pane();
        Text instru = new Text();
        instru.setText("The goal is to dodge the falling balls\n " +
                "if you get hit it is game over!\n" +
                "This is the og version. There is \n" +
                "another better version too!");
        pane.getChildren().add(instru);
        instru.setX(10);
        instru.setY(70);
        Button starttttttt = new Button("Click to Start");
        pane.getChildren().add(starttttttt);
        Scene scene = new Scene(pane,246, 400);
        Stage s = new Stage();
        s.setScene(scene);
        s.setTitle("Instructions");
        s.show();
        starttttttt.setOnMouseClicked(e -> {
            countt =0;
            s.hide();
            dodgeFallingBalls(new Stage());
            countt=0;
        });
    }
    private void dodgeFallingBalls(Stage stage){
        stage.setTitle("Dodge Falling Balls");

        Pane pane = new Pane();
        Scene scene = new Scene(pane,1500,700);

        int x = (int)(Math.random()*(pane.getWidth()-60));
        Circle ball = new Circle(x+30, 0, 30);
        pane.getChildren().add(ball);

        ArrayList<Circle> ballList = new ArrayList<>();
        ballList.add(ball);
        for (int i=1; i<100; i++){
            int y = (int)(Math.random()*(pane.getWidth()-60));
            Circle ball2 = new Circle(y+30,0,30);
            ballList.add(ball2);
        }

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        pane.getChildren().add(imageView);
        imageView.fitHeightProperty().bind(pane.heightProperty().divide(5));
        imageView.fitWidthProperty().bind(pane.widthProperty().divide(10));
        imageView.setX(600);
        imageView.setY(560);
        Rectangle hitBox = new Rectangle();
        hitBox.heightProperty().bind(pane.heightProperty().divide(5));
        hitBox.widthProperty().bind(pane.widthProperty().divide(10));
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.PURPLE);
        hitBox.setX(600);
        hitBox.setY(560);
        pane.getChildren().add(hitBox);
        pane.setOnKeyPressed(e->{
            if (e.getCode()== RIGHT) {
                if (imageView.getX() + imageView.getFitWidth()<pane.getWidth()){
                    imageView.setX(imageView.getX() + 10);
                    hitBox.setX(imageView.getX());
                }
            }
            else if(e.getCode()== LEFT){
                if (imageView.getX() >0){
                    imageView.setX(imageView.getX()-10);
                    hitBox.setX(imageView.getX());
                }
            }
        });

        final long startNanoTIme = System.nanoTime();
        stage.setScene(scene);
        stage.show();
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                ball.setCenterY(ball.getCenterY()+5);
                stage.setOnCloseRequest(e ->{
                    this.stop();
                });
                if (ball.getCenterX()+30>=imageView.getX()&& ball.getCenterX()-30<=imageView.getFitWidth()+imageView.getX()){
                    if(ball.getCenterY()+30>=560 && ball.getCenterY()-30<=imageView.getY()+imageView.getFitHeight()){
                        this.stop();
                        Text gameOver = new Text("Game Over");
                        gameOver.setFill(Color.RED);
                        gameOver.setStroke(Color.RED);
                        pane.getChildren().add(gameOver);
                        gameOver.setFont(Font.font("Comic Sans", 72));
                        gameOver.setX(500);
                        gameOver.setY(350);
                    }
                }
                temp = 1;
                if (countt ==0){
                    if (ball.getCenterY()+30>=700) {
                        pane.getChildren().add(ballList.get(1));
                        countt++;
                    }
                }
                while (temp<= countt){
                    ballList.get(temp).setCenterY(ballList.get(temp).getCenterY()+5);
                    if (ballList.get(temp).getCenterX()+30>= imageView.getX() && ballList.get(temp).getCenterX()-30<= imageView.getFitWidth()+ imageView.getX()){
                        if(ballList.get(temp).getCenterY()+30>=560 && ballList.get(temp).getCenterY()-30<=imageView.getY()+ imageView.getFitHeight()){
                            this.stop();
                            Text gameOver = new Text("Game Over");
                            gameOver.setFill(Color.RED);
                            gameOver.setStroke(Color.RED);
                            pane.getChildren().add(gameOver);
                            gameOver.setFont(Font.font("Comic Sans", 72));
                            gameOver.setX(500);
                            gameOver.setY(300);
                        }
                    }
                    if(ballList.get(countt).getCenterY()+30>=700){
                        ballList.get(countt).setCenterY(0);
                        pane.getChildren().add(ballList.get(countt+1));
                        countt++;
                    }
                    if (ballList.get(temp).getCenterY()+30>=700){
                        ballList.get(temp).setCenterY(0);
                    }
                    temp++;
                }
            }
        }.start();
        pane.requestFocus();
    }

    private void dodgeFallingBalls2(Stage stage){
        stage.setTitle("Dodge Falling Balls");

        Pane pane = new Pane();
        Scene scene = new Scene(pane,1500,700);

        int x = (int)(Math.random()*(pane.getWidth()-60));
        TraversingCircle ball = new TraversingCircle(x+30, 0);
        pane.getChildren().add(ball);

        ArrayList<TraversingCircle> ballList = new ArrayList<>();
        ballList.add(ball);
        for (int i=1; i<100; i++){
            int y = (int)(Math.random()*(pane.getWidth()-60));
            TraversingCircle ball2 = new TraversingCircle(y+30,0);
            ballList.add(ball2);
        }
        Text score = new Text(String.format("%d", countt));
        pane.getChildren().add(score);
        score.setFill(Color.PURPLE);
        score.setStroke(Color.PURPLE);
        score.setFont(Font.font("Comic Sans", 32));
        score.setX(10);
        score.setY(35);

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        pane.getChildren().add(imageView);
        imageView.fitHeightProperty().bind(pane.heightProperty().divide(15));
        imageView.fitWidthProperty().bind(pane.widthProperty().divide(30));
        imageView.setX(600);
        imageView.setY(650);
        Rectangle hitBox = new Rectangle();
        hitBox.heightProperty().bind(pane.heightProperty().divide(15));
        hitBox.widthProperty().bind(pane.widthProperty().divide(30));
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.PURPLE);
        hitBox.setX(600);
        hitBox.setY(650);
        pane.getChildren().add(hitBox);
        pane.setOnKeyPressed(e->{
            if (e.getCode()== RIGHT) {
                if (imageView.getX() + imageView.getFitWidth()<pane.getWidth()){
                    imageView.setX(imageView.getX() + 15);
                    hitBox.setX(imageView.getX());
                }
            }
            else if(e.getCode()== LEFT){
                if (imageView.getX() >0){
                    imageView.setX(imageView.getX()-15);
                    hitBox.setX(imageView.getX());
                }
            }
        });

        final long startNanoTIme = System.nanoTime();
        stage.setScene(scene);
        stage.show();
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                ball.setCenterY(ball.getCenterY()+5);
                stage.setOnCloseRequest(e ->{
                    this.stop();
                });
                if (ball.getCenterX()+30>=imageView.getX()&& ball.getCenterX()-30<=imageView.getFitWidth()+imageView.getX()){
                    if(ball.getCenterY()+30>=560 && ball.getCenterY()-30<=imageView.getY()+imageView.getFitHeight()){
                        this.stop();
                        Text gameOver = new Text("Game Over");
                        gameOver.setFill(Color.RED);
                        gameOver.setStroke(Color.RED);
                        pane.getChildren().add(gameOver);
                        gameOver.setFont(Font.font("Comic Sans", 72));
                        gameOver.setX(500);
                        gameOver.setY(350);
                    }
                }
                temp = 1;
                if (countt ==0){
                    if (ball.getCenterY()+30>=700) {
                        pane.getChildren().add(ballList.get(1));
                        countt++;
                        score.setText(String.format("%d", countt));
                    }
                }
                while (temp<= countt){
                    TraversingCircle ball = ballList.get(temp);

                    ball.setCenterY(ball.getCenterY()+5);
                    ball.setCenterX(ball.getCenterX()+ball.horizVelocity);

                    if (ball.getCenterX()+ 30>=pane.getWidth() || ball.getCenterX()-30<=0) {
                        ball.horizVelocity *= -1;
                    }

                    if (ball.getCenterX()+30>= imageView.getX() && ball.getCenterX()-30<= imageView.getFitWidth()+ imageView.getX()){
                        if(ball.getCenterY()+30>=650 && ball.getCenterY()-30<=imageView.getY()+ imageView.getFitHeight()){
                            this.stop();
                            Text gameOver = new Text("Game Over");
                            gameOver.setFill(Color.RED);
                            gameOver.setStroke(Color.RED);
                            pane.getChildren().add(gameOver);
                            gameOver.setFont(Font.font("Comic Sans", 72));
                            gameOver.setX(500);
                            gameOver.setY(300);
                        }
                    }
                    if(ballList.get(countt).getCenterY()+30>=700){
                        ballList.get(countt).setCenterY(0);
                        pane.getChildren().add(ballList.get(countt+1));
                        countt++;
                        score.setText(String.format("%d", countt));
                    }
                    if (ballList.get(temp).getCenterY()+30>=700){
                        ballList.get(temp).setCenterY(0);
                    }
                    temp++;
                }
            }
        }.start();
        pane.requestFocus();
    }

    private void musicLoopAnimation() {
        String musicFile = "Wii_Sports.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(100);
        mediaPlayer.play();
    }

    private void findGuyRules(Stage s){
        Pane pane = new Pane();

        Button b = new Button("Start");
        b.setOnMouseClicked(e -> {
            findGuy(new Stage());
            s.hide();
        });
        pane.getChildren().add(b);
        b.setLayoutX(60);
        b.setLayoutY(300);

        Text tt = new Text("Move your cursor");
        tt.setFont(Font.font("Comic Sans", 30));
        tt.setX(10);
        tt.setY(70);
        pane.getChildren().add(tt);

        Text ttt = new Text("to find Waluigi.");
        ttt.setFont(Font.font("Comic Sans", 30));
        ttt.setX(10);
        ttt.setY(110);
        pane.getChildren().add(ttt);

        Text tttt = new Text("Once you hover");
        tttt.setFont(Font.font("Comic Sans", 30));
        tttt.setX(10);
        tttt.setY(150);
        pane.getChildren().add(tttt);

        Text ttttt = new Text("over him, he will");
        ttttt.setFont(Font.font("Comic Sans", 30));
        ttttt.setX(10);
        ttttt.setY(190);
        pane.getChildren().add(ttttt);

        Text tttttt = new Text("appear.");
        tttttt.setFont(Font.font("Comic Sans", 30));
        tttttt.setX(10);
        tttttt.setY(230);
        pane.getChildren().add(tttttt);

        Scene scene = new Scene(pane, 246, 400);
        s.setScene(scene);
        //s.setTitle("Rules");
        s.setTitle("Rules");
        s.show();
    }

    private void memoryRules(Stage s){
        Pane pane = new Pane();
        Button b = new Button("Start");
        b.setOnMouseClicked(e -> {
            memory(new Stage());
            s.hide();
        });
        pane.getChildren().add(b);
        b.setLayoutX(60);
        b.setLayoutY(300);

        Text tt = new Text("Click a card to");
        tt.setFont(Font.font("Comic Sans", 30));
        tt.setX(10);
        tt.setY(70);
        pane.getChildren().add(tt);

        Text ttt = new Text("flip it over. Try");
        ttt.setFont(Font.font("Comic Sans", 30));
        ttt.setX(10);
        ttt.setY(110);
        pane.getChildren().add(ttt);

        Text tttt = new Text("to match cards");
        tttt.setFont(Font.font("Comic Sans", 30));
        tttt.setX(10);
        tttt.setY(150);
        pane.getChildren().add(tttt);

        Text ttttt = new Text("with the same");
        ttttt.setFont(Font.font("Comic Sans", 30));
        ttttt.setX(10);
        ttttt.setY(190);
        pane.getChildren().add(ttttt);

        Text tttttt = new Text("picture to win.");
        tttttt.setFont(Font.font("Comic Sans", 30));
        tttttt.setX(10);
        tttttt.setY(230);
        pane.getChildren().add(tttttt);


        Scene scene = new Scene(pane, 246, 400);
        s.setScene(scene);
        s.setTitle("Rules");
        s.show();
    }

    private void deflectorRules(Stage s){
        Pane pane = new Pane();

        Button b = new Button("Start");
        b.setOnMouseClicked(e -> {
            deflector(new Stage());
            s.hide();
        });
        pane.getChildren().add(b);
        b.setLayoutX(60);
        b.setLayoutY(300);

        Text tt = new Text("Click the circle");
        tt.setFont(Font.font("Comic Sans", 30));
        tt.setX(10);
        tt.setY(70);
        pane.getChildren().add(tt);

        Text ttt = new Text("before it hits");
        ttt.setFont(Font.font("Comic Sans", 30));
        ttt.setX(10);
        ttt.setY(110);
        pane.getChildren().add(ttt);

        Text tttt = new Text("Waluigi. It");
        tttt.setFont(Font.font("Comic Sans", 30));
        tttt.setX(10);
        tttt.setY(150);
        pane.getChildren().add(tttt);

        Text ttttt = new Text("gets faster over");
        ttttt.setFont(Font.font("Comic Sans", 30));
        ttttt.setX(10);
        ttttt.setY(190);
        pane.getChildren().add(ttttt);

        Text tttttt = new Text("time.");
        tttttt.setFont(Font.font("Comic Sans", 30));
        tttttt.setX(10);
        tttttt.setY(230);
        pane.getChildren().add(tttttt);

        Scene scene = new Scene(pane, 246, 400);
        s.setScene(scene);
        //s.setTitle("Rules");
        s.setTitle("Rules");
        s.show();
    }
}
