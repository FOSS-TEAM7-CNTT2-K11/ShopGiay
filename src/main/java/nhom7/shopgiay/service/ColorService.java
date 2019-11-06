package nhom7.shopgiay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhom7.shopgiay.entity.Color;
import nhom7.shopgiay.repository.ColorRepository;


@Service
public class ColorService {

	@Autowired
	ColorRepository colorRep;
	
	public void createNewColor(Color color, String colorInput) throws Exception{
		color.setColor(colorInput);
		
		colorRep.save(color);
	}
	
}
