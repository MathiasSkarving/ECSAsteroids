package dk.sdu.cbse.common.ecs;

public class Helpers {
    public static void centerPoints(double[] xPoints, double[] yPoints) {
        double cx = 0, cy = 0;
        for (double x : xPoints) cx += x;
        for (double y : yPoints) cy += y;
        cx /= xPoints.length;
        cy /= yPoints.length;
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] -= cx;
            yPoints[i] -= cy;
        }
    }
}
