import java.io.File;
import java.util.List;
import java.util.Map;

public class Message {

    private final static String answer = "==> ";

    public static void printScreensaver() {
        System.out.println("Крестики-нолики");
          }
    public static void printPlayWithBot() {
        System.out.println("Меню:\n" +
                "1 - играть\n" +
                "2 - запустить симуляцию");
        System.out.print(answer);
    }

    public static void printErrorAnswer() {
        System.out.println("Ошибочный вариант");
        System.out.print(answer);
    }

    public static void printPlayerSetName(String name) {
        System.out.println("Введите имя, пожалуйста " + name);
        System.out.print(answer);
    }

    public static void printErrorSetName() {
        System.out.println("Некорректно указано имя");
    }

    public static void printPlayerSetSymbol(Player p1, Player p2) {
        System.out.println("Выберите, кто будет ходить X:\n" +
                "1 - " + p1.getName() + " X\n" +
                "2 - " + p2.getName() + " X\n");
        System.out.print(answer);
    }

    public static void printStartGame(List<Player> playerList) {
        Player p1 = playerList.get(0);
        Player p2 = playerList.get(1);
        System.out.println(p1.getName() + " - " + p1.getSymbol());
        System.out.println(p2.getName() + " - " + p2.getSymbol());
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char iChar : chars) {
                char simbol = iChar;
                if (simbol == 0)
                    simbol = '-';
                System.out.print("| " + simbol + ' ');
            }
            System.out.println('|');
        }
    }

    public static void printGameRules() {
        System.out.println("Введите 2 координаты точек X и Y ячейки от 1 до 3:\n");
        System.out.println();
    }

    public static void printPersonMove(String name) {
        System.out.print(name + answer);
    }

    public static void printBotMove(String name, Step step) {
        System.out.println(name + answer + (step.getRow() + 1) + (step.getCol() + 1));
    }

    public static void printErrorMove() {
        System.out.println("Неверный ход");
    }

    public static void printWinPlayer(String name) {
        System.out.println("Выиграл " + name);
    }

    public static void printDrawPlayers() {
        System.out.println("Ничья");
    }

    public static void printGameScope(Map<String, Integer> scope) {
        for (var map : scope.entrySet()) {
            System.out.println(map.getKey() + " - " + map.getValue());
        }
    }

    public static void printContinuePersonGame() {
        System.out.println("Меню:\n" +
                "1. Сыграть еще раз?\n" +
                "2. Создать новую игру?\n" +
                "3. Выход");
        System.out.print(answer);
    }

    public static void printContinueSimulationGame() {
        System.out.println("Меню:\n" +
                "1. Повторить симуляцию?\n" +
                "2. Создать новую игру?\n" +
                "3. Выход");
        System.out.print(answer);
    }

    public static void printSeparator(String pattern, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(pattern);
        }
        System.out.println();
    }
   public static void printSelectFileForSimulation() {
        System.out.println("Введите номер файла для симуляции игры");
    }
    public static void printFileList(List<File> files) {
        for (int i = 0; i < files.size(); i++)
            System.out.println((i + 1) + ". " + files.get(i).getName());
    }

    public static void printErrorFile() {
        System.out.println("Неверный путь");
    }

    public static void printAnswer() {
        System.out.print(answer);
    }
}