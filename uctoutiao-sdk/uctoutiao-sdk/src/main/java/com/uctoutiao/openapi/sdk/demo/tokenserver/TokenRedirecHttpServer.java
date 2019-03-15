package com.uctoutiao.openapi.sdk.demo.tokenserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class TokenRedirecHttpServer {

  private static TokenStorageService storageService = new TokenStorageService();

  public static void main(String[] args) {
    try {
      ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
      context.setContextPath("/token");
      Server server = new Server(8999);
      server.setHandler(context);

      // redirect_url post： localhost:8080/token/update
      context.addServlet(new ServletHolder(new TokenUpdateServlet()), "/update");

      // 客户端获取token url： get localhost:8080/token/get
      context.addServlet(new ServletHolder(new TokenGetServlet()), "/get");

      // 启动服务器
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static class TokenGetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

      String token = storageService.getAccessToken();

      response.setContentType("application/json; charset=utf8");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("{\"code\":\"0\", \"msg\":\"success\", \"token\":\"" + token + "\"}");
    }
  }

  static class TokenUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      String newToken = request.getParameter("access_token");
      System.out.println(newToken);
      if (StringUtils.isNotBlank(newToken)) {

        storageService.storeNewAccessToken(newToken);
        System.out.println(newToken);

        response.setContentType("application/json; charset=utf8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"code\":\"0\", \"msg\":\"success\"}");
      } else {
        response.setContentType("application/json; charset=utf8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"code\":\"1\", \"msg\":\"no token\"}");
      }
    }
  }
}
