import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 250, 240};
    public static String medicHealing;
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
      
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length ; i++) {
            if(heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void chooseHeroForHealing(){
        Random random = new Random();
        int healingHero;
        do {
            healingHero = random.nextInt(heroesAttackType.length);
            medicHealing = heroesAttackType[healingHero];
        }while (heroesHealth[healingHero]==heroesHealth[3]);


    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void playRound() {
        roundNumber++;
        chooseHeroForHealing();
        chooseBossDefence();
        bossAttacks();
        heroesAttack();
        showStatistics();

    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];

                if(heroesAttackType[i] == bossDefence){
                    Random random = new Random();
                    int coefficient = random.nextInt(8) + 2; // 2, 3, 4, 5, 6, 7, 8, 9
                    damage = heroesDamage[i] * coefficient;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }



            }
        }
    }

    public static void bossAttacks() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                 heroesHealth[i] = heroesHealth[i]  - bossDamage;
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                }
                if(heroesAttackType[i] == medicHealing && heroesHealth[3]>100){
                    heroesHealth[i] = heroesHealth[i] +50;
                    System.out.println("Medic is healing: " + heroesAttackType[i]);

                }

            }




        }

    }

    public static void showStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");

        System.out.println("Boss health: " + bossHealth + " damage: "
                + bossDamage + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " damage: " + heroesDamage[i]);

        }




    }
}
