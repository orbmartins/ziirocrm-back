package com.ziirocrm.ziirocrm.controller;

import com.ziirocrm.ziirocrm.model.Oportunidade;
import com.ziirocrm.ziirocrm.repository.OportunidadeRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/oportunidades")
public class OportunidadeController {

    private final OportunidadeRepository oportunidadeRepository;

    public OportunidadeController(OportunidadeRepository oportunidadeRepository) {
        this.oportunidadeRepository = oportunidadeRepository;
    }

    @GetMapping
    public List<Oportunidade> listar() {
        return oportunidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
        return oportunidadeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Oportunidade> criar(@Valid @RequestBody Oportunidade oportunidade) {
        oportunidade.setDataCriacao(LocalDateTime.now());
        return ResponseEntity.ok(oportunidadeRepository.save(oportunidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oportunidade> atualizar(@PathVariable Long id, @RequestBody Oportunidade nova) {
        return oportunidadeRepository.findById(id)
                .map(o -> {
                    o.setNome(nova.getNome());
                    o.setEmail(nova.getEmail());
                    o.setTelefone(nova.getTelefone());
                    o.setEmpresa(nova.getEmpresa());
                    o.setStatus(nova.getStatus());
                    o.setObservacoes(nova.getObservacoes());
                    return ResponseEntity.ok(oportunidadeRepository.save(o));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!oportunidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        oportunidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}