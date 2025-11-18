package com.aluracursos.clases;

import java.time.LocalDateTime;

public record Historial(
        String monedaOrigen,
        String monedaDestino,
        String cantidad,
        String tasa,
        String resultado,
        LocalDateTime fecha
) {
}
