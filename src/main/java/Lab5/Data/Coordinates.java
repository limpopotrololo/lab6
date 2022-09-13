package Lab5.Data;

import Lab5.Exeptions.*;

public class Coordinates {

    private Integer x; //can't be null
    private Integer y; //must be more -84 and can't be null

    public Coordinates(Integer x, Integer y) throws IncorrectData {
        setX(x);
        setY(y);
    }
    public Coordinates(){}

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) throws IncorrectData {
        if (x == null) {
            throw new IncorrectData();
        }
        this.x = x;
    }

    public void setY(Integer y) {
        if ((y < -84) || (y == null)) {
            throw new IllegalArgumentException("Значение не подходит по ОДЗ");
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates: " + "\n" +
                "x = " + x + "\n" +
                "y = " + y;
    }
}
