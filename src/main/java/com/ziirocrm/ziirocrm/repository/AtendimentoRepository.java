package com.ziirocrm.ziirocrm.repository;

import com.ziirocrm.ziirocrm.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByOportunidadeId(Long oportunidadeId);
}