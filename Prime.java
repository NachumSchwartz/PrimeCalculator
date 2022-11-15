import java.util.*;

public class Prime{
    private Prime(int num){
        if(isPrime(num))
            System.out.println("Prime");
        else
            System.out.println("Not Prime");    
            
        //System.out.println(Arrays.toString(allPrimes(num).toArray()));
        System.out.println(Arrays.toString(allPrimesEratosthenes(num).toArray()));
    }

    private boolean isPrime(long num){
        if(num == 2||num == 3){
            return true;
        }
        if (num <= 1){
            return false;
        }
        if (num % 2 == 0){
            System.out.println("Divisible by 2");
            return false; 
        }
        if (num % 3 == 0){
            System.out.println("Divisible by 3");
            return false;
        }
        for (int i = 5; i * i <= num; i += 6){
            if (num % i == 0){
                System.out.println("Divisible by " + i);
                return false;
            }
            if (num % (i + 2) == 0){
                System.out.println("Divisible by " + (i + 2));
                return false;
            }
        }

        return true;
    }
    
    private ArrayList<Integer> allPrimes(int num){
        boolean[] nums = new boolean[num + 1];
        ArrayList<Integer> primes = new ArrayList<Integer>();
        
        //cycle through all integers <= num and check if they are prime
        //set equivalent boolean as true if the integer is not prime
        for(int i = 0; i <= num; i++){
            for(int j = 2; j * j <= i; j++){
                if(i % j == 0){
                    nums[i] = true;//not prime
                    break;//num has a divisor - is not prime - no need to check any longer
                }//end if
            }//end for-loop
        }//end for-loop
        
        //cycle through boolean table and place all false booleans into a list
        for(int i = 2; i <= num; i++){
            if(!nums[i]){
                primes.add(i);
            }//end if
        }//end for-loop
        
        return primes;
    }//end method
    
    private ArrayList<Integer> allPrimesEratosthenes(int num){
        boolean[] nums = new boolean[num +1];
        ArrayList<Integer> primes = new ArrayList<Integer>();
        int sqrtOfNum = (int)Math.sqrt(num);
        
        //mark off all multiples of 2 <= num as true (== not prime) in nums[]
        for(int j = 2; j * 2 <= num; j++){
            nums[j*2] = true;
        }//end for-loop
        
        //mark off all multiples of 3 and of all subsequent prime integers as true in nums[]
        for(int i = 3; i <= sqrtOfNum; i++){
            
            //only check multiples if i is prime
            //if i not not prime, all multiples have already been marked as true
            if(!nums[i]){
                
                //mark off all multiples of i beginning with i*i
                //j is incremented by two because multiples of 2 are already marked as true
                for(int j = i; j * i <= num; j = j + 2){
                    nums[i*j] = true;
                }//end for loop
            }//end if
        }//end for-loop
        
        //cycle through boolean table and place all false booleans (== primes) into a list
        //begin with 2 because 0 and 1 are false but not prime
        for(int i = 2; i <= num; i++){
            if(!nums[i]){
                primes.add(i);
            }//end if
        }//end for-loop
        
        return primes;
    }//end method
    
    public static void main(String[] args){
        Prime obj = new Prime(Integer.parseInt(args[0]));
    }//end main method 
}//end class