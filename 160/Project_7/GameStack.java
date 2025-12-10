import java.util.ArrayList;

public class GameStack {
    private ArrayList<GameState> history;
    private int len_history;
    public GameStack() {
        this.history = new ArrayList<GameState>();
        this.len_history = 0;
    }

    public void push(GameState t) {
        this.history.add(t);
        this.len_history += 1;
    }

    public GameState pop() {
        if (this.isEmpty()) {
            return null;
        }
        this.len_history -= 1;
        return this.history.remove(this.len_history);
    }

    public GameState peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.history.get(this.len_history - 1);
    }

    public boolean isEmpty() {
        return this.len_history == 0;
    }
}
