/*
Anvay Buch
Simple_Dodgeball Program 1
This program runs a simulation of the simple dodgeball game using only arrays.
*/

import java.util.Scanner;

public class Simple_Dodgeball
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n, d, remain, i;

        //user inputs
        System.out.print("\n Enter the number of players: ");
        n = sc.nextInt();
        System.out.print("Enter the number of players to count to find the target: ");
        d = sc.nextInt();
        sc.close();

        // array to depict which players are out of game
        boolean out[] = new boolean[n];
        
        // initially no one is out
        for(i=0; i<n; i++)
        out[i] = false;
        remain = n;
        int current = 0;

        // display first state
        for(i=0; i<n; i++)
        {
            if(out[i])
            continue;
            System.out.print(i+1);
            if(i == current)
            System.out.print("*");
            System.out.print(" ");
        }
        System.out.println();

        //next player catches ball, first player is out
        out[0] = true;

        //loop till only one player remains
        while(remain > 1)
        {
            i = current;
            int tarcount = 0;

            //find player right of current player
            do
            {
                i = (i+1)%n;
                if(out[i] || i == current)
                continue;
                tarcount++;
            } while(tarcount < d);
            
            //set current player
            current = i;

            //display target
            for(i=0; i<n; i++)
            {
                if(out[i])
                continue;
                System.out.print(i+1);
                if(i == current)
                System.out.print("*");
                System.out.print(" ");
            }
            System.out.println();

            //current player is out of game
            remain--;
            out[current] = true;
        }
    }
}