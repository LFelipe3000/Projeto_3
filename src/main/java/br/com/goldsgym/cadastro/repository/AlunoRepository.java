package br.com.goldsgym.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.goldsgym.cadastro.modelo.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{}