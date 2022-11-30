import java.util.*;
import java.text.*;

public class Prime{
    private int inputtedNum;
    private String divisibleBy;
    private ArrayList<Integer> listOfPrimes;
    private long durationOfCalculation;
    
    public Prime(int inputtedNum)throws InputTooLarge, NegativeInput{
        if(inputtedNum >= Integer.MAX_VALUE){
            throw new InputTooLarge();
        }
        if(inputtedNum <= 0){
            throw new NegativeInput();
        }
        this.inputtedNum = inputtedNum;
    }

    public boolean isPrime(){        
        if (inputtedNum <= 1){
            return false;
        }
        if(inputtedNum == 2||inputtedNum == 3){
            return true;
        }
        if (inputtedNum % 2 == 0){
            divisibleBy = "Divisible by 2";
            return false; 
        }
        if (inputtedNum % 3 == 0){
            divisibleBy = "Divisible by 3";
            return false;
        }
        
        //cycle through all possible divisors 
        //beginning with 5 and adding 2 and then 4 alternately
        //this covers all prime divisors
        for (int i = 5; i * i <= inputtedNum; i += 6){
            if (inputtedNum % i == 0){
                divisibleBy = "Divisible by " + i;
                return false;
            }
            if (inputtedNum % (i + 2) == 0){
                divisibleBy = "Divisible by " + (i + 2);
                return false;
            }
        }

        //if no number goes evenly into the given number it is prime
        return true;
    }
    
    //get all prime numbers <= parameter integer
    //checks each number individually to see if it is prime
    private ArrayList<Integer> allPrimes(){
        boolean[] nums = new boolean[inputtedNum + 1];
        ArrayList<Integer> primes = new ArrayList<Integer>();
        
        //cycle through all integers <= num and check if they are prime
        //set equivalent boolean as true if the integer is not prime
        for(int i = 0; i <= inputtedNum; i++){
            for(int j = 2; j * j <= i; j++){
                if(i % j == 0){
                    nums[i] = true;//not prime
                    break;//num has a divisor - is not prime - no need to check any longer
                }//end if
            }//end for-loop
        }//end for-loop
        
        //cycle through boolean table and place all false booleans into a list
        for(int i = 2; i <= inputtedNum; i++){
            if(!nums[i]){
                primes.add(i);
            }//end if
        }//end for-loop
        
        return primes;
    }//end method
    
    //get all prime numbers <= parameter integer
    //algorithm employs sieve of eratosthenes
    private ArrayList<Integer> allPrimesEratosthenes(){        
        boolean[] nums = new boolean[inputtedNum +1];
        ArrayList<Integer> primes = new ArrayList<Integer>();
        int sqrtOfNum = (int)Math.sqrt(inputtedNum);
        
        //mark off all multiples of 2 <= num as true (== not prime) in nums[]
        for(int j = 2; j * 2 <= inputtedNum; j++){
            nums[j*2] = true;
        }//end for-loop
        
        //mark off all multiples of 3 and of all subsequent prime integers as true in nums[]
        for(int i = 3; i <= sqrtOfNum; i++){
            
            //only check multiples if i is prime
            //if i not not prime, all multiples have already been marked as true
            if(!nums[i]){
                
                //mark off all multiples of i beginning with i*i
                //j is incremented by two because multiples of 2 are already marked as true
                for(int j = i; j * i <= inputtedNum; j = j + 2){
                    nums[i*j] = true;
                }//end for loop
            }//end if
        }//end for-loop
        
        //cycle through boolean table and place all false booleans (== primes) into a list
        //begin with 2 because 0 and 1 are false but not prime
        for(int i = 2; i <= inputtedNum; i++){
            if(!nums[i]){
                primes.add(i);
            }//end if
        }//end for-loop
        
        return primes;
    }//end method
    
    //return list of prime numbers using chosen algorithm
    public void runAlgorithm(boolean eratosthenes){        
        long startTime = System.nanoTime();
        
        if(!eratosthenes){
            listOfPrimes = allPrimes();
        }else{
            listOfPrimes = allPrimesEratosthenes();
        }//end if-else
        
        //store duration in instance variable
        durationOfCalculation = System.nanoTime() - startTime;
    }//end method     
    
    //getters
    public String getDivisibleBy(){
        return divisibleBy;
    }//end method    
    public ArrayList<Integer> getPrimes(){
        return listOfPrimes;
    }//end method   
    public String getDuration(){
        return durationString();
    }//end method
    
    private String durationString(){
        double microseconds = durationOfCalculation / 1000.0;//convert nano into micro
        String microString = "";
        String milliString = "";
        String duration = "";
        
        if(microseconds >= 1000){
            DecimalFormat dc = new DecimalFormat(".0");
            String doubleAsString = dc.format(microseconds);
            microString = doubleAsString.substring(doubleAsString.length() - 5);//last half is microseconds
            milliString = doubleAsString.substring(0,doubleAsString.length() - 5);//first half is milliseconds
        }else{
            microString = String.valueOf(microseconds);
        }//end if-else
        
        
        //remove extra zeros from microseconds
        if(microString.endsWith("0")){
            microString = microString.substring(0,microString.length() - 2);
        }//end if       
        while(microString.startsWith("0")){
            microString = microString.replaceFirst("0","");
        }//end while

        
        //define duration String before returning
        if(!milliString.equals(""))
            duration = duration + milliString + " milliseconds, " ;
        if(!microString.equals(""))
            duration = duration +  microString + " microseconds ";
            
        return duration;
    }//end private method
}//end class
