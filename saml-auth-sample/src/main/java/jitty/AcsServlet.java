package jitty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onelogin.saml2.Auth;
import com.onelogin.saml2.settings.Saml2Settings;
import com.onelogin.saml2.settings.SettingsBuilder;

public class AcsServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Saml2Settings settings = new SettingsBuilder().fromFile("onelogin.saml.properties").build();
			Auth auth = new Auth(settings, request, response);
			auth.processResponse(); // SAML レスポンスを検証

			if (!auth.isAuthenticated()) {
				throw new ServletException("SAML Authentication failed");
			}

			// 認証成功 → ユーザー情報をセッションに保存
			String nameId = auth.getNameId();
			request.getSession().setAttribute("user", nameId);

			response.sendRedirect("/home");
		} catch (Exception e) {
			throw new ServletException("Error processing SAML Response", e);
		}
	}
}
