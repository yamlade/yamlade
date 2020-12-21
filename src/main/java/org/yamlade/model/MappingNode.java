package org.yamlade.model;

import lombok.Data;

import java.util.Map;

@Data
public class MappingNode extends Node {
	private Map<Node, Node> pairs;
}