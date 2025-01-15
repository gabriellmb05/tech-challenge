package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.datapool.DataPoolClienteSolicitacaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteControladorIntegracaoTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	@DisplayName("Dado um cliente novo, quando nao informar o nome do cliente, então deve retornar erro de validação 'O atributo nome é obrigatório'")
	void dadoClienteNovo_quandoInserirClienteSemInformarNome_entaoDeveRetornarErroDeValidacao() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DataPoolClienteSolicitacaoDTO.gerarComNome(null))))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("O atributo nome é obrigatório")).andReturn();
	}

	@Test
	@Order(2)
	@DisplayName("Dado um cliente novo, quando nao informar o cpf do cliente, então deve retornar erro de validação 'O atributo cpf é obrigatório'")
	void dadoClienteNovo_quandoInserirClienteSemInformarCpf_entaoDeveRetornarErroDeValidacao() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DataPoolClienteSolicitacaoDTO.gerarComCpf(null))))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("O atributo cpf é obrigatório")).andReturn();
	}

	@Test
	@Order(3)
	@DisplayName("Dado um cliente novo, quando nao informar o email do cliente, então deve retornar erro de validação 'O atributo email é obrigatório'")
	void dadoClienteNovo_quandoInserirClienteSemInformarEmail_entaoDeveRetornarErroDeValidacao() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DataPoolClienteSolicitacaoDTO.gerarComEmail(null))))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("O atributo email é obrigatório")).andReturn();
	}

	@Test
	@Order(4)
	@DisplayName("Dado um cliente novo, quando inserir o cliente com CPF inválido, então deve retornar erro de validação 'O CPF informado é inválido'")
	void dadoClienteNovo_quandoInserirClienteComCpfInvalido_entaoDeveRetornarErroDeValidacao() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DataPoolClienteSolicitacaoDTO.gerarComCpf("12312312312"))))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("O CPF informado é inválido")).andReturn();
	}

	@Test
	@Order(5)
	@DisplayName("Dado um cliente novo, quando inserir o cliente com E-mail inválido, então deve retornar erro de validação 'O E-mail informado é inválido'")
	void dadoClienteNovo_quandoInserirClienteComEmailInvalido_entaoDeveRetornarErroDeValidacao() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DataPoolClienteSolicitacaoDTO.gerarComEmail("teste"))))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("O E-mail informado é inválido")).andReturn();
	}

	@Test
	@Order(6)
	@DisplayName("Dado um cliente novo, quando inserir o cliente, então ele deve ser salvo")
	void dadoClienteNovo_quandoInserirCliente_entaoDeveSerSalvo() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(
						DataPoolClienteSolicitacaoDTO.gerarComCpfEmail("10903153092", "teste@gmail.com"))))
				.andExpect(status().isCreated()).andReturn();
	}

	@Test
	@Order(7)
	@DisplayName("Dado um cliente criado, quando buscar o cliente por cpf, então deve retornar o cliente")
	void dadoClienteCriado_quandoBuscarClientePorCpf_entaoDeveRetornarOCliente() throws Exception {
		mockMvc.perform(get("/clientes/{cpf}", "10903153092")).andExpect(status().isOk())
				.andExpect(jsonPath("$.cpf").value("10903153092"));
	}

	@Test
	@Order(8)
	@DisplayName("Dado um cliente criado, quando buscar o cliente com cpf diferente do cadastrado, então deve retornar erro de validação 'Não foi encontrado Cliente para o cpf: {valor}'")
	void dadoClienteCriado_quandoBuscarClientePorCpfDiferenteDoCadastrado_entaoDeveRetornarOCliente() throws Exception {
		mockMvc.perform(get("/clientes/{cpf}", "75864522023")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("Não foi encontrado Cliente para o cpf: 75864522023"))
				.andReturn();
	}

	@Test
	@Order(9)
	@DisplayName("Dado um cliente novo, quando inserir o cliente com cpf já existente, então deve retornar erro de validação 'O CPF de número {valor} já foi utilizado'")
	void dadoClienteNovo_quandoInserirClienteComCpfJaCadastrado_entaoDeveRetornarErroDeValidacao() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(DataPoolClienteSolicitacaoDTO.gerarComCpf("10903153092"))))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("O CPF de número 10903153092 já foi utilizado")).andReturn();
	}

	@Test
	@Order(10)
	@DisplayName("Dado um cliente novo, quando inserir o cliente com E-mail já existente, então deve retornar erro de validação 'O E-mail {valor} já foi utilizado'")
	void dadoClienteNovo_quandoInserirClienteComEmailJaCadastrado_entaoDeveRetornarErroDeValidacao() throws Exception {
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(
				objectMapper.writeValueAsString(DataPoolClienteSolicitacaoDTO.gerarComEmail("teste@gmail.com"))))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("O E-mail teste@gmail.com já foi utilizado")).andReturn();
	}
}
