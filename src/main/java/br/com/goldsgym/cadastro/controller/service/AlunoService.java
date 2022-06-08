package br.com.goldsgym.cadastro.controller.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.goldsgym.cadastro.controller.dto.AlunoDto;
import br.com.goldsgym.cadastro.modelo.Aluno;
import br.com.goldsgym.cadastro.repository.AlunoRepository;

@Service
public class AlunoService {

	final AlunoRepository alunoRepository;
	
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	@Transactional
	public Aluno save(Aluno alunoDto) {
		Aluno alunoSalvo = new Aluno();
		BeanUtils.copyProperties(alunoDto, alunoSalvo);
		return alunoRepository.save(alunoSalvo);
	}

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Optional<Aluno> findById(Long id) {
		return alunoRepository.findById(id);
	}

	@Transactional
	public void deleteById(Long id) {
		alunoRepository.deleteById(id);
	}

	public boolean isAlunoPresent(Long id) {
		Optional<Aluno> alunoOptional = findById(id);
		if(!alunoOptional.isPresent()) {
			return false;
		}
		return true;
	}

	public Object get(Long id) {
		return alunoRepository.getOne(id);
	}
	
	public Aluno setDtoToObject(@PathVariable Long id, @Valid AlunoDto alunoDto) {
		Optional<Aluno> alunoOptional = alunoRepository.findById(id);
		Aluno alunoAtualizado = new Aluno();
		BeanUtils.copyProperties(alunoDto, alunoAtualizado);
		alunoAtualizado.setId(alunoOptional.get().getId());
		return alunoAtualizado;
	}
	
}