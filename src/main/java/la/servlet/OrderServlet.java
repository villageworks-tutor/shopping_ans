package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CartBean;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 遷移先URLの初期化
		String nextPage = "pages/customerInfo.jsp";
		// セッションからカートを取得
		HttpSession session = request.getSession(false);
		// エラーメッセージの初期化
		String message = "";
		if (session == null) {
			// セッションがない場合：セッションタイムアウトと判断
			message = "セッションが切れています。もう一度トップページから操作して下さい。";
			request.setAttribute("message", message);
			// 遷移先URLの遷移：エラーページで初期化しているので遷移先は未設定で構わない。
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			return;
		}
		
		// カートを取得
		CartBean cart = (CartBean) session.getAttribute("cart");
		if (cart == null) {
			// カートがない場合：不正な走査と判断
			message = "正しい操作をして下さい。";
			// 遷移先URLの遷移：エラーページで初期化しているので遷移先は未設定で構わない。
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			return;
		}
		
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータのactionキーを取得
		String action = request.getParameter("action");
		// actionキーによって処理を分岐
		if (action == null || action.isEmpty() || action.equals("entry")) {
			// 遷移先URLを設定
			nextPage = "pages/customerInfo.jsp";
		}
		// 遷移先URLに遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
