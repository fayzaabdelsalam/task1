package com.example.task1.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanSummary {

	private Double totalInterest = (double) 0; // TOTAL LOAN INTEREST
	private Double loanCost; // TOTAL LOAN COST AFTER CALCULATING INTEREST
	private Double monthlyPayment; // TOTAL PAYMENT PER MONTH
	private List<Double> monthlyInterest; // CALCULATED INTEREST PER MONTH *******
	private List<Double> principal; // CALCULATED PRINCIPAL PER MONTH (DIFFERENCE BETWEEN MONTHLY PAYMENT AND
									// MONTHLY INTEREST)
	private List<Double> beginningBalance; // START AMOUNT
	private List<Double> endingBalance; // REMAINING AMOUNT
	private Double flatInterest;
	private Double flatPrincipal;
	private Double actualPayment;

	public List<Double> calcMonthlyInterest(LoanDetails loanDetails) {
		monthlyInterest = new ArrayList<>();
		principal = new ArrayList<>();
		beginningBalance = new ArrayList<>();
		endingBalance = new ArrayList<>();

		beginningBalance.add(loanDetails.getLoanAmount());

		try {
			Date date = new SimpleDateFormat("dd-MM-yyyy").parse(loanDetails.getLoanStartDate());
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			for (int i = 0; i < loanDetails.getLoanPeriod() * 12; i++) {
				LocalDate newDate = localDate.plusMonths(i);
				monthlyInterest.add(
						beginningBalance.get(i) * loanDetails.getInterestRate() / (loanDetails.getLoanPeriod() * 12));
				principal.add(monthlyPayment - monthlyInterest.get(i));
				if (loanDetails.getInterestType().equals("FIXED") || loanDetails.getInterestType().equals("fixed")) {
					beginningBalance.add(loanDetails.getLoanAmount());
				} else {

					endingBalance.add(beginningBalance.get(i) - principal.get((i)));
					beginningBalance.add(endingBalance.get(i));

				}
				totalInterest += monthlyInterest.get(i);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return monthlyInterest;
	}

	public void recalculate(LoanDetails loanDetails) {
		for (int i = 0; i < loanDetails.getLoanPeriod() * 12; i++) {
			principal.set(i, (actualPayment - monthlyInterest.get(i)));
			endingBalance.set(i, beginningBalance.get(i) - principal.get(i));
			beginningBalance.set(i + 1, endingBalance.get(i));
		}
	}

	public void loanSummary(LoanDetails loanDetails) {
		if (loanDetails.getInterestType().equals("FIXED") || loanDetails.getInterestType().equals("fixed")) {
			totalInterest = loanDetails.getLoanAmount() * loanDetails.getInterestRate() * loanDetails.getLoanPeriod();
			loanCost = loanDetails.getLoanAmount() + totalInterest;
			monthlyPayment = loanCost / (loanDetails.getLoanPeriod() * 12);
			actualPayment = monthlyPayment;
			flatInterest = totalInterest / (loanDetails.getLoanPeriod() * 12);
			flatPrincipal = monthlyPayment - flatInterest;
		}

		else {
			Double monthlyRate = loanDetails.getInterestRate() / 12;
			Integer numberOfPayments = loanDetails.getLoanPeriod() * 12;

			monthlyPayment = loanDetails.getLoanAmount() * (monthlyRate * Math.pow((1 + monthlyRate), numberOfPayments))
					/ (Math.pow((1 + monthlyRate), numberOfPayments) - 1);

			monthlyInterest = calcMonthlyInterest(loanDetails);
			loanCost = loanDetails.getLoanAmount() + totalInterest;
			actualPayment = loanCost / numberOfPayments;
		}

		final Logger logger = Logger.getLogger(LoanSummary.class.getName());

		logger.log(Level.INFO, "   #Loan Summary#");
		logger.log(Level.INFO, "Monthly Payment: " + Math.round(actualPayment));
		logger.log(Level.INFO, "Number of Payments: " + (loanDetails.getLoanPeriod() * 12));
		logger.log(Level.INFO, "Total Interest: " + Math.round(totalInterest));
		logger.log(Level.INFO, "Total Cost of Loan: " + Math.round(loanCost));

	}

}