README.md

Conversor de Monedas en Java

Este proyecto es una aplicación de consola 
desarrollada en Java que permite convertir valores entre distintas divisas
utilizando la API ExchangeRate-API. El programa muestra un menú, solicita la cantidad a convertir,
consulta la tasa de cambio en tiempo real y muestra el resultado con formato numérico legible. 
Todas las conversiones realizadas durante la ejecución se guardan en un archivo de historial.

Descripción general

La aplicación permite realizar conversiones entre los siguientes pares de divisas:
- USD a ARS
- ARS a USD
- USD a BRL
- BRL a USD
- USD a COP
- COP a USD

El resultado de la conversión se muestra con separadores de
miles y decimales, y cada operación queda registrada para su consulta posterior.

Requisitos:

- Java 17 o superior
- Biblioteca Gson (agregada manualmente en el proyecto)

Cómo usar:

- Ejecuta la aplicación desde tu IDE o terminal.
- Selecciona la opción de conversion de monedas que quieres hacer.
- Ingresa el valor a convertir **sin comas ni puntos**.
- Se mostrará el valor del dinero convertido y te volvera a aparecer el menu.
- Haz esos 3 pasos las veces que quieras y cuando ya quieras terminar,
  solamente dale a la opción 7 para salir.
- Una vez hayas salido, la aplicación guardará el historial de conversiones con **fecha y hora**.

Funcionalidades principales:

- Menú interactivo por consola.
- Lectura de datos ingresados por el usuario.
- Peticiones HTTP a una API de tasas de cambio.
- Conversión matemática de divisas.
- Formateo de números usando DecimalFormat.
- Uso de Records (Java 16+) para representar datos.
- Generación automática de un archivo de historial con 
  todas las conversiones realizadas durante la ejecución.

Tecnologías utilizadas: 

- Java 17 o superior.
- Biblioteca Gson para trabajar con JSON.
- HttpClient (incluido en Java).
- ExchangeRate-API para obtener las tasas de cambio.
- Manejo de archivos mediante FileWriter.

Estructura del proyecto:

- El proyecto está organizado en las siguientes carpetas y clases:
  - Principal (Asi quise llamar a Main)
    - Principal.java
  - clases
    - Conversor.java
    - Moneda.java
    - Historial.java
  - libs
    - gson-2.13.2.jar

Archivo de historial:

Cuando ejecutas el programa y lo finalizas luego de hacer
unas cuantas conversiones se guarda un historial de estas 
en un archivo txt llamado HistorialDeConversiones.txt

- su interior tiene algo asi:

==============================
CONVERSIÓN DE DIVISAS
==============================
De: USD\
A: COP\
Valor: 100\
Tasa de cambio: 3,761.4279\
Resultado: 376,142.79\
Fecha: 2025-11-17 22:35:26
------------------------------

API utilizada:

- El programa utiliza la API gratuita ExchangeRate-API.
Debes reemplazar la clave dentro de la URL por tu propia API key obtenida desde:

  - https://www.exchangerate-api.com/
  - Esta es la URL que se deberia usar en el proyecto: https://v6.exchangerate-api.com/v6/TU_API_KEY/pair/USD/COP

Posibles mejoras futuras: 

- Refactorizar el código para mejorar organización y legibilidad.
- Separar responsabilidades en funciones y clases dedicadas.
- Añadir más monedas disponibles para conversión.
- Mejorar el manejo de errores para casos especiales.
- Optimizar la estructura del proyecto sin cambiar su funcionamiento principal.
- Probar nuevas API relacionadas a la conversión de monedas.