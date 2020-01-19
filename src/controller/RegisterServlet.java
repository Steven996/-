package controller;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Map;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import dao.DBUtil;

public class RegisterServlet {
	@WebServlet("/RegisterServlet")
	public class registerServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	    public registerServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//������Ӧ���������λ�ı���ҳ���ַ���GBK
//			response.setContentType("text/html;charset=GBK");
			//�����������ı��뷽ʽΪGBK
			request.setCharacterEncoding("GBK");
//			PrintWriter out = response.getWriter();
			//��ȡ���������ֵ
			String un = request.getParameter("username");
			String pw = request.getParameter("password");
			//�û����Ƿ�Ψһ
			String sql="select * from user where userName=?";
			String[] params=new String[] {un};
			Map<String,String> user=new dao.DBUtil_teacher().getMap(sql,params);
			response.setContentType("text/html:charset=GBK");
			PrintWriter out =response.getWriter();
			if(user == null) {
				sql="insert into user values(null,?,?)";
				params= new String[] {un,pw};
				int r = new dao.DBUtil_teacher().update(sql,params);
				if(r==1) {
					out.println("ע��ɹ�");
				}
				else {
					out.println("ע��ʧ�ܣ�");
				}
			}
			else {
				out.println("���û����ѱ�ʹ�ã�");
				out.println("<a href=\"javascript:history.back()\">����</a>");
			}
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
	}
}
