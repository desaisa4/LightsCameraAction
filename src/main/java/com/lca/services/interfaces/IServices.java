package com.lca.services.interfaces;

import java.io.IOException;
import java.util.List;

public interface IServices {
	
	public List<String> parseData(String json) throws IOException;

}
