/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author USUARIO
 */
public class UsuarioDAO {
    Conexion cn= new Conexion();
    Connection con;
    
    int r;
    ResultSet rs;
    CallableStatement cs;
    
    public Usuario Validar(String correo, String contra){
        Usuario usu= new Usuario();
        StringBuilder sb= new StringBuilder();
        sb.append("{CALL SP_LISTAUSUARIOS(?, ?)}");
        con=cn.Conexion();
        try {
            cs=con.prepareCall(sb.toString());
            cs.setString(1,correo);
            cs.setString(2,contra);
            rs=cs.executeQuery();
            
            while(rs.next()){
                usu.setIDE_USU(rs.getInt("Ide"));
                usu.setCOR_USU(rs.getString("Correo"));
                usu.setCON_USU(rs.getString("Contraseña"));   
                usu.setNOM_USU(rs.getString("Nombres"));
                usu.setAPE_USU(rs.getString("Apellidos"));
            }           
        } catch (Exception e) { 
            System.err.println("Error:" + e);
        } 
        finally{
            try {
                if(con!=null) con.close();
                if(rs!=null)  rs.close();
                if(cs!=null) cs.close();       
            } catch (Exception e) {
                System.err.println("Error:" + e);
            }
        }
        return usu;     
    }
    
    public int Agregar(Usuario u){
        StringBuilder sb= new StringBuilder();
        sb.append("{CALL SP_NUEVOUSUARIO(?, ?, ?, ?)}");
        con=cn.Conexion();
        try {
            cs=con.prepareCall(sb.toString());
            cs.setString(1,u.getNOM_USU());
            cs.setString(2,u.getAPE_USU());
            cs.setString(3,u.getCOR_USU());
            cs.setString(4,u.getCON_USU());
            cs.executeQuery();
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
        finally{
            try {
                if(con!=null) con.close();
                if(cs!=null) cs.close();       
            } catch (Exception e) {
                System.err.println("Error:" + e);
            }
        }
        return r;
    }
    
        public void Modificar(Usuario u){
        StringBuilder sb= new StringBuilder();
        sb.append("{CALL SP_ACTUALIZAUSUARIO(?, ?, ?, ?)}");
        con=cn.Conexion();
        try {
            cs=con.prepareCall(sb.toString());
            cs.setInt(1,u.getIDE_USU());
            cs.setString(2,u.getNOM_USU());
            cs.setString(3,u.getAPE_USU());
            cs.setString(4,u.getCOR_USU());
            cs.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
        finally{
            try {
                if(con!=null) con.close();
                if(cs!=null) cs.close();       
            } catch (Exception e) {
                System.err.println("Error:" + e);
            }
        }
    }
    
    
    public Usuario ListarID(int ide){
        Usuario usu= new Usuario();
        StringBuilder sb= new StringBuilder();
        sb.append("{CALL SP_NUEVOUSUARIO(?)}");
        con=cn.Conexion();
        try {
            cs=con.prepareCall(sb.toString());
            cs.setInt(1,ide);
            cs.executeUpdate();
            while(rs.next()){           
                usu.setNOM_USU(rs.getString("Nombres"));
                usu.setAPE_USU(rs.getString("Apellidos"));
                usu.setCOR_USU(rs.getString("Correo")); 
            }    
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
        finally{
            try {
                if(con!=null) con.close();
                if(cs!=null) cs.close();       
            } catch (Exception e) {
                System.err.println("Error:" + e);
            }
        }
        return usu;
    }
   
}
