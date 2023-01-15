package com.example.task1;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Task1Application {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Loan Amount");
		Double loanAmount = input.nextDouble();
		System.out.println("Enter Interest Rate");
		Double interestRate = input.nextDouble() / 100; // => 0.075
		System.out.println("Enter Loan Period (IN YEARS)");
		Integer loanPeriod = input.nextInt();
		System.out.println("Enter Loan Start Date");
		String loanStartDate = input.next();
		System.out.println("Enter Interest Type (FIXED / VARIABLE)");
		String interestType  = input.next();
		
		Double interest, loanCost, monthlyPayment, monthlyInterest,
		principal, beginningBalance, endingBalance;
		
		Double totalPrincipal = (double) 0;
		Double totalInterest = (double) 0;
		Double monthlyRate = interestRate / 12;
		final Integer numberOfPayments = loanPeriod * 12; // => MONTHS 1*12
		if (interestType.equals("fixed"))
		{
		interest = loanAmount * interestRate * loanPeriod; // => TOTAL INTEREST 750
		loanCost = loanAmount + interest;	// => TOTAL COST => 10750
		monthlyPayment = loanCost / numberOfPayments;
		monthlyInterest = interest / numberOfPayments;
		}
		else
		{
			
		monthlyPayment = loanAmount * (monthlyRate * Math.pow ( (1 + monthlyRate) , numberOfPayments)) /
				( Math.pow( ( 1+monthlyRate ) , numberOfPayments) - 1);
		
		loanCost = monthlyPayment * numberOfPayments;
		
		interest = loanCost - loanAmount;
		
		monthlyInterest = ( loanAmount * interestRate ) / 12; // => INTEREST PER MONTH

		}
		
		System.out.println("	#Loan Summary#");
		System.out.printf("Monthly Payment: %.2f %n", monthlyPayment);
		System.out.println("Number of Payments: " + numberOfPayments);
		System.out.printf("Total Interest: %.2f %n", interest);
		System.out.printf("Total Cost of Loan: %.2f %n", loanCost);
		System.out.println("####################################");

		principal = monthlyPayment - monthlyInterest; // => PRINCIPAL
		
		beginningBalance = loanAmount; 
		
		endingBalance = loanAmount - principal;
		for (int i = 0; i < numberOfPayments; i++)
		{
			Integer paymentNumber = i+1;
			if (i == 0)
			{
				
				System.out.printf("Payment Number: %d %n", paymentNumber);
				System.out.printf("Payment Date: %s %n",loanStartDate);
				if (!interestType.equals("fixed"))
				{
				System.out.printf("Beginning Balance: %.2f %n",loanAmount);
				}
				System.out.printf("Payment: %.2f %n",monthlyPayment);
				System.out.printf("Principal: %.2f %n",principal);
				System.out.printf("Interest: %.2f %n",monthlyInterest);
				if (!interestType.equals("fixed")) 
				{
					System.out.printf("Ending Balance: %.2f %n",endingBalance);
				}
				System.out.println("####################################");
			}
			else
			{

				if (!interestType.equals("fixed"))
				{
				beginningBalance -= principal;
				monthlyInterest = (beginningBalance * interestRate) / 12;
				}
				
				principal = monthlyPayment - monthlyInterest;
				endingBalance = beginningBalance - principal;
			
				System.out.printf("Payment Number: %d %n", paymentNumber);
				if (!interestType.equals("fixed"))
				{
				System.out.printf("Beginning Balance: %.2f %n",loanAmount);
				}
				System.out.printf("Payment: %.2f %n",monthlyPayment);
				System.out.printf("Principal: %.2f %n",principal);
				System.out.printf("Interest: %.2f %n",monthlyInterest);
				if (!interestType.equals("fixed")) 
				{
					System.out.printf("Ending Balance: %.2f %n",endingBalance);
				}
				System.out.println("####################################");
		
			}
			totalPrincipal += principal;
			totalInterest += monthlyInterest;
		}
		System.out.printf("Total Principal: %.2f %n",totalPrincipal);
		System.out.printf("Total Interest: %.2f %n", totalInterest);
	}
//		SpringApplication.run(Task1Application.class, args);
}


