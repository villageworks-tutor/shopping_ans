package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CategoryBean;
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ShowItemServlet
 */
@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {
	
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		try {
			// すべての商品カテゴリーを取得
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findAllCategory();
			// 取得した商品カテゴリーリストをアプリケーションスコープに登録
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードの設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータからactionキーを取得
		String action = request.getParameter("action");
		// 遷移先URLを初期化
		String nextPage = "pages/errInternal.jsp";
		//actionキーによる処理の分岐
		if (action == null || action.isEmpty() || action.equals("top")) {
			// actionキーが未入力または空文字が指定されているか、または「top」が指定されている場合：遷移先URLを初期画面に設定
			nextPage = "pages/top.jsp";
		} else if (action.equals("list")) {
			// リクエストパラメータから商品カテゴリコードを取得
			String code = request.getParameter("code");
			int categoryCode = Integer.parseInt(code);
			try {
				// 商品カテゴリーコードに合致した商品を取得
				ItemDAO dao = new ItemDAO();
				List<ItemBean> list = dao.findByCategory(categoryCode);
				// 取得した商品リストをリクエストスコープに登録
				request.setAttribute("items", list);
				// 遷移先URLを設定
				nextPage = "pages/list.jsp";
			} catch (DAOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
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
