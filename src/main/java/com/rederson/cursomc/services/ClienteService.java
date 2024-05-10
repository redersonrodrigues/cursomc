package com.rederson.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rederson.cursomc.domain.Cliente;
import com.rederson.cursomc.repositories.ClienteRepository;
import com.rederson.cursomc.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	}
