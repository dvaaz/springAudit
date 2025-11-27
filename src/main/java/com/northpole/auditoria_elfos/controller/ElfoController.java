//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.northpole.auditoria_elfos.controller;

import com.northpole.auditoria_elfos.entity.*;
import com.northpole.auditoria_elfos.service.AuditoriaService;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.history.Revisions;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/elfos"})
public class ElfoController {
    private final AuditoriaService service;

    public ElfoController(AuditoriaService service) {
		    this.service = service;
    }

    @PostMapping({"/criar-pedido"})
    @Tag(name="Criar Pedido")
    public Crianca criarPedido(@RequestBody CriancaDTO crianca) {
        return this.service.registrarPedido(crianca);
    }

    // Forma simples de fazer toda a fiscalização
    @PutMapping({"/fiscalizacao-geral"})
    @Tag(name="Fiscalizar Todos", description = "Fiscalização de todo o grupo")
    public void fiscalizarTodaLista() { this.service.fiscalizarTodaLista();}

    @PatchMapping({"/{id}/atualizar"})
    @Tag(name="Atualizar dados", description="Atualiza comportamento e presente")
    public Crianca atualizar(@PathVariable Long id, @RequestBody ComportamentoDTO comportamento){
        return this.service.fiscalizarComportamento(id, comportamento.bemComportada());
    }

    @GetMapping({"/{id}/historico"})
    @Tag(name="Histórico", description="Demonstrativo de toda as informações referentes a revisao")
    public Revisions<Long, Crianca> verHistorico(@PathVariable Long id) {
		    return this.service.obterHistorico(id);
    }


    @GetMapping("/{id}/monitoramento")
    @Tag(name="Monitoramento", description = "Apresenta o presente atual e sua data")
    public HistoricoSimplesDTO monitorar(@PathVariable Long id) {
        return service.monitorarMudancas(id);
    }
    @GetMapping("/{id}/rastreamento")
    @Tag(name="Histórico de Alterações", description = "Demonstra de forma simplificada os presentes da criança")
    public HistoricoCompletoDTO rastrear(@PathVariable Long id) {
        return service.rastrearPresentes(id);
    }

    @GetMapping({"/lista"})
    @Tag(name="Lista Atual de Presentes")
    public List<Crianca> verLista() {
        return this.service.verLista();
    }

}
