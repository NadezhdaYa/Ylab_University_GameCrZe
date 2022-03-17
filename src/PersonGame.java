import java.util.*;

public class PersonGame extends Game {
    private final GameTXT storage;
    private final GameXMLParser parser;

    public PersonGame(Scanner scan, TypeGame typeGame) {
        super();
        this.scan = scan;
        this.typeGame = typeGame;
        this.scope = new HashMap<>();
        this.players = new ArrayList<>();
        this.steps = new ArrayList<>();
        this.storage = new GameTXT();
        this.parser = new GameXMLParser();
    }

    //Логика игры
    @Override
    public ContinueGame play() {
        ContinueGame playGame = ContinueGame.CONTINUE;
        Message.printScreensaver();
        configuration();
        Message.printMatrix(matrix);
       Message.printGameRules();
        while (playGame == ContinueGame.CONTINUE) {
            Player winPlayer = game();
            playGame = end(winPlayer);
        }
        return playGame;
    }

    /*
    Конфигурация игры:
    - создание игроков
    - выбор какой игрок будет Х, а какой О
     */
    private void configuration() {
        boolean flag = true;
        Player player1 = null;
        Player player2 = null;

        //Создание игроков
       if (typeGame == TypeGame.PLAYER_PLAYER) {
            player1 = addPlayer(1);
            player2 = addPlayer(2);
        }
       Message.printSeparator("-", countPattern);

        //Выбор какой игрок будет Х, а какой О
         Message.printPlayerSetSymbol(player1, player2);
        while (flag) {
            String answer = scan.nextLine();
            switch (answer) {
                //player1 будет ходить Х
                case "1":
                    fillPlayersAndScope(player1, player2);
                    flag = false;
                    break;
                //player2 будет ходить Х
                case "2":
                    fillPlayersAndScope(player2, player1);
                    flag = false;
                    break;

                default:
                    Message.printErrorAnswer();
            }
        }
    }

   private Player addPlayer(int num) {
        String standardName = "Player " + num;
        Message.printPlayerSetName(standardName);
        Player player;
        while (true) {
            String name = scan.nextLine().replace("\t", "");
            if (name.isEmpty()) {
                Message.printErrorSetName();
            }
            else {
             player = storage.get(name);
                break;
            }
        }
        return player;
    }

   private void fillPlayersAndScope(Player x, Player o) {
        x.setSymbol(Symbol.X);
        o.setSymbol(Symbol.O);
        players.add(x);
        players.add(o);

        scope.put(x.getName(), 0);
        scope.put(o.getName(), 0);
    }

    // Возвращает победителя, если ничья - null
    private Player game() {
        ResultGame resultGame = ResultGame.NEXT_MOVE;
        Player winPlayer = null;

        Message.printStartGame(players);
        Message.printSeparator("-", countPattern);
        int num = 1;
        while (resultGame == ResultGame.NEXT_MOVE) {
            for (Player player : players) {
                if (resultGame == ResultGame.NEXT_MOVE) {
                    Step step = player.move(scan, matrix);
                    step.setNum(num);
                    steps.add(step);
                    int row = step.getRow();
                    int col = step.getCol();
                    char sing = step.getSymbol().getSing();
                    matrix[row][col] = sing;
                    Message.printMatrix(matrix);
                    Message.printSeparator("*", countPattern);
                    resultGame = checkWin(matrix);
                    num++;
                    if (resultGame == ResultGame.WIN) {
                        winPlayer = player;
                        break;
                    }
                } else
                    break;
            }
        }
        return winPlayer;
    }

    private ContinueGame end(Player winPlayer) {
        if (winPlayer != null) {
            winPlayer.addPoint();
            String winName = winPlayer.getName();
            int point = scope.get(winName);
            scope.put(winName, ++point);
            Message.printWinPlayer(winName);
        }
        else
            Message.printDrawPlayers();
       storage.saveAll(players);
        parser.write(players, winPlayer, steps);
        Message.printGameScope(scope);
        Message.printSeparator("*", countPattern);

        Message.printContinuePersonGame();
        return this.isContinueGame(scan);
    }
}