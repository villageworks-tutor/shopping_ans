package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CategoryBean;

/**
 * 商品関連のテーブル（item/category）にアクセスするDAO
 * @author tutor
 */
public class ItemDAO {
	
	/**
	 * クラスフィールド：データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @throws DAOException 
	 */
	public ItemDAO() throws DAOException {
		
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
	 * すべての商品カテゴリーを取得する。
	 * @return List<CategoryBean> 商品カテゴリーリスト
	 * @throws DAOException
	 */
	public List<CategoryBean> findAllCategory() throws DAOException {
		String sql = "SELECT * FROM category ORDER BY code";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);
			 // SQLの実行と結果セットの取得
			 ResultSet rs = pstmt.executeQuery();) {
			// 戻り値としての商品カテゴリーリストを初期化
			List<CategoryBean> list = new ArrayList<>();
			// 結果セットから商品カテゴリーリストを生成
			while (rs.next()) {
				// 商品カテゴリーをインスタンス化
				CategoryBean bean = new CategoryBean();
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				// 商品カテゴリーのインスタンスを商品カテゴリーリストに追加
				list.add(bean);
			}
			
			// 商品カテゴリーリストを返却
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}
