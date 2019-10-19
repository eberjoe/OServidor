/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oservidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author old_adam
 */
public class Servidor extends Thread {
    private ServerSocket serverSocket = null;
    private Vector<Usuario> listaDeUsuarios = new Vector();
    
    /**
     * Construtor. Para deixar o codigo mais limpo
     * decidi jogar a exception para quem usar a classe. 
     * @param porta onde o servidor estarah disponivel.
     * @throws IOException 
     */
    public Servidor(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }
    
    public void run() {
        if(serverSocket != null) {
            Usuario novo;
            do{
                try {
                    Socket s = serverSocket.accept();
                    // Ler o nome do cliente
                    DataInputStream din = new DataInputStream(s.getInputStream());
                    // criando a classe usuario
                    String nome = din.readUTF();
                    // adicionando na lista.
                    novo = new Usuario(nome, s);
                    if(listaDeUsuarios.add(novo)){
                        System.out.println(nome + " conectado. IP " + s.getInetAddress());
                        novo.start();
                    }
                    
                } catch (IOException ex) {
                    System.out.println("Erro criando Socket.");
                }
            }while(true);
        }
    }
    
}
