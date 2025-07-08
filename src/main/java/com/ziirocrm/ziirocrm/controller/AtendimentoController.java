package com.ziirocrm.ziirocrm.controller;

import com.ziirocrm.ziirocrm.model.Atendimento;
import com.ziirocrm.ziirocrm.model.Oportunidade;
import com.ziirocrm.ziirocrm.repository.AtendimentoRepository;
import com.ziirocrm.ziirocrm.repository.OportunidadeRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/oportunidades/{oportunidadeId}/atendimentos")
public class AtendimentoController {

    private final AtendimentoRepository atendimentoRepository;
    private final OportunidadeRepository oportunidadeRepository;

    public AtendimentoController(AtendimentoRepository atendimentoRepository,
            OportunidadeRepository oportunidadeRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.oportunidadeRepository = oportunidadeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Atendimento>> listar(@PathVariable Long oportunidadeId) {
        if (!oportunidadeRepository.existsById(oportunidadeId)) {
            return ResponseEntity.notFound().build();
        }
        List<Atendimento> atendimentos = atendimentoRepository.findByOportunidadeId(oportunidadeId);
        return ResponseEntity.ok(atendimentos);
    }

    @PostMapping
    public ResponseEntity<Atendimento> adicionar(
            @PathVariable Long oportunidadeId,
            @Valid @RequestBody Atendimento atendimento) {
        return oportunidadeRepository.findById(oportunidadeId)
                .map(oportunidade -> {
                    atendimento.setOportunidade(oportunidade);
                    atendimento.setData(LocalDateTime.now());
                    return ResponseEntity.ok(atendimentoRepository.save(atendimento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // (Opcional) Remover um atendimento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long oportunidadeId, @PathVariable Long id) {
        if (!oportunidadeRepository.existsById(oportunidadeId)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Atendimento> atendimentoOptional = atendimentoRepository.findById(id);

        if (atendimentoOptional.isPresent()) {
            atendimentoRepository.delete(atendimentoOptional.get());
            return ResponseEntity.noContent().build(); // já retorna ResponseEntity<Void>
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}