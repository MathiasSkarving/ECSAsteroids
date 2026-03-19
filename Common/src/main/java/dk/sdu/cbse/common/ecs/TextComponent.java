package dk.sdu.cbse.common.ecs;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextComponent extends Component {
    public String text;
    public Font font;
    public String color = "#FFFFFF";
    public Offset verticalOffset = Offset.TOP;
    public Offset horizontalOffset = Offset.LEFT;
    public double offsetX = 0;
    public double offsetY = 0;

    public TextComponent(String text, Font font, String color, Offset verticalOffset, Offset horizontalOffset) {
        this.text = text;
        this.color = color;
        this.font = font;
        this.verticalOffset = verticalOffset;
        this.horizontalOffset = horizontalOffset;
        recalculateOffset();
    }

    public void recalculateOffset() {
        Text textNode = new Text(text);
        textNode.setFont(font);
        double width = textNode.getLayoutBounds().getWidth();

        switch(verticalOffset) {
            case TOP -> offsetY = 0;
            case BOTTOM -> offsetY = -font.getSize();
            case CENTER -> offsetY = -font.getSize()/(float)2;
        }

        switch (horizontalOffset) {
            case LEFT -> offsetX = 0;
            case RIGHT -> offsetX = -width;
            case CENTER -> offsetX = -width/2;
        }

    }
}

