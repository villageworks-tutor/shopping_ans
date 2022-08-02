package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.CartBean;
import la.bean.CustomerBean;
import la.bean.ItemBean;

/**
 * 注文関連のテーブル（customer/ordered/ordered_detail）にアクセスするDAO
 * @author tutor
 */
public class OrderDAO {
	
	/**
	 * クラスフィールド：データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @throws DAOException 
	 */
	public OrderDAO() throws DAOException {
		
		// データベース接続情報文字列
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/sample";
		String user = "student";
		String password = "himitu";
		
		try {
			// JDBCドライバのロード
			Class.forName(driver);
			// データベース接続オブジェクトを取得：データベースへの接続完了
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("データベースへの接続に失敗しました。");
		}
	}

	/**
	 * 注文処理を実行する。
	 * @param customer 送付先情報
	 * @param cart     カート
	 * @return         新規注文番号
	 * @throws DAOException
	 */
	public int saveOrder(CustomerBean customer, CartBean cart) throws DAOException {
		// 処理実行日付の取得
		Date today = new Date(System.currentTimeMillis());
		// 実行するSQL文字列の初期化
		String sql = "";
		// 新規顧客番号を取得
		int customerCode = 0;
		sql = "SELECT nextval('customer_code_seq')";
		try (// SQL実行オブジェクトの取得と結果セットの取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();) {
			// 新規顧客番号を初期化
			if (rs.next()) {
				customerCode = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		
		// 顧客を登録
		sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?)";
		try (// SQL実行オブジェクトの取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// パラメータバインディング
			pstmt.setInt(1, customerCode);
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getAddress());
			pstmt.setString(4, customer.getTel());
			pstmt.setString(5, customer.getEmail());
			// SQLの実行
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		
		//　新規注文番号を取得
		int orderNumber = -1;
		sql = "SELECT nextval('ordered_code_seq')";
		try (// SQL実行オブジェクトの取得と結果セットの取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();) {
			// 新規注文番号の取得
			if (rs.next()) {
				orderNumber = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		
		// 注文の登録
		sql = "INSERT INTO ordered VALUES (?, ?, ?, ?)";
		try (// SQL実行オブジェクトの取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// パラメータバインディング
			pstmt.setInt(1, orderNumber);
			pstmt.setInt(2, customerCode);
			pstmt.setDate(3, today);
			pstmt.setInt(4, cart.getTotalPrice());
			// SQLの実行
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		
		// 注文明細の登録
		sql = "INSERT INTO ordered_detail VALUES (?, ?, ?)";
		try (// SQL実行オブジェクトの取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// カート内の商品の登録
			for (ItemBean item : cart.getItems()) {
				// パラメータバインディング
				pstmt.setInt(1, orderNumber);
				pstmt.setInt(2, item.getCode());
				pstmt.setInt(3, item.getQuantity());
				// SQLの実行
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		
		// 注文番号を返却
		return orderNumber;
	}

}
