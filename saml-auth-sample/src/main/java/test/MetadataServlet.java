package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onelogin.saml2.settings.Metadata;
import com.onelogin.saml2.settings.Saml2Settings;
import com.onelogin.saml2.settings.SettingsBuilder;

@WebServlet("/metadata")
public class MetadataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Saml2Settings settings = new SettingsBuilder().fromFile("onelogin.saml.properties").build();
            Metadata metadata = new Metadata(settings);
            response.setContentType("application/xml");
            response.getWriter().write(metadata.getMetadataString());
        } catch (Exception e) {
            throw new ServletException("Error generating metadata", e);
        }
    }
}
