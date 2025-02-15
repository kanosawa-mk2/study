package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onelogin.saml2.Auth;
import com.onelogin.saml2.settings.Saml2Settings;
import com.onelogin.saml2.settings.SettingsBuilder;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Saml2Settings settings = new SettingsBuilder().fromFile("onelogin.saml.properties").build();
            Auth auth = new Auth(settings, request, response);
            auth.logout();  // IdP へ SLO リクエスト
        } catch (Exception e) {
            throw new ServletException("Logout failed", e);
        }
    }
}
