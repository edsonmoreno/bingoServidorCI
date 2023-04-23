/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import servidor.juego.Juego;

/**
 *
 * @author ACER
 */
public class Servidor extends JFrame implements Runnable{
    public Servidor(){
        super();
        ancho = 640;
        alto = 480;
        pantalla = new Pantalla();
        setTitle("Bingo Mi Juego favorito");
        super.setBounds(20, 20, ancho, alto);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(null);
        super.add(pantalla);
        juego = new Juego();
        Thread h = new Thread(this);
        Thread es = new Thread(new EscucharClientes());
        h.start();
        es.start();
        super.setVisible(true);
    }
    
    
    @Override
    public void run() {
         try {
            this.skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO );
            String bolita="";
            while(juego.isFin()){
            //while(true){
                Socket cliente = skServidor.accept();

                OutputStream aux = cliente.getOutputStream();
                DataOutputStream flujo= new DataOutputStream( aux );
                flujo.writeUTF( ""+NUMERO );

                cliente.close();
            }
        } catch (IOException ex) {
            System.out.println(""+ex.getMessage());
        }
        pantalla.getT().stop();
        System.out.println("fin");

    }
    
    private class Pantalla extends JPanel implements ActionListener{

        public Pantalla() {
            super();   
            super.setLayout(null);
            Font f = new Font("Arial", Font.BOLD+Font.CENTER_BASELINE, 36);
            Font g = new Font("console", Font.BOLD+Font.CENTER_BASELINE, 20);
            
            ganador = new JLabel("");
            ganador.setFont(g);
            ganador.setBounds(80, 70, 400, 50);
            add(ganador);
            super.setBackground(Color.WHITE);
            super.setBounds(0, 0, 640, 480);
            this.agregarBotones();
            bolita_actual.setFont(f);
            
             t = new Timer(5000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Servidor.NUMERO = juego.sacarBola();
                        bolita_actual.setText("  "+Servidor.NUMERO);
                        System.out.println("numero: "+NUMERO);
                        NUMERO_BOLA++;
                        System.out.println("bol "+NUMERO_BOLA);
                        if(NUMERO_BOLA == 75) t.stop();
                    }  
                });
        }

        public Timer getT() {
            return t;
        }
        
        
    
        private void  agregarBotones(){
            iniciar = new JButton("Iniciar Servidor");
            bolita_actual = new JTextField("");
            bolita_actual.setBounds(240, 160, 100, 100);
            iniciar.setBounds(250, 400, 150, 20);
            super.add(iniciar);
            super.add(bolita_actual);
            iniciar.addActionListener(this);
        }
        
        
        
        private JButton camb_nombre;
        private JButton iniciar;
        private JButton salir;
        private JTextField bolita_actual;
        protected Timer t;
    
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals((Object)iniciar)){
                t.start();
            }
        }
        
    }
    
    private class EscucharClientes implements Runnable{

        @Override
        public void run() {
            String r="";
            try {
                skServidori = new ServerSocket(5001);
                System.out.println("Escucho el puerto " + PUERTO );
                while(juego.isFin()){
                    //while(true){
                    Socket cliente = skServidori.accept();
                    
                    InputStream auxi = cliente.getInputStream();
                    DataInputStream flujoi = new DataInputStream( auxi );
                    
                    r = flujoi.readUTF();
                    System.out.println("EEXFFUILGG");
                    ganador.setText("BINGO, El GANADOR ES: "+r);
                    pantalla.t.stop();
                    juego.setFin();
                    cliente.close(); 
                }
                } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    static final int PUERTO=5000;
    private final int ancho, alto;    
    private JButton iniciar;
    private JButton salir;
    private Pantalla pantalla;
    private Juego juego;;
    private ServerSocket skServidor;
     private ServerSocket skServidori;
     private JLabel ganador;
   
    private static int NUMERO=0;
    private static int NUMERO_BOLA=0;
}
