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
}
