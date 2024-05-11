package com.rederson.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rederson.cursomc.domain.Pedido;
import com.rederson.cursomc.repositories.PedidoRepository;
import com.rederson.cursomc.services.exceptions.ObjectNotFoundExceptions;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {

		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	}
