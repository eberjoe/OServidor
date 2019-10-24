/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oservidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
                    InputStreamReader din = new InputStreamReader(s.getInputStream());
                    BufferedReader bread = new BufferedReader(din);
                    // Lendo o nome do usuario do stream.
                    String nome = bread.readLine();
                    // criando um novo usuario
                    novo = new Usuario(this, nome, s);
                    //Adicionando o novo usuario na lista de usuarios.
                    if(listaDeUsuarios.add(novo)){
                        System.out.println(nome + " conectado. IP " + s.getInetAddress());
                        //inicializando a thread Usuario para este novo objeto.
                        novo.start();
                    }
                    
                } catch (IOException ex) {
                    System.out.println("Erro criando Socket.");
                }
            }while(true);
        }
    }

    void send(String nome, String texto) {
        try {
            for(Usuario u : listaDeUsuarios) {
                if(u.getNome().equalsIgnoreCase(nome)) {
                    u.post(texto);
                }
            }
        }
        catch(NullPointerException e) {
            System.out.println("Erro no send " + e.getMessage());
        }
    }
    
}
