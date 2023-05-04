package ConcepteOOP;

public class PlayerStatus {


    private String nickname;
    private int score;
    private int lives;
    private int health;
    private String weaponInHand;
    private double positionX;
    private double positionY;

    public void setHealth(int health) {
        this.health = health;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void chooseDifficulty(String difficulty) {
        switch (difficulty) {
            case "beginner" -> {
                System.out.println("Game difficulty is set to " + difficulty + ". " + nickname + " has no weapon");
                weaponInHand = "none";
            }
            case "rookie" -> {
                System.out.println("Game difficulty is set to " + difficulty + ". " + nickname + " has a knife");
                weaponInHand = "knife";
            }
            case "advanced" -> {
                System.out.println("Game difficulty is set to " + difficulty + ". " + nickname + " has a sniper");
                weaponInHand = "sniper";
            }case "pro" -> {
                System.out.println("Game difficulty is set to " + difficulty + ". " + nickname + " has a kalashnikov");
                weaponInHand = "kalashnikov";
            }
        }
    }

    public void initPlayer(String nickname) {
            this.nickname = nickname;
            this.lives = 3;
            this.score = 0;
    }

    public void initPlayer(String nickname, int lives) {
            this.nickname = nickname;
            this.lives = lives;
            this.score = 0;
    }

    public void initPlayer(String nickname, int lives, int score) {
            this.nickname = nickname;
            this.lives = lives;
            this.score = score;
    }

    public void initWeapon(String weapon) {
        this.weaponInHand = weapon;
    }

    public String getNickname() {
        return nickname;
    }

    public void findArtifacts(int artifactCode) {

        LazyNumber lazyNumber =  new LazyNumber(artifactCode);
        boolean isPerfect = lazyNumber.isPerfect();
        boolean isPrime = lazyNumber.isPrime();
        boolean isMagic = lazyNumber.isMagic();

        //Actualizare score, lives, health in functie de artifactCode
        if (isPerfect) {
            System.out.println(nickname + " found perfect artifact with code " + artifactCode);
            score += 5000;
            lives += 1;
            health = 100;
        } else if (isPrime) {
            System.out.println(nickname + " found prime artifact with code " + artifactCode);
            score += 1000;
            lives += 2;
            if (health >= 75) {
                health = 100;
            } else {
                health += 25;
            }
        } else if (isMagic) {
            System.out.println(nickname + " found magic artifact with code " + artifactCode);
            if (score < 3000) {
                score = 0;
            } else {
                score -= 3000;
            }
            health -= 25;
        } else {
            System.out.println(nickname + " found normal artifact with code " + artifactCode);
            score += artifactCode;
        }
    }



    public void setWeaponInHand() {

        if (score >= 20000 && !(getWeaponInHand().equals("kalashnikov"))) {
            System.out.println(nickname + " purchased kalashnikov");
            this.weaponInHand = "kalashnikov";
            score -= 20000;
        } else if (score >= 10000 && !(getWeaponInHand().equals("sniper")) && !(getWeaponInHand().equals("kalashnikov"))) {
            System.out.println(nickname + " purchased sniper");
            this.weaponInHand = "sniper";
            score -= 10000;
        } else if (score >= 1000 && (getWeaponInHand().equals("none"))) {
            System.out.println(nickname + " purchased knife");
            this.weaponInHand = "knife";
            score -= 1000;
        } else {
            System.out.println(nickname + " could not purchase anything");
        }
    }

    public String getWeaponInHand() {
        return weaponInHand;
    }

    public int evaluateGame() {
        if (health <= 0) {
            lives--;
            System.out.println(nickname + " died - remaining lives: " + lives + "\n");
            health = 100;
            return lives;
        }
        if (lives == 0) {
            System.out.println("Game has ended !");
            return lives;
        }
        return lives;
    }

    public void movePlayerTo(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void takeLive() {
        health = 0;
    }

    public boolean shouldAttackOpponent(PlayerStatus opponent) {
        return winChance(opponent) > opponent.winChance(opponent);
    }

    public int winChance(PlayerStatus opponent) {
        if (getWeaponInHand().equals(opponent.getWeaponInHand())) {
            return (3 * health + score / 1000) / 4;
        } else {
            if (distanceEuclid(opponent) > 1000) {
                switch (weaponInHand) {
                    case "knife":
                        return 1;
                    case "kalashnikov":
                        return 2;
                    case "sniper":
                        return 3;
                }
            } else if (distanceEuclid(opponent) <= 1000) {
                switch (weaponInHand) {
                    case "knife":
                        return 1;
                    case "sniper":
                        return 2;
                    case "kalashnikov":
                        return 3;
                }
            }
        }
        return 0;
    }

    public double distanceEuclid(PlayerStatus opponent) {
        return Math.sqrt(Math.pow(positionX - opponent.positionX, 2) +
                Math.pow(positionY - opponent.positionY, 2));
    }

    public void printStatus() {
        System.out.println("------" + nickname + "'s STATUS------");
        System.out.println(nickname +"'s score: " + score);
        System.out.println(nickname + "'s lives: " + lives);
        System.out.println(nickname + "'s health: " + health);
        System.out.println("-------------------------");
        System.out.println();
    }

    public void printWeaponStatus() {
        System.out.println(nickname + "'s current weapon -> " + weaponInHand);
        System.out.println("-------------------------");
        System.out.println();
    }
}
