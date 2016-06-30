<%@page import="java.util.ArrayList"%>
<%@page import="jums.UserDataBeans"%>
<%@page import="jums.JumsHelper" %>
<%
    HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    UserDataBeans udb = (UserDataBeans)request.getAttribute("userData");
    ArrayList<String> chkList = udb.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS更新結果画面</title>
    </head>
    <body>
        <% if(chkList.size()==0){ %>
        <h1>更新結果</h1><br>
        名前:<%= udb.getName() %><br>
        生年月日:<%= udb.getYear()+"年"+udb.getMonth()+"月"+udb.getDay()+"日" %><br>
        種別:<%= jh.exTypenum(udb.getType()) %><br>
        電話番号:<%= udb.getTell() %><br>
        自己紹介:<%= udb.getComment() %><br>
        以上の内容で更新しました。<br>
        <form action="ResultDetail" method="POST">
        <%--アクセスルートチェック--%>
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="resultdetail" value="詳細画面へ戻る">
        </form>
        <% }else{ %>
        <h1>入力が不完全です</h1>
        <%=jh.chkinput(chkList) %>
        <% } %>
        <br>
        <%--トップページに戻る--%>
        <%=jh.home()%>
    </body>
</html>
