package com.ziirocrm.ziirocrm.config;

import com.ziirocrm.ziirocrm.model.Atendimento;
import com.ziirocrm.ziirocrm.model.Oportunidade;
import com.ziirocrm.ziirocrm.repository.AtendimentoRepository;
import com.ziirocrm.ziirocrm.repository.OportunidadeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class TestDataLoader {

    @Bean
    public CommandLineRunner loadTestData(
            OportunidadeRepository oportunidadeRepository,
            AtendimentoRepository atendimentoRepository
    ) {
        return args -> {
            List<Oportunidade> oportunidades = new ArrayList<>();
            List<Atendimento> atendimentos = new ArrayList<>();

            for (int i = 1; i <= 50; i++) {
                Oportunidade oportunidade = Oportunidade.builder()
                        .nome("Cliente " + i)
                        .email("cliente" + i + "@email.com")
                        .telefone("1199999" + String.format("%04d", i))
                        .empresa("Empresa " + ((i % 10) + 1))
                        .status(i % 3 == 0 ? "Virou Cliente" : (i % 2 == 0 ? "Em negociação" : "Novo"))
                        .dataCriacao(LocalDateTime.now().minusDays(i))
                        .observacoes("Observação para cliente " + i)
                        .build();
                oportunidades.add(oportunidade);
            }

            oportunidadeRepository.saveAll(oportunidades);

            String[] responsaveis = {"Rodrigo", "Ana", "Carlos", "Joana", "Beatriz"};

            for (Oportunidade o : oportunidades) {
                int atendimentosPorOportunidade = ThreadLocalRandom.current().nextInt(1, 9); // de 1 a 8
                for (int j = 0; j < atendimentosPorOportunidade; j++) {
                    Atendimento atendimento = Atendimento.builder()
                            .descricao("Atendimento " + (j + 1) + " para " + o.getNome())
                            .responsavel(responsaveis[ThreadLocalRandom.current().nextInt(responsaveis.length)])
                            .data(o.getDataCriacao().plusDays(j + 1))
                            .oportunidade(o)
                            .build();
                    atendimentos.add(atendimento);
                }
            }

            atendimentoRepository.saveAll(atendimentos);

            System.out.println(">>> Dados de teste criados com sucesso: "
                    + oportunidades.size() + " oportunidades e "
                    + atendimentos.size() + " atendimentos.");
        };
    }
}
