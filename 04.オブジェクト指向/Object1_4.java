/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kadai;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1999itukinao
 */
//抽象化  class麦わら海賊団（名前、年齢）
//（モンキー・D・ルフィ、19歳）

//課題4．3のクラスを継承し、2つの変数の中身をクリアするパブリックなメソッド

public class Object1_4 extends HttpServlet {
    public String name = "";
    public Integer age = 0;
    class Human extends Object1_4 {
        public void setHuman(String n, Integer a) {
            this.name = n;
            this.age = a;
        }
        public String showName() {
            return name;
        }
        public Integer showAge() {
            return age;
        }
    }
        class Clear extends Human {
        public void setClear(String n, Integer a) {
            this.name = n;
            this.age = a;
        }
        public String showName() {
            return name;
        }
        public Integer showAge() {
            return age;
        }
    }

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Object1_4</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Object1_4 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            Human mugi = new Human();
            mugi.setHuman("モンキー・D・ルフィ", 19);
            
            Clear clear = new Clear();
            clear.setClear("null",0);
            
            out.println(mugi.showName());
            out.println(mugi.showAge());
            
            out.println(clear.showName());
            out.println(clear.showAge());
            
        } finally {
            out.close();
        }
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
