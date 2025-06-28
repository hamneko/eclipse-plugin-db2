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
		// Process "EXEC" to ";" tokens.
		if ("exec".equalsIgnoreCase(nextToken.toString())) {
			while (true) {
				nextToken = super.nextToken();
				if ("sqlca".equalsIgnoreCase(nextToken.toString())) {
					DB2TokenUtil.addSqlcaTokens(this, nextToken);
					DB2TokenUtil.addGlobalVariableTokens(this, nextToken);
					DB2TokenUtil.addTypeDefs(this, nextToken);
					// Initialize sqlca struct.
					tokens.add(DB2TokenUtil.createStructToken(this, nextToken));
					tokens.add(DB2TokenUtil.createIdentifierToken(this, nextToken, "sqlca"));
					tokens.add(DB2TokenUtil.createIdentifierToken(this, nextToken, "sqlca"));
					continue;
				}
				if ("call".equalsIgnoreCase(nextToken.toString())) {
					return super.nextToken();
				}
				if ("do".equalsIgnoreCase(nextToken.toString())) {
					nextToken = super.nextToken();
					if ("break".equalsIgnoreCase(nextToken.toString())
							|| "continue".equalsIgnoreCase(nextToken.toString())) {
						// ignore those tokens.
						continue;
					}
					return nextToken;
				}
				if (":".equals(nextToken.toString())) {
					nextToken = super.nextToken();
					// Modify tokens to be treated as variables.
					tokens.add(nextToken);
					tokens.add(DB2TokenUtil.createAssignToken(this, nextToken));
					tokens.add(DB2TokenUtil.createIntegerToken(this, nextToken, "0"));
					tokens.add(DB2TokenUtil.createSemiToken(this, nextToken));
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

	public Queue<IToken> tokens() {
		return tokens;
	}

	private IToken clearPrefetchedToken(IToken token) {
		if (token.getNext() != null) {
			token.setNext(null);
		}
		return token;
	}

}
