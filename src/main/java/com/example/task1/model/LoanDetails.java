package com.example.task1.model;
import java.util.logging.Logger;
import java.util.Formatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDetails {

	private Double loanAmount;
	private Double interestRate;
	private Integer loanPeriod;
	private String loanStartDate;
	private String interestType;
	
	public LoanDetails( Double loanAmount, Double interestRate, Integer loanPeriod, String loanStartDate, String interestType)
	{
		super();
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanPeriod = loanPeriod;
		this.loanStartDate = loanStartDate;
		this.interestType = interestType;
	}
	
	final Logger logger = Logger.getLogger(LoanDetails.class.getName());
	
	public void setFlatRateTable (LoanSummary loanSummary)
	{
		Formatter fmt = new Formatter();  
		fmt.format("%15s %15s %17s %17s\n", "Payment Number","Payment", "Principal" , "Interest" );
		System.out.println(fmt);
		for (Long i = (long) 1; i <= ( loanPeriod * 12 ); i++)
		{
			Long num[] = { i, Math.round( loanSummary.getMonthlyPayment()),
					 Math.round( loanSummary.getPrincipal()),
					 Math.round( loanSummary.getMonthlyInterest())};  
			Formatter fmt2 = new Formatter();  
			fmt2.format("%8s %21s %15s %17s\n",(Object[]) num);
			System.out.println(fmt2);
		}
	}
	public void setReducingRateTable (LoanSummary loanSummary)
	{
		Formatter fmt = new Formatter();  
		fmt.format("%15s %21s %15s %17s %15s %17s\n", "Payment Number", "Beginning Balance",
				"Payment", "Principal" , "Interest" , "Ending Balance");
		System.out.println(fmt);  

		for (Long i = (long) 1; i <= ( loanPeriod * 12 ); i++)
		{	
			if (i != 1)
			{
		
				loanSummary.setBeginningBalance(loanSummary.getBeginningBalance() - loanSummary.getPrincipal());
				loanSummary.setMonthlyInterest( (loanSummary.getBeginningBalance() * interestRate) / 12 );
				loanSummary.setPrincipal(loanSummary.getMonthlyPayment() - loanSummary.getMonthlyInterest());
				loanSummary.setEndingBalance(loanSummary.getBeginningBalance() - loanSummary.getPrincipal());
			}
			Long num[] = { i, Math.round( loanSummary.getBeginningBalance()),
					 Math.round( loanSummary.getMonthlyPayment()),
					 Math.round( loanSummary.getPrincipal()),
					 Math.round( loanSummary.getMonthlyInterest()),
					 Math.round( loanSummary.getEndingBalance())};  
			Formatter fmt2 = new Formatter();  
			fmt2.format("%8s %21s %21s %15s %15s %15s\n",(Object[]) num);  
			System.out.println(fmt2);  		
		}	
	}

	
	public void setAmortizationTable(LoanSummary loanSummary)
	{
		loanSummary.setPrincipal(loanSummary.getMonthlyPayment() - loanSummary.getMonthlyInterest());
		loanSummary.setBeginningBalance(loanAmount); 
		loanSummary.setEndingBalance(loanAmount - loanSummary.getPrincipal());
		if ( interestType.equals("FIXED") || interestType.equals("fixed") )
		{
			setFlatRateTable(loanSummary);
		}
		else
		{
			setReducingRateTable(loanSummary);	
		}

	}	

}

