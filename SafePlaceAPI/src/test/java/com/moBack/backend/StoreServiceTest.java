package com.moBack.backend;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.moBack.backend.BackendApplication;
import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.Store;
import com.moBack.backend.service.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {BackendApplication.class})
public class StoreServiceTest extends AbstractTest {

	@Autowired private StoreService storeService;
	
	@Test
	public void findAllTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		storeService.save(store1);
		Store store2 = new Store("star bucks yongin","Park Ji sung","Cafe",35.123,128.123);
		storeService.save(store2);
		List<Store> stores = storeService.findAll();
		assertTrue(stores.size()>=2);
	}
	
	@Test
	public void findByIdTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Store newStore = storeService.save(store1);
		assertEquals(store1,storeService.findById(newStore.getId()));
	}
	
	@Test
	public void saveTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Store newStore = storeService.save(store1);
		assertEquals(store1,newStore);
	}
	
	@Test
	public void deleteByIdTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		storeService.save(store1);
		storeService.deleteById(store1.getId());
	}
	
	@Test
	public void findStoreFromPositionTest() {
		Store store1 = new Store("star bucks jincheon","Hong Kill Dong","Cafe",35.814346,128.524734);
		storeService.save(store1);
		Store store2 = new Store("star bucks yongin","Park Ji sung","Cafe",35.818496,128.536702);
		storeService.save(store2);
		Store store3 = new Store("angelius","amuge","Cafe",36.4344,154.12323);
		storeService.save(store3);
		List<Store> nearStores = storeService.findStoreFromPosition(new Position(35.818689,128.529462), 1000);
		assertTrue(nearStores.size() >= 2);
	}
	
	

}