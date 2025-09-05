package plugin.db2.parser.scanner;

import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.parser.EndOfFileException;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.IncludeFileContentProvider;
import org.eclipse.cdt.core.parser.ParserLanguage;

import plugin.common.parser.scanner.PluginScanner;
import plugin.common.parser.scanner.PluginTokenUtil;

@SuppressWarnings("restriction")
public class DB2Scanner extends PluginScanner {

	public DB2Scanner(FileContent fileContent, IScannerInfo info, ParserLanguage language, IParserLogService log,
			IScannerExtensionConfiguration configuration, IncludeFileContentProvider readerFactory) {
		super(fileContent, info, language, log, configuration, readerFactory);
	}

	@Override
	public IToken nextToken() throws EndOfFileException {
		if (tokens().peek() != null) {
			return clearPrefetchedToken(tokens().poll());
		}
		IToken nextToken = super.nextToken();
		// Process "EXEC" to ";" tokens().
		if ("exec".equalsIgnoreCase(nextToken.toString())) {
			while (true) {
				nextToken = super.nextToken();
				if ("sqlca".equalsIgnoreCase(nextToken.toString())) {
					PluginTokenUtil.addSqlcaTokens(this, nextToken);
					PluginTokenUtil.addGlobalVariableTokens(this, nextToken);
					PluginTokenUtil.addTypeDefs(this, nextToken);
					// Initialize sqlca struct.
					tokens().add(PluginTokenUtil.createStructToken(this, nextToken));
					tokens().add(PluginTokenUtil.createIdentifierToken(this, nextToken, "sqlca"));
					tokens().add(PluginTokenUtil.createIdentifierToken(this, nextToken, "sqlca"));
					continue;
				}
				if ("call".equalsIgnoreCase(nextToken.toString())) {
					return super.nextToken();
				}
				if ("do".equalsIgnoreCase(nextToken.toString())) {
					nextToken = super.nextToken();
					if ("break".equalsIgnoreCase(nextToken.toString())
							|| "continue".equalsIgnoreCase(nextToken.toString())) {
						// ignore those tokens().
						continue;
					}
					return nextToken;
				}
				if (":".equals(nextToken.toString())) {
					nextToken = super.nextToken();
					// Modify tokens().to be treated as variables.
					tokens().add(nextToken);
					tokens().add(PluginTokenUtil.createAssignToken(this, nextToken));
					tokens().add(PluginTokenUtil.createIntegerToken(this, nextToken, "0"));
					tokens().add(PluginTokenUtil.createSemiToken(this, nextToken));
					continue;
				}
				if (";".equals(nextToken.toString())) {
					tokens().add(nextToken);
					return clearPrefetchedToken(tokens().poll());
				}
			}
		}
		return nextToken;
	}

}
