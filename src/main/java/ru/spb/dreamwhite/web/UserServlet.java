package ru.spb.dreamwhite.web;

import ru.spb.dreamwhite.Config;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepository = Config.get().getUserRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
     //   Integer id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        User user = new User(email);
        userRepository.save(user);

        response.sendRedirect("user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
     /*
        Integer id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        User user;
        switch (action) {
            case "view":
                user = userRepository.get(id);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("user", user);
        */
        request.getRequestDispatcher("/WEB-INF/user.jsp" ).forward(request, response);
    }

}
