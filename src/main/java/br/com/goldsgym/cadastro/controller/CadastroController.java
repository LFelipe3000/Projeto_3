package br.com.goldsgym.cadastro.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	@ResponseBody
	public Aluno cadastrarAluno(@RequestBody @Valid Aluno alunoSalvo) {
		return alunoService.save(alunoSalvo);
	}
	
	@GetMapping
	@ResponseBody
	public List<Aluno> getAllAlunos(){
		return alunoService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Aluno getOneAluno(@PathVariable Long id){
	Optional<Aluno> alunoOptional = alunoService.findById(id);
		return alunoOptional.get();
	}	
		
	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteAluno(@PathVariable Long id){
		alunoService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public Object atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoDto alunoDto ){
		Aluno alunoAtualizado = alunoService.setDtoToObject(id, alunoDto);
		return alunoService.save(alunoAtualizado);
	}
}