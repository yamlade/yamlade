package org.yamlade.exception.i18n;

import org.yamlade.parser.model.ParsingContext;

public class ParsingException extends Exception {

	public ParsingException(final ParsingContext context, final String parsedLine, final Integer parsingPosition) {
		super();
		System.out.println(parsedLine);
		System.out.printf("%-" + (parsingPosition) + "s%n", " ^ -- Invalid sequence marker ["+parsedLine.charAt(parsingPosition)+"] detected on line "+context.getCurrentLine());
	}
}
