package com.example.task1.model;

import java.util.logging.Level;
import java.util.logging.Logger;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanSummary {

	
	private Double interest;			// TOTAL LOAN INTEREST 
	private Double loanCost;			// TOTAL LOAN COST AFTER CALCULATING INTEREST
	private Double monthlyPayment;		// TOTAL PAYMENT PER MONTH
	private Double monthlyInterest;		// CALCULATED INTEREST PER MONTH
	private Double principal;			// CALCULATED PRINCIPAL PER MONTH (DIFFERENCE BETWEEN MONTHLY PAYMENT AND MONTHLY INTEREST)
	private Double beginningBalance;	// START AMOUNT
	private Double endingBalance;		// REMAINING AMOUNT

	
	public void loanSummary (LoanDetails loanDetails)
	{
		if ( loanDetails.getInterestType().equals("FIXED") || loanDetails.getInterestType().equals("fixed")  )
		{
			interest = loanDetails.getLoanAmount() * loanDetails.getInterestRate() * loanDetails.getLoanPeriod();
			loanCost = loanDetails.getLoanAmount() + interest;
			monthlyPayment = loanCost / ( loanDetails.getLoanPeriod() * 12 );
			monthlyInterest = interest / ( loanDetails.getLoanPeriod() * 12 );
		}
		
		else
		{
			Double monthlyRate = loanDetails.getInterestRate() / 12;
			Integer numberOfPayments = loanDetails.getLoanPeriod() * 12;
			
		    monthlyPayment = loanDetails.getLoanAmount() * 
		    		(monthlyRate * Math.pow ( (1 + monthlyRate) , numberOfPayments)) /
		    			( Math.pow( ( 1 + monthlyRate ) , numberOfPayments) - 1);
	
		  loanCost = monthlyPayment * ( loanDetails.getLoanPeriod() * 12 );
		
		  interest = loanCost - loanDetails.getLoanAmount();
		
		  monthlyInterest = ( loanDetails.getLoanAmount() * loanDetails.getInterestRate() ) / 12;
		}
		
		final Logger logger = Logger.getLogger(LoanSummary.class.getName());
		
		logger.log(Level.INFO, "   #Loan Summary#");
		logger.log(Level.INFO,"Monthly Payment: " + Math.round(monthlyPayment));
		logger.log(Level.INFO, "Number of Payments: " + ( loanDetails.getLoanPeriod() * 12 ));
		logger.log(Level.INFO, "Total Interest: " + Math.round(interest));
		logger.log(Level.INFO, "Total Cost of Loan: " + Math.round(loanCost));

	}
	
}
