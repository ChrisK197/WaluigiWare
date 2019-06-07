import javafx.scene.shape.Circle;

public class TraversingCircle extends Circle {
    protected double horizVelocity = 0;

    public TraversingCircle (int x, int y) {
        super (x, y, 30);
        horizVelocity = 10 * Math.random() - 5;
    }

}

