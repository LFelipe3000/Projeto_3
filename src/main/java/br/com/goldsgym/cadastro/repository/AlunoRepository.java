package br.com.goldsgym.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.goldsgym.cadastro.modelo.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public default boolean isAlunoPresent(Long id) {
		Optional<Aluno> alunoOptional = findById(id);
		if (!alunoOptional.isPresent()) {
			return false;
		}
		return true;
	}
	
}