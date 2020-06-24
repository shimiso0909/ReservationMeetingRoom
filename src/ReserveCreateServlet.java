

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
import JavaBeans.RoomBean;

/**
 * Servlet implementation class ReserveCreateServlet
 */
@WebServlet("/ReserveCreateServlet")
public class ReserveCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session=request.getSession();
		MeetingRoom meetingRoom=(MeetingRoom)session.getAttribute("meetingRoom");
		String roomId=request.getParameter("roomId");
		String time=request.getParameter("time");
		ReservationBean reservation=meetingRoom.createReservation(roomId,time);
		RoomBean room=meetingRoom.getRooms(roomId);
		session.setAttribute("reservation",reservation);
		session.setAttribute("room",room);
		RequestDispatcher rd=request.getRequestDispatcher("reserveConfirm.jsp");
		rd.forward(request,response);
	}

}
