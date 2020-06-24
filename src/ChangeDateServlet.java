

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
 * Servlet implementation class ChangeDateServlet
 */
@WebServlet("/ChangeDateServlet")
public class ChangeDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String date=request.getParameter("date");
		String returnPage=request.getParameter("page");
		HttpSession session=request.getSession();
		MeetingRoom meetingRoom=(MeetingRoom)session.getAttribute("meetingRoom");
		if(date.matches("\\d{4}-\\d{2}-\\d{2}")) { 
			
			meetingRoom.setDate(date);
			session.setAttribute("meetingRoom",meetingRoom);
			
		
			
		}
		RequestDispatcher rd=request.getRequestDispatcher(returnPage);
		rd.forward(request,response);
	}

}
