import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Deflecter extends Application {
    protected Pane p = new Pane();
    protected Timeline animation;
    protected int dx = (int)((Math.random()*49)+10);
    protected int dy = (int)((Math.random()*49)+10);


    public void start (Stage ps) {

        Circle shield = new Circle(150);
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

        Circle circle = new Circle(50);
        circle.setCenterX(75);
        circle.setCenterY(75);
        circle.setFill(Color.BLACK);
        p.getChildren().add(circle);
        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall(circle)));
        handleCollision(circle, shield);

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        Scene scene = new Scene(p, 1000, 1000);
        ps.setTitle("Deflecter");
        ps.setScene(scene);
        ps.show();
    }

    protected void handleCollision(Circle c1, Circle c2) {
        if (Math.pow(c1.getCenterX()-c2.getCenterX(), 2) + Math.pow(c1.getCenterY()-c2.getCenterY(), 2) < Math.pow(c1.getRadius()+c2.getRadius(), 2)) {
            System.out.println(Math.pow(c1.getCenterX()-c2.getCenterX(), 2));
            System.out.println(Math.pow(c1.getCenterY()-c2.getCenterY(), 2));
            System.out.println(Math.pow(c1.getRadius()+c2.getRadius(), 2));
            c1.setFill(Color.RED);
        }
        else {
            c1.setFill(Color.BLACK);
        }
    }

    protected void moveBall(Circle c) {
        if (c.getCenterX()<c.getRadius() || c.getCenterX()>p.getWidth() - c.getRadius()) {
            dx *= -1;
        }
        if (c.getCenterY()<c.getRadius()|| c.getCenterY()>p.getHeight() - c.getRadius()) {
            dy *= -1;
        }

        c.setCenterX(c.getCenterX() + dx);
        c.setCenterY(c.getCenterY() + dy);
    }
}
