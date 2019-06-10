
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Josue
 */
public class Conexion
{
   Socket s;
private DataInputStream entra;
   private DataOutputStream sale;
    private boolean conectado;
    private int contadorMensajes=0;
    Thread hilo;
    String m="",msj="";
    
    public Conexion(Socket so)
    {
        s=so;
        conectado=true;
        hilo=new Thread(r1);
        try
        {
           entra=new DataInputStream(so.getInputStream()); 
           sale=new DataOutputStream(so.getOutputStream());
            iniciaHilo();
        }
        catch(Exception e)
        {
            
        } 
    }
    
    
    
      public void iniciaHilo()
    {
        hilo.start();
    }
    
    
    
    
      
    
      
        public void enviarMensaje(String msj)
    {
        try
        {
            sale.writeUTF(msj);
        }
        catch(Exception ef)
        {
          
        }
    }
      
     
    public void lecturaDatos(int da)
    {
        if(da==99)
        {
            
        }
    }
        
        
        
    Runnable r1=new Runnable()
    {

    @Override
    public void run() 
    {
         while(conectado==true)
            {
                try
                {
                    
                    
                          int msj=entra.readInt();
                      /*  Estructura.getInstance().redirecionarMensaje(msj);*/
                        System.out.print("el int q llego es -> "+msj+"\n");
                        //redireccionar mensaje
                        
                        //leer mensajes desde el controlador hacia la vista
                    
                }
                catch(Exception ee)
                {
                    try
                    {
                    conectado=false;
                    s.close();
                    System.out.print("se desconectado fulano");
                    }
                    catch(Exception f){}
                    
                }
            }
        
        
    }
        
    };    
}
