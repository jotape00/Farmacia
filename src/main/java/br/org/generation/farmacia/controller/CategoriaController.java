package br.org.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.org.generation.farmacia.model.Categoria;
import br.org.generation.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	private ResponseEntity<List<Categoria>> getAll(){
		
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Categoria> getById(@PathVariable long id){
		
		return categoriaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Categoria>> GetByTipo(@PathVariable String tipo){
		
		return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));	
	}
	
	@GetMapping("/descricao/{descricaoCategoria}")
	public ResponseEntity<List<Categoria>> GetByDescricao(@PathVariable String descricaoCategoria){
		
		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoCategoriaContainingIgnoreCase(descricaoCategoria));	
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria descricaoCategoria){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(descricaoCategoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria descricaoCategoria){
		
		return ResponseEntity.ok(categoriaRepository.save(descricaoCategoria));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {	
		categoriaRepository.deleteById(id);
	}
}
