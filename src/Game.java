import java.util.*;

public abstract class Game {
    protected Map<String, Integer> scope;
    protected char[][] matrix;
    protected TypeGame typeGame;
    protected List<Player> players;
    protected List<Step> steps;
    protected Scanner scan;
    protected final int countPattern = 30;

    public Game() {
        this.matrix = new char[3][3];
    }

    public abstract ContinueGame play();

    // Проверяет результат игры, если ничья возвращает null
   public static ResultGame checkWin(char[][] matrix) {
        ResultGame resultGame = ResultGame.NEXT_MOVE;
        int numberEmptyCells = 0;

        //Проверка по столбцам и строкам на победу Х или О
        for (int i = 0; i < matrix.length; i++) {

            //проверка по строке
            if (matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2]) {
                if (matrix[i][0] != 0) {
                    resultGame = ResultGame.WIN;
                    Symbol symbol = Symbol.getSymbol(String.valueOf(matrix[i][0]));
                    resultGame.setSymbol(symbol);
                    return resultGame;
                }
            }

            //проверка по столбцу
            if ((matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i])) {
                if (matrix[0][i] != 0) {
                    resultGame = ResultGame.WIN;
                    Symbol symbol = Symbol.getSymbol(String.valueOf(matrix[0][i]));
                    resultGame.setSymbol(symbol);
                    return resultGame;
                }
            }

            //подсчет пустых ячеек
            for (char c : matrix[i]) {
                if (c == 0)
                    numberEmptyCells++;
            }
        }

        //Проверка по диагоналям на победу Х или О
        if ((matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) ||
                (matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]))
            if (matrix[1][1] != 0) {
                resultGame = ResultGame.WIN;
                Symbol symbol = Symbol.getSymbol(String.valueOf(matrix[1][1]));
                resultGame.setSymbol(symbol);
                return resultGame;
            }

        //Проверка на ничью
        if (numberEmptyCells == 0)
            resultGame = ResultGame.DRAW;

        return resultGame;
    }

  protected ContinueGame isContinueGame(Scanner scan) {
        while (true) {
            String answer = scan.nextLine();
            switch (answer) {
                //Сыграть еще раз
                case "1":
                    matrix = new char[3][3];
                    return ContinueGame.CONTINUE;
                //Создать новую игру
                case "2":
                    return ContinueGame.NEW_GAME;
                //Закончить игру
                case "3":
                    return ContinueGame.EXIT;
                default:
                    Message.printErrorAnswer();
            }
        }
    }
}