import java.util.Scanner;

public class Simulation extends Player {

    public Simulation() {
        super();
    }

    public Simulation(String name) {
        super(name);
    }

    @Override
    public Step move(Scanner scanner, char[][] matrix) {
        return null;
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol=" + symbol +
                ", point=" + point +
                '}';
    }
}