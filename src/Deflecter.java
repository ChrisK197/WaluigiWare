import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Deflecter extends Application {

    private ArrayList<Shape> nodes;

    public void start (Stage ps) {
        Pane p = new Pane();

        Image waluigi = new Image("Waluigi.png");
        ImageView imageView = new ImageView(waluigi);
        p.getChildren().add(imageView);
        imageView.xProperty().bind(p.widthProperty().divide(2).subtract(waluigi.getWidth()/2));
        imageView.yProperty().bind(p.heightProperty().divide(2).subtract(waluigi.getHeight()/2));

        for (int i=0; i<5; i++) {
            Circle circle = new Circle(10);
            circle.setCenterX((int)(Math.random()*p.getWidth()));
            circle.setCenterY((int)(Math.random()*p.getHeight()));
            //nodes.add(circle);

        }

        Scene scene = new Scene(p, 1000, 1000);
        ps.setTitle("Deflecter");
        ps.setScene(scene);
        ps.show();
    }

    //private void checkBounds(Circle c) {
    //    boolean collisionDetected = false;
    //    for (Circle static_bloc : nodes) {
    //        if (static_bloc != block) {
    //            static_bloc.setFill(Color.GREEN);

     //           if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
     //               collisionDetected = true;
     //           }
     //       }
     //   }

     //   if (collisionDetected) {
     //       block.setFill(Color.BLUE);
     //   } else {
     //       block.setFill(Color.GREEN);
     //   }
    //}
}
