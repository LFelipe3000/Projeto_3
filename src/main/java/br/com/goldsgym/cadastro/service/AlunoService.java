package br.com.goldsgym.cadastro.service;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.goldsgym.cadastro.mapper.AlunoMapper;
import br.com.goldsgym.cadastro.model.Aluno;
import br.com.goldsgym.cadastro.model.dto.AlunoDto;
import br.com.goldsgym.cadastro.repository.AlunoRepository;

@Service
public class AlunoService {
	

	private final AlunoRepository alunoRepository;
	private final AlunoMapper alunoMapper;
	
	public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
		this.alunoRepository = alunoRepository;
		this.alunoMapper = alunoMapper;
	}

//  Method to register a client(POST)
	@Transactional
	public AlunoDto save(@RequestBody AlunoDto alunoDto){
		
		Aluno aluno = this.alunoMapper.toEntity(alunoDto);
		Aluno alunoSalvo = this.alunoRepository.save(aluno);
		
		return this.alunoMapper.toDto(alunoSalvo);
	}

//  Method to list all registered clients(GET w/ Paging)	
	public Page<AlunoDto> findPaginated(int pageNo, int pageSize) {
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("nome").ascending());
		Page<Aluno> pagedResult = alunoRepository.findAll(paging);
		Page<AlunoDto> pagedResultDto = pagedResult.map(aluno->this.alunoMapper.toDto(aluno));
		
		return pagedResultDto;
	}

//	Method to look for a specific client by id(GET(ID))
	public AlunoDto findById2(Long id) {
		
		Aluno aluno = this.alunoRepository.findById(id).orElseThrow(()->
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
		
		return this.alunoMapper.toDto(aluno);
	}

//  Method to delete a client(DELETE)		
	@Transactional
	public void deleteById(Long id) {
		
		Aluno aluno = this.alunoRepository.findById(id).orElseThrow(()->
		 	new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não cadastrado"));
		
		this.alunoRepository.delete(aluno);{}
	}
	
//  Method to change/update a registered client(PUT)		
	public AlunoDto atualizarAluno(@PathVariable Long id, @Valid AlunoDto alunoDto) {
		
		Aluno aluno = alunoRepository.findById(id).orElseThrow(()-> 
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não cadastrado"));
		
		aluno.setId(alunoDto.getId());
		aluno.setNome(alunoDto.getNome());
		aluno.setCpf(alunoDto.getCpf());
		aluno.setTelefone(alunoDto.getTelefone());
		
		Aluno alunoAtualizado = this.alunoRepository.save(aluno);
		AlunoDto alunoAtualizadoDto = this.alunoMapper.toDto(alunoAtualizado);
		
		return alunoAtualizadoDto;
	}
}