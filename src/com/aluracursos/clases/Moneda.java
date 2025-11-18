package com.aluracursos.clases;

public record Moneda(
        String result,
        String base_code,
        String target_code,
        double conversion_rate
) { }
