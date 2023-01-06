package com.example.task1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanResponse {
	
	private Double monthlyPayment;
	private Integer numberOfPayments;
	private Double interest;
	private Double loanCost;
	private Double monthlyInterest;
	private Double principal;
	private Double endingBalance;
}
