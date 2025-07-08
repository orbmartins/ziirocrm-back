package com.ziirocrm.ziirocrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    private String responsavel;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "oportunidade_id")
    @JsonBackReference
    private Oportunidade oportunidade;
}