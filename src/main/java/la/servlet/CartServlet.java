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
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータからactionキーを取得
		String action = request.getParameter("action");
		// 遷移先URLの初期化
		String nextPage = "pages/errInternal.jsp";
		// actionキーによる処理の分岐
		if (action == null || action.isEmpty() || action.equals("show")) {
			// 遷移先URLを設定
			nextPage = "pages/cart.jsp";
		} else if (action.equals("add")) {
			try {
				// リクエストパラメータを取得
				String codeString = request.getParameter("code");
				int code = Integer.parseInt(codeString);
				String quantityString = request.getParameter("quantity");
				int quantity = Integer.parseInt(quantityString);
				// セッションからカートを取得
				HttpSession session = request.getSession();
				CartBean cart = (CartBean) session.getAttribute("cart");
				if (cart == null) {
					// カートがない場合：はじめてカートに商品を入れる場合
					cart = new CartBean();
					// セッションに登録
					session.setAttribute("cart", cart);
				}
				// 指定された商品を取得
				ItemDAO dao = new ItemDAO();
				ItemBean item = dao.findByPrimaryKey(code);
				// カートに商品を追加
				cart.addCart(item, quantity);
				// 遷移先URLの設定
				nextPage = "pages/cart.jsp";
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		} else if (action.equals("delete")) {
			// セッションからカートを取得
			HttpSession session = request.getSession(false);
			// エラーメッセージを初期化
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
			
			// リクエストパラメータからcodeキーを取得
			String codeString = request.getParameter("code");
			int code = Integer.parseInt(codeString);
			
			// カートから指定された商品を削除
			cart.deleteCart(code);
			
			// 遷移先URLの設定
			nextPage = "pages/cart.jsp";
		}
		// 遷移先URLの遷移
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
