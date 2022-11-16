package com.example.diploma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiplomApplication implements CommandLineRunner {
    @Autowired
    private ParserApp parserApp;

    public static void main(String[] args) {
        SpringApplication.run(DiplomApplication.class, args);
    }

    @Override
    public void run(String... args) {
        parserApp.run();
    }
}

