package org.yamlade.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Stream {
	private List<Document> documents;

	public static StreamBuilder builder() {
		return new StreamBuilder();
	}

	public static class StreamBuilder {
		private List<Document> documents;
		private Document currentDocument;

		public StreamBuilder document(Document document) {
			if (this.documents == null) {
				this.documents = new ArrayList<>();
			}
			this.documents.add(document);
			this.currentDocument = document;
			return this;
		}

		public Document getCurrentDocument() {
			return this.currentDocument;
		}

		public Stream build() {
			Stream stream = new Stream();
			stream.documents = this.documents;
			return stream;
		}
	}
}