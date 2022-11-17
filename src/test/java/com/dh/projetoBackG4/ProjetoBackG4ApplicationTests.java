package com.dh.projetoBackG4;

import com.dh.projetoBackG4.dao.impl.ImplDentistaDaoH2;
import com.dh.projetoBackG4.dao.impl.ImplEnderecoDaoH2;
import com.dh.projetoBackG4.dao.impl.ImplPacienteDaoH2;
import com.dh.projetoBackG4.model.Dentista;
import com.dh.projetoBackG4.model.Endereco;
import com.dh.projetoBackG4.service.DentistaService;
import com.dh.projetoBackG4.service.EnderecoService;
import com.dh.projetoBackG4.service.PacienteService;
import com.dh.projetoBackG4.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class ProjetoBackG4ApplicationTests {

	PacienteService pacienteService;
	EnderecoService enderecoService;
	DentistaService dentistaService;

	@BeforeEach
	void doBefore(){
		pacienteService = new PacienteService();
		enderecoService = new EnderecoService();
		dentistaService = new DentistaService();
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
	public void salvarCadastroEndereco() throws SQLException{
		Endereco endereco1 = new Endereco("Rua Pintassilgo", 12, "Condado", "Neverland", "Forks");

		enderecoService.create(endereco1);
		Assertions.assertTrue(endereco1.getidEndereco() > 0);

		System.out.println("Novo endereço cadastrado: " + endereco1.getRua() + ", nº " + endereco1.getNumero() + ", Bairro " + endereco1.getBairro() + ". " + endereco1.getCidade() + ", " + endereco1.getEstado() + ".");
	}

	@Test
	public void salvarCadastroDentista() throws SQLException{
		Dentista dentista1 = new Dentista("Tira", "Dentes");

		dentistaService.create(dentista1);
		Assertions.assertTrue(dentista1.getIdDentista() > 0);

		System.out.println("Novo dentista cadastrado: " + dentista1.getNome() + " " + dentista1.getSobrenome() + ".");
	}


	@Test
	public void atualizarCadastroPaciente() throws SQLException, ClassNotFoundException {
		ImplPacienteDaoH2 pacienteDao = new ImplPacienteDaoH2();

		Optional<Paciente> pacienteOptional = pacienteDao.buscarPorId(2);

		Paciente paciente = pacienteOptional.get();
//		System.out.println(paciente.getIdPaciente());
		System.out.println(paciente.getNome());
		System.out.println(paciente.getSobrenome());
		System.out.println(paciente.getRg());
		System.out.println(paciente.getEndereco());
		System.out.println(paciente.getDataCadastro());

		paciente.setIdPaciente(1);
		paciente.setNome("Sabrina2");
		paciente.setSobrenome("Freiberg");
		paciente.setRg("12345698-7");
		paciente.setEndereco("Rua da Amizade - 16");
		paciente.setDataCadastro(LocalDate.of(2022,11,15));
    
		System.out.println(paciente.getIdPaciente());
		System.out.println(paciente.getNome());
		System.out.println(paciente.getSobrenome());
		System.out.println(paciente.getRg());
		System.out.println(paciente.getEndereco());
		System.out.println(paciente.getDataCadastro());

		pacienteService.update(paciente);

	}

	@Test
	public void editarEndereco() throws SQLException, ClassNotFoundException {
		ImplEnderecoDaoH2 enderecoDao = new ImplEnderecoDaoH2();
		Optional<Endereco> enderecoOptional = enderecoDao.buscarPorId(2);

		Endereco endereco = enderecoOptional.get();

//		System.out.println(endereco.getidEndereco());
		System.out.println(endereco.getRua());
		System.out.println(endereco.getNumero());
		System.out.println(endereco.getBairro());
		System.out.println(endereco.getCidade());
		System.out.println(endereco.getEstado());

		endereco.setRua("Policarpo Quaresma");
		endereco.setNumero(64);
		endereco.setBairro("Essex");
		endereco.setCidade("Tókio");
		endereco.setEstado("Mississsipi");

		System.out.println(endereco.getRua());
		System.out.println(endereco.getNumero());
		System.out.println(endereco.getBairro());
		System.out.println(endereco.getCidade());
		System.out.println(endereco.getEstado());

		enderecoService.update(endereco);
	}

	@Test
	public void atualizarCadastroDentista() throws SQLException, ClassNotFoundException{
		ImplDentistaDaoH2 dentistaDao = new ImplDentistaDaoH2();
		Optional<Dentista> dentistaOptional = dentistaDao.buscarPorId(2);

		Dentista dentista = dentistaOptional.get();

		System.out.println(dentista.getNome());
		System.out.println(dentista.getSobrenome());

		dentista.setNome("Arranca");
		dentista.setSobrenome("Sisos");

		System.out.println(dentista.getNome());
		System.out.println(dentista.getSobrenome());

		dentistaService.update(dentista);
	}
}
