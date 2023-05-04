package ConcepteOOP;

public class Main {
    public static void main(String[] args) {

        System.out.println("------------ Welcome to Test game -------------");


        PlayerStatus player = new PlayerStatus();
        player.initPlayer("Player", 3, 0);
        player.setHealth(100);
        player.initWeapon("none");
        PlayerStatus opponent = new PlayerStatus();
        opponent.initPlayer("Opponent", 3, 0);
        opponent.setHealth(100);
        opponent.chooseDifficulty("pro");

        // inital position
        player.movePlayerTo(2593, 3842);
        opponent.movePlayerTo(5873, 2384);


        boolean gameOver = false;
        int playerLives;
        int opponentLives;



        while (!gameOver) {

            int playerArtifact = (int) (Math.random() * 10);

            System.out.println("\nPlayer is looking for artifacts");
            player.findArtifacts(playerArtifact);

            System.out.println("\nPlayer visits the shop");
            player.setWeaponInHand();
            System.out.println();

            player.printStatus();
            opponent.printStatus();

            System.out.println("------WEAPON STATUS------");
            player.printWeaponStatus();
            opponent.printWeaponStatus();

            System.out.println("--------WIN CHANCE--------");
            System.out.println("Player winchance : " + player.winChance(opponent));
            System.out.println("Opponent winchance : " + opponent.winChance(player));
            System.out.println("--------------------------");
            System.out.println();

            System.out.println("-PLAYER DISTANCE (OX AXIS)-");
            if(player.getPositionX() > opponent.getPositionX()) {
                System.out.println("Player distance from Opponent (OX) : " + (player.getPositionX() - opponent.getPositionX()));
            } else {
                System.out.println("Player distance from Opponent (OX) : " + (opponent.getPositionX() - player.getPositionX()));
            }
            System.out.println("---------------------------");
            System.out.println();

            System.out.println("-PLAYER DISTANCE (OY AXIS)-");
            if(player.getPositionY() > opponent.getPositionY()) {
                System.out.println("Player distance from Opponent (OY) : " + (player.getPositionY() - opponent.getPositionY()));
            } else {
                System.out.println("Player distance from Opponent (OY) : " + (opponent.getPositionY() - player.getPositionY()));
            }
            System.out.println("---------------------------");
            System.out.println();


            boolean shouldAttackOpponent = player.shouldAttackOpponent(opponent);
            boolean shouldAttackPlayer = opponent.shouldAttackOpponent(player);

            if (shouldAttackOpponent) {
                System.out.println("Player attacks Opponent");
                opponent.takeLive();
            } else if (shouldAttackPlayer) {
                System.out.println("Opponent attacks Player");
                player.takeLive();
            }


            playerLives = player.evaluateGame();
            opponentLives = opponent.evaluateGame();

            if (playerLives == 0) {
                System.out.println(opponent.getNickname() + " won");
                gameOver = true;
            } else if (opponentLives == 0) {
                System.out.println(player.getNickname() + " won!");
                gameOver = true;
            }




        }






    }
}

