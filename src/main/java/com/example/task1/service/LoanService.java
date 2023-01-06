package com.example.task1.service;

import org.springframework.stereotype.Service;

import com.example.task1.model.LoanRequest;
import com.example.task1.model.LoanResponse;

@Service
public class LoanService {

	public LoanResponse loanSummary (LoanRequest loanRequest)
	{
		LoanResponse loanResponse = new LoanResponse();
		
		final Double interestRate = loanRequest.getInterestRate(); //R
		final Double loanAmount = loanRequest.getLoanAmount(); //A
		final Integer numberOfPayments = loanRequest.getLoanPeriod()*12; //n
		final Double interest = loanAmount * (interestRate/100); //I
		final Double monthlyPayment = (loanAmount + interest) / numberOfPayments; //m
		final Double loanCost = loanAmount + interest; //110000
//		final Double payment = monthlyPayment;
		final Double monthlyInterest = interest / numberOfPayments;
		final Double principal = monthlyPayment - monthlyInterest;
		final Double endingBalance = loanAmount - principal;
		
		loanResponse.setMonthlyPayment(monthlyPayment);
		loanResponse.setNumberOfPayments(numberOfPayments);
		loanResponse.setInterest(interest);
		loanResponse.setLoanCost(loanCost);
		loanResponse.setMonthlyInterest(monthlyInterest);
		loanResponse.setPrincipal(principal);
		loanResponse.setEndingBalance(endingBalance);
		
		return loanResponse;
	}
}
