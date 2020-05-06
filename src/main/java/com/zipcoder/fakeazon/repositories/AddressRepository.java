package com.zipcoder.fakeazon.repositories;

import com.zipcoder.fakeazon.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Integer, Address> {

}