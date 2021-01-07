/*
 Author:  Colin Morentin
 Date: 	  9/22/2019
 Version: 1.0
 
 Function:
 Write a program to
 read data from a text file and output
 basic data from said text file to a table 
 */

package cis279;
// file assocation in eclipse

import java.io.File;
// I/O with files
import java.io.FileNotFoundException;
// errors
import java.util.ArrayList;
// continer
import java.util.InputMismatchException;
// errors
import java.util.Scanner;
// reading input


public class CIS279_HW3_MorentinColin 
{
	final static String DATA_FILE =
			"C:\\Users\\rvom\\Downloads\\HW3_Accounts.txt";
	// hard coded location of the text file for analysis
	final static int FAIL= -1;
	// integer used for terminating system processes 
	
	public static void main(String[] args) 
	{
		ArrayList<CreditCard> creditCxs = new ArrayList<>();
		// stores data read from a file 
		ReadData(creditCxs);
		// collect data from text file
		printTable(creditCxs);
		// print requested data to a text file

	}
	
	private static void ReadData(ArrayList<CreditCard> creditAccounts) 
	// reads data from a text file and stores it into an ArrayList
	{
		
		Scanner inputFile = null;
		// Initializes scanner object
		try
		{
			inputFile = new Scanner(new File(DATA_FILE));
			// creates file association 
		
		}
		catch(FileNotFoundException ex)
		// if file cannot be opened
		{
			System.out.println("\n **** Exception"
					+ " occured when opening "
					+ DATA_FILE + "--" 
					+ ex.getMessage() + "***" );
			// output error
			System.exit(FAIL);
			// terminate program
		}
		
		while(inputFile.hasNext())
			// while EOF has not been reached
		{
			CreditCard creditCx = new CreditCard();
			// Dynamically allocating memory to the heap for 
			// creditCard object
			try
			{
				creditCx.setAccountNumber(inputFile.nextInt());
				// collects data
			}
			catch (InputMismatchException ex)
			// if error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			try
			{
				creditCx.setBeginningBalance(inputFile.nextDouble());
				// collect data
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			try
			{
				creditCx.setEndingBalance(inputFile.nextDouble());
				// collect data
			
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			try
			{
				creditCx.setCreditLimit(inputFile.nextDouble());
				// collect data
			
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			try
			{
				creditCx.setInterestRate(inputFile.nextDouble());
				// collect data
			
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			
			Customer tempCx = new Customer();
			// another temporary holder for an additional 
			// object data
			try
			{
				tempCx.setcustomerNumber(inputFile.next());
				// collect data
			
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			try
			{
				tempCx.setLastName(inputFile.next());
				// collect data
			
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			try
			{
				tempCx.setFirstName(inputFile.next());
				// collect data
			
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			try
			{
				tempCx.setCreditScore(inputFile.nextInt());
				// collect data
			
			}
			catch (InputMismatchException ex)
			// error associated with data collection
			{
				errorProtocol(inputFile, ex);
			}	
			creditCx.setCx(tempCx);
			// add data to primary object for manipulation
			creditAccounts.add(creditCx);
			// add primary object to list
		}
		inputFile.close();
		// close open file
	}
	
	public static void errorProtocol(Scanner inputFile, InputMismatchException ex)
	// what is to be done when a error is thrown in data collection
	{
		System.out.println("\n ***  Data in "
		+ DATA_FILE + " doesn't match what the program expected -- "
		+ ex.toString() + " ***" );
		// error message
		inputFile.close();
		// close open file
		System.exit(FAIL);
		// terminate program
	}
	
	public static void printTable(ArrayList<CreditCard> creditAccounts)
	// prints requested data into a file
	
	{	
		String accountStatus = "OK";
		// whether account has exceeded credit limit
		double averageBalance = 0.0;
		// beginning balance +  ending balance / 2
		double penaltyFee = 0.0;
		// 5% penalty fee for exceeding credit limit
		// is 5% of ending balance 
		
		System.out.printf("%s %48s %8s %10s %10s %9s %9s %9s", "Account", "Credit",
				"Credit", "Ending", "Account", "Penalty", "Average",
				"Interest");
		System.out.printf("\n%s %14s %12s %12s %7s %8s %12s %8s %8s %11s %8s", "Number", "Customer ID", 
				"Last Name", "First Name", "Score", "Limit", "Balance", "Status", "Fee",
				"Balance", "Rate");
		// printing table headers
		for(int ii = 0; ii < creditAccounts.size(); ii++)
		{
			accountStatus = "OK";
			// assume account is in good standing
			penaltyFee = 0.0;
			// assume account is in good standing
			
			System.out.printf("\n%-12d %-11s %-11s %-13s %-5d %10.2f %9.2f",
			creditAccounts.get(ii).getAccountNumber(),
		
			creditAccounts.get(ii).getCustomer1().getcustomerNumber(),
			creditAccounts.get(ii).getCustomer1().getLastName(),
			creditAccounts.get(ii).getCustomer1().getFirstName(),
			creditAccounts.get(ii).getCustomer1().getCreditScore(),
			creditAccounts.get(ii).getCreditLimit(),
			creditAccounts.get(ii).getEndingBalance());
			// prints data to table
			
			if(creditAccounts.get(ii).getCreditLimit() < 
			creditAccounts.get(ii).getEndingBalance())
				// account is in bad standing
			{
				accountStatus = ("OVER");
				// indicate account is in bad standing
				penaltyFee = (creditAccounts.get(ii).getEndingBalance() * .05);
				// apply a penalty fee
				
			}
			
			averageBalance = (creditAccounts.get(ii).getBeginningBalance() + 
					creditAccounts.get(ii).getEndingBalance())/2;
			// detrmine the average balance of the account
			
			System.out.printf("    %-8s %6.2f %10.2f %6.0f%%",accountStatus, penaltyFee, averageBalance,
					creditAccounts.get(ii).getInterestRate() * 100);
			// output remaining fields of the table
		}
	}
}
