package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import game.Items.*;
import game.CombatEntities.Entity;
import game.CombatEntities.Player.Player;

public class testStatMod {
    @Test
    public void testSingleAddMod() {
        Entity e = new Entity("Test Entity", 100, 10, 0, 25, 0.0);
        StatModifier[] mods = {new IncreaseStat("speed", 5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster", 0, mods);
        s.applyBuffs(e);
        assertEquals(30, e.getSpeed(), 1e-9);
    }

    @Test
    public void testMultipleAddMod() {
        Entity e = new Entity("Test Entity", 100, 10, 0, 25, 0.0);
        StatModifier[] mods = {new IncreaseStat("speed", 5), new IncreaseStat("defense", 2)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster. Also slight protection.", 0, mods);
        s.applyBuffs(e);
        assertEquals(30, e.getSpeed(), 1e-9);
        assertEquals(2, e.getDefense(), 1e-9);
    }

    @Test
    public void testSingleMultMod() {
        Entity e = new Entity("Test Entity", 100, 10, 0, 25, 0.0);
        StatModifier[] mods = {new MultiplyStat("speed", 1.5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster", 0, mods);
        s.applyBuffs(e);
        assertEquals(37.5, e.getSpeed(), 1e-9);
    }

    @Test
    public void testMultipleMultMod() {
        Entity e = new Entity("Test Entity", 100, 10, 0, 25, 0.0);
        StatModifier[] mods = {new MultiplyStat("speed", 1.5), new MultiplyStat("defense", 1.5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster. Also slight protection.", 0, mods);
        s.applyBuffs(e);
        assertEquals(37.5, e.getSpeed(), 1e-9);
        assertEquals(0, e.getDefense(), 1e-9);
    }

    @Test
    public void testMixedMod() {
        Entity e = new Entity("Test Entity", 100, 10, 0, 25, 0.0);
        StatModifier[] mods = {new IncreaseStat("speed", 5), new MultiplyStat("defense", 1.5)};
        StatBuffItem s = new StatBuffItem(0, "Boots", "Some boots to walk faster. Also slight protection.", 0, mods);
        s.applyBuffs(e);
        assertEquals(30, e.getSpeed(), 1e-9);
        assertEquals(0, e.getDefense(), 1e-9);
    }

    @Test
    public void testGiveUndos() {
        Player e = new Player("Test Entity", 100, 10, 0, 25, 0.0, 0);
        StatModifier[] mods = {new UndoGiver(3)};
        StatBuffItem s = new StatBuffItem(0, "Rewind", "Allows you to rewind three times.", 0, mods);
        s.applyBuffs(e);
        assertEquals(3, e.getNumUndos(), 1e-9);
    }
}