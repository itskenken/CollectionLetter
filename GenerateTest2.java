
import java.util.Scanner;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;


public class GenerateTest2 {
    
    public static void main(String[] args) throws IOException {
        File data = new File ("src//generateLetter//smalldata.txt");
        Scanner input = new Scanner (data).useDelimiter("\\||First\\s*");//so the dollar amount isn't parsed as $xxxx.xxFirstName
        generateStatement(input);   
    }

    public static double parseNumber(String str)
    {
        String [] noletter = str.split("(?i)(?=[a-z])",2);
        
        double value = Double.parseDouble(noletter[0]);
        return value;
    }
    public static void generateStatement (Scanner input)throws IOException
        {
        LocalDate localdatenow = LocalDate.now();
        
        DateTimeFormatter simpleDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        //these two will generate today's date and change it into a string
       
        String today = localdatenow.format(simpleDate);
        String tenDays = localdatenow.plusDays(10).format(simpleDate);
        
        DecimalFormat d = new DecimalFormat("0.00");//change the Double amount to something that looks better on paper
        String output = "";
        String filename;//will be useful to store a filename (based on customer's first and last name) for the PrintWriter. 
        String firstName;
        String lastName;
        String adr,adr2,city,state;
        int zip;
        String money;
        double amt;
        double amtplus;
        int lettersCreated = 0;//used this just during testing because i was curious
        
         while(input.hasNext())
         {
             
           firstName = input.next();
           firstName ="First"+firstName;//not sure why FirstName was present in the template file; i kept it anyway
           lastName = input.next();
           adr = input.next();
           adr2 = input.next();
           city = input.next();
           state = input.next();
           zip = input.nextInt();
           money = input.next().substring(1);// substring(1) always removed the dollar sign, which made the next step easier.
           amt = parseNumber(money);
           amtplus = (amt * 1.1);//increase by ten percent
           
           if(amt > 5)//skip over any letters that state the amount owed as 5 dollars or less
           {
           lettersCreated++;
           
           //System.out.println(firstName + " " +lastName+" \r\nAmount Owed: $"+d.format(amt));
           //This code was here for testing purposes, it's commented out now
           
           /*
           Output will be sent to printwriter; and printwriter obeys most often when
           i include both carriage return and newline escape characters.
           */
           output +="\r\n\r\n\r\n\r\n"
                   + "\r\n"+ "\r\n"+ "\r\n"+ "\r\n"+ "\r\n"
                   + "   ACME Inc." + "\r\n"+ 
                    "   123 Coffee Beans Street" + "\r\n"+ 
                    "   Java, GA 99999 "+ "\r\n"
                   
                   
                   +"\r\n\r\n\r\n"+ "\r\n" +"\r\n" +"\r\n" +"\r\n" +"\r\n" +
                   "   " +firstName + " " + lastName + 
                   "\r\n   "+adr+"\r\n   "+adr2+"\r\n   "
                   +city+", "+state + " "+ zip +
                   "\r\n" + "\r\n"+ "\r\n"+ "\r\n"
                   + "\r\n"+ "\r\n"+ "\r\n"+ "\r\n"
                   + "\t\t\t\t\t"+today+"\r\n\r\n"
            
                   +"\r\n" +"\r\n" +"\r\n" +"\r\n" +"\r\n" 
                   
                   + "\r\n"+ "  Dear "+firstName+" "+lastName+","+ "\r\n"+ "\r\n" 
                   +"\r\n     Our records show an unpaid balance of $"+d.format(amt)+" that is over 120+ days old."+
                  "\r\n"+ "  This balance is due now. If not fully paid in ten days, (by "+tenDays+"),"+ "\r\n"+ 
                   "  we will have to inform the collection agent for outstanding balance plus "
                   + "\r\n"+"  a 10% processing fee; bringing your total amount owed to $"+d.format(amtplus)+"." 
                   + "\r\n"+"\r\n"+ 
                   "    If you have any questions or would like to discuss a payment plan,"+ "\r\n"+ 
                   "  please do not hesitate to contact our office at 555-555-5555."+ "\r\n"+ "\r\n"+ "\r\n"+ 
                   "  Acme Financial Team"+ "\r\n"+ "\r\n"+ "\r\n"
                   + "\r\n" +"\r\n" +"\r\n" +"\r\n" +"\r\n" +"\r\n" + "\r\n"+ "\r\n"+ "\r\n"+ "\r\n"+ "\r\n" ;
           
           
           filename = "ColLetter_"+firstName+lastName+".txt";
           System.out.println("Storing in "+filename+"\n\n");
               try (PrintWriter p = new PrintWriter(filename) //printwriter has to take in a string, which i have converted the client's name to
               ) 
               {
                   p.print(output);//Prints the above string object
               } //Prints the above string object
           output = "";//if I don't reset output, then every letter has all of the information of the letter before it.
           //           That gets pretty annoying. 
           
         }
           
           
           
        }
         System.out.println("LETTERS CREATED:"+lettersCreated);
         //also not required, but i wanted to see it for its own sake
    
    
}
}
