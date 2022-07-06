package br.com.goldsgym.cadastro.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.goldsgym.cadastro.model.Aluno;
import br.com.goldsgym.cadastro.model.dto.AlunoDto;

@Component
public class AlunoMapper {

	public AlunoDto toDto(Aluno aluno) {
		AlunoDto alunoDto = new AlunoDto();
		alunoDto.setId(aluno.getId());
		alunoDto.setNome(aluno.getNome());
		alunoDto.setCpf(aluno.getCpf());
		alunoDto.setTelefone(aluno.getTelefone());
		return alunoDto;
	}

	public Aluno toEntity(AlunoDto alunoDto) {
		Aluno aluno = new Aluno();
		aluno.setId(alunoDto.getId());
		aluno.setNome(alunoDto.getNome());
		aluno.setCpf(alunoDto.getCpf());
		aluno.setTelefone(alunoDto.getTelefone());
		return aluno;
	}

	List<Aluno> toEntity(List<AlunoDto> alunosDto) {
		return alunosDto.stream()
						.map(alunoDto -> this.toEntity(alunoDto))
						.collect(Collectors.toList());
	}

	List<AlunoDto> toDto(List<Aluno> alunos) {
		return alunos.stream()
					 .map(aluno -> this.toDto(aluno))
					 .collect(Collectors.toList());
	}
}