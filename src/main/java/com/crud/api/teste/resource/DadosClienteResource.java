package com.crud.api.teste.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.crud.api.teste.modelo.DadosCliente;
import com.crud.api.teste.repository.ClienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST DadosClientes")
@RestController
@RequestMapping(value = "/dadosClientes")
// testando o commit
public class DadosClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@ApiOperation(value="Retorna uma lista de clientes")
	@GetMapping("/todos")
	
	public List<DadosCliente> listarTodos() {
		return clienteRepository.findAll();
	}
	
	@ApiOperation(value="Retorna o cliente selecionado pelo seu id")
	@GetMapping("/{codigo}")
	public DadosCliente buscarId(@PathVariable Long codigo) {
		return clienteRepository.findById(codigo).get();
	}
		
	@ApiOperation(value="Retorna um cliente filtrado pelo nome")
	@GetMapping("/nome")
	public ResponseEntity<List<DadosCliente>> buscarPorNome(@RequestParam("nome") String nome) {
		List<DadosCliente> clientesEncontrados = clienteRepository.findByNomeLike(nome.toLowerCase());
		return clientesEncontrados != null ? ResponseEntity.ok().body(clientesEncontrados)
			 : ResponseEntity.notFound().build();		
	}
	
	@ApiOperation(value="Retorna um cliente filtrado pelo nome")
	@GetMapping("/letra")
	public ResponseEntity<List<DadosCliente>> buscarTeste(@RequestParam("letras") String letra) {
		List<DadosCliente> clientesEncontrados = clienteRepository.findByteste(letra.toLowerCase());
		return clientesEncontrados != null ? ResponseEntity.ok().body(clientesEncontrados)
			 : ResponseEntity.notFound().build();		
	}
	
	@GetMapping("/coluna")
	public ResponseEntity<List<DadosCliente>> buscarColuna(@RequestParam("coluna")String coluna){
		List<DadosCliente> colunaEncontrada = clienteRepository.findByColuna(coluna.toLowerCase());
		return colunaEncontrada != null ? ResponseEntity.ok().body(colunaEncontrada)
				:ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/clienteOrdenado")
	public ResponseEntity <List<DadosCliente>> ordenar() {
		List<DadosCliente> clientesFiltrados = clienteRepository.ordenaCliente();
		return ResponseEntity.ok().body(clientesFiltrados);
	}
	
	
	
	@ApiOperation(value="Salva cliente")
	@PostMapping
	public ResponseEntity<DadosCliente> criar(@Valid @RequestBody DadosCliente clienteSalva,
			HttpServletResponse response) {

		DadosCliente dadosCliente = clienteRepository.save(clienteSalva);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(dadosCliente.getCodigoCliente()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(dadosCliente);
	}
	@ApiOperation(value="Deleta cliente")
	@DeleteMapping("/{codigoCliente}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarcliente(@PathVariable("codigoCliente") Long codigo) {
		clienteRepository.deleteById(codigo);
	}
	@ApiOperation(value="Atualiza uma lista de clientes")
	@PutMapping("/{codigoCliente}")
	public ResponseEntity<DadosCliente> atualizar(@PathVariable Long codigoCliente,
			@Valid @RequestBody DadosCliente recebeDados) {
		Optional<DadosCliente> atualizaDados = clienteRepository.findById(codigoCliente);
		BeanUtils.copyProperties(recebeDados, atualizaDados.get(), "codigoCliente");
		clienteRepository.save(atualizaDados.get());
		return ResponseEntity.ok(atualizaDados.get());

	}

}