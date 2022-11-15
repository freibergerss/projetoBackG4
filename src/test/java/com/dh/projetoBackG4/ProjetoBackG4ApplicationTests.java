package com.dh.projetoBackG4;

import com.dh.projetoBackG4.service.PacienteService;
import model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootTest
class ProjetoBackG4ApplicationTests {

	PacienteService pacienteService;

	@BeforeEach
	void doBefore(){
		pacienteService = new PacienteService();
	}

	@Test
	public void salvarCadastroPaciente() throws SQLException {
		Paciente paciente1 = new Paciente();
		paciente1.setNome("Sasá");
		paciente1.setSobrenome("Freiberger");
		paciente1.setRg("25874992-4");
		paciente1.setEndereco("Rua Pequeno Principe");
		paciente1.setDataCadastro(LocalDate.now());

		pacienteService.create(paciente1);
		Assertions.assertTrue(paciente1.getIdPaciente() > 0);
		System.out.println("Cadastrando o paciente " + paciente1.getNome() + " com o ID " + paciente1.getIdPaciente());
	}

//
//	idPaciente INT auto_increment PRIMARY KEY,
//	nome VARCHAR(255) NOT NULL,
//	sobrenome VARCHAR(255),
//	rg VARCHAR(15) NOT NULL,
//	endereco VARCHAR(350),
//	dataCadastro DATE NOT NULL

}
