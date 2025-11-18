package com.aluracursos.principal;

import com.aluracursos.clases.Conversor;
import com.aluracursos.clases.Historial;
import com.aluracursos.clases.Moneda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Creacion de un objeto escaner para poder hacer la entrada de datos por teclado
        // por parte del usuario
        var opcion = 0;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(','); // separador de miles
        symbols.setDecimalSeparator('.');  // separador decimal
        DecimalFormat df = new DecimalFormat("#,###.####", symbols);        Scanner entrada = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        boolean seguir = true;
        List<Historial> historialDeConversiones = new ArrayList<>();

        while(seguir){
            // creacion de un menu sencillo para el uso de la aplicación.
            System.out.println("Bievenido a esta herramienta para convertir divisas :D");
            System.out.println("*****************************************************");
            System.out.println("** 1. USD ==> ARS                                  **");
            System.out.println("** 2. ARS ==> USD                                  **");
            System.out.println("** 3. USD ==> BRL                                  **");
            System.out.println("** 4. BRL ==> USD                                  **");
            System.out.println("** 5. USD ==> COP                                  **");
            System.out.println("** 6. COP ==> USD                                  **");
            System.out.println("** 7. Salir                                        **");
            System.out.println("*****************************************************");
            System.out.println("** Elije de las siguientes opciones la que quieras **");
            System.out.println("*****************************************************");
            System.out.println("Opcion: ");

            try {
                opcion = entrada.nextInt();
            } catch (Exception e) {
                entrada.nextLine(); // limpia el buffer para evitar loop infinito
                continue; // vuelve al menú sin cerrar el programa
            }

            String divisa1 = "";
            String divisa2 = "";

            switch (opcion){
                case 1:
                    divisa1 = "USD";
                    divisa2 = "ARS";
                    break;
                case 2:
                    divisa1 = "ARS";
                    divisa2 = "USD";
                    break;
                case 3:
                    divisa1 = "USD";
                    divisa2 = "BRL";
                    break;
                case 4:
                    divisa1 = "BRL";
                    divisa2 = "USD";
                    break;
                case 5:
                    divisa1 = "USD";
                    divisa2 = "COP";
                    break;
                case 6:
                    divisa1 = "COP";
                    divisa2 = "USD";
                    break;
                case 7:
                    System.out.println("Nos vemos luego :)");
                    seguir = false;
                    break;
                default:
                    System.out.println("esa opcion no es valida");
                    continue;
            }

            if (seguir == true){
                System.out.println("Ahora ingresa la cantidad de dinero a convertir:");
                double cantidad = entrada.nextDouble();
                String cantidadString = df.format(cantidad);
                try {
                    URI urlAPI = new URI("https://v6.exchangerate-api.com/v6/0c90948bce310a41d898f09a/pair/"+divisa1+"/"+divisa2+"");

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(urlAPI)
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    String json = response.body();

                    Moneda moneda = gson.fromJson(json, Moneda.class);
                    double tasa = moneda.conversion_rate();
                    String tasaString = df.format(tasa);
                    LocalDateTime fecha = LocalDateTime.now();
                    double resultado = Conversor.convertir(cantidad, tasa);
                    String resultadoString = df.format(resultado);
                    System.out.println("Resultado: " + resultadoString);

                    Historial historial = new Historial(
                            divisa1,
                            divisa2,
                            cantidadString,
                            tasaString,
                            resultadoString,
                            fecha
                    );
                    historialDeConversiones.add(historial);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        FileWriter escritura = new FileWriter("historialDeConversiones.txt");
        for (Historial h : historialDeConversiones) {
            escritura.write("==============================\n");
            escritura.write(" CONVERSIÓN DE DIVISAS\n");
            escritura.write("==============================\n");
            escritura.write("De: " + h.monedaOrigen() + "\n");
            escritura.write("A: " + h.monedaDestino() + "\n");
            escritura.write("Valor: " + h.cantidad() + "\n");
            escritura.write("Tasa de cambio: " + h.tasa() + "\n");
            escritura.write("Resultado: " + h.resultado() + "\n");
            escritura.write("Fecha: " + h.fecha().toString().replace("T", " ") + "\n");
            escritura.write("------------------------------\n\n");
        }
        escritura.close();
    }
}
