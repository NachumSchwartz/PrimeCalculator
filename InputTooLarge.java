
class InputTooLarge extends Exception{
    public InputTooLarge(){
        super("Please enter an integer not greater than 2147483646");
    }
}//end class
class NegativeInput extends Exception{
    public NegativeInput(){
        super("Please enter a positive integer");
    }
}//end class
class NoListYet extends Exception{
    public NoListYet(){
        super("Please calculate primes before pressing this button");
    }
}//end class
