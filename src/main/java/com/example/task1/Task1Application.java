package com.example.task1;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Task1Application {

	public static void main(String[] args) {

//		Scanner input = new Scanner(System.in);
//		
//		System.out.println("Enter Loan Amount");
//		Double loanAmount = input.nextDouble();
//		System.out.println("Enter Interest Rate");
//		Double interestRate = input.nextDouble();
//		System.out.println("Enter Loan Period (IN YEARS)");
//		Integer loanPeriod = input.nextInt();
//		System.out.println("Enter Loan Start Date");
//		String loanStartDate = input.next();
//		System.out.println("Enter Interest Type (FIXED / VARIABLE)");
//		String interestType  = input.next();
//		
//		final Integer numberOfPayments = loanPeriod*12;
//		final Double interest = loanAmount * (interestRate/100);
//		final Double monthlyPayment = (loanAmount + interest) / numberOfPayments;
//		final Double loanCost = loanAmount + interest;	
//		
//		Double monthlyInterest = interest / numberOfPayments;
////		String paymentDate;
//		Double principal = monthlyPayment - monthlyInterest;
//		Double beginningBalance = loanAmount;
//		Double endingBalance = loanAmount - principal;
//		
//		System.out.println("	#Loan Summary#");
//		System.out.printf("Monthly Payment: %.2f %n", monthlyPayment);
//		System.out.println("Number of Payments: " + numberOfPayments);
//		System.out.println("Total Interest: " + interest);
//		System.out.println("Total Cost of Loan: " + loanCost);
//		System.out.println("####################################");
//
//		for (int i=0; i<numberOfPayments; i++)
//		{
//			Integer paymentNumber = i+1;
//			if (i == 0)
//			{
//				System.out.printf("Payment Number: %d %n", paymentNumber);
//				System.out.printf("Payment Date: %s %n",loanStartDate);
//				System.out.printf("Beginning Balance: %.2f %n",loanAmount);
//				System.out.printf("Payment: %.2f %n",monthlyPayment);
//				System.out.printf("Principal: %.2f %n",principal);
//				System.out.printf("Interest: %.2f %n",monthlyInterest);
//				System.out.printf("Ending Balance: %.2f %n",endingBalance);
//				System.out.println("####################################");
//			}
//			else
//			{
//
//				beginningBalance-=principal;
//				monthlyInterest = (beginningBalance*interestRate) / (numberOfPayments*100);
//				principal = monthlyPayment - monthlyInterest;
//				endingBalance = beginningBalance - principal;
//				System.out.printf("Payment Number: %d %n", paymentNumber);
////				System.out.printf("Payment Date: %s %n",loanStartDate);
//				System.out.printf("Beginning Balance: %.2f %n",beginningBalance);
//				System.out.printf("Payment: %.2f %n",monthlyPayment);
//				System.out.printf("Principal: %.2f %n",principal);
//				System.out.printf("Interest: %.2f %n",monthlyInterest);
//				System.out.printf("Ending Balance: %.2f %n",endingBalance);
//				System.out.println("####################################");
//						
//			}
//			
//		}
		SpringApplication.run(Task1Application.class, args);
	}

}
