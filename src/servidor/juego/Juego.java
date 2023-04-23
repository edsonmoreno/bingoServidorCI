/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.juego;

import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Juego {
    public Juego(){
        ronda=1;
        bolitas = new  int[75];
        for(int i=0; i<bolitas.length; i++) bolitas[i]=-1;
        fin = true;
    }
    
    public int sacarBola(){
        int numero;
        do{
            numero = (int) ((Math.random()*75) +1); 
        }while(validarRepetidos(bolitas, numero));
        bolitas[ronda-1] =numero;
        ronda++;
        
        if(ronda == 75) fin = false;
        return numero;
    }
    private boolean validarRepetidos(int[]v, int n){
       // if(n==75) JOptionPane.showMessageDialog(null , "75");
        for(int i=0; i<75; i++){
            if(v[i] == n)return true;
        }
         return false;
     }

    public boolean isFin() {
        return fin;
    }
    public void setFin(){
        fin = false;
    }
    
    
    private int ronda;
    private int[] bolitas;
    private boolean fin;
}
