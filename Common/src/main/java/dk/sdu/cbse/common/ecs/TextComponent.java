package dk.sdu.cbse.common.ecs;

public class TextComponent extends Component {
    public String text;
    public String font = "Arial";
    public double fontSize = 14;
    public String color = "#FFFFFF";
    public double offsetX = 0;
    public double offsetY = 0;

    public TextComponent(String text, String color, double fontSize) {
        this.text = text;
        this.color = color;
        this.fontSize = fontSize;
    }
}