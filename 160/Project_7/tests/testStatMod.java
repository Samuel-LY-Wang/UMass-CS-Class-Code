package tests;
import org.junit.Test;
import static org.junit.Assert.*;

import game.Combat.Player.Player;
import game.Items.*;

public class testStatMod {
    @Test
    public void testSingleAddMod() {
        Player e = new Player("Merchant", "Test Player", 0);
        StatModifier[] mods = {new IncreaseStat("speed", 5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster", 0, mods, 0);
        s.apply(e);
        assertEquals(30, e.getStat("speed"), 1e-9);
    }

    @Test
    public void testMultipleAddMod() {
        Player e = new Player("Merchant", "Test Player", 0);
        StatModifier[] mods = {new IncreaseStat("speed", 5), new IncreaseStat("defense", 2)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster. Also slight protection.", 0, mods, 0);
        s.apply(e);
        assertEquals(30, e.getStat("speed"), 1e-9);
        assertEquals(2, e.getStat("defense"), 1e-9);
    }

    @Test
    public void testSingleMultMod() {
        Player e = new Player("Merchant", "Test Player", 0);
        StatModifier[] mods = {new MultiplyStat("speed", 1.5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster", 0, mods, 0);
        s.apply(e);
        assertEquals(37.5, e.getStat("speed"), 1e-9);
    }

    @Test
    public void testMultipleMultMod() {
        Player e = new Player("Merchant", "Test Player", 0);
        StatModifier[] mods = {new MultiplyStat("speed", 1.5), new MultiplyStat("defense", 1.5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster. Also slight protection.", 0, mods, 0);
        s.apply(e);
        assertEquals(37.5, e.getStat("speed"), 1e-9);
        assertEquals(0, e.getStat("defense"), 1e-9);
    }

    @Test
    public void testMixedMod() {
        Player e = new Player("Merchant", "Test Player", 0);
        StatModifier[] mods = {new IncreaseStat("speed", 5), new MultiplyStat("defense", 1.5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster. Also slight protection.", 0, mods, 0);
        s.apply(e);
        assertEquals(30, e.getStat("speed"), 1e-9);
        assertEquals(0, e.getStat("defense"), 1e-9);
    }
}