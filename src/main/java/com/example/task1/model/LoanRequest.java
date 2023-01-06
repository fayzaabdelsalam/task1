package com.example.task1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequest {

	private Double loanAmount;
	private Double interestRate;
	private Integer loanPeriod;
	private String loanStartDate;
	private String interestType;
}
