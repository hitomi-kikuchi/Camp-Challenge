package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * insertconfirm.jspと対応するサーブレット
 * フォーム入力された情報はここでセッションに格納し、以降持ちまわることになる
 * 直接アクセスした場合はerror.jspに振り分け
 * @author hayashi-s
 */
public class InsertConfirm extends HttpServlet {

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
        
        try{
            HttpSession hs = request.getSession();
            request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
            //直リンク防止用の処理
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            //フォームからの入力を取得
            String name = request.getParameter("name");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String type = request.getParameter("type");
            String tell = request.getParameter("tell");
            String comment = request.getParameter("comment");
            
            //フォームからの入力をUserDataBeansセット
            UserDataBeans udb = new UserDataBeans();
            udb.setName(name);           
            udb.setYear(Integer.parseInt(year));
            udb.setMonth(Integer.parseInt(month));
            udb.setDay(Integer.parseInt(day));
            udb.setType(Integer.parseInt(type));
            udb.setTell(tell);
            udb.setComment(comment);
            
            //セッションに値をセット
            hs.setAttribute("udb", udb);
            hs.setAttribute("name", name);
            hs.setAttribute("year", year);
            hs.setAttribute("month", month);
            hs.setAttribute("day", day);
            hs.setAttribute("tell", tell);
            hs.setAttribute("comment", comment);
            
            //radioボタンの選択した値の保持
            if(type.equals("1")) {
                hs.setAttribute("checkE", "checked");
                hs.setAttribute("checkS", "");
                hs.setAttribute("checkO", "");
            }else if(type.equals("2")) {
                hs.setAttribute("checkE", "");
                hs.setAttribute("checkS", "checked");
                hs.setAttribute("checkO", "");
            }else {
                hs.setAttribute("checkE", "");
                hs.setAttribute("checkS", "");
                hs.setAttribute("checkO", "checked");
            }
            
            System.out.println("Session updated!!");
            
            request.getRequestDispatcher("/insertconfirm.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
