package game.GameHistory;
import java.util.ArrayList;

public class GameStack {
    private final ArrayList<GameState> states;
    private int len;
    public GameStack() {
        this.states = new ArrayList<>();
        this.len = 0;
    }
    public void push_new(GameState state) {
        states.add(state);
        this.len++;
    }
    public GameState pop() {
        if (this.len == 0) {
            return null;
        }
        GameState top = states.remove(this.len - 1);
        this.len--;
        return top;
    }
    public boolean canUndo() {
        return (this.len > 1);
    }
    public void clear() {
        // GameStack clears at end of wave
        this.states.clear();
        this.len = 0;
    }
}
