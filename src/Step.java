public class Step {
    private Player player;
    private int num;
    private int row;
    private int col;
    public Step() {
    }
    public Step(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }
    public int getRow() {
        return row;
    }
     public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public Symbol getSymbol() {
        return player.getSymbol();
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}