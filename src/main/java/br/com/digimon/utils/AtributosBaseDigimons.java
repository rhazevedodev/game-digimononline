package br.com.digimon.utils;


import br.com.digimon.domain.AtributosBaseEntity;
import br.com.digimon.domain.enums.EnumAtributosBase;
import br.com.digimon.domain.enums.EnumDigimonRookie;

public class AtributosBaseDigimons {

    public static AtributosBaseEntity definirAtributosBaseDigimonsRookies(AtributosBaseEntity atributosBase, EnumDigimonRookie rookie) {
        EnumAtributosBase atributosBaseDigimon = EnumAtributosBase.valueOf(rookie.name());

        return new AtributosBaseEntity(
                atributosBaseDigimon.getBaseResistencia(),
                atributosBaseDigimon.getBaseVida(),
                atributosBaseDigimon.getBaseEnergia(),
                atributosBaseDigimon.getBaseForca(),
                atributosBaseDigimon.getBaseInteligencia(),
                atributosBaseDigimon.getBaseAgilidade(),
                atributosBaseDigimon.getBaseConhecimento()
        );
    }

    public static AtributosBaseEntity definirAtributosBaseDigimonsDigimonsBaby1(AtributosBaseEntity atributosBase, String digiBaby1) {

        GetByJsonUtils.carregarAtributosBaseDigimonsBaby1()
                .getDigimonsBaby1()
                .stream()
                .filter(digimon -> digimon.getNome().equals(digiBaby1))
                .findFirst()
                .ifPresent(digimon -> {
                    atributosBase.setBaseVida(digimon.getBaseVida());
                    atributosBase.setBaseEnergia(digimon.getBaseEnergia());
                    atributosBase.setBaseForca(digimon.getBaseForca());
                    atributosBase.setBaseInteligencia(digimon.getBaseInteligencia());
                    atributosBase.setBaseAgilidade(digimon.getBaseAgilidade());
                    atributosBase.setBaseConhecimento(digimon.getBaseConhecimento());
                    atributosBase.setBaseResistencia(digimon.getBaseResistencia());
                });

        return new AtributosBaseEntity(
                atributosBase.getBaseVida(),
                atributosBase.getBaseEnergia(),
                atributosBase.getBaseResistencia(),
                atributosBase.getBaseForca(),
                atributosBase.getBaseInteligencia(),
                atributosBase.getBaseAgilidade(),
                atributosBase.getBaseConhecimento()
        );
    }
}
