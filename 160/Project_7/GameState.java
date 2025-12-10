public class GameState {
    protected CombatEntity[] Beings;
    protected int[] BeingsHealth;
    public GameState(CombatEntity[] b, int[] bHealth) {
        this.Beings = b;
        this.BeingsHealth = bHealth;
    }

    public CombatEntity getCE(int index) {
        return this.Beings[index];
    }

    public int getCEHealth(int index) {
        return this.BeingsHealth[index];
    }
}
