package com.sd.SpringRESTApi.dao;

import com.sd.SpringRESTApi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* JpaRepository provides all basic CRUD features out of the box, Hence no
* need to write all the CRUD operations manually. Instead we just need to
* extend JpaRepository interface and plugged with Entity class and its
* primary key type.
*
* This way we can very easily create DAO class for any entity and no need
* to write same CRUD operations again and again.
*
* JpaRepository provides basis CRUD methods like- findAll(), findById(), save(), delete() etc..
* */

public interface ICustomerDao extends JpaRepository<Customer, Integer> {

}
