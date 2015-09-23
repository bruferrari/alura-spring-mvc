package br.com.caelum.contas.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.ConnectionFactory;

@Controller
public class InfraController {

	@RequestMapping("/infra/tabelas")
	public String criaBanco() throws SQLException {
		Connection c = new ConnectionFactory().getConnection();
		PreparedStatement st1 = c.prepareStatement("drop table if exists contas");
		st1.execute();

		PreparedStatement st11 = c.prepareStatement("CREATE TABLE contas (id int(11) NOT NULL AUTO_INCREMENT, "
				+ "descricao varchar(255) DEFAULT NULL, valor double NOT NULL, "
				+ "paga tinyint(1) NOT NULL, dataPagamento datetime NOT NULL, "
				+ "tipo varchar(20) DEFAULT NULL, PRIMARY KEY(id))");
		st11.execute();
		
		PreparedStatement st2 = c.prepareStatement("drop table if exists usuarios");
		st2.execute();

		PreparedStatement st22 = c.prepareStatement("create table usuarios (login VARCHAR(255),senha VARCHAR(255));");
		st22.execute();

		PreparedStatement st3 = c.prepareStatement("insert into usuarios (login, senha) values ('caelum', 'online');");
		st3.execute();
		
		c.close();
		
		return "/infra/infra-ok";

	}
}
