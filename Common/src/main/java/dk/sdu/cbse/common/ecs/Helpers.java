package dk.sdu.cbse.common.ecs;

public class Helpers {
    public static void centerPoints(Vector2[] vertices) {
        double sumX = 0;
        double sumY = 0;

        for (Vector2 v : vertices) {
            sumX += v.x;
            sumY += v.y;
        }

        double centerX = sumX / vertices.length;
        double centerY = sumY / vertices.length;

        for (Vector2 v : vertices) {
            v.x -= centerX;
            v.y -= centerY;
        }
    }

    public static double calculateDistance(PositionComponent pos1, PositionComponent pos2) {
        return Math.pow(Math.pow(Math.abs(pos1.position.x - pos2.position.x),2) + Math.pow(Math.abs(pos1.position.y - pos2.position.y),2),0.5);
    }

    public static double calculateColliderRadiusFromVertices(Vector2[] vertices) {
        double maxDist = 0;
        for (int i = 0; i < vertices.length; i++) {
            double dist = Math.sqrt(vertices[i].x * vertices[i].x + vertices[i].y * vertices[i].y);
            maxDist = Math.max(maxDist, dist);
        }
        return maxDist;
    }

    public static double[] calculateXpointsFromVectors(Vector2[] vertices) {
        double[] xPoints = new double[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            xPoints[i] = vertices[i].x;
        }
        return xPoints;
    }
    public static double[] calculateYpointsFromVectors(Vector2[] vertices) {
        double[] yPoints = new double[vertices.length];
        for (int i= 0; i < vertices.length; i++) {
            yPoints[i] = vertices[i].y;
        }
        return yPoints;
    }


}
