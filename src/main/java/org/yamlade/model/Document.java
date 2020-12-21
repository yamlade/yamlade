package org.yamlade.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Document {
	private List<Node> nodes;

	public static DocumentBuilder builder() {
		return new DocumentBuilder();
	}

	public static class DocumentBuilder {
		private List<Node> nodes;
		private Node currentNode;
		private Integer blockLevel = 0;

		public DocumentBuilder node(Node node) {
			if(nodes == null) {
				this.nodes = new ArrayList<>();
			}
			this.nodes.add(node);
			return this;
		}

		public Document build() {
			Document document = new Document();
			document.nodes = this.nodes;
			return document;
		}
	}
}
