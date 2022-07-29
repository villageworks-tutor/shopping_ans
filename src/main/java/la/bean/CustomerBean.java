package la.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {

	/**
	 * クラスフィールド
	 */
	private String name;	// 顧客名
	private String address;	// 送付先住所
	private String tel;		// 電話番号
	private String email;	// 電子メールアドレス
	
	/**
	 * デフォルトコンストラクタ
	 */
	public CustomerBean() {}

	/**
	 * コンストラクタ
	 * @param name	  顧客名
	 * @param address 送付先住所
	 * @param tel	  電話番号
	 * @param email   電子メールアドレス
	 */
	public CustomerBean(String name, String address, String tel, String email) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
	}

	/**
	 * 顧客名を取得する。
	 * @return name 顧客名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 顧客名を設定する。
	 * @param name 設定する顧客名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 送付先住所を取得する。
	 * @return address 送付先住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 送付先住所を設定する。
	 * @param address 設定する送付先住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 電話番号を取得する。
	 * @return tel 電話番号
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 電話番号を設定する。
	 * @param tel 設定する電話番号
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 電子メールアドレスを取得する。
	 * @return email 電子メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 電子メールアドレスを設定する。
	 * @param email 設定する電子メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerBean [");
		builder.append("name=" + name + ", ");
		builder.append("address=" + address + ", ");
		builder.append("tel=" + tel + ", ");
		builder.append("email=" + email + "]");
		return builder.toString();
	}

}
