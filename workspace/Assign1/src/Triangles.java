//Ron Harvey
//SE 433 spring
//4/3/17

import java.util.Scanner;
public class Triangles {
	public static void main(String args[]) {
		
		Scanner stdin = new Scanner(System.in);
		String input = "";
		int side1 = 0;
		int side2 = 0;
		int side3 = 0;
		int testNum = 0;
		
		System.out.println("Please enter 3 integers seperated by a space. Entering 'quit' will terminat during manual entry");
		 while(stdin.hasNextLine())
		    {
			 try {
				testNum++;
				input = stdin.nextLine();
				System.out.println("T"+ testNum + " Input: " + input);
				if (input.equals("quit"))
				{
					break;
				}
		        String[] tokens = input.split(" ");
		        side1 = Integer.parseInt(tokens[0]);
		        side2 = Integer.parseInt(tokens[1]);
		        side3 = Integer.parseInt(tokens[2]);
		        if (side1 < 1 || side2 < 1 || side3 <1)
		        {
		        	System.out.println("T"+ testNum + " Please use positive integers");
		        }
		        else if (tokens.length >= 4)
		        {
		        	System.out.println("T"+ testNum + " Too many numbers");
		        }
		        else if ((side1==side2) && (side2 == side3))
		        {
		        	System.out.println("T"+ testNum + " Equilateral");
		        }
		        else if ((side1==side2 && side2!=side3 ) || (side1!=side2 && side3==side1) || (side3==side2 && side3!=side1))
		        {
		        	System.out.println("T"+ testNum + " Isosceles");
		        }
		        else if ((side1 != side2) && (side2 != side3) && (side1 != side3))
		        {
		        	System.out.println("T"+ testNum + " Scalene");
		        }
		        else
		        {
		        	System.out.println("T"+ testNum + " Error");
		        }
		    }
			 catch (NumberFormatException e)
			 {
			 System.out.println("T"+ testNum + " NumberFormatException. Please use integers");
			 }
			 catch (ArrayIndexOutOfBoundsException e)
			 {
				 System.out.println("T"+ testNum + " ArrayIndexOutOfBoundsException. Too few numbers");
			 }
		    }
		stdin.close();
		
	}
	
}

