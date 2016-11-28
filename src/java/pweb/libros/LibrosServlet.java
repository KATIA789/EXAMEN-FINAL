package pweb.libros;

import com.sun.xml.ws.tx.coord.common.PendingRequestManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pweb.business.Libro;
import pweb.business.Usuario;
import pweb.business.Venta;
import pweb.data.LibroDB;
import pweb.data.VentaDB;

public class LibrosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "";            
        String message = "";
        
        // reuperar accion actual 
        String action = request.getParameter("action");
        
        if (action == null) 
        {
            url = "index.jsp";
            action = "";  
        }

        // realizar accion y establecer la URL a una pagina apropiada
        if (action.equals("login")) 
        {
            // obteniendo parametros
            String user = "admin";        
            String pass  = "123";                
            
            if (true) 
            {                           
                if (user.equalsIgnoreCase("user")) 
                {                       
                    url = "/libros.jsp";            
                }
                else if (user.equalsIgnoreCase("admin")) 
                {                       
                    url = "/ventas.jsp";                                
                }                                    
            }
            else
            {
                url = "/index.jsp";                            
            }            
        }
        else if(action.equals("comprar"))
        {            
            String codigo = request.getParameter("codigo");
            request.setAttribute("codigo", codigo);
            url = "/pago.jsp";
        }
        else if(action.equals("pagar"))
        {               
            String codigo = request.getParameter("codigo");                                   
            String nombres = request.getParameter("nombres");
            String numtarjeta = request.getParameter("numtarjeta");
            
            Libro libro = LibroDB.selectLibro (codigo);
            
            Venta venta = new Venta ();
            venta.setCodigoLibro (codigo);
            venta.setTitularTarjeta (nombres);
            venta.setNumeroTarjeta (numtarjeta);
            venta.setTotalVenta (libro.getPrecio ());
            VentaDB.insert (venta);
                               
            request.setAttribute("libro", libro);
            request.setAttribute("nombres", nombres);
            request.setAttribute("numtarjeta", "************" + numtarjeta.substring(12));
            url = "/confirmacion.jsp";                        
        }        
        else if (action.equals("ventas"))
        {            
            String codigoVenta = request.getParameter("CodigoVenta");                                   
            String codigoLibro = request.getParameter("CodigoLibro");
            String titularTarjeta = request.getParameter("TitularTarjeta");
            url = "/ventas.jsp";
            
            Venta venta  =
        }
        else if (action.equals("resumen"))
        {               
            url = "/resumen.jsp";   
        }
        
        // forward request and response objects to specified URL
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);  
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
