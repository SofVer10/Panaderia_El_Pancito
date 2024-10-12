package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class Panaderia {

    public String getUUID_Panadero() {
        return UUID_Panadero;
    }

    public void setUUID_Panadero(String UUID_Panadero) {
        this.UUID_Panadero = UUID_Panadero;
    }

    public String getNombre_Panadero() {
        return Nombre_Panadero;
    }

    public void setNombre_Panadero(String Nombre_Panadero) {
        this.Nombre_Panadero = Nombre_Panadero;
    }

    public int getEdad_Panadero() {
        return Edad_Panadero;
    }

    public void setEdad_Panadero(int Edad_Panadero) {
        this.Edad_Panadero = Edad_Panadero;
    }

    public double getPeso_Panadero() {
        return Peso_Panadero;
    }

    public void setPeso_Panadero(double Peso_Panadero) {
        this.Peso_Panadero = Peso_Panadero;
    }

    public String getCorreo_Panadero() {
        return Correo_Panadero;
    }

    public void setCorreo_Panadero(String Correo_Panadero) {
        this.Correo_Panadero = Correo_Panadero;
    }
    
    String UUID_Panadero;
    String Nombre_Panadero;
    int Edad_Panadero;
    double Peso_Panadero;
    String Correo_Panadero;
    
    
    //Método para ingresar datos
    public void Guardar() {
    Connection conexion = ClaseConexion.getConexion();
    if (conexion == null) {
        System.out.println("Error: La conexión es nula.");
        return;
    }

    try {
        
        PreparedStatement addPanadero = conexion.prepareStatement("INSERT INTO tbPanadero (UUID_Panadero, Nombre_Panadero, Edad_Panadero, Peso_Panadero, Correo_Panadero) VALUES (?, ?, ?, ?, ?)");
        
        // Establecer los parámetros
        addPanadero.setString(1, UUID.randomUUID().toString());  
        addPanadero.setString(2, getNombre_Panadero()); 
        addPanadero.setInt(3, getEdad_Panadero()); 
        addPanadero.setDouble(4, getPeso_Panadero()); 
        addPanadero.setString(5, getCorreo_Panadero()); 
        
        
        addPanadero.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("este es el error en el modelo: metodo guardar " + ex);
    }
}
    



    //Método para mostrar datos
    public void Mostrar(JTable tabla) {
       
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modeloPanadero = new DefaultTableModel();
        
        modeloPanadero.setColumnIdentifiers(new Object[]{"UUID_Panadero", "Nombre_Panadero", "Edad_Panadero", "Peso_Panadero", "Correo_Panadero"});
        try {
            
            Statement statement = conexion.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM tbPanadero");

            while (rs.next()) {

                modeloPanadero.addRow(new Object[]{rs.getString("UUID_Panadero"), 
                    rs.getString("Nombre_Panadero"), 
                    rs.getInt("Edad_Panadero"), 
                    rs.getString("Peso_Panadero"),
                    rs.getString("Correo_Panadero")});
            }

            tabla.setModel(modeloPanadero);
            
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    
     //Método de actualizar
    
    public void Actualizar(JTable tabla) {

    Connection conexion = ClaseConexion.getConexion();

    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {

        String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        try { 

            PreparedStatement updatePanadero = conexion.prepareStatement("UPDATE tbPanadero SET Nombre_Panadero = ?, Edad_Panadero = ?, Peso_Panadero = ?, Correo_Panadero = ? WHERE UUID_Panadero = ?");
            
            // Establecer los valores a actualizar
            updatePanadero.setString(1, getNombre_Panadero()); 
            updatePanadero.setInt(2, getEdad_Panadero()); 
            updatePanadero.setDouble(3, getPeso_Panadero()); 
            updatePanadero.setString(4, getCorreo_Panadero()); 
            updatePanadero.setString(5, miUUID); 
            
            // Ejecutar la actualización
            updatePanadero.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error en el método de actualizar: " + e);
        }
    } else {
        System.out.println("No se seleccionó ninguna fila para actualizar.");
    }
}
    
    //Método de elimininar
    
     public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //Borramos 
        try {
            PreparedStatement deletePanadero = conexion.prepareStatement("delete from tbPanadero where UUID_Panadero = ?");
            deletePanadero.setString(1, miId);
            deletePanadero.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     
     
        
}