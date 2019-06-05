import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Deflector extends Application {
    protected Pane p = new Pane();
    protected Timeline animation;
    protected int dx = 5;
    protected int dy = 5;
    protected Circle shield = new Circle(150);
    //protected Circle shield = new Circle(500, 500, 150);
    protected Circle circle = new Circle(50);
    protected Image loser = new Image("Waluigi5.png");
    protected ImageView imageView2 = new ImageView(loser);
    protected Text t = new Text("WAAAA! You lose!");

    public void start (Stage ps) {
        shield.centerXProperty().bind(p.widthProperty().divide(2).add(10));
        shield.centerYProperty().bind(p.heightProperty().divide(2));
        shield.setStroke(Color.RED);
        shield.setStrokeWidth(5);
        shield.setFill(Color.WHITE);
        p.getChildren().add(shield);

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        p.getChildren().add(imageView);
        //imageView.xProperty().set(500-(waluigi.getWidth()/2));
        //imageView.yProperty().set(500-(waluigi.getHeight()/2));
        imageView.xProperty().bind(p.widthProperty().divide(2).subtract(waluigi.getWidth()/2));
        imageView.yProperty().bind(p.heightProperty().divide(2).subtract(waluigi.getHeight()/2));

        if ((int)(Math.random()*4) == 0) {
            circle.setCenterX(80);
            circle.setCenterY((int)((Math.random()*920)+80));
        }
        else if ((int)(Math.random()*4) == 1) {
            circle.setCenterX(920);
            circle.setCenterY((int)((Math.random()*920)+80));
        }
        else if ((int)(Math.random()*4) == 2) {
            circle.setCenterX((int)((Math.random()*920)+80));
            circle.setCenterY(80);
        }
        else {
            circle.setCenterX((int)((Math.random()*920)+80));
            circle.setCenterY(920);
        }
        circle.setFill(Color.PURPLE);
        p.getChildren().add(circle);
        circle.setOnMousePressed(e -> {
            if ((int)(Math.random()*4) == 0) {
                circle.setCenterX(80);
                circle.setCenterY((int)((Math.random()*920)+80));
            }
            else if ((int)(Math.random()*4) == 1) {
                circle.setCenterX(920);
                circle.setCenterY((int)((Math.random()*920)+80));
            }
            else if ((int)(Math.random()*4) == 2) {
                circle.setCenterX((int)((Math.random()*920)+80));
                circle.setCenterY(80);
            }
            else {
                circle.setCenterX((int)((Math.random()*920)+80));
                circle.setCenterY(920);
            }
            dx *= 1.25;
            dy *= 1.25;
        });
        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
        //handleCollision();

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        Scene scene = new Scene(p, 1000, 1000);
        ps.setTitle("Deflector");
        ps.setScene(scene);
        ps.show();
    }

    protected void handleCollision() {
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
    }

    protected void moveBall() {
        if (circle.getCenterX()<circle.getRadius() || circle.getCenterX()>p.getWidth() - circle.getRadius()) {
            dx *= -1;
        }
        if (circle.getCenterY()<circle.getRadius()|| circle.getCenterY()>p.getHeight() - circle.getRadius()) {
            dy *= -1;
        }

        handleCollision();
        circle.setCenterX(circle.getCenterX() + dx);
        circle.setCenterY(circle.getCenterY() + dy);
    }
}
