package lk.ijse.webservice.resource_access.servlet;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import lk.ijse.webservice.resource_access.modal.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

public class ChatConnectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String s = CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        HttpSession session = req.getSession();

        UserDTO userDTO = new Gson().fromJson(s, UserDTO.class);
        userDTO.setDateTime(new Timestamp(System.currentTimeMillis()).toLocalDateTime().toString());

        session.setAttribute("user", userDTO);

    }

}
