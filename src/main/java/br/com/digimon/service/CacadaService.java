package br.com.digimon.service;

import br.com.digimon.domain.CacadaEntity;
import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.TempoDisponivelCacadaEntity;
import br.com.digimon.repository.CacadaRepository;
import br.com.digimon.repository.TempoDisponivelCacadaRepository;
import br.com.digimon.utils.Data;
import br.com.digimon.utils.Feriados;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CacadaService {

    private CacadaRepository cacadaRepository;
    private TempoDisponivelCacadaRepository tempoDisponivelRepository;
    private DigimonService digimonService;

    public CacadaService(CacadaRepository cacadaRepository, TempoDisponivelCacadaRepository tempoDisponivelRepository, DigimonService digimonService) {
        this.cacadaRepository = cacadaRepository;
        this.tempoDisponivelRepository = tempoDisponivelRepository;
        this.digimonService = digimonService;
    }

    public Map<String, Object> carregarTelaCacada(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        DigimonEntity digimon = digimonService.getDigimonById(idDigimon);

        // Chama os métodos existentes e adiciona os resultados ao response
        response.putAll(carregarDadosCacada(idDigimon));
//        response.put("nivel", digimon.getNivel());
//        response.put("vida", digimon.getAtributos().getPontosVida());
//        response.put("energia", digimon.getAtributos().getPontosEnergia());

        return response;
    }

    public Map<String, Object> carregarDadosCacada(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("tempoDisponivel", verificarTempoDisponivelCacada(idDigimon));
        response.put("emAndamento", verificarCacadaEmAndamento(idDigimon));
        response.put("horaResgate", buscarTempoResgateCacadaEmAndamento(idDigimon));
        response.put("resgateDisponivel", validarResgateDisponivelCacada(idDigimon));

        return response;
    }

    public int verificarTempoDisponivelCacada(Long idDigimon) {
        if (!digimonService.verificarExistenciaDigimon(idDigimon)) {
            throw new RuntimeException("Digimon não encontrado");
        }
        LocalDate hoje = LocalDate.now();
        Optional<TempoDisponivelCacadaEntity> registro = tempoDisponivelRepository.findByIdDigimonAndDataCadastro(idDigimon, hoje);

        // Verifica se o registro está vazio ou se hoje é final de semana
        if (registro.isEmpty() || Data.validarFinalDeSemana(hoje)) {
            int tempoDisponivel = (Feriados.isFeriado(hoje) || Data.validarFinalDeSemana(hoje)) ? 120 : 60;

            // Salva o novo registro se necessário
            if (registro.isEmpty()) {
                tempoDisponivelRepository.save(new TempoDisponivelCacadaEntity(idDigimon, hoje, tempoDisponivel));
            }
            return tempoDisponivel;
        }

        // Retorna o tempo disponível do registro existente
        return registro.get().getTempoDisponivel();
    }

    public boolean verificarCacadaEmAndamento(Long idDigimon) {
        return cacadaRepository.existsByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
    }

    public String buscarTempoResgateCacadaEmAndamento(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            return "Não há caçada em andamento";
        }
        CacadaEntity cacada = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        String dataFormatada = Data.formatarDataCadastroParaTelaStatus(cacada.getHoraResgateDisponivel());
        return dataFormatada;
    }

    public boolean validarResgateDisponivelCacada(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            return false;
        }
        CacadaEntity cacada = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        LocalDateTime horaResgateDisponivel = cacada.getHoraResgateDisponivel();
        return LocalDateTime.now().isAfter(horaResgateDisponivel);
    }
}
