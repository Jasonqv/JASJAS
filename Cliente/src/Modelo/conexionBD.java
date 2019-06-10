/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jason
 */
public class conexionBD {
    public String bd = "video";
public String login = "root";
String matrizPeliculas [] ;
int vectorPrecioTotal[];
public String password = "root";
public String url = "jdbc:mysql://localhost/"+bd;
int id_Aumenta=0;
String resultadoBusqueda="";
private String apellidos="",cedula="",fecha_nacimiento="",saldo="",nombre="",nombrePelicula;
private String num="CP0001";
private String primeroSerie="";
Connection conn; 
PreparedStatement pSentencias,pSocio_a_Factura,pFactura_a_Prestamo;
ResultSet rsDatos,res;
Statement stSentencias;
 String variables[]=new String[6];
 String auxiliar="";
 String temporal;
 String busqueda;
 String traida;
 int precioPeli=0;
 int sumatoriaPrecio=0;
  String id,id_socioS;
  int tam=1;
  int IDFactura,Pelicantidad;
    public String getPrimeroSerie() {
        return primeroSerie;
    }

    public void setPrimeroSerie(String primeroSerie) {
        this.primeroSerie = primeroSerie;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getId_socioS() {
        return id_socioS;
    }

    public void setId_socioS(String id_socioS) {
        this.id_socioS = id_socioS;
    }

    public int getIDFactura() {
        return IDFactura;
    }

    public void setIDFactura(int IDFactura) {
        this.IDFactura = IDFactura;
    }
    
    
    
    
    
    
     public conexionBD() { 
      try{          
         Class.forName("com.mysql.jdbc.Driver");          
         conn = DriverManager.getConnection(url,login,password);
         stSentencias=conn.createStatement();
         if (conn!=null){ 
            JOptionPane.showMessageDialog(null,"Conexión a base de datos "+bd+". listo"); 
            

         } 
      }catch(SQLException e){ 
         System.out.println(e); 
      }catch(ClassNotFoundException e){ 
         System.out.println(e); 
      } 
   }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
    public void aumentaIDPrestamo(int dato)
    {
        int cont=1;
        
        if(dato>=1000||dato<10000)
        {
            int can=cont+dato;
            num="PP"+can;
        }
        
         if((dato>=100) || (dato<1000))
           {
               int can=cont+dato;
               num = "PP0"  + can; 
           }
           if((dato>=9) && (dato<100)) 
           {
                int can=cont+dato;
               num = "PP00"  + can; 
           }
           if((dato>=1) && (dato<9)) 
           {
               int can=cont+dato;
               num = "PP000" + can; 
           }
           if(dato==0)
           {
              int can=cont+dato;
               num = "PP000" + can; 
           }
        
        
    }

    public String getNum() {
        return num;
    }

    public int getSumatoriaPrecio() {
        return sumatoriaPrecio;
    }

    public void setSumatoriaPrecio(int sumatoriaPrecio) {
        this.sumatoriaPrecio = sumatoriaPrecio;
    }
    
    
    
    
    public void traeIDFacturaAPrestamo()
    {
         ResultSet rsFactura;
        Statement st;
        
        try
            
        {
            st=conn.createStatement();  
            rsFactura=st.executeQuery("select * from autoincrementa where id_socio= '"+id_socioS+"'");
            
            if(rsFactura.next())
            {
               IDFactura=rsFactura.getInt("id"); 
               System.out.print("ID Factura es -> "+IDFactura);
            }
        }
        catch(Exception u)
        {
            
        }
        
    }
    
    
    
    public void traeIDSocioNombre()
    {
        ResultSet rsSocios;
        Statement st;
        
        try
            
        {
            st=conn.createStatement();  
            rsSocios=st.executeQuery("select * from socios where nombre= '"+nombre+"'");
            
            if(rsSocios.next())
            {
               id_socioS=rsSocios.getString("id_socios"); 
               System.out.print("ID es -> "+id_socioS);
            }
        }
        catch(Exception u)
        {
            
        }
       
        
    }
     
    
    public void insertaIDFacturaPrestamos()
    {
        try
        {
                  pFactura_a_Prestamo=conn.prepareStatement("INSERT INTO prestamos (id_peli,id_facturas) VALUES("+id+","+IDFactura+")");
                   int resultado3=pFactura_a_Prestamo.executeUpdate();
                     System.out.println("\n"+resultado3);
        }
       catch (SQLException e){
            System.out.println("Error: " + e);
        }
  
        
        
        
        
    }
       public boolean buscarPeliculas(String lo_que_se_busca)
     {
          Statement st;
       ResultSet rs;
      
       
       try
       {
            st=conn.createStatement();
       
   
            rs=st.executeQuery("select * from peliculas where nombre= '"+lo_que_se_busca+"'");
           
      
       busqueda="'"+lo_que_se_busca+"'";
       
       temporal="'select id_pelicula from peliculas'" ;
       if(rs.next())
       {
           
           nombrePelicula=rs.getString("nombre");
           id=rs.getString("id_pelicula");
          precioPeli=rs.getInt("precio");
            System.out.print("EL RS2 ES -> "+id);
            System.out.println("\n El precio es -> "+precioPeli);
            
       return true;
       
       }
      /* if(rsSocio_factura.next())
       {
           nombresillo=rsSocio_factura.getString("nombre");
            System.out.print("EL Nombre ES -> "+nombresillo);
       }*/
       }
       catch(SQLException r)
       {
           
       }
  return false;
         
     }
       
       public String [] llenaTabla(String lo_que_se_busca)
       {
           String sql="";
           
          
           
           
           if(lo_que_se_busca.equals(""))
    {
        sql="SELECT * FROM peliculas";
    }
    else{
        sql="SELECT * FROM peliculas WHERE nombre='"+lo_que_se_busca+"'";
    }
           
           
           
           try
           {
             Statement st=conn.createStatement();  
             ResultSet rs=st.executeQuery(sql);
             
             
             
             
             
             while(rs.next()){
                variables[0]=rs.getString(1);
                variables[1]=rs.getString(2);
                variables[2]=rs.getString(3);
                variables[3]=rs.getString(4);
                variables[4]=rs.getString(5);
                variables[5]=rs.getString(6);
                
                
            }
           }
           catch(Exception j){}
           
           return variables;
       }
       
      
          public String insertar (String id_Pres)
     {
         int resultado=0;
                 try {
                  
      /* pSentencias = conn.prepareStatement("INSERT INTO prestamos (id_peli) VALUES("+id+")");*/
     pSocio_a_Factura=conn.prepareStatement("INSERT INTO autoincrementa (id_socio,precio_total) VALUES("+id_socioS+","+sumatoriaPrecio+")");
     
        
       
        /*resultado =  pSentencias.executeUpdate();*/
        int resultado2=pSocio_a_Factura.executeUpdate();
           
        
       System.out.println("\n"+resultado+" "+resultado2);
       
   

}
        catch (SQLException e){
            System.out.println("Error: " + e);
        }
                 
             return (String.valueOf(resultado));          
     }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       public void insertarEnPrestamo()
       {
           String sqlsentencia="INSERT INTO prestamos(id_Prestamos) VALUES (?)";
           
           try
           {
        PreparedStatement pst=conn.prepareStatement(sqlsentencia);
        pst.setString(1,num);
        
        //falta los otros 2 por agregar
        
       /* int i=pst.executeUpdate();
        if(i>0)
        {
           JOptionPane.showMessageDialog(null, "Se guardo correctamente"); 
        }*/
       pst.execute();
       JOptionPane.showMessageDialog(null,"guardado");
            
        
       
        
        
           }catch (SQLException ex){}
           
           
           
       }
       
       
       
    public void traeCantidadPeliculas()
    {
       
            Statement st;
       ResultSet rs;
      
       
       try
       {
            st=conn.createStatement();
       
   
            rs=st.executeQuery("select * from peliculas where nombre= '"+nombrePelicula+"'");
           
      
      
       
       
       if(rs.next())
       {
           
           Pelicantidad=rs.getInt("cantidad_disponible");
           
           
            System.out.println("La CANTIDAD DE LA PELI ES -> "+Pelicantidad);
         
          Pelicantidad--;
          
          
          actualizarCantidad();
          
          
          
          
          
          
          
          
          
          
          
          
       }
       }
       catch(SQLException h){
           System.out.println("ERROR: "+h);
       }
        
       
       
       
      
       
       
    }  
       
     public String actualizarCantidad()
     {
         int res=0;
                 try {
       
        pSentencias = conn.prepareStatement("UPDATE peliculas SET cantidad_disponible= ? where nombre=? ");

     pSentencias.setInt(1, Pelicantidad);
         /*pSentencias.setString(1, nombre);
        pSentencias.setString(2, edad);
        pSentencias.setString(3, promedio);
         pSentencias.setString(4, id);*/
     pSentencias.setString(2, nombrePelicula);
        res =  pSentencias.executeUpdate();
}
        catch (SQLException e){
            System.out.println("Error: " + e);
        }
                 
            return (String.valueOf(res));      
       
     }   

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }
    
