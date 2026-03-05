package dk.sdu.cbse.common.ecs;

import java.util.Random;

public class Helpers {
    public static void centerPoints(Vector2[] vertices) {
        double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;

        for (Vector2 v : vertices) {
            minX = Math.min(minX, v.x);
            maxX = Math.max(maxX, v.x);
            minY = Math.min(minY, v.y);
            maxY = Math.max(maxY, v.y);
        }

        double centerX = (minX + maxX) / 2;
        double centerY = (minY + maxY) / 2;

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

    public static Vector2[] makeCircle(double radius, int points) {
        Vector2[] vertices = new Vector2[points];
        double increments = 360 / (double)points;

        for(int i = 0; i < points; i++) {
            double x = Math.cos(Math.toRadians(increments*i));
            double y = Math.sin(Math.toRadians(increments*i));
            vertices[i] = new Vector2(x,y);
            vertices[i] = vertices[i].scale(radius);
        }

        return vertices;
    }

    public static Vector2 getRandomVector() {
        Random rand = new Random();
        double randomAngle = rand.nextInt(360);
        Vector2 randomVector = new Vector2(Math.cos(Math.toRadians(randomAngle)), Math.sin(Math.toRadians(randomAngle)));
        double randomScale = 50+rand.nextInt(200);
        randomVector = randomVector.scale(randomScale);

        return randomVector;
    }

    public static Vector2 getRandomVector(double worldWidth, double worldHeight) {
        Random rand = new Random();
        double randomX = rand.nextInt((int)worldWidth);
        double randomY = rand.nextInt((int)worldHeight);
        return new Vector2(randomX, randomY);
    }

    public static double getRandomNum(double origin, double bound) {
        double num;
        Random rand = new Random();
        num = origin+rand.nextDouble()*(bound-origin);
        return num;
    }
}
