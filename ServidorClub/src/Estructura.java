
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Josue
 */
public class Estructura 
{
ArrayList<Conexion> listaConexiones;
    String nombresUsuarios[];
   int cuentaNick=0;
    private static Estructura singleton=new Estructura();
    
    public static Estructura getInstance()
    {
        return singleton;
    }
    
    public Estructura()
    {
        listaConexiones=new ArrayList<Conexion>();
        
    }
    
     public void recibeNick(String nick)
      {
          switch(cuentaNick)
          {
              case 0:
                         
                         nombresUsuarios[0]=nick;
                         cuentaNick++;
                  break;
                  
                  
              case 1:
                         
                         nombresUsuarios[1]=nick;
                         cuentaNick++;
                  break;
                  
              case 2:
                       
                         nombresUsuarios[2]=nick;
                  break;
          }
         
          
        
      }
     
     public void redirecionarMensaje(String msj)
{
    for(Conexion c:listaConexiones)
    {
        c.enviarMensaje(msj);
    }
}
     
     public void agregaConexion(Conexion c)
{
    listaConexiones.add(c);
}
    
    
    
    
    
    
}
