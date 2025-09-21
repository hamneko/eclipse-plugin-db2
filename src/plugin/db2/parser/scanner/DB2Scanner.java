package plugin.db2.parser.scanner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.parser.EndOfFileException;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.IncludeFileContentProvider;
import org.eclipse.cdt.core.parser.ParserLanguage;

import plugin.common.parser.scanner.ExecSqlPosition;
import plugin.common.parser.scanner.PluginScanner;
import plugin.common.parser.scanner.PluginTokenUtil;

@SuppressWarnings("restriction")
public class DB2Scanner extends PluginScanner {

	private List<ExecSqlPosition> execSqlPositions;

	private Map<String, String> cursorVariables = new HashMap<>();

	public DB2Scanner(FileContent fileContent, IScannerInfo info, ParserLanguage language, IParserLogService log,
			IScannerExtensionConfiguration configuration, IncludeFileContentProvider readerFactory) {
		super(fileContent, info, language, log, configuration, readerFactory);
	}

	public DB2Scanner(FileContent fileContent, IScannerInfo info, ParserLanguage language, IParserLogService log,
			IScannerExtensionConfiguration configuration, IncludeFileContentProvider readerFactory,
			List<ExecSqlPosition> execSqlPositions) {
		super(fileContent, info, language, log, configuration, readerFactory);
		this.execSqlPositions = execSqlPositions;
	}

	@Override
	public IToken nextToken() throws EndOfFileException {
		if (tokens().peek() != null) {
			return clearPrefetchedToken(tokens().poll());
		}
		// Initialize required variables
		int from = 0, to = 0;
		boolean colonStarted = false;
		boolean immediateAfterComma = false;
		boolean immediateAfterVariable = false;
		IToken nextToken = super.nextToken();
		// Process "EXEC" to ";" tokens().
		if ("exec".equalsIgnoreCase(nextToken.toString())) {
			from = nextToken.getOffset();
			while (true) {
				nextToken = super.nextToken();
				if ("sqlca".equalsIgnoreCase(nextToken.toString())) {
					to = nextToken.getOffset();
					immediateAfterVariable = true;
					// In DB2, EXEC SQL INCLUDE SQLCA; must be declared for sqlca.
					PluginTokenUtil.addSqlcaTokens(this, nextToken);
					// Initialize sqlca struct.
					PluginTokenUtil.addSqlcaStructTokens(this, nextToken);
					//
					PluginTokenUtil.addSqlCodeTokens(this, nextToken);
					PluginTokenUtil.addSqlStateTokens(this, nextToken);
					PluginTokenUtil.addTypeDefs(this, nextToken);
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
				if ("declare".equalsIgnoreCase(nextToken.toString())
						|| "prepare".equalsIgnoreCase(nextToken.toString())) {
					nextToken = super.nextToken();
					if ("section".equalsIgnoreCase(nextToken.toString())) {
						continue;
					}
					// Modify tokens to be treated as variable declaration.
					cursorVariables.put(nextToken.toString(), "");
					tokens().add(PluginTokenUtil.createCharToken(this, nextToken));
					tokens().add(nextToken);
					tokens().add(PluginTokenUtil.createSemiToken(this, nextToken));
					continue;
				}
				if (cursorVariables.containsKey(nextToken.toString())) {
					to = nextToken.getOffset();
					immediateAfterVariable = true;
					// Modify tokens to be treated as variables.
					tokens().add(nextToken);
					PluginTokenUtil.addAssignLiteralTokens(this, nextToken);
					continue;
				}
				if (":".equals(nextToken.toString())) {
					nextToken = super.nextToken();
					to = nextToken.getOffset();
					immediateAfterVariable = true;
					// Modify tokens().to be treated as variables.
					tokens().add(nextToken);
					PluginTokenUtil.addAssignIntegerTokens(this, nextToken);
					colonStarted = true;
					immediateAfterComma = false;
					continue;
				}
				if (colonStarted && !";".equals(nextToken.toString())) {
					immediateAfterVariable = false;
					// Ignore comma
					if (",".equals(nextToken.toString())) {
						immediateAfterComma = true;
						continue;
					}
					if (!immediateAfterComma) {
						continue;
					}
					immediateAfterComma = false;
					to = nextToken.getOffset();
					immediateAfterVariable = true;
					// Modify tokens to be treated as variables.
					tokens().add(nextToken);
					PluginTokenUtil.addAssignIntegerTokens(this, nextToken);
					continue;
				}
				if (";".equals(nextToken.toString())) {
					colonStarted = false;
					immediateAfterComma = false;
					if (!immediateAfterVariable) {
						to = nextToken.getOffset();
					}
					execSqlPositions.add(new ExecSqlPosition(from, to));
					tokens().add(nextToken);
					return clearPrefetchedToken(tokens().poll());
				}
				immediateAfterVariable = false;
			}
		}
		return nextToken;
	}

}
