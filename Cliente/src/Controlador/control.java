/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.cliente;
import Modelo.conexionBD;
import Vista.Ventana_cliente_alquiler;
import Vista.Ventana_cliente_generar_factura;
import Vista.Ventana_cliente_menu;
import Vista.login;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Jason
 */
public class control implements ActionListener{

    private  Ventana_cliente_menu vMenu;
       private Ventana_cliente_generar_factura vFactu;
       private Ventana_cliente_alquiler vAlqui;
       private cliente cli;
       private login log;
       private conexionBD cBD;
    
    public control(Ventana_cliente_menu vMenu,Ventana_cliente_generar_factura vFactu,Ventana_cliente_alquiler vAlqui,cliente cli,login log,conexionBD cBD)
    {
        this.vMenu=vMenu;
        this.vAlqui=vAlqui;
        this.vFactu=vFactu;
        this.cli=cli;
        this.log=log;
        this.cBD=cBD;
        
        this.log.getBtnConectar().addActionListener(this);
        this.vMenu.getBtn_aceptar().addActionListener(this);
        this.vMenu.getBtn_alquilar().addActionListener(this);
        
        this.vAlqui.getBtn_buscar().addActionListener(this);
        this.vAlqui.getBtn_generar_factura().addActionListener(this);
        this.vFactu.getBtnRegresaMenu().addActionListener(this);
        this.vAlqui.getBtnTodos().addActionListener(this);
       
        this.vAlqui.getEnviar_peli().addActionListener(this);
        
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==log.getBtnConectar())
        {
            cli.recibeNickIp(log.getEspacioIp().getText(), log.getEspacioUsuario().getText());
            cli.arrancaHiloCliente();
            
            log.dispose();
            vMenu.setVisible(true);
            vMenu.getLabel_mensaje().setText("Conectado");
            vMenu.getLabel_mensaje().setForeground(Color.GREEN);
            
        }
        
        if(e.getSource()==vMenu.getBtn_aceptar())
        {
            
            if(cBD.realizaBusqueda(vMenu.getTxt_id().getText())==true)
            
            {
            vMenu.getLabel_resultado().setText("Bienvenido "+cBD.getNombre());
            vMenu.getBtn_alquilar().setEnabled(true);
            }
            else
            {
                JOptionPane.showMessageDialog(vMenu, "Usuario NO registrado en la Video Club");
            }
            
            
        }
        
        if(e.getSource()==vMenu.getBtn_alquilar())
        {
            vMenu.dispose();
            vAlqui.setVisible(true);
            vAlqui.meteVariablesTipo();
            
            
        }
        
        if(e.getSource()==vAlqui.getEnviar_peli())
        {
           /* cBD.insertar(vAlqui.getTxt_nombre().getText());*/
            cBD.traeIDFacturaAPrestamo();
            cBD.insertaIDFacturaPrestamos();
             
            cBD.traeCantidadPeliculas();
            String conver=String.valueOf(cBD.getIDFactura());
            vFactu.getTxt_id_factura().setText(conver);
            vFactu.getTxt_id_socio().setText(cBD.getId_socioS());
            vFactu.getTxt_nombre().setText(cBD.getNombre());
           
            vFactu.getModelo_tabla2().addRow(cBD.busquedaParaPrecio(vFactu.getTxt_nombre().getText(), vFactu.getTxt_id_socio().getText(), vFactu.getTxt_id_factura().getText()));
           /* vAlqui.dispose();*/
           
            
            
        }
        
      
        
        
        
        
        
        
        if(e.getSource()==vAlqui.getBtn_buscar())
        {
            if(cBD.buscarPeliculas(vAlqui.getTxt_nombre().getText())==true)
        {
            
          
            
            vAlqui.getModelo_tabla().addRow(cBD.llenaTabla(vAlqui.getTxt_nombre().getText()));
            vAlqui.getEnviar_peli().setEnabled(true);
            cBD.traeIDSocioNombre();
           
           
            
          
        
            
        }
            else
            {
                JOptionPane.showMessageDialog(null, "Pelicula NO registrada");
            }
            
            
        
        }
        
        if(e.getSource()==vAlqui.getBtnTodos())
        {
            
            /*vAlqui.getModelo_tabla().addRow(cBD.getVariables());*/
        }
        
      
        
        if(e.getSource()==vAlqui.getBtn_generar_factura())
        {
             
            
            vFactu.setVisible(true);
            String pepa=String.valueOf(cBD.getSumatoriaPrecio());
            vFactu.getTxt_precio_total().setText(pepa);
            cBD.insertar(vAlqui.getTxt_nombre().getText());
           /* cBD.insertaTotalEnFactura();*/
        
        }
        
        if(e.getSource()==vFactu.getBtnRegresaMenu())
        {
            
            vFactu.dispose();
            vAlqui.setVisible(true);
            
            
            
        }
    }
    
}
