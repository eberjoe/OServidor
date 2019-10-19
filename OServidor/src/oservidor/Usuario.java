/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oservidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Representa um usuario do servidor.
 * @author old_adam
 */
class Usuario extends Thread {
    private final String nome;
    private final Socket socket;
    /**
     * constroe um Usuario
     * @param nome nome do usuario
     * @param s socket para criar os stream.
     */
    public Usuario(String nome, Socket s) {
        this.nome = nome;
        this.socket = s;
    }
    
    public String getNome(){
        return nome;
    }
    public Socket getSocket() {
        return socket;
    }
    /**
     * Neste metodo ocorrerah o processamento das mensagens trocadas
     * entre os usuarios.
     */
    public void run(){
        try {
            //Criando o Data Input para ler a mensagem enviada pelo OCliente.
            InputStream inp = socket.getInputStream();
            DataInputStream din = new DataInputStream(inp);
            String usuario, texto;
            String linha;
            
            do {
                    linha = din.readUTF();
                    System.out.println("SERVIDOR: " + linha);
//                    usuario = din.readUTF();
//                    texto = din.readUTF();
//                    System.out.println("SERVIDOR: usuario: "+usuario);
//                    System.out.println("SERVIDOR: texto: "+texto);
            } while (true);
        }
        catch(IOException ex) {
            System.out.println("Erro Usuario "+ex.getMessage());
        }
    }
    
}
