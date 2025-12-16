## So this is a simple text-based roguelike
Current progress:\
Should be done with the basic coding, now I just need to test\
Tested so far:\
Retreating\
Healing
Shop item spawning\
The shop actually functioning\
Moving\
Melee and ranged attacks\
Some amount of error handling\
Different classes giving different stats

## How to run the code:
First, download the folder "game"\
Then, navigate to the **parent directory** of "game/"\
Then, run the commands ```javac game/Main.java``` then ```java game/Main```

## Extensibility
The code should be fairly okay with extensions\
To add new enemy types, add their stats to Stats/EnemyStats.java\
If the enemy type is special in some way, create a new class like I have with RangedEnemy\
To add new player classes, add their stats to Stats/PlayerStats.java\
If the class has a special attack, a new class must be created (as was done with Wizard)\
If you wish to modify literally any of the stats, they're just about all stored in the Stats folder\
In any case, Main (the main script) and Utils (utility functions for Main) ***MUST*** be updated.