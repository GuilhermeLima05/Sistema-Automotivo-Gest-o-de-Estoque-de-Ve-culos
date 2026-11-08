package com.example.concessionariacrud.service;

import com.example.concessionariacrud.model.Veiculo;
import com.example.concessionariacrud.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Adicionar veículo
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    // Visualizar todos os veículos
    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    // Visualizar veículo por ID
    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    // Editar veículo
    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente.isPresent()) {
            Veiculo veiculo = veiculoExistente.get();
            veiculo.setMarca(veiculoAtualizado.getMarca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setAno(veiculoAtualizado.getAno());
            veiculo.setPreco(veiculoAtualizado.getPreco());
            veiculo.setStatus(veiculoAtualizado.getStatus());
            return veiculoRepository.save(veiculo);
        }
        return null; // Ou lance uma exceção
    }

    // Excluir veículo
    public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }

    // Filtrar veículos
    public List<Veiculo> filtrarVeiculos(String marca, String status) {
        return veiculoRepository.findByMarcaAndStatus(marca, status);
    }
}