    public void insertaTotalEnFactura()
    {
        PreparedStatement insertaTotal;
     
                 try {
                  
    
     insertaTotal=conn.prepareStatement("INSERT INTO autoincrementa (precio_total) VALUES("+sumatoriaPrecio+")");
     
        
       
       
        int resultado2=insertaTotal.executeUpdate();
           
        
       System.out.println("\n"+resultado2+" ");
       
   

}
        catch (SQLException e){
            System.out.println("Error: " + e);
        }
                 
               
        
        
        
        
        
        
        
        
        
        
    }
     
     
     
     
     
     
     
     
     
     
    public String[] busquedaParaPrecio(String id_peli,String nom,String precios)
    {
         
        String c=String.valueOf(precioPeli);
        matrizPeliculas=new String [3];
        
              
        matrizPeliculas[0]=id;
        matrizPeliculas[1]=nombrePelicula;
        matrizPeliculas[2]=c;
        sumatoriaPrecio+=precioPeli;
        
        
                
   /*for(int x=0;x<tam;x++)
   {
       for(int y=0;y<3;y++)
       {
           if(y==0)
           {
                matrizPeliculas[x][y]=id_peli;
           }
           if(y==1)
           {
               matrizPeliculas[x][y]=nom;
           }
           if(y==2)
           {
               String c=String.valueOf(precio);
               matrizPeliculas[x][y]=c;
               
           }
          
       }
       
   }*/
   
        
      
            
            for(int j=0;j<3;j++)
            {
                System.out.println("["+matrizPeliculas[j]+"]");
            }
        
      
            tam++; 
      return matrizPeliculas;
 
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
       
       public boolean realizaBusqueda(String lo_que_se_busca)
       {
           try
           {
               
            res=consulta("select * from socios where id_socios= '"+lo_que_se_busca+"'");
            
            String nom;
            if(res.next()==true)
            {
                nom=res.getString("nombre");
                nombre=nom;
                return true;
                
            }
           
           }
           
           catch(Exception r)
           {
               System.out.println(r);
           }
           
           
           return false;
       }
       
       
       
       
       
       
       
       
       
       
       public ResultSet consulta(String sentencia_sql)
       {
           
           try
           {
               rsDatos=stSentencias.executeQuery(sentencia_sql);
           }
           catch (SQLException r)
           {
               
           }
           
           return rsDatos;
       }
       
       
       
       
       
       
       public void numeros()
       {
           int conver;
           String SQL="SELECT MAX(id_Prestamos) AS id_Prestamos FROM prestamos";
 
           String c="";
            
      
           try
           {
               
                Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery(SQL);
       
      
       
       if(rs.next())
       {
            c=rs.getString("id_Prestamos");
          
       }
        rs.close();
        st.close();
        
       
       
           
       
       System.out.println(c);
       
       /*int j=Integer.parseInt(c);
         aumentaIDPrestamo(j);*/
               
       
   
      
       
           
           char r1=c.charAt(2);
           char r2=c.charAt(3);
           char r3=c.charAt(4);
           char r4=c.charAt(5);
           System.out.println(""+r1+r2+r3+r4);
           String juntar=""+r1+r2+r3+r4;
           int var=Integer.parseInt(juntar);
                      aumentaIDPrestamo(var);

           System.out.println("\n esto vale numericamente"+var);
           
           System.out.println("mi serie es: "+getNum()+1);
               
               
               
           
       
              
              
             
                  
               
             
               
              
               
               
              
               
           }
           catch(Exception r)
           {
               System.out.println(r.getMessage());
           }
       }
    
}
