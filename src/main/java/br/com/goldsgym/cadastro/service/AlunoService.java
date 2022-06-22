package br.com.goldsgym.cadastro.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import br.com.goldsgym.cadastro.modelo.Aluno;
import br.com.goldsgym.cadastro.modelo.dto.AlunoDto;
import br.com.goldsgym.cadastro.repository.AlunoRepository;

@Service
public class AlunoService {

	final AlunoRepository alunoRepository;
	//private AlunoService alunoService;

	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

//  Método para cadastrar um aluno(POST)
	@Transactional
	public AlunoDto save(@Valid AlunoDto alunoDto){
		Aluno alunoSalvo = new Aluno();
		BeanUtils.copyProperties(alunoDto, alunoSalvo);
		
		alunoSalvo = alunoRepository.save(alunoSalvo);
		BeanUtils.copyProperties(alunoSalvo, alunoDto);
		return alunoDto;
	}

//  Método para listar todos os alunos cadastrados(GET)	
	public List<Aluno> findAll(){
	 return alunoRepository.findAll();
	}

//	Método para buscar um determinado aluno pelo id(GET(ID))
	public Optional<Aluno> findById(Long id) {
		boolean isAlunoPresent = alunoRepository.isAlunoPresent(id);
		if (!isAlunoPresent) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
		}
		return alunoRepository.findById(id);
	}

//  Método para deletar um aluno(DELETE)		
	@Transactional
	public void deleteById(Long id) {
		boolean isAlunoPresent = alunoRepository.isAlunoPresent(id);
		if(!isAlunoPresent) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não cadastrado");
		}	
		alunoRepository.deleteById(id);
	}

//  Método para alterar/atualizar o cadastro de um aluno(PUT)		
	public Aluno setDtoToObject(@PathVariable Long id, @Valid AlunoDto alunoDto) {
		Optional<Aluno> alunoOptional = alunoRepository.findById(id);
		boolean isAlunoPresent = alunoRepository.isAlunoPresent(id);
		if(!isAlunoPresent) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não cadastrado");
		}
		Aluno alunoAtualizado = new Aluno();
		BeanUtils.copyProperties(alunoDto, alunoAtualizado);
		alunoAtualizado.setId(alunoOptional.get().getId());
		return alunoAtualizado;
	}

	public boolean isAlunoPresent(Long id) {
		Optional<Aluno> alunoOptional = findById(id);
		if (!alunoOptional.isPresent()) {
			return false;
		}
		return true;
	}

	public Aluno get(Long id) {
		return alunoRepository.getOne(id);
	}

}