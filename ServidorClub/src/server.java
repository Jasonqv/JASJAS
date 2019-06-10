
import java.net.ServerSocket;
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
public class server 
{
Socket ss;
ServerSocket serverSo;
Thread hiloServer;
  private boolean estado;  

public server()
{
   
    hiloServer=new Thread(r1);
}

public void arrancaHiloServer()
{
    hiloServer.start();
}

Runnable r1=new Runnable()
{

    @Override
    public void run()
    {
     try
     {
         serverSo=new ServerSocket(8282);
          estado=true;
          System.out.println("\n Conexion al puerto 8282");
          System.out.println("\n Esperando clientes");
          
          while(estado)
          {
              ss=serverSo.accept();
              Estructura.getInstance().agregaConexion(new Conexion (ss));
          }
     }
     catch(Exception ee)
     {
         
     }
     try
     {
         serverSo.close();
     }
     catch(Exception e){}
    }
    
};
    
    
}
