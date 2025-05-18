package br.com.digimon.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Feriados {
    public static List<LocalDate> getFeriadosFixos(int ano) {
        return Arrays.asList(
                LocalDate.of(ano, 1, 1),   // Ano Novo
                LocalDate.of(ano, 4, 21),  // Tiradentes
                LocalDate.of(ano, 5, 1),   // Dia do Trabalho
                LocalDate.of(ano, 9, 7),   // Independência do Brasil
                LocalDate.of(ano, 10, 12), // Nossa Senhora Aparecida
                LocalDate.of(ano, 11, 2),  // Finados
                LocalDate.of(ano, 11, 15), // Proclamação da República
                LocalDate.of(ano, 11,20), // Dia da Consciência Negra
                LocalDate.of(ano, 12, 25)  // Natal
        );
    }

    public static LocalDate calcularPascoa(int ano) {
        // Algoritmo para calcular a data da Páscoa
        int a = ano % 19;
        int b = ano / 100;
        int c = ano % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        return LocalDate.of(ano, month, day);
    }

    public static List<LocalDate> getFeriadosMoveis(int ano) {
        LocalDate pascoa = calcularPascoa(ano);
        return Arrays.asList(
                pascoa.minusDays(48), // Carnaval (48 dias antes da Páscoa)
                pascoa.minusDays(2),  // Sexta-feira Santa (2 dias antes da Páscoa)
                pascoa.plusDays(60)   // Corpus Christi (60 dias após a Páscoa)
        );
    }

    public static boolean isFeriado(LocalDate data) {
        int ano = data.getYear();
        List<LocalDate> feriadosFixos = getFeriadosFixos(ano);
        List<LocalDate> feriadosMoveis = getFeriadosMoveis(ano);

        return feriadosFixos.contains(data) || feriadosMoveis.contains(data);
    }
}
