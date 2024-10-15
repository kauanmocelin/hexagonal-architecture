package dev.kauanmocelin.bank.infrastructure;

import dev.kauanmocelin.bank.SpringAppConfig;
import org.springframework.boot.SpringApplication;

public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringAppConfig.class, args);
    }
}