package io.github.heltonricardo.ingressoja.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

	@GetMapping("somar/{x}/{y}")
	public double somar(@PathVariable double x, @PathVariable double y) {
		return x + y;
	}

	@GetMapping("/subtrair")
	public double subtrair(@RequestParam(name = "a") double a,
			@RequestParam(name = "b") double b) {
		return a - b;
	}
}
