package br.com.goldsgym.cadastro.controller;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<?> cadastrarAluno(@RequestBody @Valid AlunoDto alunoDto) {
		Aluno alunoSalvo = new Aluno();
		BeanUtils.copyProperties(alunoDto, alunoSalvo);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoSalvo));
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> getAllAlunos(){
		return ResponseEntity.ok().body(alunoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneAluno(@PathVariable Long id){
		Optional<Aluno> alunoOptional = alunoService.findById(id);
		if(!alunoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(alunoOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAluno(@PathVariable Long id){
		Optional<Aluno> alunoOptional = alunoService.findById(id);
		if(!alunoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado");
		}
		alunoService.delete(alunoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarAluno(@PathVariable Long id, 
												 @RequestBody @Valid AlunoDto alunoDto ){
		Optional<Aluno> alunoOptional = alunoService.findById(id);
		if(!alunoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado");
		}
		Aluno alunoAtualizado = new Aluno();
		BeanUtils.copyProperties(alunoDto, alunoAtualizado);
		alunoAtualizado.setId(alunoOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(alunoService.save(alunoAtualizado));
	}
}