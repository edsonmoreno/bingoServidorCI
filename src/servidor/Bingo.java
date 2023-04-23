package servidor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class Bingo {
    
    
    public Bingo(){
        servidor = new Servidor();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bingo b = new Bingo();
    }
    
    private Servidor servidor;
    
}
