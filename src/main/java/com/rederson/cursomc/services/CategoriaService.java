package com.rederson.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rederson.cursomc.domain.Categoria;
import com.rederson.cursomc.repositories.CategoriaRepository;
import com.rederson.cursomc.services.exceptions.ObjectNotFoundExceptions;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {

		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	}
