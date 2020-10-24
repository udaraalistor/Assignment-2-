package lk.ijse.webservice.resource_access.servlet;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import lk.ijse.webservice.resource_access.modal.ChatDTO;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ChatSendServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String s = CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        ChatDTO chatDTO = new Gson().fromJson(s, ChatDTO.class);
        chatDTO.setDateTime(new Timestamp(System.currentTimeMillis()).toString());

        ArrayList<ChatDTO> chatStore = (ArrayList<ChatDTO>) getServletContext().getAttribute("chatStore");
        if (chatStore == null) {
            ArrayList<ChatDTO> objects = new ArrayList<>();
            objects.add(chatDTO);
            getServletContext().setAttribute("chatStore", objects);
        } else {
            chatStore.add(chatDTO);
        }

    }
}
