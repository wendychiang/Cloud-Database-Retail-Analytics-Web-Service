/*
 * WebServlet has doget method which will invoke the method in model to connect to the 
 * Azure database and return the information
 */
package mysql;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wendychiang
 */
@WebServlet(name = "MysqlServlet", urlPatterns = {"/*"})
public class MysqlServlet extends HttpServlet {

    MysqlModel model = null;  // The "business model" for this app

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        model = new MysqlModel();
    }

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

        String name = (request.getPathInfo()).substring(1);
        
        
        if(name.equals("revenue/") || name.equals("newusercount/") || name.equals("arpau/") || name.equals("activeusers/")){

        //result is a string that will send back to client
        String result = "";

        //call the right method based on the ending text of the coming url
        if (name.equals("revenue/")) {

            result = model.getRevenue();

        } else if (name.equals("activeusers/")) {
            result = model.getActiveuser();
        } else if (name.equals("newusercount/")) {
            result = model.getNewuser();
        } else if (name.equals("arpau/")) {
            result = model.getAvgRevenue();
        }

        response.setStatus(200);
        
        // tell the client the type of the response
        response.setContentType("text/plain;charset=UTF-8");
        // return the value from a GET request
        PrintWriter out = response.getWriter();

        out.println(result);
        }
        else{
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Two Six Captical Data Challenge\nName: Ping Jou Chiang");
        }
    }
    
    
    //main is just for testing
    public static void main(String[] args) {
         MysqlModel model = new MysqlModel();

        System.out.println(model.getRevenue());
    }

}
