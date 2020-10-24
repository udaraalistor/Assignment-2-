package lk.ijse.webservice.resource_access.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lk.ijse.webservice.resource_access.modal.ChatDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ChatReceiveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        ArrayList<ChatDTO> chatStore = (ArrayList<ChatDTO>) getServletContext().getAttribute("chatStore");

        if (chatStore != null) {

            String element = new Gson().toJson(chatStore, new TypeToken<ArrayList<ChatDTO>>() {}.getType());
            System.out.println(element);
            resp.getWriter().print(element);

        }else {
            resp.getWriter().print("{}");
        }
    }

}
