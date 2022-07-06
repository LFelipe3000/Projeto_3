package br.com.goldsgym.cadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldsgym.cadastro.model.dto.AlunoDto;
import br.com.goldsgym.cadastro.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class CadastroController {

	@Autowired
	private AlunoService alunoService;
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public AlunoDto cadastrarAluno(@RequestBody @Valid AlunoDto alunoSalvo) {
		return alunoService.save(alunoSalvo);	
	}
	
	@GetMapping
	@ResponseBody
	public Page<AlunoDto> findPaginated(@RequestParam (defaultValue = "0") int pageNo, 
			 						 	@RequestParam (defaultValue = "10") int pageSize) {
		return alunoService.findPaginated(pageNo, pageSize);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public AlunoDto getOneAluno(@PathVariable Long id){
	AlunoDto alunoDto = alunoService.findById2(id);
		return alunoDto;
	}	
		
	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteAluno(@PathVariable Long id){
		alunoService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public AlunoDto atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoDto alunoDto){
		AlunoDto alunoAtualizadoDto = alunoService.atualizarAluno(id, alunoDto);
		return alunoAtualizadoDto;
	}
}