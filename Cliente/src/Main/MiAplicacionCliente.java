
import Controlador.control;
import Modelo.cliente;
import Modelo.conexionBD;
import Vista.Ventana_cliente_alquiler;
import Vista.Ventana_cliente_generar_factura;
import Vista.Ventana_cliente_menu;
import Vista.login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Josue
 */
public class MiAplicacionCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Ventana_cliente_menu vMenu=new Ventana_cliente_menu();
        Ventana_cliente_generar_factura vFactu=new Ventana_cliente_generar_factura();
        Ventana_cliente_alquiler vAlqui=new Ventana_cliente_alquiler();
        cliente cli=new cliente();
        login log=new login();
        conexionBD cBD=new conexionBD();
        control c=new control(vMenu,vFactu,vAlqui,cli,log,cBD);
        log.arrancaLogin();
    }
    
}
