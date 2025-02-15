package jitty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import test.AcsServlet;
import test.SsoServlet;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        // コンテキストを作成
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        // SAML 認証のエンドポイントを登録
        context.addServlet(new ServletHolder(new SsoServlet()), "/sso");
        context.addServlet(new ServletHolder(new AcsServlet()), "/saml/acs");

        server.setHandler(context);

        // Jetty サーバーを開始
        server.start();
        server.join();
    }
}
