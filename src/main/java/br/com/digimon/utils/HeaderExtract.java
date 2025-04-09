package br.com.digimon.utils;

import jakarta.servlet.http.HttpServletRequest;

public class HeaderExtract {

    public static String extrairTokenDoHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Remove "Bearer " do início
        }

        return null; // Ou lançar exceção se preferir
    }
}
