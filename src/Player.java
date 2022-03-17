import java.util.Scanner;

public abstract class Player {
    protected int id;
    protected String name;
    protected Symbol symbol;
    protected int point;
    private static int count = 0;

    public Player() {
        count++;
        this.id = count;
    }

    public Player(String name) {
        this.name = name;
        this.point = 0;
        count++;
        this.id = count;
    }

    public Player(String name, int point) {
        this.name = name;
        this.point = point;
        count++;
        this.id = count;
    }

    public abstract Step move(Scanner scan, char[][] matrix);

    public void addPoint() {
        point++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public int getPoint() {
        return point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}