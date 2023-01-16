package com.example.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.task1.model.LoanDetails;
import com.example.task1.model.LoanSummary;

import java.util.Scanner;
import java.util.logging.*;

@SpringBootApplication
public class Task1Application {

	public static void main(String[] args) {
			SpringApplication.run(Task1Application.class, args);	
		
			final Logger logger = Logger.getLogger(Task1Application.class.getName());
			try (Scanner input = new Scanner(System.in)) {
				logger.log(Level.INFO, "Enter Loan Amount");
				Double loanAmount = input.nextDouble();
				logger.log(Level.INFO, "Enter Interest Rate");
				Double interestRate = input.nextDouble() / 100;
				logger.log(Level.INFO, "Enter Loan Period (IN YEARS)");
				Integer loanPeriod = input.nextInt();
				logger.log(Level.INFO, "Enter Loan Start Date");
				String loanStartDate = input.next();
				logger.log(Level.INFO, "Enter Interest Type (FIXED / VARIABLE)");
				String interestType  = input.next();			 
 
				LoanDetails loanDetails = new LoanDetails (loanAmount,interestRate,loanPeriod,loanStartDate,interestType);
				LoanSummary loanSummary = new LoanSummary();
				loanSummary.loanSummary(loanDetails);
				loanDetails.setAmortizationTable(loanSummary);
			}
}
}