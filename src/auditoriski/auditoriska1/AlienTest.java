package auditoriski.auditoriska1;

class Alien {
//    public static final int SNAKE_ALIEN = 0;
//    public static final int OGRE_ALIEN = 1;
//    public static final int MARSHMALLOW_MAN_ALIEN = 2;
//    public int type; // Stores one of the three above types
    private int health;
    private String name;

    public Alien(int health, String name) {
        this.health = health;
        this.name = name;
    }

    public int getDamage(){
        return 0;
    }
}

class SnakeAlien extends Alien{

    public SnakeAlien(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage(){
        return 10;
    }
}

class OgreAlien extends Alien{

    public OgreAlien(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage(){
        return 6;
    }
}

class MarshmallowManAlien extends Alien{

    public MarshmallowManAlien(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage(){
        return 1;
    }
}

class AlienPack {
    private Alien[] aliens;

    public AlienPack(int numAliens) {
        aliens = new Alien[numAliens];
    }

    public void addAlien(Alien newAlien, int index) {
        aliens[index] = newAlien;
    }

    public Alien[] getAliens() {
        return aliens;
    }

//    public int calculateDamage() {
//        int damage = 0;
//        for (int i = 0; i < aliens.length; i++) {
//            if (aliens[i].type == auditoriski.auditoriska1.Alien.SNAKE_ALIEN) {
//                damage += 10;// Snake does 10 damage
//            } else if (aliens[i].type == auditoriski.auditoriska1.Alien.OGRE_ALIEN) {
//                damage += 6;// Ogre does 6 damage
//            } else if (aliens[i].type == auditoriski.auditoriska1.Alien.MARSHMALLOW_MAN_ALIEN) {
//                damage += 1;
//                // Marshmallow Man does 1 damage
//            }
//        }
//        return damage;
//    }

    public int calculateDamage() {
        int damage = 0;
        for (int i = 0; i < aliens.length; i++) {
            damage += aliens[i].getDamage();
        }
        return damage;
    }
}

public class AlienTest {
    public static void main(String[] args) {
        AlienPack pack = new AlienPack(3);
        pack.addAlien(new SnakeAlien(100, "Ognen"), 0);
        pack.addAlien(new OgreAlien(100, "Evica"), 1);
        pack.addAlien(new MarshmallowManAlien(100, "Kiki"), 2);

        System.out.println("Damage: " + pack.calculateDamage());
    }
}