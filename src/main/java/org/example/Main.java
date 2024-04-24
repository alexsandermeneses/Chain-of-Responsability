package org.example;

import org.example.middlewares.CheckPermissionMiddleware;
import org.example.middlewares.CheckUserMiddleware;
import org.example.middlewares.Middleware;
import org.example.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
    private static Server server;
    public static void init(){
        server = new Server();
        server.registerUser("develop@outlook.com.br", "123");
        server.registerUser("user@gmail.com", "12345");

        Middleware middleware = new CheckUserMiddleware(server);
        middleware.linkWith(new CheckPermissionMiddleware());

        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean done;

        do {
            System.out.println("Digite o e-mail: ");
            String email = reader.readLine();
            System.out.println("Digite sua senha: ");
            String password = reader.readLine();
            done = server.logIn(email, password);
        }while (!done);

    }
}