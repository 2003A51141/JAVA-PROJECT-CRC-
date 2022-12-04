import java.awt.*;
import javax.swing.*;
public class App{
    static JTextField output = new JTextField();
    static JFrame frame = new JFrame("Cyclic Redundent check algorithm");
    static JTextArea txf = new JTextArea();
    static String input ="";
    static private JScrollPane scrollPane;
   public int divisorbit[] ={1,0,1,1};
    String raw ="";
    int length=0;
    public static void main(String[] a){
        App app =new App();   
        frame.setSize(1080,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
          frame.setLayout(null);
        JButton submit = new JButton("submit");
        submit.setBounds(240, 300, 300, 20);
        JLabel label1 = new JLabel("Enter the String for sending the Data Transmission ");  
        txf.setBounds(240,250,300,30);
        label1.setBounds(240,200,300,20);
        output.setEditable(false);
       // output.setBackground();
       JLabel label = new JLabel("output");
        label.setBounds(780,100,60,40);
        output.setBounds(720,150,320,250);
        scrollPane = new JScrollPane(txf);
        JButton sender = new JButton("send");
        sender.setBounds(240, 350, 300, 20);
        frame.add(sender);
        frame.add(output);
        frame.add(scrollPane);
        frame.add(label1);
        frame.add(txf);
        frame.add(submit);
        frame.add(label);
        submit.addActionListener(e -> app.output());
        sender.addActionListener(e -> app.send());
    }
    private void send() {
        new Dialog();
    }
    private  void output() {
        input = txf.getText();
       raw=convertdata(input);
        String[] arr = raw.split(" ");
        output.setText(raw);
        generator(arr);
        
    }
  String  convertdata(String input){
    String raw="";
    for(int i=0;i<input.length();i++){
        String temp = Integer.toString(input.charAt(i),2);
        for(int j=0;j<temp.length();j++){
            raw+=temp.charAt(j);
        }
        raw+=" ";
}
return raw;
    }
    
    void generator(String[] rawarray){
 //   raw +="  GENERATED CRC BIT IS ";
    int temp[] = new int[rawarray[0].length()];
    for(int i=0;i<rawarray[0].length();i++) temp[i] =(int) rawarray[0].charAt(i);
   
    int rem[] = divideDataWithDivisor(temp, divisorbit); 
    String tem ="";
    // for(int i : divisorbit) tem+=""+i;
    //  output.setText(rawarray[0]+""+tem);
    for(int i : rem) tem+=""+i;
    output.setText("Generated CRC Bit is : "+rawarray[0]+tem);
    }
    static int[] divideDataWithDivisor(int old_data[], int divisor[])
    {
      int remainder[] , i;
      int data[] = new int[old_data.length + divisor.length];
      System.arraycopy(old_data, 0, data, 0, old_data.length);

      remainder = new int[divisor.length];
      System.arraycopy(data, 0, remainder, 0, divisor.length);
      for(i=0 ; i < old_data.length ; i++)
          {
          System.out.println((i+1) + ".) First data bit is : "+ remainder[0]);
          System.out.print("Remainder is : ");
          if(remainder[0] == 1) 
                     {
                      for(int j=1 ; j < divisor.length ; j++)
                      {
                         remainder[j-1] = exor(remainder[j], divisor[j]);
                         System.out.print(remainder[j-1]);
                     }
                 }
                 else {
         
                     for(int j=1 ; j < divisor.length ; j++) 
                            {
                         remainder[j-1] = exor(remainder[j], 0);
                         System.out.print(remainder[j-1]);
                     }
                 }
                 remainder[divisor.length-1] = data[i+divisor.length];
                 System.out.println(remainder[divisor.length-1]);
             }
             return remainder;
         }
         static int exor(int a, int b) 
         {
                 if(a == b ) {
                  return 0;
              }
              return 1;
      }
    
}
class Dialog{
    String raw="";
    App app = new App();
    static int[] dividserbit ;
    JFrame frame = new JFrame("start");
    Dialog(){
    dividserbit = app.divisorbit;
    frame.setLayout(null);
    frame.setSize(480,480);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton button = new JButton("close");
    JButton buttonsub = new JButton("submit");
    button.setBounds(150,150,200,50);
    buttonsub.setBounds(150,250,200,50);
    JTextField t = new JTextField();
    frame.add(button);
    frame.add(buttonsub);
    JLabel lb = new JLabel("enter The sending crc array with comman as 1,0,1,0,1");
    lb.setBounds(150,50,480,50);
    t.setBounds(150,100,200,50);
    frame.add(t);
    frame.add(lb);
    buttonsub.addActionListener(e-> Sender(t.getText()));
    button.addActionListener(e -> frame.setVisible(false));
   
    }
   static void Sender(String text){
        // String msg="";
String[] arr = text.split(",");
int data[]= new int[arr.length];
    for(int i =0;i<arr.length;i++)data[i] = Integer.parseInt(arr[i].charAt(i)+"");
      //   JOptionPane.showMessageDialog(frame, msg,"response", JOptionPane.WARNING_MESSAGE);     
      receiveData(data, dividserbit);  
    }
    static int[] divideDataWithDivisor(int old_data[], int divisor[])
    {
      int remainder[] , i;
      int data[] = new int[old_data.length + divisor.length];
      System.arraycopy(old_data, 0, data, 0, old_data.length);

      remainder = new int[divisor.length];
      System.arraycopy(data, 0, remainder, 0, divisor.length);
      for(i=0 ; i < old_data.length ; i++)
          {
          System.out.println((i+1) + ".) First data bit is : "+ remainder[0]);
          System.out.print("Remainder is : ");
          if(remainder[0] == 1) 
                     {
                      for(int j=1 ; j < divisor.length ; j++)
                      {
                         remainder[j-1] = exor(remainder[j], divisor[j]);
                         System.out.print(remainder[j-1]);
                     }
                 }
                 else {
         
                     for(int j=1 ; j < divisor.length ; j++) 
                            {
                         remainder[j-1] = exor(remainder[j], 0);
                         System.out.print(remainder[j-1]);
                     }
                 }
                 remainder[divisor.length-1] = data[i+divisor.length];
                 System.out.println(remainder[divisor.length-1]);
             }
             return remainder;
         }
    static void receiveData(int data[], int divisor[]) 
    {
        int remainder[] = divideDataWithDivisor(data, divisor);
        for(int i=0 ; i < remainder.length ; i++) 
            {
            if(remainder[i] != 0) 
                 {//statement evaluting
               // System.out.println("There is an error in received data...");
                JOptionPane.showMessageDialog(null, "There is an error in received data...","response", JOptionPane.WARNING_MESSAGE);     
    
                return;
            }
           }
           //if any data not mismatched data wen to correct state of transfer.
           JOptionPane.showMessageDialog(null, "Data was received without any error.","response", JOptionPane.WARNING_MESSAGE);     
   
           //System.out.println(");
    }
    static int exor(int a, int b) 
         {
                 if(a == b ) {
                  return 0;
              }
              return 1;
      }
}