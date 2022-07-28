package br.com.falcon.controller;




import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.falcon.domain.Note;
import br.com.falcon.requests.NotePostRequestBody;
import br.com.falcon.requests.NotePutRequestBody;
import br.com.falcon.service.NoteService;
import br.com.falcon.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("notes")
@Log4j2
@RequiredArgsConstructor
public class NoteController {
    private final DateUtil dateUtil;
    
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(noteService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Note> findById(@PathVariable long id){
        return ResponseEntity.ok(noteService.findByIdOrThrowBadRequestException(id));
    }
    
    /*
    @GetMapping(path = "/find/{descricao}")
    public ResponseEntity<List<Note>> findByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok(noteService.findByDescricao(descricao));
    }
    */
    ///*
    @GetMapping(path = "/find") //teste ->  localhost:8080/notes/find?descricao=vaio_15
    public ResponseEntity<List<Note>> findByDescricao(@RequestParam String descricao){
        return ResponseEntity.ok(noteService.findByDescricao(descricao));
    }
   // */
    
    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Note> save(@RequestBody @Valid NotePostRequestBody notePostRequestBody){
    	return new ResponseEntity<>(noteService.save(notePostRequestBody), HttpStatus.CREATED);
    	
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
    	noteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody NotePutRequestBody notePutRequestBody) {
        //log.info("PUT "+ dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
    	noteService.replace(notePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
    
    

