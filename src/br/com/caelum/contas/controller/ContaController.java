package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO dao;

	@Autowired
	public ContaController(ContaDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/conta/form")
	public String formulario() {
		return "/conta/formulario";
	}
	
	@RequestMapping("/conta/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {
		
		if(result.hasErrors()) {
			return "conta/formulario";
		}
		
		if(conta.getValor() < 0.00 || String.valueOf(conta.getValor()).equals("")) {
			return "conta/formulario";
		}
		
		System.out.println("Conta adicionada eh: " + conta.getDescricao());
		dao.adiciona(conta);
		
		return "/conta/conta-adicionada";
	}

	@RequestMapping("/conta/pagaConta")
	public void paga(Conta conta, HttpServletResponse response) {
		dao.paga(conta.getId());
		
		response.setStatus(200);
	}
	
	@RequestMapping("/conta/listaContas")
	public String lista(Model model) {
		
		List<Conta> contas = dao.lista();
		
		model.addAttribute("contas", contas);
		
		return "conta/lista";
	}
	
	@RequestMapping("/conta/removeConta")
	public String deletar(Conta conta) {
		dao.remove(conta);
		
		return "redirect:listaContas";
	}
	
	@RequestMapping("/conta/mostraConta")
	public String mostra(Long id, Model model) {
		model.addAttribute("conta", dao.buscaPorId(id));
		return "conta/mostra";
	}
	
	@RequestMapping("/conta/alteraConta")
	public String altera(Conta conta) {
		dao.altera(conta);
		return "redirect:listaContas";
	}
}
