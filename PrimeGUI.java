
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrimeGUI extends JFrame{
    Prime primeMethods = new Prime();
    
    private JPanel panel = new JPanel();
    private JLabel inputLabel = new JLabel("Enter Integer:");
    private JTextField inputField = new JTextField(10);
    private JButton primeButton = new JButton("Prime Check");
    private JTextField primeField = new JTextField(10);
    private JTextField divisibleByField = new JTextField(10);
    private JLabel calculateAllLabel = new JLabel("Calculate all primes until integer:");
    private JComboBox algorithmCB = new JComboBox();
    private JButton calculateButton = new JButton("Calculate");
    private JLabel numOfPrimesLabel = new JLabel("Number of Primes");
    private JTextField numOfPrimesField = new JTextField(10);
    private JLabel timeElapsedLabel = new JLabel("Time Elapsed");
    private JTextField timeElapsedField = new JTextField(10);
    private JButton printPrimeButton = new JButton("Print all primes to console");
    
    private GridBagLayout gbLayout = new GridBagLayout();
    
    
        
    public PrimeGUI(){       
        //frame specifications
        setTitle("Prime Calculator");
        setVisible(true);
        setSize(600,400);
        setResizable(false);
        setLocationRelativeTo(null);          
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setLayout(gbLayout);
        
        panel.add(inputLabel, new GBC(1,0).setInsets(10));
        panel.add(inputField, new GBC(2,0).setInsets(10));
        panel.add(primeButton, new GBC(1,1).setInsets(10));
        panel.add(primeField, new GBC(2,1).setInsets(10));
        panel.add(divisibleByField, new GBC(1,2,2,1).setInsets(10));
        panel.add(calculateAllLabel, new GBC(0,3,2,1).setInsets(10));
        panel.add(algorithmCB, new GBC(2,3).setInsets(10));
        panel.add(calculateButton, new GBC(3,3).setInsets(10));
        panel.add(numOfPrimesLabel, new GBC(0,4).setInsets(10));
        panel.add(numOfPrimesField, new GBC(1,4).setInsets(10));
        panel.add(timeElapsedLabel, new GBC(2,4).setInsets(10));
        panel.add(timeElapsedField, new GBC(3,4).setInsets(10));
        panel.add(printPrimeButton, new GBC(1,5,2,3).setInsets(10));
        
        add(panel);
        
        PrimeBtnAL pbAL = new PrimeBtnAL();
        primeButton.addActionListener(pbAL);
    }
    private class PrimeBtnAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                boolean isPrime = primeMethods.isPrime(Integer.parseInt(inputField.getText()));
                
                if(isPrime){
                    primeField.setText("Prime");
                }else{
                    primeField.setText("Not Prime");
                    divisibleByField.setText(primeMethods.getDivisibleBy());
                }
            }catch(NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter an integer", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }//end method
    }//end private ActionListener class 
    private class CalculateBtnAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        }//end method
    }//end private ActionListener class 
    private class printPrimeBtnAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        }//end method
    }//end private ActionListener class 
    public static void main(String[] args){
        PrimeGUI obj = new PrimeGUI();
    }//end main method  
}//end class 
