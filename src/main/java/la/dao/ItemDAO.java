package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CategoryBean;
import la.bean.ItemBean;

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

	/**
	 * 指定された商品カテゴリーに含まれる商品を取得する。
	 * @param categoryCode 商品カテゴリーコード
	 * @return List<ItemBean> 商品リスト：登録されていない商品カテゴリーが指定された場合は空リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findByCategory(int categoryCode) throws DAOException {
		String sql = "SELECT * FROM item WHERE category_code=? ORDER BY code";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// パラメータバインディング
			pstmt.setInt(1, categoryCode);
			try (// SQLの実行と結果セットの取得
				 ResultSet rs = pstmt.executeQuery();) {
				// 商品リストを初期化
				List<ItemBean> list = new ArrayList<>();
				// 結果セットから商品リストを生成
				while (rs.next()) {
					// 商品をインスタンス化
					ItemBean bean = new ItemBean();
					bean.setCode(rs.getInt("code"));
					bean.setName(rs.getString("name"));
					bean.setPrice(rs.getInt("price"));
					// 商品のインスタンスを商品リストに追加
					list.add(bean);
				}
				// 商品リストを返却
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 指定した商品番号の主賓を取得する。
	 * @param code 商品番号
	 * @return ItemBean 商品番号に対応した商品：未登録の商品番号が指定された場合はnullを返す。
	 * @throws DAOException
	 */
	public ItemBean findByPrimaryKey(int code) throws DAOException {
		String sql = "SELECT * FROM item WHERE code=?";
		try(// SQL実行オブジェクトを取得
			PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// パラメータバインディング
			pstmt.setInt(1, code);
			try (// SQLの実行と結果セットの取得
				 ResultSet rs = pstmt.executeQuery()) {
				// 戻り値の商品を初期化
				ItemBean bean = null;
				// 結果セットから商品を取得
				if (rs.next()) {
					// 商品をインスタンス化
					bean = new ItemBean();
					bean.setCode(rs.getInt("code"));
					bean.setName(rs.getString("name"));
					bean.setPrice(rs.getInt("price"));
				}
				// 商品を返却
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}
