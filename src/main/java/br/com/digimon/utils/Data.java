package br.com.digimon.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data {

    public static String formatarDataCadastroParaTelaStatus(LocalDateTime data)  {
        // Define o formato da string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        // Formata a data conforme necessário
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String dataFormatada = data.format(formatoDesejado);
        return dataFormatada;
    }

    public static boolean validarFinalDeSemana(LocalDate date){
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }


}
