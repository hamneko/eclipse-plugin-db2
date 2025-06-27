package plugin.db2.parser.scanner;

import java.util.ArrayDeque;
import java.util.Queue;

import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.parser.EndOfFileException;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.IncludeFileContentProvider;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.internal.core.parser.scanner.CPreprocessor;
import org.eclipse.cdt.internal.core.parser.scanner.TokenUtil;
import org.eclipse.cdt.internal.core.parser.scanner.TokenWithImage;

@SuppressWarnings("restriction")
public class DB2Scanner extends CPreprocessor {

	/*
	 * Inner Queue for token manipulation.
	 */
	private Queue<IToken> tokens = new ArrayDeque<>();

	public DB2Scanner(FileContent fileContent, IScannerInfo info, ParserLanguage language, IParserLogService log,
			IScannerExtensionConfiguration configuration, IncludeFileContentProvider readerFactory) {
		super(fileContent, info, language, log, configuration, readerFactory);
	}

	@Override
	public IToken nextToken() throws EndOfFileException {
		if (tokens.peek() != null) {
			return clearPrefetchedToken(tokens.poll());
		}
		IToken nextToken = super.nextToken();

		// Ignore from "EXEC" to ";" tokens.
		if ("exec".equalsIgnoreCase(nextToken.toString())) {
			IToken peekNextToken;
			while (true) {
				nextToken = super.nextToken();
				if ("sqlca".equalsIgnoreCase(nextToken.toString())) {
					DB2TokenUtil.addSqlcaTokens(this, nextToken);
					DB2TokenUtil.addGlobalVariableTokens(this, nextToken);
					DB2TokenUtil.addTypeDefs(this, nextToken);
					// To prevent warning, assign dummy value.
					IToken t1 = new TokenWithImage(IToken.t_struct, this, nextToken.getOffset(),
							nextToken.getEndOffset(), TokenUtil.getImage(IToken.t_struct));
					IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, this, nextToken.getOffset(),
							nextToken.getEndOffset(), "sqlca".toCharArray());
					IToken t3 = new TokenWithImage(IToken.tIDENTIFIER, this, nextToken.getOffset(),
							nextToken.getEndOffset(), "sqlca".toCharArray());
					tokens.add(t1);
					tokens.add(t2);
					tokens.add(t3);
					continue;
				}
				if ("call".equalsIgnoreCase(nextToken.toString())) {
					return super.nextToken();
				}
				if ("do".equalsIgnoreCase(nextToken.toString())) {
					peekNextToken = super.nextToken();
					nextToken.setNext(null);
					if ("break".equalsIgnoreCase(peekNextToken.toString())
							|| "continue".equalsIgnoreCase(peekNextToken.toString())) {
						// ignore those tokens
						continue;
					}
					return peekNextToken;
				}
				if (":".equals(nextToken.toString())) {
					nextToken = super.nextToken();
					// To prevent warning, assign dummy value.
					IToken t1 = new TokenWithImage(IToken.tASSIGN, this, nextToken.getOffset(),
							nextToken.getEndOffset(), TokenUtil.getImage(IToken.tASSIGN));
					IToken t2 = new TokenWithImage(IToken.tSTRING, this, nextToken.getOffset(),
							nextToken.getEndOffset(), "\"FAKE_STRING\"".toCharArray());
					IToken t3 = new TokenWithImage(IToken.tSEMI, this, nextToken.getOffset(), nextToken.getEndOffset(),
							TokenUtil.getImage(IToken.tSEMI));
					tokens.add(nextToken);
					tokens.add(t1);
					tokens.add(t2);
					tokens.add(t3);
					continue;
				}
				if (";".equals(nextToken.toString())) {
					tokens.add(nextToken);
					return clearPrefetchedToken(tokens.poll());
				}
			}
		}
		return nextToken;
	}

	public Queue<IToken> getTokens() {
		return tokens;
	}

	private IToken clearPrefetchedToken(IToken token) {
		if (token.getNext() != null) {
			token.setNext(null);
		}
		return token;
	}

}
