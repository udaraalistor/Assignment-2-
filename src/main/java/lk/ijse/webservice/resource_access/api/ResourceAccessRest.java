package lk.ijse.webservice.resource_access.api;

import com.google.gson.Gson;
import lk.ijse.webservice.resource_access.modal.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class ResourceAccessRest extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            System.out.println(e);
        }

        req.getSession().getServletContext().setAttribute("name", "abc");

        String payloadString = jb.toString();

        Gson gson = new Gson();
        Message msg = gson.fromJson(payloadString, Message.class);
        System.out.println(msg);

        req.getSession().setAttribute("session_key", System.nanoTime());
        System.out.println("Session Key : " + req.getSession().getAttribute("session_key"));


        resp.getWriter().println("OK");
    }

    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse res)
            throws ServletException, IOException {

        Message message = new Message("Test", "Message", new Date());
        Gson gson = new Gson();
        String json = gson.toJson(message);

        System.out.println("Session Key : " + req.getSession().getAttribute("session_key"));
        System.out.println("Application Scope Name "+req.getSession().getServletContext().getAttribute("name"));


        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().println(json);
    }

}