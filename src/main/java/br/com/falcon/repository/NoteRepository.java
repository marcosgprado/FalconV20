package br.com.falcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.falcon.domain.Note;

public interface NoteRepository extends JpaRepository< Note, Long> {

	List<Note> findByDescricao(String descricao);
}
