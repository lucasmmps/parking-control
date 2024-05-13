package com.api.parkingcontrol.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    //Definindo um novo formato para a data e hora e atribuindo para a constante DATETIME_FORMAT
    public static final String DATETIME_FORMAT = "yyy-MM-dd'T'HH:mm:ss'Z'";
    //Atribuindo a serialização do novo formato para data e hora definido para a constante LOCAL_DATETIME_SERIALIZER
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean //Mapeia ao Spring que o método definido abaixo é um bean, necessário por ser uma classe externa à aplicação
    @Primary //Define prioridade caso sejam gerados outros beans para a classe ObjectMapper posteriormente
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule(); //Instancia de um novo módulo do tipo JavaTimeModule
        module.addSerializer(LOCAL_DATETIME_SERIALIZER); //Adicionando a serialização do fomrato de data e hora definido para o módulo
        return new ObjectMapper().registerModule(module); //Retorno da instância para a classe ObjectMapper com o registro do novo módulo
    }
}
