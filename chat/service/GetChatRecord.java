package service;

import dao.MessageDao;
import dao.MessageDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 查询聊天记录
 */

@WebServlet("/GetChatRecord")
public class GetChatRecord extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String first=req.getParameter("first");
        String second=req.getParameter("second");

        MessageDao messageDao=new MessageDaoImpl();
        String chatRecord=messageDao.getChatRecord(first,second).toString();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        out.println(chatRecord);
    }
}
