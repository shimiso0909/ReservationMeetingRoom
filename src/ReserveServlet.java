

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaBeans.MeetingRoom;
import JavaBeans.ReservationBean;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		MeetingRoom meetingRoom=(MeetingRoom)session.getAttribute("meetingRoom");
		ReservationBean reservation=(ReservationBean)session.getAttribute("reservation");
		String nextPage;
		try {
			meetingRoom.reserve(reservation);
			nextPage="reserved.jsp";
			
		}catch(Exception e) {
			request.setAttribute("errorReason",e.getMessage());
			nextPage="reserveError.jsp";
			
		}
		RequestDispatcher rd=request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
		
		
	}

}
