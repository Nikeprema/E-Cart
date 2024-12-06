package com.example.E_Cart.Address.Service;




import com.example.E_Cart.Address.Model.Address;
import com.example.E_Cart.Address.Repository.AddressRepository;
import com.example.E_Cart.Config.UserContext;
import com.example.E_Cart.user.Model.User;
import com.example.E_Cart.user.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.List;
@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    public Address createAddress(Address address)throws Exception {

        Long userId= UserContext.getUserId();

        User user=userRepository.findById(userId).orElseThrow(()->new Exception("User does not exixts"));
     return addressRepository.save(address);}

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address updateAddress(Long id, Address address) {

        return addressRepository.save(address); }


    public void deleteAddress(Long id){
         addressRepository.deleteById(id);
    }


}
