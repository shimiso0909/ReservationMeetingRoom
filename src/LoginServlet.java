

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaBeans.MeetingRoom;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		HttpSession session=request.getSession();
		MeetingRoom meetingRoom=(MeetingRoom)session.getAttribute("meetingroom");
		if(meetingRoom==null) {
		 meetingRoom=new MeetingRoom();
		 
		}
		String userId=request.getParameter("userId");
		String userPw=request.getParameter("userPw");
		String nextPage;
		boolean result=meetingRoom.login(userId,userPw);
		if(result) {
			nextPage="menu.jsp";
		}else {
			nextPage="login.jsp";
		}
		session.setAttribute("meetingRoom",meetingRoom);
		RequestDispatcher rd=request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
		
		
	}

}
