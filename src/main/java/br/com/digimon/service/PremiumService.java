package br.com.digimon.service;

import br.com.digimon.domain.PremiumEntity;
import br.com.digimon.domain.dto.RequestAtivarPremiumDTO;
import br.com.digimon.repository.PremiumRepository;
import br.com.digimon.utils.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PremiumService {

    private final PremiumRepository premiumRepository;
    private final DigimonService digimonService;

    public PremiumService(PremiumRepository premiumRepository, DigimonService digimonService) {
        this.premiumRepository = premiumRepository;
        this.digimonService = digimonService;
    }

    public Map<String, Object> carregarInformacoesPremium(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.putAll(buscarPeriodoPremium(idDigimon));
        return response;
    }

    public String validarPremium(Long idDigimon) {
        Optional<PremiumEntity> premium = findLatestByIdDigimon(idDigimon);
        if (premium.isPresent()) {
            if (premium.get().getDataFim().isAfter(LocalDateTime.now())) {
                return "Ativo";
            }
        }
        return "Inativo";
    }

    public Map<String, Object> buscarPeriodoPremium(Long idDigimon) {
        Optional<PremiumEntity> premium = findLatestByIdDigimon(idDigimon);
        Map<String, Object> response = new LinkedHashMap<>();

        if (premium.isPresent()) {
            String dataInicio = Data.formatarDataCadastroParaTelaStatus(premium.get().getDataInicio());
            String dataFim = Data.formatarDataCadastroParaTelaStatus(premium.get().getDataFim());

            response.put("idDigimon", premium.get().getIdDigimon());
            response.put("dataInicio", dataInicio);
            response.put("dataFim", dataFim);
            response.put("status", validarPremium(idDigimon));
        } else {
            response.put("idDigimon", idDigimon);
            response.put("dataInicio", "Sem Premium Ativo");
            response.put("dataFim", "Sem Premium Ativo");
            response.put("status", validarPremium(idDigimon));
        }

        return response;
    }

    public Map<String, Object> ativarPremium(RequestAtivarPremiumDTO request) {
        LocalDateTime agora = LocalDateTime.now();
        String statusPremium = validarPremium(request.getIdDigimon());
        PremiumEntity premium;

        if (statusPremium.equals("Ativo")) {
            Optional<PremiumEntity> premiumOptional = findLatestByIdDigimon(request.getIdDigimon());

            if (premiumOptional.isPresent()) {
                premium = premiumOptional.get();
                premium.setDataFim(premium.getDataFim().plusDays(request.getPeriodoDias()));
            } else {
                premium = criarNovoPremium(request, agora);
            }
        } else premium = criarNovoPremium(request, agora);

        premiumRepository.save(premium);

        String dataInicio = Data.formatarDataCadastroParaTelaStatus(premium.getDataInicio());
        String dataFim = Data.formatarDataCadastroParaTelaStatus(premium.getDataFim());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("idDigimon", premium.getIdDigimon());
        response.put("dataInicio", dataInicio);
        response.put("dataFim", dataFim);

        return response;
    }

    private PremiumEntity criarNovoPremium(RequestAtivarPremiumDTO request, LocalDateTime agora) {
        PremiumEntity premium = new PremiumEntity();
        premium.setIdDigimon(request.getIdDigimon());
        premium.setTotalDias(request.getPeriodoDias());
        premium.setDataInicio(agora);
        premium.setDataFim(agora.plusDays(request.getPeriodoDias()));
        return premium;
    }

    private Optional<PremiumEntity> findLatestByIdDigimon(Long idDigimon) {
        return premiumRepository.findLatestByIdDigimon(idDigimon);
    }
}
