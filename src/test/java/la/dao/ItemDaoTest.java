package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import la.bean.CategoryBean;
import la.bean.ItemBean;

class ItemDaoTest {

	/** テスト対象クラス：system under test */
	private ItemDAO sut;
	
	@BeforeEach
	void setUp() throws Exception {
		// テスト対象クラスをインスタンス化
		this.sut = new ItemDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("ItemDAO#findByCategoryメソッドのテストクラス")
	class FindByCategoryTest {
		@Test
		@DisplayName("【Test_22】登録されていない商品カテゴリーコード「-1」の商品リストは空リストである")
		void test_22() throws Exception {
			// setup
			int target = -1;
			List<ItemBean> expected = new ArrayList<>();
			// execute
			List<ItemBean> actual = sut.findByCategory(target);
			// verify
			assertThat(actual, is(expected));
		}
		@Test
		@DisplayName("【Test_21】商品カテゴリーコード「1」の商品は「Javaの基本」「MLB Fun」「料理BOOK!」である")
		void test_21() throws Exception {
			// setup
			int target = 1;
			List<ItemBean> expectedList = new ArrayList<>();
			expectedList.add(new ItemBean(1, "Javaの基本", 2500));
			expectedList.add(new ItemBean(2, "MLB Fun", 980));
			expectedList.add(new ItemBean(3, "料理BOOK!", 1200));
			//execute
			List<ItemBean> actualList = sut.findByCategory(target);
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected =expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドは実装されていません。");
			}
		}
		
	}
	
	@Nested
	@DisplayName("ItemDAO#fidAllCategoryメソッドのテストクラス")
	class FindAllCategoryTest {
		@Test
		@DisplayName("【Test_11】すべての商品カテゴリーを取得できる")
		void test_11() throws Exception {
			// setup
			List<CategoryBean> expectedList = new ArrayList<>();
			expectedList.add(new CategoryBean(1, "本"));
			expectedList.add(new CategoryBean(2, "DVD"));
			expectedList.add(new CategoryBean(3, "ゲーム"));
			// execute
			List<CategoryBean> actualList = sut.findAllCategory();
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					CategoryBean actual = actualList.get(i);
					CategoryBean expected =expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドは実装されていません。");
			}
		}
	}

	@Nested
	@DisplayName("ItemDAO#condtructorメソッドのテストクラス")
	class ConstructorTest {
		@Test
		@DisplayName("【Test_02】ItemDAOのインスタンスのconnフィールドはConnectionクラスのインスタンスである")
		void test_02() throws Exception {
			// setup：privateフィールドにアクセスするための前準備
			ItemDAO target = new ItemDAO();
			Class<? extends ItemDAO> clazz = target.getClass();
			Field targetField = clazz.getDeclaredField("conn");
			targetField.setAccessible(true);
			// execute
			Object actual = targetField.get(target);
			// verify
			assertThat(actual, is(instanceOf(Connection.class)));
		}
		@Test
		@DisplayName("【Test01】ItemDAOをインスタンス化できる")
		void test_01() throws Exception {
			// execute
			Object actual = new ItemDAO();
			// verify
			assertThat(actual, is(instanceOf(ItemDAO.class)));
		}
	}

}
