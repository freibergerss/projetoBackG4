package com.dh.projetoBackG4;

import com.dh.projetoBackG4.dao.impl.ImplPacienteDaoH2;
import com.dh.projetoBackG4.service.PacienteService;
import model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

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
		paciente1.setNome("Roger");
		paciente1.setSobrenome("Ricco");
		paciente1.setRg("12354698-7");
		paciente1.setEndereco("Rua Principe pequeno");
		paciente1.setDataCadastro(LocalDate.now());

		pacienteService.create(paciente1);
		Assertions.assertTrue(paciente1.getIdPaciente() > 0);
		System.out.println("Cadastrando o paciente " + paciente1.getNome() + " com o ID " + paciente1.getIdPaciente());
	}

	@Test
	public void atualizarCadastroPaciente() throws SQLException {
		ImplPacienteDaoH2 pacienteDao = new ImplPacienteDaoH2();

		Optional<Paciente> pacienteOptional = pacienteDao.buscarPorId(1);

		Paciente paciente = pacienteOptional.get();
		System.out.println(paciente.getIdPaciente());
		System.out.println(paciente.getNome());
		System.out.println(paciente.getSobrenome());
		System.out.println(paciente.getRg());
		System.out.println(paciente.getEndereco());
		System.out.println(paciente.getDataCadastro());

		paciente.setNome("Sabrina");
		paciente.setSobrenome("Freiberg");
		paciente.setRg("12345698-7");
		paciente.setEndereco("Rua da Amizade - 16");
		paciente.setDataCadastro(LocalDate.of(2022,11,15));

		pacienteService.update(paciente);


	}

}
