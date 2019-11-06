package nhom7.shopgiay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.Checkout;
import nhom7.shopgiay.model.AccountModel;
import nhom7.shopgiay.repository.AccountRepository;
import nhom7.shopgiay.repository.CheckoutRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accRep;
	@Autowired
	CheckoutRepository checkoutRep;

	public AccountModel getAccountModel(long accountId) throws Exception {

		Account acc = accRep.findById(accountId).get();

		Map<String, String> address = getHandleAddress(acc.getAddress());

		AccountModel accModel = new AccountModel();
		accModel.setId(acc.getId());
		accModel.setCreated(acc.getCreated());
		accModel.setEmail(acc.getEmail());
		accModel.setPhone(acc.getPhone());
		accModel.setUsername(acc.getUsername());
		accModel.setDetailAddress(address.get("detailAddress"));
		accModel.setCommune(address.get("commune"));
		accModel.setDistrict(address.get("district"));
		accModel.setProvince(address.get("province"));

		System.out.println(accModel.getCommune() + "|" + accModel.getDistrict() + "|" + accModel.getProvince());
		return accModel;
	}

	public Map<String, String> getHandleAddress(String addressInput) throws Exception {
		Map<String, String> address = new HashMap<String, String>();
		String[] add = addressInput.split(";");
		for (String string : add) {
			System.out.println("phan tu: " + string);
		}
		address.put("detailAddress", add[0]);
		address.put("commune", add[1]);
		address.put("district", add[2]);
		address.put("province", add[3]);
		return address;
	}

	
}
