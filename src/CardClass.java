import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardClass {
    private Image star = new Image("star.jpg");
    private ImageView card = new ImageView(star);
    private Image front;


    public CardClass(Image front){
        this.front = front;
        card.setOnMouseClicked(e ->card.setImage(card.getImage() == star ? front : star));

    }

    public boolean sameCard(CardClass x){
        return this.front == x.front;

    }


    public Image getStar() {
        return star;
    }

    public void setStar(Image star) {
        this.star = star;
    }

    public ImageView getCard() {
        return card;
    }

    public void setCard(ImageView card) {
        this.card = card;
    }

    public Image getFront() {
        return front;
    }

    public void setFront(Image front) {
        this.front = front;
    }
}
