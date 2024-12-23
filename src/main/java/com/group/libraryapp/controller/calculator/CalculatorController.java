package com.group.libraryapp.controller.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;

@RestController
public class CalculatorController {

	// @GetMapping("/add")
	// public int addTwoNumbers(@RequestParam int number1, @RequestParam int number2) {
	// 	return number1 + number2;
	// }

	@GetMapping("/add")
	public int addTwoNumbers(CalculatorAddRequest request) {
		return request.getNumber1() + request.getNumber2();
	}

	@GetMapping("/sub")
	public int subTwoNumbers(@RequestParam int number1, @RequestParam int number2) {
		return number1 - number2;
	}

	@PostMapping("/multiply")
	public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
		return request.getNumber1() * request.getNumber2();
	}
}
