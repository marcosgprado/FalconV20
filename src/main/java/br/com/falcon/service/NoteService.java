package br.com.falcon.service;



import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.falcon.domain.Note;
import br.com.falcon.exception.BadRequestException;
import br.com.falcon.repository.NoteRepository;
import br.com.falcon.requests.NotePostRequestBody;
import br.com.falcon.requests.NotePutRequestBody;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteService {
    
	private final NoteRepository noteRepository;
	
	
	//Array para represntar o BD temporariamente.
	/*
	private static List<Note> notes;
    
    static {
    	notes = new ArrayList<>( List.of(new Note(1L,"ACER14","notebook", 4500.00, 10, "corei5", 16, 500),
                                         new Note(2L,"DELL15","notebook", 7500.00, 10, "corei7", 8, 1000),
                                         new Note(3L,"VAIO15","notebook", 8500.00, 10, "corei7", 8, 1000),
                                         new Note(4L,"MACBOOK15","notebook", 20500.00, 10, "corei7", 16, 1000)));
    } 
    */
	
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    
    public List<Note> findByDescricao(String descricao) {
        return noteRepository.findByDescricao(descricao);
    }
    
    
    public Note findByIdOrThrowBadRequestException(long id) {
        return noteRepository.findById(id)
        		.orElseThrow(() -> new BadRequestException("_ Note not Found _"));

        		//.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Note not Found"));
        	
        
        	/*notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Note not Found"));
             */
    }
    @Transactional
    public Note save(NotePostRequestBody notePostRequestBody) {
    	
          
    	return noteRepository.save(Note.builder()
    			                       .descricao(notePostRequestBody.getDescricao())
    			                       .categoria(notePostRequestBody.getCategoria())
    			                       .preco(notePostRequestBody.getPreco())
    			                       .quantidade(notePostRequestBody.getQuantidade())
    			                       .processador(notePostRequestBody.getProcessador())
    			                       .memoriaRam(notePostRequestBody.getMemoriaRam())
    			                       .capacidadeSSD(notePostRequestBody.getCapacidadeSSD())
    			                       .build());
    	
    	//if(true)
    		//throw new RuntimeException("bed code");
    	
    	//return x;
    	
    	
    	/*
    	note.setId(ThreadLocalRandom.current().nextLong(10, 1000));
    	notes.add(note);
    	return note;
    	*/
    	
    }
    
    public void delete(long id) {
    	noteRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(NotePutRequestBody notePutRequestBody) {
    	Note savedNote = findByIdOrThrowBadRequestException(notePutRequestBody.getId());
    	
    	Note note = Note.builder()
    	        .id(savedNote.getId()) 
                .descricao(notePutRequestBody.getDescricao())
                .categoria(notePutRequestBody.getCategoria())
                .preco(notePutRequestBody.getPreco())
                .quantidade(notePutRequestBody.getQuantidade())
                .processador(notePutRequestBody.getProcessador())
                .memoriaRam(notePutRequestBody.getMemoriaRam())
                .capacidadeSSD(notePutRequestBody.getCapacidadeSSD())
                .build();
    			
    			noteRepository.save(note);
    }
       
    
    
    }
