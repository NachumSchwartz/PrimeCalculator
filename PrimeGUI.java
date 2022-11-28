
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrimeGUI extends JFrame{  
    private Prime primeMethods;
    private boolean selectedAlgorithm = false;
    
    private JPanel panel = new JPanel();
    private JButton inputButton = new JButton("Enter Integer:");
    private JTextField inputField = new JTextField(10);
    private JButton primeButton = new JButton("Prime Check");
    private JTextField primeField = new JTextField(10);
    private JTextField divisibleByField = new JTextField(10);
    private JLabel calculateAllLabel = new JLabel("Calculate all primes until integer:");
    private String[] algorithmOptions = {"Simple", "Eratosthenes"};
    private JComboBox<String> algorithmCB = new JComboBox<String>(algorithmOptions);
    private JButton calculateButton = new JButton("Calculate");
    private JLabel numOfPrimesLabel = new JLabel("Number of Primes");
    private JTextField numOfPrimesField = new JTextField(10);
    private JLabel timeElapsedLabel = new JLabel("Time Elapsed");
    private JTextField timeElapsedField = new JTextField(10);
    private JButton printPrimesButton = new JButton("Print all primes to console");
    
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
        
        panel.add(inputButton, new GBC(1,0).setInsets(10));
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
        panel.add(printPrimesButton, new GBC(1,5,2,3).setInsets(10));
        
        add(panel);
        
        InputBtnAL ibAL = new InputBtnAL();
        inputButton.addActionListener(ibAL);
        PrimeBtnAL pbAL = new PrimeBtnAL();
        primeButton.addActionListener(pbAL);
        CalculateBtnAL cbAL = new CalculateBtnAL();
        calculateButton.addActionListener(cbAL);
        PrintPrimesBtnAL prbAL = new PrintPrimesBtnAL();
        printPrimesButton.addActionListener(prbAL);
        ComboBoxAL cmboAL = new ComboBoxAL();
        algorithmCB.addActionListener(cmboAL);
    }//end constructor
    
    //private ActionListener classes
    private class InputBtnAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                int inputtedInt = Integer.parseInt(inputField.getText());
                primeMethods = new Prime(inputtedInt);
                
                divisibleByField.setText("");
                primeField.setText("");
                numOfPrimesField.setText("");
                timeElapsedField.setText("");
            }catch(NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter an integer not greater than 2147483646", "Error", JOptionPane.ERROR_MESSAGE);
            }catch(InputTooLarge itl){
                JOptionPane.showMessageDialog(null, itl.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(NegativeInput ni){
                JOptionPane.showMessageDialog(null, ni.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }//end try-catch
        }//end method 
    }//end private ActionListener class
    private class PrimeBtnAL implements ActionListener{  
        public void actionPerformed(ActionEvent e){
            try{
                boolean isPrime = primeMethods.isPrime();
                
                if(isPrime){
                    primeField.setText("Prime");
                    divisibleByField.setText("");
                }else{
                    primeField.setText("Not Prime");
                    divisibleByField.setText(primeMethods.getDivisibleBy());
                }
            }catch(NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Please enter an integer before pressing this button", "Error", JOptionPane.ERROR_MESSAGE);            
            }//end try-catch
        }//end method  
    }//end private ActionListener class  
    private class CalculateBtnAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                primeMethods.runAlgorithm(selectedAlgorithm);
                numOfPrimesField.setText(String.valueOf(primeMethods.getPrimes().size()));
                timeElapsedField.setText(String.valueOf(primeMethods.getDuration()));
            }catch(NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Please enter an integer before pressing this button", "Error", JOptionPane.ERROR_MESSAGE);            
            }//end try-catch
        }//end method
    }//end private ActionListener class  
    private class PrintPrimesBtnAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                if(primeMethods.getPrimes() == null){
                    throw new NoListYet();
                }else{
                    System.out.println(primeMethods.getPrimes());
                }
                
            }catch(NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Please calculate primes before pressing this button", "Error", JOptionPane.ERROR_MESSAGE);            
            }catch(NoListYet nly){
                JOptionPane.showMessageDialog(null, nly.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }//end try-catch 
        }//end method 
    }//end private ActionListener class
    private class ComboBoxAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String selected = (String)algorithmCB.getSelectedItem();
            
            if(selected.equals("Simple")){
                selectedAlgorithm = false;
            }else{
                selectedAlgorithm = true;
            }//end if-else
        }//end method
    } 

    
    public static void main(String[] args){
        PrimeGUI obj = new PrimeGUI(); 
    }//end main method  
}//end class   
