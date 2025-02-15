package jitty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onelogin.saml2.Auth;
import com.onelogin.saml2.settings.Saml2Settings;
import com.onelogin.saml2.settings.SettingsBuilder;


public class SsoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Saml2Settings settings = new SettingsBuilder().fromFile("onelogin.saml.properties").build();
            Auth auth = new Auth(settings, request, response);
            auth.login();  // Microsoft Entra ID へリダイレクト
        } catch (Exception e) {
            throw new ServletException("SSO Authentication failed", e);
        }
    }
}
