package com.moBack.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.moBack.backend.dao.StoreRepository;
import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.Store;
import com.moBack.backend.entity.User;
import com.moBack.backend.service.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {BackendApplication.class})
public class StoreServiceTest extends AbstractTest {

	@Autowired private StoreService storeService;
	@MockBean private StoreRepository storeRepository;
	
	@Test
	public void findAllTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Store store2 = new Store("star bucks yongin","Park Ji sung","Cafe",35.123,128.123);
		Mockito.when(storeRepository.findAll()).thenReturn(Arrays.asList(store1,store2));
		List<Store> stores = storeService.findAll();
		assertTrue(stores.size() == 2);
	}
	
	@Test
	public void findByIdTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Mockito.when(storeRepository.findById(store1.getId())).thenReturn(Optional.of(store1));
		assertEquals(store1,storeService.findById(store1.getId()));
	}
	
	@Test
	public void saveTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Mockito.when(storeRepository.save(store1)).thenReturn(store1);
		Store newStore = storeService.save(store1);
		assertEquals(store1,newStore);
	}
	
	@Test
	public void deleteByIdTest() {
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Mockito.when(storeRepository.findById(store1.getId())).thenReturn(Optional.of(store1));
		storeService.deleteById(store1.getId());
		
		Mockito.when(storeRepository.findById(store1.getId())).thenReturn(null);
		assertThrows(RuntimeException.class,() -> {storeService.deleteById(store1.getId());});
	}
	
	@Test
	public void findStoreFromPositionTest() {
		Store store1 = new Store("star bucks jincheon","Hong Kill Dong","Cafe",35.814346,128.524734);
		Store store2 = new Store("star bucks yongin","Park Ji sung","Cafe",35.818496,128.536702);
		Store store3 = new Store("angelius","amuge","Cafe",36.4344,154.12323);
		Mockito.when(storeRepository.findAll()).thenReturn(Arrays.asList(store1,store2,store3));
		List<Store> nearStores = storeService.findStoreFromPosition(new Position(35.818689,128.529462), 1000);
		assertTrue(nearStores.size() == 2);
	}
	
	

}