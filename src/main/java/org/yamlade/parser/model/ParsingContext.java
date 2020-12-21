package org.yamlade.parser.model;

import lombok.Getter;
import lombok.Setter;
import org.yamlade.model.Node;

@Getter
@Setter
public class ParsingContext {
	private Integer indentationLevel = 0;
	private Node currentNode;
	private Integer currentLine = 1;
}
