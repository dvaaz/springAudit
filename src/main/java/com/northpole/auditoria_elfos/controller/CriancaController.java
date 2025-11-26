package com.northpole.auditoria_elfos.controller;

import com.northpole.auditoria_elfos.entity.Crianca;
import com.northpole.auditoria_elfos.service.CriancaService;
import org.springframework.data.history.Revisions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/criancas")
public class CriancaController {

    private final CriancaService criancaService;

    public CriancaController(CriancaService criancaService) {
        this.criancaService = criancaService;
    }

    // Criar nova criança (Auditada)
    @PostMapping
    public Crianca criarCrianca(@RequestBody Crianca crianca) {
        return criancaService.salvarCrianca(crianca);
    }

    // Atualizar comportamento/presente (Auditado)
    @PutMapping("/{id}")
    public ResponseEntity<Crianca> atualizarCrianca(@PathVariable Integer id, @RequestBody Crianca detalhes) {
        Optional<Crianca> criancaAtual = criancaService.buscarCrianca(id);

        if (criancaAtual.isPresent()) {
            Crianca c = criancaAtual.get();
            c.setCriancaComportamento(detalhes.getCriancaComportamento());
            
            // Simula uma mudança no Pedido também (se fornecido)
            if (detalhes.getPedido() != null) {
                 c.getPedido().setPedidoNome(detalhes.getPedido().getPedidoNome());
            }

            // O save() aciona o Envers e o ElfoRevisionListener!
            return ResponseEntity.ok(criancaService.salvarCrianca(c));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // **ENDPOINT DE AUDITORIA**
    @GetMapping("/{id}/historico")
    public Revisions<Integer, Crianca> getHistorico(@PathVariable Integer id) {
        // Retorna a lista de revisões, incluindo o Elfo e o timestamp.
        return criancaService.buscarHistorico(id);
    }
}