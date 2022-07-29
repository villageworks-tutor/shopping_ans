package la.bean;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CartBeanTest {

	/** テスト対象キウラス：system under test */
	private CartBean sut;
	
	@BeforeEach
	void setUp() throws Exception {
		// テスト対象クラスのインスタンス化
		this.sut = new CartBean();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("CartBean#deleteCartメソッドのテストクラス")
	class DeleteCartTest {
		@Test
		@DisplayName("【Test_23】商品番号「８」の商品が２個と商品番号「３」の商品が４個入っているカートから商品番号「８」の商品を削除するとカートの商品総額は4800円である")
		void test_23() {
			// setup
			int target = 8;
			int expected = 4800;
			sut.addCart(new ItemBean(8, "Invader Fighter", 3400), 2);
			sut.addCart(new ItemBean(3, "料理BOOK!", 1200) , 4);
			// execute
			sut.deleteCart(target);
			int actual = sut.getTotalPrice();
			// verify
			assertThat(actual, is(expected));
		}
		@Test
		@DisplayName("【Test_22】商品番号「８」の商品が２個と商品番号「３」の商品が４個入っているカートから商品番号「８」の商品を削除するとカートは商品番号「３」の商品だけになる")
		void test_22() {
			// setup
			int target = 8;
			ItemBean bean = new ItemBean(3, "料理BOOK!", 1200);
			bean.setQuantity(4);
			List<ItemBean> expectedList = new ArrayList<>();
			expectedList.add(bean);
			sut.addCart(new ItemBean(8, "Invader Fighter", 3400), 2);
			sut.addCart(new ItemBean(3, "料理BOOK!", 1200) , 4);
			// execute
			sut.deleteCart(target);
			List<ItemBean> actualList = sut.getItems();
			// verify
			if (actualList.size() >= 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドは実装されていません。");
			}
			
		}
		@Test
		@DisplayName("【Test_21】商品番号「８」の商品が２個入っているカートから商品番号「８」の商品を削除するとカートは空である")
		void test_21() {
			// setup
			int target = 8;
			List<ItemBean> expected = new ArrayList<>();
			sut.addCart(new ItemBean(8, "Invader Fighter", 3400), 2);
			// execute
			sut.deleteCart(target);
			List<ItemBean> actual = sut.getItems();
			// verify
			assertThat(actual, is(expected));
		}
	}

	@Nested
	@DisplayName("CartBean#addCartメソッドのテストクラス")
	class AddCartTest {
		
		/** テスト補助メソッド */
		private List<ItemBean> createPreCart(String testCode) {
			List<ItemBean> preCart = new ArrayList<>();
			ItemBean bean = null;
			
			if (testCode.equals("11")) {
				bean = new ItemBean(1, "Javaの基本", 2500);
				bean.setQuantity(1);
				preCart.add(bean);
			} else if (testCode.equals("12")) {
				bean = new ItemBean(1, "Javaの基本", 2500);
				bean.setQuantity(1);
				preCart.add(bean);
				
				bean = new ItemBean(4, "なつかしのアニメシリーズ", 2000);
				bean.setQuantity(1);
				preCart.add(bean);
			} else if (testCode.equals("13")) {
				bean = new ItemBean(1, "Javaの基本", 2500);
				bean.setQuantity(2);
				preCart.add(bean);
				
				bean = new ItemBean(4, "なつかしのアニメシリーズ", 2000);
				bean.setQuantity(1);
				preCart.add(bean);
			}
			return preCart;
		}
		
		@Test
		@DisplayName("【Test_15】商品番号「１」の商品を１個と商品番号「４」の商品を１個が入っているカートに商品番号「４」の商品２個追加すると商品総額は8500円である")
		void test_15() {
			// setup
			sut.addCart(new ItemBean(1, "Javaの基本", 2500), 1);
			sut.addCart(new ItemBean(4, "なつかしのアニメシリーズ", 2000), 1);
			sut.addCart(new ItemBean(4, "なつかしのアニメシリーズ", 2000), 2);
			int expected = 8500;
			// execute
			int actual = sut.getTotalPrice();
			// verify
			assertThat(actual, is(expected));
		}
		@Test
		@DisplayName("【Test_14】商品番号「１」の商品を１個入っているカートに商品番号「４」の商品を１個入れると商品総額は4500円である")
		void test_14() {
			// setup
			sut.addCart(new ItemBean(1, "Javaの基本", 2500), 1);
			sut.addCart(new ItemBean(4, "なつかしのアニメシリーズ", 2000), 1);
			int expected = 4500;
			// execute
			int actual = sut.getTotalPrice();
			// verify
			assertThat(actual, is(expected));
		}
		
		@Test
		@DisplayName("【Test_13】商品番号「１」の商品１個と商品番号「４」の商品を１個が入っているカートに商品番号「１」の商品を１個追加できる")
		void test_13() {
			// setup
			ItemBean target = new ItemBean(1, "Javaの基本", 2500);
			int quantity = 1;
			List<ItemBean> expectedList = createPreCart("13");
			sut.addCart(new ItemBean(1, "Javaの基本", 2500), 1);
			sut.addCart(new ItemBean(4, "なつかしのアニメシリーズ", 2000), 1);
			// execute
			sut.addCart(target, quantity);
			List<ItemBean> actualList = sut.getItems();
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドは実装されていません。");
			}
		}
		@Test
		@DisplayName("【Test_12】商品番号「１」の商品を１個入っているカートに商品番号「４」の商品を１個入れることができる")
		void test_12() {
			// setup
			ItemBean target = new ItemBean(4, "なつかしのアニメシリーズ", 2000);
			int quantity = 1;
			List<ItemBean> expectedList = createPreCart("12");
			sut.addCart(new ItemBean(1, "Javaの基本", 2500), 1);
			// execute
			sut.addCart(target, quantity);
			List<ItemBean> actualList = sut.getItems();
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドは実装されていません。");
			}
		}
		@Test
		@DisplayName("【Test_11】空のカートに商品番号「１」の商品を１個入れることができる")
		void test_11() {
			// setup
			ItemBean target = new ItemBean(1, "Javaの基本", 2500);
			int quantity = 1;
			List<ItemBean> expectedList = createPreCart("11");
			// execute
			sut.addCart(target, quantity);
			List<ItemBean> actualList = sut.getItems();
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドは実装されていません。");
			}
		}
	}
	
	@Nested
	@DisplayName("CartBean#constructorのテストクラス")
	class ConstructorTest {
		@Test
		@DisplayName("【Test_01】CartBeanはインスタンス化できる")
		void test_01() {
			// execute
			Object actual = new CartBean();
			// verify
			assertThat(actual, is(instanceOf(CartBean.class)));
		}
	}

}
