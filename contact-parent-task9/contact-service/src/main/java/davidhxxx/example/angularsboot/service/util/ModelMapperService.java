package davidhxxx.example.angularsboot.service.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

	private ModelMapper modelMapper = new ModelMapper();

	public <D> D map(Object source, Class<D> destinationType) {
		return modelMapper.map(source, destinationType);
	}

	public <D> List<D> mapList(List<?> inputList, Class<D> class1) {
		List<D> targetList = new ArrayList<>();
		for (Object o : inputList) {
			targetList.add(map(o, class1));
		}
		return targetList;
	}

}
