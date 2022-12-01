
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

public class PrimeGUI extends JFrame{  
    private Prime primeMethods;
    private boolean selectedAlgorithm = false;
    private javax.swing.Timer delayTimer;
    
    private JPanel panel = new JPanel();
    private JButton inputButton = new JButton("Press To Accept Integer");
    private JTextField inputField = new JTextField(10);
    private JButton primeButton = new JButton("Prime Check");
    private JTextField primeField = new JTextField(10);
    private JTextField divisibleByField = new JTextField(10);
    private JLabel calculateAllLabel = new JLabel("Calculate all primes until integer:");
    private String[] algorithmOptions = {"Simple", "Eratosthenes"};
    private JComboBox<String> algorithmCB = new JComboBox<String>(algorithmOptions);
    private JButton calculateButton = new JButton("Calculate");
    private JLabel numOfPrimesLabel = new JLabel("Number of Primes:");
    private JTextField numOfPrimesField = new JTextField(30);
    private JLabel timeElapsedLabel = new JLabel("Time Elapsed:");
    private JTextField timeElapsedField = new JTextField(30);
    private JButton printPrimesButton = new JButton("Print all primes to console");
    
    private GridBagLayout gbLayout = new GridBagLayout(); 
    
    
        
    public PrimeGUI(){       
        //frame specifications
        setTitle("Prime Calculator");
        setVisible(true);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);          
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setLayout(gbLayout);
        panel.setBackground(new Color(63,127,0));
        
        primeField.setEditable(false);
        divisibleByField.setEditable(false);
        numOfPrimesField.setEditable(false);
        timeElapsedField.setEditable(false);
        inputField.setOpaque(false);
        primeField.setOpaque(false);
        divisibleByField.setOpaque(false);
        numOfPrimesField.setOpaque(false);
        timeElapsedField.setOpaque(false);
        inputField.setForeground(Color.white);
        primeField.setForeground(Color.white);
        divisibleByField.setForeground(Color.white);
        numOfPrimesField.setForeground(Color.white);
        timeElapsedField.setForeground(Color.white);        
        calculateAllLabel.setForeground(Color.white);
        numOfPrimesLabel.setForeground(Color.white);
        timeElapsedLabel.setForeground(Color.white);
        
        panel.add(inputField, new GBC(1,0).setInsets(0,0,10,10));
        panel.add(inputButton, new GBC(1,1).setInsets(10,0,40,0));       
        panel.add(primeButton, new GBC(0,2).setInsets(10,10,25,0));
        panel.add(primeField, new GBC(1,2).setInsets(10,0,25,10));
        panel.add(divisibleByField, new GBC(2,2).setInsets(10,0,25,10));
        panel.add(calculateAllLabel, new GBC(0,3).setInsets(10,0,10,0));
        panel.add(algorithmCB, new GBC(1,3).setInsets(10,0,10,10));
        panel.add(calculateButton, new GBC(2,3).setInsets(10,0,10,50));
        panel.add(numOfPrimesLabel, new GBC(0,4).setInsets(10,20,10,0));
        panel.add(numOfPrimesField, new GBC(1,4,2,1).setInsets(10,0,10,50));
        panel.add(timeElapsedLabel, new GBC(0,5).setInsets(10,20,10,0));
        panel.add(timeElapsedField, new GBC(1,5,2,1).setInsets(10,0,10,50));
        panel.add(printPrimesButton, new GBC(1,6).setInsets(75,10,10,10).setAnchor(GBC.SOUTH));
        
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
        TextFieldKL tfKL = new TextFieldKL();
        inputField.addKeyListener(tfKL);
        ComboBoxML cbML = new ComboBoxML();
        algorithmCB.addMouseListener(cbML);
        HoverTimerAL htAL = new HoverTimerAL();
        delayTimer = new javax.swing.Timer(500,htAL);
        delayTimer.setRepeats(false);
    }//end constructor
    
    //private ActionListener classes
    private class InputBtnAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                int inputtedInt = Integer.parseInt(inputField.getText());
                primeMethods = new Prime(inputtedInt);
                
                inputButton.setBackground(new Color(0,96,96));
                inputButton.setForeground(Color.white);
                inputButton.setText("Integer Accepted");
                               
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
                timeElapsedField.setText(primeMethods.getDuration());
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
    }//end private ActionListener class 
    private class TextFieldKL implements KeyListener{
        public void keyReleased(KeyEvent e){
            primeMethods = null;
            inputButton.setBackground(new JButton().getBackground()); 
            inputButton.setForeground(Color.black);
            inputButton.setText("Press To Accept Integer");
        }//end method 
        public void keyPressed(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}  
    }//end private KeyListener class    
    private class ComboBoxML implements MouseListener{
        public void mouseEntered(MouseEvent e){
            if(!delayTimer.isRunning())
                delayTimer.start();            
        }
        public void mouseExited(MouseEvent e){}
        public void mouseClicked(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }//end private MouseListener class 
    private class HoverTimerAL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String selected = (String)algorithmCB.getSelectedItem();
            String message = "";
            
            if(selected.equals("Simple")){
                message = "Cycles through integers and checks each one if it's prime.";
            }else{
                message = "Cycles through integers and marks off all multiples of each one as not prime. Any integer already marked is skipped.";
            }//end if-else
            
            JOptionPane.showMessageDialog(null, message, "Algorithm Info", JOptionPane.INFORMATION_MESSAGE);
        }//end method
    }//end private ActionListener class 
    public static void main(String[] args){
        Font largerFont = new Font("Dialog",Font.BOLD,20);
        UIManager.put("Label.font", largerFont);
        UIManager.put("Button.font", largerFont);
        UIManager.put("TextField.font", largerFont);
        UIManager.put("ComboBox.font", largerFont);
        
        PrimeGUI obj = new PrimeGUI(); 
    }//end main method   
}//end class   
