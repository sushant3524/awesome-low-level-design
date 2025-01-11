import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private TicTacToe INSTANCE;
    List<String> board;
    Player oPlayer;
    Player xPlayer;
    public enum PlayerType {
        ZERO, CROSS
    }

    private TicTacToe() {
        board = new ArrayList<>();
        for(int i=0;i<3;i++) {
            String st = "000";
            board.add(st);
        }
        oPlayer = new Player(PlayerType.ZERO, "1", "zero");
        xPlayer = new Player(PlayerType.CROSS, "2", "cross");
    }

    public TicTacToe getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TicTacToe();
        }
        return INSTANCE;
    }

    public abstract class Player {
        private String id;
        private String name;

        public Player(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public class ZeroPlayer extends Player {

        public ZeroPlayer(String id, String name) {
            super(id, name);
        }

        public void makeMove(int x, int y, List<String> board) {

        }
    }
}
