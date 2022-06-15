package br.com.goldsgym.cadastro.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldsgym.cadastro.controller.dto.AlunoDto;
import br.com.goldsgym.cadastro.controller.service.AlunoService;
import br.com.goldsgym.cadastro.modelo.Aluno;

@RestController
@RequestMapping("/alunos")
public class CadastroController {

	@Autowired
	private AlunoService alunoService;
	
	@PostMapping
	public ResponseEntity<?> cadastrarAluno(@RequestBody @Valid Aluno alunoSalvo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoSalvo));
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> getAllAlunos(){
		return ResponseEntity.ok().body(alunoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneAluno(@PathVariable Long id){
		boolean isAlunoPresent = alunoService.isAlunoPresent(id);
		if(!isAlunoPresent) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(alunoService.get(id));
	}	
		
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> deleteAluno(@PathVariable Long id){
		boolean isAlunoPresent = alunoService.isAlunoPresent(id);
		if(!isAlunoPresent) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado");
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não cadastrado");
		}	
		alunoService.deleteById(id);		
		return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarAluno(@PathVariable Long id, 
												 @RequestBody @Valid AlunoDto alunoDto ){
		boolean isAlunoPresent = alunoService.isAlunoPresent(id);
		if(!isAlunoPresent) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado");
		}
		Aluno alunoAtualizado = alunoService.setDtoToObject(id, alunoDto);
		return ResponseEntity.status(HttpStatus.OK).body(alunoService.save(alunoAtualizado));
	}
}