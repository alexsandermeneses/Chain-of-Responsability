package org.example.server;

import org.example.middlewares.Middleware;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    public boolean logIn(String email, String password){
        if (middleware.check(email, password)){
            System.out.println("Usuáio autenticado");
            System.out.println("Bem vindo!");
            return true;
        }
        return false;
    }

    public void registerUser(String email, String password){
        users.put(email, password);
    }

    public boolean hasEmail(String email){
        return users.containsKey(email);
    }
    public boolean isValidPassword(String email, String password){
        return users.get(email).equals(password);
    }
}
