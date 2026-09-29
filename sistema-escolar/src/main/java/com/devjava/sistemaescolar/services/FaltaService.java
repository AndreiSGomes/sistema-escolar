package com.devjava.sistemaescolar.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Falta;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
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
	
	public Falta salvar(Falta obj) {
		Aluno aluno = alunoRepository.findById(obj.getAluno().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getAluno().getId()));
		
		Falta falta = new Falta();
		falta.setData(obj.getData());
		falta.setJustificada(obj.getJustificada());
		falta.setAluno(aluno);
		
		return faltaRepository.save(falta);
	}
	
	public Falta buscarPorId(Integer id) {
		Falta falta = faltaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		return falta;
	}
	
	public List<Falta> buscarTodos() {
		List<Falta> faltas = faltaRepository.findAll();
		return faltas;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		faltaRepository.deleteById(id);
	}
	
	public Falta atualizar(Integer id, Falta obj) {
		Falta falta = buscarPorId(id);
		Aluno aluno = alunoRepository.findById(obj.getAluno().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getAluno().getId()));
		
		falta.setData(obj.getData());
		falta.setJustificada(obj.getJustificada());
		falta.setAluno(aluno);
		
		return faltaRepository.save(falta);
	}
}
