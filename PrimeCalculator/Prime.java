import java.util.*;

public class Prime{
    private Prime(long num){
        System.out.println(isPrime(num));
    }
    private Prime(int num){
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
            System.out.println(i);
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
        boolean[] nums = new boolean[num];
        ArrayList<Integer> primes = new ArrayList();
        
        for(int i = 0; i < num; i++){
            for(int j = 2; j * j <= i; j++){
                if(i % j == 0){
                    nums[i] = true;//not prime
                }
            }
        }
        
        for(int i = 2; i < num; i++){
            if(!nums[i]){
                primes.add(i);
            }
        }
        
        return primes;
    }
    
    public static void main(String[] args){
        Prime obj = new Prime(Long.parseLong(args[0]));
        Prime obj2 = new Prime(Integer.parseInt(args[0]));
    } 
}