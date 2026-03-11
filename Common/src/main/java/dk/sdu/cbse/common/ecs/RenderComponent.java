package dk.sdu.cbse.common.ecs;

public class RenderComponent extends Component {

    public RenderComponent(Vector2[] vertices, String color) {
        this.vertices = vertices;
        this.fillColor = color;
    }

    public RenderComponent() {

    }
    
    public Vector2[] vertices;
    public String fillColor;
    public String strokeColor;
    public double strokeWidth;
}
