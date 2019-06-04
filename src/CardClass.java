import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class CardClass {
    private Image star = new Image("star.jpg");
    private ImageView card = new ImageView(star);
    private Image front;
    private boolean faceUp = false;
    private boolean rectIn = false;


    public CardClass(Image front){
        this.front = front;
        //card.setOnMouseClicked(e ->{
          //  card.setImage(front);
            //faceUp = true;
        //});


    }

    public boolean sameCard(CardClass x){
        return this.front == x.front;

    }

    public void erase(){
        Image white = new Image("white.jpg");
        card.setImage(white);
    }

    public void unflip(){
        card.setImage(star);
    }

    public boolean bothUp(CardClass c){
        return this.isFaceUp() && c.isFaceUp();
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

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public boolean isRectIn(){
        return rectIn;
    }
}
