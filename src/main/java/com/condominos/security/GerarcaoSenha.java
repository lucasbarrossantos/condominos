package com.condominos.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class GerarcaoSenha {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("teste"));
    }

}
