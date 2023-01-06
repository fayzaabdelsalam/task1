package com.example.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task1.model.LoanRequest;
import com.example.task1.model.LoanResponse;
import com.example.task1.service.LoanService;

@RestController

@RequestMapping("loans")

public class LoanController {
	@Autowired
	private LoanService loanService;
		
	@GetMapping(path="summary")
	public LoanResponse loanSummary(@RequestBody LoanRequest loanRequest)
	{
		LoanResponse loanResponse;
		loanResponse = loanService.loanSummary(loanRequest);
		return loanResponse;
	}

}
