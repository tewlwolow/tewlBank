// A *very* simple test-case console-based banking programme simulator.

package tewlBank;

import java.util.Scanner;
import java.util.InputMismatchException;


// Define container class to initialise the programme
public class BankApp
{
	// Define constants for quicker access
	static double APP_VERSION = 1.1;
	static String USER_NAME = "Default User";
	static String USER_ID = "0000000";
	
	// Define entry point method, create user object from constants and show UI
	public static void main(String[] args) {
		BankAccount testUser = new BankAccount(USER_NAME, USER_ID);
		testUser.showMenu();
	}

}

// Define bank account class
class BankAccount
{
	// Unpack received object data
	String customerName;
	String customerId;
	
	BankAccount(String customerName, String customerId)
	{
		this.customerName = customerName;
		this.customerId = customerId;
	}
	
	// Declare calculation variables
	int balance;
	int previousTransaction;
	
	/**
	 *  Define method for depositing funds
	 *  Make sure no negatives numbers are entered
	 */
	void deposit(int amount)
	{
		if(amount > 0)
		{
			balance = balance + amount;
			previousTransaction = amount;
		}
		else
		{
			System.out.println("Can't deposit negative amount. Try withdrawing.");
		}
	}
	
	/**
	 *  Define method for withdrawing funds
	 *  Make sure no negatives numbers are entered
	 *  Make sure there's no up-front deduction (negative balance)
	 */
	void withdraw(int amount)
	{
		if(amount > 0 && amount<=balance)
		{
			balance = balance - amount;
			previousTransaction = -amount;
		}
		else if(amount < 0)
		{
			System.out.println("Can't withdraw negative amount. Try depositing.");
		}
		else if(amount>balance)
		{
			System.out.println("Not enough funds to withdraw.");
		}
	}
	
	// Define method for retrieving data about prior transaction
	void getPreviousTransaction()
	{
		if(previousTransaction > 0)
		{
			System.out.println("Deposited: " + previousTransaction);
		}
		else if(previousTransaction < 0)
		{
			System.out.println("Withdrawn: " + Math.abs(previousTransaction));
		}
		else
		{
			System.out.println("No previous transaction to display.");
		}
			
	}
	
	/**
	 * Define method for error handling
	 * Receives scanner object from main loop
	 * Returns amount if entered correctly
	 * No logging cuz it's a really really really simple test programme
	 */
	public int checkInputAmount(Scanner scanner)
	{
		int amount = 0;
        try
        {
            amount = scanner.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid amount. Try again.");
        }
        
        return amount;
       
	}
	
	// Define options menu method
	void showOptions()
	{
		System.out.println("B. Balance");
		System.out.println("D. Deposit");
		System.out.println("W. Withdraw");
		System.out.println("P. Previous transaction");
		System.out.println("O. Options");
		System.out.println("E. Exit");
	}
	
	// Define main UI method
	void showMenu()
	{
		// Declare necessary variables
		String option;
		Scanner scanner = new Scanner(System.in);
		
		// Print welcome screen
		System.out.println("tewlBank v. " + BankApp.APP_VERSION);
		System.out.println("----------------------\n");
		System.out.println("Welcome " + customerName);
		System.out.println("Your ID is: " + customerId);
		System.out.println("\n");
		
		showOptions();
		
		// Define main programme loop via do-switch-while loop
		do
		{
			System.out.println("====================================================");
			System.out.println("Enter an option: ");			
			System.out.println("====================================================");
			
			// Make sure lowercase input is caught
			option = scanner.next().toUpperCase();
						
			switch(option)
			{
			
			// Show balance option
			case "B":
				System.out.println("----------------------");
				System.out.println("Balance = " + balance);
				System.out.println("----------------------\n");
				break;
			
			// Deposit option
			case "D":
				System.out.println("----------------------");
				System.out.println("Enter the amount to deposit:");
				System.out.println("----------------------");
				int amountDeposit = checkInputAmount(scanner);
				deposit(amountDeposit);
				System.out.println("\n");
				break;
			
			// Withdraw option
			case "W":
				System.out.println("----------------------");
				System.out.println("Enter the amount to withdraw:");
				System.out.println("----------------------");
				int amountWithdraw = checkInputAmount(scanner);
				withdraw(amountWithdraw);
				System.out.println("\n");
				break;
			
			// Previous transaction option
			case "P":
				System.out.println("----------------------");
				getPreviousTransaction();
				System.out.println("----------------------\n");
				break;
			
			// Re-show options (I'm scatterbrained to no end mate)
			case "O":
				showOptions();
				break;				
			
			// Print a faux-divider	
			case "E":
				System.out.println("************************************************************************");
				break;	
			
			// Invalid input handling
			default:
				System.out.println("Invalid option. Please try again.");
				break;		
				
			}

		// Repeat unless user explicitly quits
		} while(!option.equals("E"));
		
		// Close scanner to ensure no memory leaks (not paramount, but Eclipse won't complain)
		scanner.close();
		
		// Print goodbye message and finish this breathtaking story
		System.out.println("Thank you for using our services!");
		
	}
	
}
