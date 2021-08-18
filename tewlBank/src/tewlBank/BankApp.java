package tewlBank;

import java.util.Scanner;
import java.util.InputMismatchException;

public class BankApp
{
	
	public static void main(String[] args ) {
		BankAccount testUser = new BankAccount("Default User", "0000000");
		testUser.showMenu();
	}

}

class BankAccount
{
	
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	
	BankAccount(String customerName, String customerId)
	{
		this.customerName = customerName;
		this.customerId = customerId;
	}
	
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
	
	
	public int checkInputAmount(Scanner scanner)
	{
		int amount = 0;
        try {
            amount = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Try again.");
        }
        
        return amount;
       
	}
	
	void showOptions()
	{
		System.out.println("B. Balance");
		System.out.println("D. Deposit");
		System.out.println("W. Withdraw");
		System.out.println("P. Previous transaction");
		System.out.println("O. Options");
		System.out.println("E. Exit");
	}
	
	void showMenu()
	{
		
		String option;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome " + customerName);
		System.out.println("Your ID is: " + customerId);
		System.out.println("\n");
		showOptions();
		
		do
		{
			System.out.println("====================================================");
			System.out.println("Enter an option: ");			
			System.out.println("====================================================");
					
			option = scanner.next().toUpperCase();
						
			switch(option)
			{
			
			case "B":
				System.out.println("----------------------");
				System.out.println("Balance = " + balance);
				System.out.println("----------------------");
				System.out.println("\n");
				break;
				
			case "D":
				System.out.println("----------------------");
				System.out.println("Enter the amount to deposit:");
				System.out.println("----------------------");
				int amountDeposit = checkInputAmount(scanner);
				deposit(amountDeposit);
				System.out.println("\n");
				break;
				
			case "W":
				System.out.println("----------------------");
				System.out.println("Enter the amount to withdraw:");
				System.out.println("----------------------");
				int amountWithdraw = checkInputAmount(scanner);
				withdraw(amountWithdraw);
				System.out.println("\n");
				break;
				
			case "P":
				System.out.println("----------------------");
				getPreviousTransaction();
				System.out.println("----------------------");
				System.out.println("\n");
				break;
				
			case "O":
				showOptions();
				break;				
				
			case "E":
				System.out.println("*********************************************");
				break;	
				
			default:
				System.out.println("Invalid option. Please try again.");
				break;		
				
			}

		} while(!option.equals("E"));
		
		scanner.close();
		System.out.println("Thank you for using our services!");
		
	}
	
}
