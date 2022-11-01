import java.util.*;

public class Prime{
    private Prime(int num){
        if(isPrime(num))
            System.out.println("Prime");
        else
            System.out.println("Not Prime");    
            
        System.out.println(Arrays.toString(allPrimes(num).toArray()));
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
        ArrayList<Integer> primes = new ArrayList();
        
        //cycle through all integers <= num and check if they are prime
        //set equivalent boolean as true if the integer is not prime
        for(int i = 0; i <= num; i++){
            for(int j = 2; j * j <= i; j++){
                if(i % j == 0){
                    nums[i] = true;//not prime
                    break;//num has a divisor - is not prime - no need to check any longer
                }
            }
        }
        
        //cycle through boolean table and place all false booleans into a list
        for(int i = 2; i <= num; i++){
            if(!nums[i]){
                primes.add(i);
            }
        }
        
        return primes;
    }
    
    public static void main(String[] args){
        Prime obj = new Prime(Integer.parseInt(args[0]));
    } 
}