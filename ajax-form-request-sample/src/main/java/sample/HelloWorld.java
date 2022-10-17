package sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloWorld() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
//		String requestText = "";
//		try(BufferedReader br = new BufferedReader(request.getReader())){
//			String s = "";
//			while((s= br.readLine()) != null) {
//				requestText += s;
//			}
//		}
//		System.out.println(URLDecoder.decode(requestText));
		
		String aaa = request.getParameter("aaa");
		String bbb = request.getParameter("bbb");
		String ccc = request.getParameter("ccc");
		System.out.println(aaa);
		System.out.println(bbb);
		System.out.println(ccc);
		
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().append("resuleCd=0&resultTxt=" + aaa + bbb + ccc);
		
	}

}
