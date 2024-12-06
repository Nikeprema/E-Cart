package com.example.E_Cart.Address.Repository;



import com.example.E_Cart.Address.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    }

