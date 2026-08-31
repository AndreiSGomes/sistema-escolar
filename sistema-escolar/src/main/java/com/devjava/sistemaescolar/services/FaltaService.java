package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Falta;
import com.devjava.sistemaescolar.repositories.AlunoRepository;
import com.devjava.sistemaescolar.repositories.FaltaRepository;

@Service
public class FaltaService {

	private final FaltaRepository faltaRepository;
	private final AlunoRepository alunoRepository;
	
	public FaltaService(FaltaRepository faltaRepository, AlunoRepository alunoRepository) {
		this.faltaRepository = faltaRepository;
		this.alunoRepository = alunoRepository;
	}
	
	
	public Falta insert(Falta obj) {
		Optional<Aluno> aluno = alunoRepository.findById(obj.getId());
				
		Falta falta = new Falta();
		falta.setData(obj.getData());
		falta.setJustificada(obj.getJustificada());
		falta.setAluno(aluno.get());
		
		return faltaRepository.save(falta);
	}
	
	public Falta findById(Integer id) {
		Optional<Falta> obj = faltaRepository.findById(id);
		return obj.get();
	}
	
	public List<Falta> findAll() {
		List<Falta> faltas = faltaRepository.findAll();
		return faltas;
	}
}
