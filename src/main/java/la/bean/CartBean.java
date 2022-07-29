package la.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ショッピングカートを管理するJavaBean
 * @author tutor
 */
public class CartBean implements Serializable {
	
	/**
	 * クラスフィールド
	 */
	private List<ItemBean> items = new ArrayList<>(); // カート内商品リスト
	private int totalPrice;							  // カート内商品総額
	
	/**
	 * デフォルトコンストラクタ
	 */
	public CartBean() {}

	/**
	 * 商品リストを取得する。
	 * @return List<ItemBean> カート内商品の商品リスト
	 */
	public List<ItemBean> getItems() {
		return this.items;
	}
	
	/**
	 * 商品総額を取得する。
	 * @return カート内の商品の商品総額
	 */
	public int getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * カートに商品を追加する。
	 * @param newItem  追加する商品
	 * @param quantity 追加する数量
	 */
	public void addCart(ItemBean newItem, int quantity) {
		// カート内の商品を走査：カートに入っている商品と入っていない商品の処理の切り分け
		for (ItemBean item : this.items) {
			if (item.getCode() == newItem.getCode()) {
				// すでにカートに入っている商品の場合：購入数量を変更して終了
				int newQuantity = item.getQuantity() + quantity;
				item.setQuantity(newQuantity);
				
				// 商品総額を再計算
				this.recalcTotalPrice();
				
				// 処理の終了
				return;
			}
		}
		
		// カートにない商品の場合
		newItem.setQuantity(quantity);
		this.items.add(newItem);
		
		// 商品総額を再計算
		this.recalcTotalPrice();
		
	}
	
	/**
	 * 商品総額を計算する。
	 */
	private void recalcTotalPrice() {
		// 再計算する商品総額の初期化
		int total = 0;
		// カート内の商品総額を計算
		for (ItemBean item : this.items) {
			total += item.getPrice() * item.getQuantity();
		}
		// 計算結果を商品総額に設定
		this.totalPrice = total;
	}

}
