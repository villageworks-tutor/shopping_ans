package la.dao;

/**
 * DAOクラスで発生したすべての例外を変換する独自例外
 * @author tutor
 */
public class DAOException extends Exception {

	/**
	 * コンストラクタ
	 * @param message 例外メッセージ
	 */
	public DAOException(String message) {
		super(message);
	}

}
