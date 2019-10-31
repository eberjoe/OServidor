/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oservidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Representa um usuario do servidor.
 *
 * @author old_adam
 */
class Usuario extends Thread {

    private final String nome;
    private final Socket socket;
    //atributo que guarda a referencia do servidor para enviar mensagens.
    private Servidor server;
    // atributo que vai ser usado para enviar mensagens para o Usuario do outro lado do socket.
    private PrintStream printOut;

    /**
     * constroe um Usuario
     *
     * @param nome nome do usuario
     * @param s socket para criar os stream.
     */
    public Usuario(Servidor server, String nome, Socket s) {
        this.nome = nome;
        this.socket = s;
        this.server = server;
    }

    public String getNome() {
        return nome;
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * Neste metodo ocorrerah o processamento das mensagens trocadas entre os
     * usuarios.
     */
    public void run() {
        try {
            //Criando o printStream para escrever mensagens.
            printOut = new PrintStream(socket.getOutputStream());
            //Criando o Data Input para ler a mensagem enviada pelo OCliente.
            InputStream inp = socket.getInputStream();
            InputStreamReader iRead = new InputStreamReader(inp);
            BufferedReader inFromCliente = new BufferedReader(iRead);
            String toUsuario, texto;
            String mensagem;
            String[] elementosDaMensagem;;

            do {
                mensagem = inFromCliente.readLine();
                elementosDaMensagem = mensagem.split("->");
                toUsuario = elementosDaMensagem[0];
                texto = elementosDaMensagem[1];
//                System.out.println("SERVIDOR: usuario: " + toUsuario);
//                System.out.println("SERVIDOR: line: " + texto);
                server.send(this.nome, toUsuario, texto);
            } while (!texto.equalsIgnoreCase("sair"));
        } catch (IOException ex) {
            System.out.println("Erro Usuario " + ex.getMessage());
        }
    }

    void post(String texto) {
        try {
            printOut.println(texto);
        } catch (Exception e) {
            System.out.println("Erro no post: " + e.getMessage());
        }
    }

}

