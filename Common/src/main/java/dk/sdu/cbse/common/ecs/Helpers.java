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

    public static double calculateDistance(PositionComponent pos1, PositionComponent pos2) {
        return Math.pow(Math.pow(Math.abs(pos1.position.x - pos2.position.x),2) + Math.pow(Math.abs(pos1.position.y - pos2.position.y),2),0.5);
    }

    public static double calculateColliderRadiusFromPoints(double[] xPoints, double[] yPoints) {
        double maxDist = 0;
        for (int i = 0; i < xPoints.length; i++) {
            double dist = Math.sqrt(xPoints[i] * xPoints[i] + yPoints[i] * yPoints[i]);
            maxDist = Math.max(maxDist, dist);
        }
        return maxDist;
    }
}
