package game;

public class HandleStatChanger {
    public static void handleSingleBuff(String[] buff, Entity entity) {
        String stat = buff[0];
        String characteristic = buff[1];
        double amount = Double.parseDouble(buff[2]);
        switch (stat) {
            case "attack":
                switch (characteristic) {
                    case "add":
                        entity.updateBaseAttack(amount);
                        break;
                    case "subtract":
                        entity.updateBaseAttack(-amount);
                        break;
                    case "multiply":
                        entity.multiplyAttack(amount);
                        break;
                    case "divide":
                        entity.multiplyAttack(1.0 / amount);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid characteristic: " + characteristic);
                }
            case "defense":
                switch (characteristic) {
                    case "add":
                        entity.updateBaseDefense(amount);
                        break;
                    case "subtract":
                        entity.updateBaseDefense(-amount);
                        break;
                    case "multiply":
                        entity.multiplyDefense(amount);
                        break;
                    case "divide":
                        entity.multiplyDefense(1.0 / amount);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid characteristic: " + characteristic);
                }
                break;
            case "maxHealth":
                switch (characteristic) {
                    case "add":
                        entity.updateBaseMaxHealth(amount);
                        break;
                    case "subtract":
                        entity.updateBaseMaxHealth(-amount);
                        break;
                    case "multiply":
                        entity.multiplyMaxHealth(amount);
                        break;
                    case "divide":
                        entity.multiplyMaxHealth(1.0 / amount);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid characteristic: " + characteristic);
                }
                break;
            case "speed":
                switch (characteristic) {
                    case "add":
                        entity.updateBaseSpeed((int) amount);
                        break;
                    case "subtract":
                        entity.updateBaseSpeed((int) -amount);
                        break;
                    case "multiply":
                        entity.updateSpeedMult(amount);
                        break;
                    case "divide":
                        entity.updateSpeedMult(1.0 / amount);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid characteristic: " + characteristic);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid stat: " + stat);
        }
    }

    public static void handleBuffs(StatBuffItem item, Entity entity) {
        for (int i = 0; i < item.numBuffs; i++) {
            String[] buff = item.getBuff(i);
            handleSingleBuff(buff, entity);
        }
    }
}