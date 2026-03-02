package dk.sdu.cbse.common.ecs;

public class Vector2 {
    public double x,y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(double angle) {
        this.x = Math.cos(Math.toRadians(angle));
        this.y = Math.sin(Math.toRadians(angle));
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(x+other.x, y+other.y);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(x-other.x, y-other.y);
    }

    public Vector2 scale(double amount) {
        return new Vector2(x*amount, y*amount);
    }

    public double dot(Vector2 other) {
        return x*other.x + y*other.y;
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector2 normalize() {
        return scale(1/magnitude());
    }

    public double angleBetween(Vector2 other) {
        double angle = 0;
        double dot = dot(other);
        angle = Math.acos(dot/(magnitude()*other.magnitude()));
        return angle;
    }
}
