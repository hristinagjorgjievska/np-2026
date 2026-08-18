package laboratoriska1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

interface Movable{
    void moveUp() throws ObjectCanNotBeMovedException;
    void moveDown() throws ObjectCanNotBeMovedException;
    void moveRight() throws ObjectCanNotBeMovedException;
    void moveLeft() throws ObjectCanNotBeMovedException;
    int getCurrentXPosition();
    int getCurrentYPosition();
}

class MovablePoint implements Movable{
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if (y + ySpeed > MovablesCollection.getY_MAX()){
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, y + ySpeed));
        }
        y += ySpeed;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException{
        if (y - ySpeed < MovablesCollection.getY_MIN()){
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, y - ySpeed));
        }
        y -= ySpeed;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException{
        if (x + xSpeed > MovablesCollection.getX_MAX()){
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x + xSpeed, y));
        }
        x += xSpeed;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if (x - xSpeed < MovablesCollection.getX_MIN()){
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x - xSpeed, y));
        }
        x -= xSpeed;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Movable point with coordinates (%d,%d)", x, y);
    }
}

class MovableCircle implements Movable{
    private int radius;
    private MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public MovablePoint getCenter() {
        return center;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        center.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException{
        center.moveDown();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException{
        center.moveRight();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        center.moveLeft();
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    @Override
    public String toString() {
        return String.format("Movable circle with center coordinates (%d,%d) and radius %d", center.getCurrentXPosition(), center.getCurrentYPosition(), radius);
    }
}

class MovableObjectNotFittableException extends Exception{
    public MovableObjectNotFittableException(String message){
        super(message);
    }
}

class ObjectCanNotBeMovedException extends Exception {
    public ObjectCanNotBeMovedException(String message) {
        super(message);
    }
}

class MovablesCollection{
    private List<Movable> movable = new ArrayList<>();
    private static int x_MIN = 0;
    private static int y_MIN = 0;
    private static int x_MAX;
    private static int y_MAX;

    public MovablesCollection(int x_MAX, int y_MAX) {
        this.x_MAX = x_MAX;
        this.y_MAX = y_MAX;
    }

    public static int getX_MIN(){
        return x_MIN;
    }

    public static int getX_MAX(){
        return x_MAX;
    }

    public static int getY_MIN(){
        return y_MIN;
    }

    public static int getY_MAX(){
        return y_MAX;
    }

    public static void setx_max(int x_MIN) {
        MovablesCollection.x_MIN = x_MIN;
    }

    public static void setY_MIN(int y_MIN) {
        MovablesCollection.y_MIN = y_MIN;
    }

    public static void setxMax(int x_MAX) {
        MovablesCollection.x_MAX = x_MAX;
    }

    public static void setyMax(int y_MAX) {
        MovablesCollection.y_MAX = y_MAX;
    }

    void addMovableObject(Movable m) {
        if (m instanceof MovablePoint){
            if (((MovablePoint) m).getX() < x_MIN || ((MovablePoint) m).getX() > x_MAX){
                System.out.printf("Movable point with center (%d,%d) can not be fitted into the collection%n", m.getCurrentXPosition(), m.getCurrentYPosition());
                return;
            }

            if (((MovablePoint) m).getY() < y_MIN || ((MovablePoint) m).getY() > y_MAX){
                System.out.printf("Movable point with center (%d,%d) can not be fitted into the collection%n", m.getCurrentXPosition(), m.getCurrentYPosition());
                return;
            }
        }

        if (m instanceof MovableCircle){

            if (((MovableCircle) m).getCenter().getX() - ((MovableCircle) m).getRadius() < x_MIN || ((MovableCircle) m).getCenter().getX() + ((MovableCircle) m).getRadius() > x_MAX){
                System.out.printf("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection%n", m.getCurrentXPosition(), m.getCurrentYPosition(), ((MovableCircle) m).getRadius());
                return;
            }

            if (((MovableCircle) m).getCenter().getY() - ((MovableCircle) m).getRadius() < y_MIN || ((MovableCircle) m).getCenter().getY() + ((MovableCircle) m).getRadius() > y_MAX){
                System.out.printf("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection%n", m.getCurrentXPosition(), m.getCurrentYPosition(), ((MovableCircle) m).getRadius());
                return;
            }

        }

        movable.add(m);
    }

    void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction){
        for (Movable m : movable){
            boolean matches = false;
            if ((type == TYPE.POINT && m instanceof MovablePoint)
                    || (type == TYPE.CIRCLE && m instanceof MovableCircle)){
                matches = true;
            }

            if (!matches) continue;

            try {
                switch (direction){
                    case UP: m.moveUp(); break;
                    case DOWN: m.moveDown(); break;
                    case LEFT: m.moveLeft(); break;
                    case RIGHT: m.moveRight(); break;
                }
            } catch (ObjectCanNotBeMovedException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Collection of movable objects with size %d:\n", movable.size()));
        for (Movable m : movable) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
}


public class CirclesTest {

    public static void main(String[] args){

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());


    }
}

