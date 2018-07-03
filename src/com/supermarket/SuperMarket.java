package com.supermarket;

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author MukulJaiswal
 *
 */
public class SuperMarket {
	/**
	 * @return Nothing.
	 * @param args the command line arguments.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		Scanner scanner= new Scanner(System.in);
		int choice;
		System.out.println("Enter 1. List Items \nEnter 2. Add Items \nEnter 3. Remove Items");
		System.out.println("Enter 0 for Exit");
		
		// Boolean variable to store whether user wants to quit or not
		boolean flag= true ; 
		
		//Creating object of Item class
		Item item= new Item();
		
		while (flag)
		{		
			System.out.print("Enter your choice : ");
			
			try {
				
				choice=scanner.nextInt();	
				
				switch(choice)
				{				
					case 1:		
						item.listAllItems();
						break;
						
					case 2:			
						item.addNewItems();			
						break;
						
					case 3:
						item.removeItems();
						break;
					
					default:
						if(choice != 0)
							System.out.println("Wrong Choice !");
					
				}
				if(choice == 0) 
				{
					flag = false;
					System.out.println("Thank You! Visit Again.");				
				}	
			}
			catch(Exception exception) {
				System.out.println(exception);
				flag=false;
			}
		}
		scanner.close();
	}
}