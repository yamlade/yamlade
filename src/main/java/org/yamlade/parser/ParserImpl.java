package org.yamlade.parser;

import lombok.extern.slf4j.Slf4j;
import org.yamlade.exception.i18n.ParsingException;
import org.yamlade.model.*;
import org.yamlade.parser.model.ParsingContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Slf4j
public class ParserImpl implements Parser {
	private static final Pattern COMMENT_PATTERN = Pattern.compile("\\s*#.*");
	private static final Predicate<String> commentFilter = str -> !COMMENT_PATTERN.matcher(str).matches();

	public Stream parseInput(final BufferedReader br) {
		Stream stream = new Stream();
		Document.DocumentBuilder documentBuilder = Document.builder();
		ParsingContext context = new ParsingContext();
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				int currentLineIndentation = determineIndentation(context, line);
				if(currentLineIndentation < context.getIndentationLevel()) {
					if(context.getCurrentNode() != null) {
						documentBuilder.node(context.getCurrentNode());
						context.setCurrentNode(null);
					}
				}
				context.setIndentationLevel(currentLineIndentation);
				switch (line.charAt(currentLineIndentation)) {
					case Indicators.SEQUENCE_ENTRY:
						if (context.getCurrentNode() == null) {
							context.setCurrentNode(new SequenceNode());
						} else if (context.getCurrentNode().getClass() != SequenceNode.class) {
							documentBuilder.node(context.getCurrentNode());
							context.setCurrentNode(new SequenceNode());
						}
						processSequenceLine(context, line);
						break;
					default:
						log.warn("Unexpected character reached {}", line.charAt(0));
						break;
				}
				context.setCurrentLine(context.getCurrentLine() + 1);
			}
			documentBuilder.node(context.getCurrentNode());
			stream = Stream.builder().document(documentBuilder.build()).build();
		} catch (IOException e) {
			log.error("Unexpected error while reading file", e);
		} catch (ParsingException e) {

		}
		return stream;
	}

	private void processSequenceLine(final ParsingContext context, final String line) throws ParsingException {
		int separatorPlacement = context.getIndentationLevel() + 1;
		if (line.charAt(separatorPlacement) != Indicators.SPACE && line.charAt(separatorPlacement) != Indicators.TAB) {
			throw new ParsingException(context, line, separatorPlacement);
		}

		String remainingLine = line.substring(context.getIndentationLevel() + 2);
		ScalarNode lineNode = new ScalarNode();
		lineNode.setValue(remainingLine);
		SequenceNode currentNode = (SequenceNode) context.getCurrentNode();
		currentNode.getNodes().add(lineNode);
	}

	private int determineIndentation(final ParsingContext context, final String line) {
		int indentationLevel = 0;
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == Indicators.SPACE) {
				indentationLevel++;
			} else {
				break;
			}
		}
		return indentationLevel;
	}
}