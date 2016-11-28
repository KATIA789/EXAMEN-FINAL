package pweb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pweb.business.Libro;

import pweb.business.Venta;

public class VentaDB {
    
    public static int insert(Venta venta) 
    {
        // codigo
        return 0;
    }    
    
    public static Venta selectVenta(int codigo) 
    {
        return null;    
    }   
    
    public static ArrayList<Venta> selectVentas() 
    {
        return null;
    } 
}
