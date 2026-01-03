package br.orfeu.music.service.impl;

import br.orfeu.music.model.Faixa;
import br.orfeu.music.repository.FaixaRepository;
import br.orfeu.music.service.FaixaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaixaServiceImpl implements FaixaService {

    private final FaixaRepository faixaRepository;

    public FaixaServiceImpl(FaixaRepository faixaRepository) {
        this.faixaRepository = faixaRepository;
    }

    @Override
    public List<Faixa> listar() {
        return faixaRepository.findAll();
    }

    @Override
    public Faixa buscarPorId(Long id) {
        return faixaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faixa não encontrada"));
    }

    @Override
    public Faixa criar(Faixa faixa) {
        return faixaRepository.save(faixa);
    }

    @Override
    public Faixa atualizar(Long id, Faixa faixa) {
        Faixa existente = buscarPorId(id);
        existente.setTitulo(faixa.getTitulo());
        existente.setDuracao(faixa.getDuracao());
        existente.setAlbum(faixa.getAlbum());
        existente.setCreditos(faixa.getCreditos());
        return faixaRepository.save(existente);
    }

    @Override
    public void remover(Long id) {
        faixaRepository.deleteById(id);
    }
}

