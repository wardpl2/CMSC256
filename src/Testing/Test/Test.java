package Testing.Test;

import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.games.NonBlockingGame;

public class Test extends NonBlockingGame {

    public Test(int assignmentID, String login, String apikey) {
        super(assignmentID, login, apikey, 10, 10);
    }


    protected void initialize() {
        for (int i = 0; i < getBoardHeight(); ++i)
            for (int j = 0; j < getBoardWidth(); ++j)
                setBGColor(i, j, NamedColor.ivory);
    }


    protected void gameLoop() {
        if (keyUp()) {
            drawSymbol(1, 1, NamedSymbol.H, NamedColor.blue);
            drawSymbol(1, 2, NamedSymbol.E, NamedColor.yellow);
            drawSymbol(1, 3, NamedSymbol.L, NamedColor.black);
            drawSymbol(1, 4, NamedSymbol.L, NamedColor.green);
            drawSymbol(1, 5, NamedSymbol.O, NamedColor.red);
        }
        else {
            drawSymbol(1, 1, NamedSymbol.none, NamedColor.blue);
            drawSymbol(1, 2, NamedSymbol.none, NamedColor.yellow);
            drawSymbol(1, 3, NamedSymbol.none, NamedColor.black);
            drawSymbol(1, 4, NamedSymbol.none, NamedColor.green);
            drawSymbol(1, 5, NamedSymbol.none, NamedColor.red);
        }
    }

    public static void main(String[] args) {
        Test t = new Test(6,"wardpl2","133617755398");
        t.start();
    }
}
