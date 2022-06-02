package br.com.goldsgym.cadastro.controller.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.goldsgym.cadastro.modelo.Aluno;
import br.com.goldsgym.cadastro.repository.AlunoRepository;

@Service
public class AlunoService {

	final AlunoRepository alunoRepository;
	
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	@Transactional
	public Aluno save(Aluno alunoSalvo) {
		return alunoRepository.save(alunoSalvo);
	}

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Optional<Aluno> findById(Long id) {
		return alunoRepository.findById(id);
	}

	@Transactional
	public void delete(Aluno aluno) {
	alunoRepository.delete(aluno);
	
	}
}