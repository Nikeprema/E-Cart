package com.example.E_Cart.Address.Controller;



import com.example.E_Cart.Address.Model.Address;
import com.example.E_Cart.Address.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
   private AddressService addressService;


    @PostMapping
    public Address createAddress(@RequestBody Address address)throws  Exception{
        try {
            return addressService.createAddress(address);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"user not exist",e);
        }

    }



    @GetMapping
    public List<Address> getAllAddress(){
    return addressService.getAllAddress();
    }


    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long addressid, @RequestBody Address address) {
        return addressService.updateAddress(addressid, address); }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok("delete success");
    }
}






