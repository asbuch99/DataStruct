/*
Anvay Buch
Dodgeball Program 2
This program runs a simulation of the full dodgeball game using only arrays.
Received help from a friend that goes to Pitt
*/

import java.util.*;

public class Dodgeball {
    public static void main(String[] args) {
        int n;

        //prompt to input number of players
        System.out.print("Enter the number of players: ");
        Scanner sc = new Scanner(System.in);

        Random rand = new Random();

        //input until valid number of Players

        do {
            n = sc.nextInt();

            if (n < 2)
                System.out.println("Enter the number of players: ");
        } while (n < 2);

        int Players[] = new int[n];

        for (int i = 0; i < n; i++) {
            Players[i] = i + 1;
        }

        //To store result 
        String res[] = {
            "catch","hit","miss"
        };

        int currPlayers = n;

        //Initial thrower is the player one
        int thrower = Players[0];
        int target = -1;
        int r;

        String result;

        while (true) {
            //First to current number of players minus 1 and generate a random target
            if (currPlayers == n && thrower == Players[0]) {
                target = Players[rand.nextInt(currPlayers - 1) + 1];
            } else if (thrower == -1 && target == -1) {
                thrower = Players[rand.nextInt(currPlayers)];

                do {
                    target = Players[rand.nextInt(currPlayers)];
                } while (target == thrower);
            }

            r = rand.nextInt(3);
            result = res[r];

            //Print the players
            for (int i = 0; i < currPlayers; i++) {

                //If the player is a thrower add asterisk
                if (Players[i] == thrower)
                    System.out.print(Players[i] + "* ");

                else
                    System.out.print(Players[i] + " ");

            }

            //Print the target and result
            System.out.println("\ntarget = player " + target + ",result = " + result + ".");
            if (result.equals("catch")) {
                removeval(Players, thrower);
                currPlayers--;
                thrower = target;

                //If there are players left
                if (currPlayers != 1) {
                    do {
                        target = Players[rand.nextInt(currPlayers)];
                    } while (target == thrower);
                }
            }

            //If the ball is hit
            else if (result.equals("hit")) {
                removeval(Players, target);
                thrower = -1;
                target = -1;
                currPlayers--;
            }

            //If ball missed
            else {
                thrower = Players[rand.nextInt(currPlayers)];
                if (currPlayers != 1) {
                    do {
                        target = Players[rand.nextInt(currPlayers)];
                    } while (target == thrower);
                }
            }
            //One player left means the game is over
            if (currPlayers == 1)
                break;
        }
    }

    //To remove a value from the array
    public static void removeval(int arr[], int val) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                for (int j = i; j < (arr.length - 1); j++) {
                    arr[j] = arr[j + 1];
                }
                break;
            }
        }
    }
}