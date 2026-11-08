package com.example.concessionariacrud.controller;

import com.example.concessionariacrud.model.Veiculo;
import com.example.concessionariacrud.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    // Adicionar veículo (POST)
    @PostMapping
    public ResponseEntity<Veiculo> adicionarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.salvarVeiculo(veiculo);
        return ResponseEntity.ok(novoVeiculo);
    }

    // Visualizar todos os veículos (GET)
    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculos(@RequestParam(required = false) String marca,
            @RequestParam(required = false) String status) {
        List<Veiculo> veiculos = veiculoService.filtrarVeiculos(marca, status);
        return ResponseEntity.ok(veiculos);
    }

    // Visualizar veículo por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.buscarVeiculoPorId(id);
        return veiculo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Editar veículo (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
        if (veiculoAtualizado != null) {
            return ResponseEntity.ok(veiculoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Excluir veículo (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
