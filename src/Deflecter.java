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

        //for (int i=0; i<5; i++) {
            Circle circle = new Circle(75);
            circle.setCenterX(75);
            circle.setCenterY(75);
            circle.setFill(Color.BLACK);
            p.getChildren().add(circle);
            animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall(5, 5, circle)));
            if (handleCollision(circle, shield) == true) {
                circle.setFill(Color.RED);
            }
        //}

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        Scene scene = new Scene(p, 1000, 1000);
        ps.setTitle("Deflecter");
        ps.setScene(scene);
        ps.show();
    }

    private boolean handleCollision(Circle c1, Circle c2) {
        if (c1 != c2) {
            return true;
        }
        return false;
    }

    protected void moveBall(int dx, int dy, Circle c) {
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
