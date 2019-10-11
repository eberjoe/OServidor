/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oservidor;

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
        do {
            
        }while(true);
    }
    
}
