import java.util.*;

public class Dodgeball
{
   public static void main(String[] args) {
   //number of players
       int n;
  
   //prompt to input number of players
       System.out.print("Enter the number of players > ");
       Scanner sc = new Scanner(System.in);
      
       //Object of class Random for generating uniformly distributed random numbers
       Random ran = new Random();
      
       //input until valid number of Players
       //at least 2 players are required
       do{
       //input number of Players
       n = sc.nextInt();
      
       if(n<2)
       System.out.println("Enter number of players more than 1: ");
       }while(n<2);
      
   //an array of Players
       int Players[] = new int[n];
      
       //initialize array from 1 to n
       for(int i=0; i<n;i++){
       Players[i] = i+1;
       }
      
       //to store result of a turn
       String res[] = {"catch", "hit", "miss"};
  
   //Players left to play
       int currPlayers = n;
      
       //initial thrower is the first Player
       int thrower = Players[0];
       //for storing the target
       int target = -1;
      
       //to store result of turn
       //0=catch, 1=hit, 2=miss
       int r;
      
       //for result as String - "catch", "hit", "miss"
       String result;
      
       //till condition is true
       while(true){
       //1st to current number of Players - 1
       if (currPlayers == n && thrower == Players[0]){
       //generating random target
       target = Players[ran.nextInt(currPlayers-1) + 1];
       }
      
       //0th index to current no. of Players-1
       else if (thrower == -1 && target == -1){
       //generating thrower
       thrower = Players[ran.nextInt(currPlayers)];
      
       //generating target
       do{
       target = Players[ran.nextInt(currPlayers)];
       }while(target==thrower);
       }
      
       //generating result in Integer
       r = ran.nextInt(3);
       //accessing String equivalent result
       result = res[r];
      
       //print the Players
       for(int i=0;i<currPlayers;i++){
      
       //if the Player is a thrower add an asterisk
       if(Players[i] == thrower)
       System.out.print(Players[i] + "* ");
      
       else
       //print only Player number
       System.out.print(Players[i] + " ");
      
       }
      
       //print the target, result
       System.out.println("\ntarget = player " + target + ",result = " + result + ".");
       //result is catch
       if (result.equals("catch")){
       //remove thrower from Players
       remove(Players, thrower);
       //decrement number of Players
       currPlayers--;
       //the target becomes thrower
       thrower = target;
      
       //if Players left
       if(currPlayers!=1){
       do{
       //generating new target
       target = Players[ran.nextInt(currPlayers)];
       }while(target==thrower); //till target and thrower are not same
       }
       }
      
       //ball was hit
       else if (result.equals("hit")){
       //remove target from Players
       remove(Players, target);
       //assign -1 to both
       thrower = -1;
       target = -1;
       //decrement number of Players
       currPlayers--;
       }
      
       //ball missed
       else{
       //find new thrower
       thrower = Players[ran.nextInt(currPlayers)];
       //enough Players left
       if(currPlayers!=1){
       do{
       //generating new target
       target = Players[ran.nextInt(currPlayers)];
       }while(target==thrower);
       }
       }
       //game over because only one Player left
       if(currPlayers==1)
       break;
       }
   }
  
   //method to remove a value from the array
   public static void remove(int arr[], int val){
   //traverse the array
   for(int i=0; i<arr.length; i++)
{
//compare the value with elements
//if value matches
if(arr[i] == val)
{
//shift one position backwards
for(int j=i; j<(arr.length-1); j++)
{
arr[j] = arr[j+1];
}
//break from the loop
break;
}
}
   }
}