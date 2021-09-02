/*
Anvay Buch
Simple_Dodgeball Program 1
This program runs a simulation of the simple dodgeball game using only arrays+123.
*/

import java.util.Scanner;

public class Dodgeball
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n, d, left, i;

        // accept user inputs
        System.out.print("\nEnter the number of players > ");
        n = sc.nextInt();
        System.out.print("Enter the number of players to count to find the target > ");
        d = sc.nextInt();
        sc.close();

        // out array to depict which players are out of game
        boolean out[] = new boolean[n];
        
        // initially no one is out
        for(i=0; i<n; i++)
        out[i] = false;
        left = n;
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

        // as next player catches ball, first player is out
        out[0] = true;

        // loop till only one player left
        while(left > 1)
        {
            i = current;
            int dcount = 0;

            // find dth player right of current player
            do
            {
                i = (i+1)%n;
                if(out[i] || i == current)
                continue;
                dcount++;
            } while(dcount < d);
            
            // that player is current player
            current = i;

            // display state
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

            // the current player is out of game
            left--;
            out[current] = true;
        }
    }
}