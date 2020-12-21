package org.yamlade.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SequenceNode extends Node {
	private List<Node> nodes = new ArrayList<>();
}
