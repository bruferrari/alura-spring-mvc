package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {

	@RequestMapping("/infra/olaMundoSpring")
	public String execute() {
		System.out.println("Executando uma lógica com spring mvc");
		return "/hello/ok";
	}
}
