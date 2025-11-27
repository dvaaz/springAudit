//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.northpole.auditoria_elfos.service;

import com.northpole.auditoria_elfos.audit.ElfoRevisionEntity;
import com.northpole.auditoria_elfos.entity.*;
import com.northpole.auditoria_elfos.entity.HistoricoSimplesDTO;
import com.northpole.auditoria_elfos.repository.CriancaRepository;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditoriaService {
    @Autowired
    private CriancaRepository repository;

    public AuditoriaService() {
    }

    public Crianca registrarPedido(CriancaDTO dtoRequest) {
        Crianca nova = new Crianca();
        nova.setNome(dtoRequest.nome());
        nova.setPresente(dtoRequest.presente());
        nova.setBemComportada(dtoRequest.bemComportada());
        Crianca save = (Crianca)this.repository.save(nova);
        return (Crianca)this.repository.save(save);
    }


    // Para fazer uma checagem geral
    @Transactional
    public void fiscalizarTodaLista() {
        List<Crianca> lista = this.repository.findAll();
        Iterator var2 = lista.iterator();

        while(var2.hasNext()) {
            Crianca crianca = (Crianca)var2.next();
            if (!crianca.isBemComportada()) {
                crianca.setPresente("Picolé de Bacalhau");
            }
        }

        this.repository.saveAll(lista);
    }

    public List<Crianca> verLista() {
        return this.repository.findAll();
    }

    public Revisions<Long, Crianca> obterHistorico(Long id) {
        return this.repository.findRevisions(id);
    }

    @Transactional
    public int salvarTodas(List<Crianca> criancas) {
        List<Crianca> lista = this.repository.saveAll(criancas);
        int qtdCriancas = lista.size();
        return qtdCriancas;
    }
    
    // Return de simples Leitura
    public HistoricoSimplesDTO monitorarMudancas(Long id) {

        Revisions<Long, Crianca> revisoes = repository.findRevisions(id);

        if (revisoes.isEmpty()) {
            throw new RuntimeException("Histórico não encontrado para o ID: " + id);
        }

        // 1. Converte o Revisions (Iterable) para uma Lista
        List<Revision<Long, Crianca>> listaRevisoes = StreamSupport
                .stream(revisoes.spliterator(), false)
                .collect(Collectors.toList());

        // 2. Obtém a última revisão (que é o último item da lista)
        int ultimoIndice = listaRevisoes.size() - 1;
        Revision<Long, Crianca> ultimaRevisao = listaRevisoes.get(ultimoIndice);

        // 3. Mapeia os dados para o DTO simples (o restante é igual)
        String nome = ultimaRevisao.getEntity().getNome();
        String presente = ultimaRevisao.getEntity().getPresente();

        // Converte Instant para LocalDateTime para melhor representação no JSON
        LocalDateTime data = ultimaRevisao.getMetadata().getRevisionInstant()
                .orElseThrow()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // 4. Retorna o DTO
        return new HistoricoSimplesDTO(nome, presente, data);
    }

    /**
     * Método que retorna o histórico completo de alterações do presente.
     * @param id ID da criança.
     * @return DTO com a lista de presentes e datas de alteração.
     */
    public HistoricoCompletoDTO rastrearPresentes(Long id) {

        // 1. Busca todas as revisões
        Revisions<Long, Crianca> revisoes = repository.findRevisions(id);

        if (revisoes.isEmpty()) {
            throw new RuntimeException("Histórico não encontrado para o ID: " + id);
        }

        // Converte Revisions para List para facilitar o processamento
        List<Revision<Long, Crianca>> listaRevisoes = StreamSupport
                .stream(revisoes.spliterator(), false)
                .collect(Collectors.toList());

        // 2. Mapeia a lista de Revisões para a lista de RevisaoDTO
        List<RevisaoDTO> historicoSimplificado = listaRevisoes.stream()
                .map(revision -> {
										// Obtem a entidade de Revisao
		                ElfoRevisionEntity revEntity = (ElfoRevisionEntity) revision.getMetadata().getDelegate();
                    // Mapeia os campos da Entidade e da Metadata para o DTO
                    String presente = revision.getEntity().getPresente();
		                // Extrai o nome do Elfo (Poderia ser outro dado)
										String elfo = revEntity.getElfoResponsavel();

                    // Converte Instant para LocalDateTime (como fizemos antes)
                    LocalDateTime data = revision.getMetadata().getRevisionInstant()
                            .orElseThrow()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();

                    return new RevisaoDTO(presente, data, elfo);
                })
                .collect(Collectors.toList());

        // 3. Obtém o estado atual (último registro da lista) para o DTO principal
        Revision<Long, Crianca> ultimaRevisao = listaRevisoes.get(listaRevisoes.size() - 1);

        String nome = ultimaRevisao.getEntity().getNome();
        boolean comportada = ultimaRevisao.getEntity().isBemComportada();

        // 4. Retorna o DTO completo
        return new HistoricoCompletoDTO(nome, comportada, historicoSimplificado);
    }

    /**
     * Auxiliar: Busca o presente que foi registrado na Revisão 1 (INSERT).
     */
    private String obterPresenteOriginal(Long id) {
        // 1. Busca todas as revisões
        Revisions<Long, Crianca> revisoes = repository.findRevisions(id);

        if (revisoes.isEmpty()) {
            // Nunca deve acontecer após a inserção, mas é uma boa prática defensiva
            throw new RuntimeException("Histórico não encontrado. Criança sem registro inicial.");
        }

        // 2. Converte para lista para acessar o primeiro elemento (Índice 0)
        List<Revision<Long, Crianca>> listaRevisoes = StreamSupport
                .stream(revisoes.spliterator(), false)
                .collect(Collectors.toList());

        // 3. Pega a primeira revisão (onde o presente foi inserido)
        Revision<Long, Crianca> primeiraRevisao = listaRevisoes.get(0);

        return primeiraRevisao.getEntity().getPresente();
    }

    /**
     * Método principal para fiscalizar e reverter o presente se a criança se comportar.
     */
    @Transactional
    public Crianca fiscalizarComportamento(Long id, boolean bomComportamento) {
        Crianca crianca = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Criança não encontrada"));

        crianca.setBemComportada(bomComportamento);

        if (!bomComportamento) {
            // Regra A: Se a criança for MÁ, o presente é "Bacalhau"
            crianca.setPresente("Picolé de Bacalhau ");
            System.out.println("⚠️ ALERTA! " + crianca.getNome() + " receberá Picolé de Bacalhau.");

        } else {
            // Regra B: Se a criança for BOA, REVERTE o presente para o desejo original.
            String presenteOriginal = obterPresenteOriginal(id);
            crianca.setPresente(presenteOriginal);
            System.out.println("🎉 BOAS NOTÍCIAS! O presente de " + crianca.getNome() + " foi restaurado para " + presenteOriginal + ".");
        }

        return repository.save(crianca);
    }
}
