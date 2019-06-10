/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Josue
 */
public class cliente 
{
    Socket s;
    DataInputStream entrada;
    DataOutputStream salida,salidaInt;
    String usuario="",ip;
    Thread hilo1;
    int conteo=0,ce=0;
    boolean estado;
public cliente()
{
   
    estado=true;

    
  
       hilo1=new Thread(r1);
}

public void recibeNickIp(String ipp,String niick)
{
    ip=ipp;
    usuario=niick;
}
public void establecerConexion()
{
     try
            {
                s=new Socket(ip,8282);
                
                entrada=new DataInputStream(s.getInputStream());
                salida=new DataOutputStream(s.getOutputStream());
                   salidaInt=new DataOutputStream(s.getOutputStream());
                System.out.println("Me he conectado al servidor "+ip);
                /*salida.writeUTF("\n "+usuario+" entró al server ");*/
                while(estado)
                {
                    char sms=entrada.readChar();
                    
                }
            }
            catch(Exception e)
            {
                System.out.println("\n No se pudo conectar con el server ");
            }
}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

public void leeMensajes(String sms)
{
    System.out.println("\n "+sms);
}


public void enviarInt(int sms)
{
    try
    {
        salidaInt.writeInt(sms);
    }
    catch(Exception ee)
    {
        System.out.println("\n Mensaje no enviado");
    }
}

public void arrancaHiloCliente()
{
    hilo1.start();
}

public void Desconectar(){
          
          try{
            
              if(ce==0){
              s.close();
              ce++;
           
             
             
              JOptionPane.showMessageDialog(null,"Sesion cerrada ");
              
               estado=false;}
              else{
                  JOptionPane.showMessageDialog(null,"Ya se cerró sesión");
                  
              }
              
              
          }catch(Exception e){
              
             
          }
             
             
         }



Runnable r1=new Runnable()
{

        @Override
        public void run() 
        {
         
           establecerConexion();
            
            
        }
    
};
}
