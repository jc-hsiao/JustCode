package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.repositories.ShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ShopServiceTest {

    @Autowired
    private ShopService service;

    @MockBean
    private ShopRepository repo;

    Shop mockShop = new Shop();

    @Test
    @DisplayName("Test Check Shop Existence: PASS")
    public void testItemExists() throws Exception {
        doReturn(Optional.of(mockShop)).when(repo).findById(1);
        assertNotNull(service.checkIfShopExists(1));
    }

    @Test
    @DisplayName("Test Check Shop Existence: FAIL")
    public void testItemNotExist() throws Exception {
        Assertions.assertThrows(Exception.class,
                () -> service.checkIfShopExists(15));
    }

    @Test
    @DisplayName("Test Find By Id: PASS")
    public void testFindByIdPass(){
        doReturn(Optional.of(mockShop)).when(repo).findById(1);
        assertTrue(service.findOne(1).isPresent());
    }

    @Test
    @DisplayName("Test Find All Shops")
    public void testFindAllItems(){
        List<Shop> shops = new ArrayList<>(Arrays.asList(new Shop(), new Shop()));
        doReturn(shops).when(repo).findAll();
        assertEquals(2, service.findAll().size());
    }

    @Test
    @DisplayName("Test Save Shop")
    public void testSaveShop(){
        doReturn(mockShop).when(repo).save(any());
        assertNotNull(service.saveShop(mockShop));
    }

    @Test
    @DisplayName("Test Delete Shop: PASS")
    public void testDeleteShop(){
        doReturn(Optional.of(mockShop)).when(repo).findById(1);
        assertTrue(service.deleteShop(1));
    }

    @Test
    @DisplayName("Test Delete Shop: FAIL")
    public void testDeleteShopFail(){
        doReturn(Optional.of(mockShop)).when(repo).findById(1);
        assertFalse(service.deleteShop(5));
    }

    @Test
    @DisplayName("Test Update Shop Name")
    public void testUpdateShopName() throws Exception {
        Shop testShop = new Shop();
        testShop.setName("Test");
        doReturn(Optional.of(testShop)).when(repo).findById(1);
        doReturn(testShop).when(repo).save(testShop);
        String expected = "Updated";

        String actual = service.updateName(1,expected).getName();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Update Shop Description")
    public void testUpdateShopDescription() throws Exception {
        Shop testShop = new Shop();
        testShop.setDescription("Test Test Test");
        doReturn(Optional.of(testShop)).when(repo).findById(1);
        doReturn(testShop).when(repo).save(testShop);
        String expected = "Updated Updated Updated";

        String actual = service.updateDescription(1,expected).getDescription();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Update Shop Logo URL")
    public void testUpdateShopLogoUrl() throws Exception {
        Shop testShop = new Shop();
        testShop.setLogoUrl("imgur.com/myLogo");
        doReturn(Optional.of(testShop)).when(repo).findById(1);
        doReturn(testShop).when(repo).save(testShop);
        String expected = "imgur.com/updatedLogo";

        String actual = service.updateLogoUrl(1,expected).getLogoUrl();
        assertEquals(expected,actual);
    }

}