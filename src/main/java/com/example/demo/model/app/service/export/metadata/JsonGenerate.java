package com.example.demo.model.app.service.export.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonGenerate {
	
	final ObjectMapper mapper = new ObjectMapper();
	ArrayNode  rootNode = mapper.createArrayNode();
	ObjectNode label = rootNode.addObject();
	ObjectNode data = rootNode.addObject();


}
