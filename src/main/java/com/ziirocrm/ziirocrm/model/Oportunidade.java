package com.ziirocrm.ziirocrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    private String email;

    private String telefone;

    private String empresa;

    private String status; // Ex: "novo", "em andamento", "fechado"

    private LocalDateTime dataCriacao;

    private String observacoes;

    @OneToMany(mappedBy = "oportunidade", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Atendimento> atendimentos;
}