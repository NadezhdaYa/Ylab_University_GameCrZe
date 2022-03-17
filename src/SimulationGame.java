import java.util.List;
import java.util.Scanner;

public class SimulationGame extends Game {

    public SimulationGame(List<Player> players, List<Step> steps, int sizeMatrix) {
        this.players = players;
        this.steps = steps;
        this.scan = new Scanner(System.in);
        this.matrix = new char[sizeMatrix][sizeMatrix];
    }

    public SimulationGame(List<Player> players, List<Step> steps) {
        super();
        this.players = players;
        this.steps = steps;
        this.scan = new Scanner(System.in);
    }

    @Override
    public ContinueGame play() {
        Message.printStartGame(players);
        Message.printSeparator("*", countPattern);
        for (Step step: steps) {
            Player player = step.getPlayer();
            Message.printBotMove(player.getName(), step);
            int row = step.getRow();
            int col = step.getCol();
            char sing = step.getSymbol().getSing();
            matrix[row][col] = sing;
            Message.printMatrix(matrix);
            Message.printSeparator("*", countPattern);
        }
        Player winPlayer = players.size() == 3 ? players.get(2) : null;

        if (winPlayer != null) {
            String winName = winPlayer.getName();
            Message.printWinPlayer(winName);
        }
        else
            Message.printDrawPlayers();
        Message.printContinueSimulationGame();
        return isContinueGame(scan);
    }
}