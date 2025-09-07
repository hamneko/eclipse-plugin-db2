package plugin.db2;

import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.model.ICLanguageKeywords;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.IncludeFileContentProvider;

import plugin.db2.parser.scanner.DB2Scanner;

public class DB2Language extends GCCLanguage {

	public DB2Language() {
	}

	@Override
	public String getId() {
		return "plugin.db2.editor";
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (adapter.isAssignableFrom(ICLanguageKeywords.class)) {
			return (T) this;
		}
		return super.getAdapter(adapter);
	}

	@Override
	protected IScanner createScanner(FileContent content, IScannerInfo scanInfo, IncludeFileContentProvider fcp,
			IParserLogService log) {
		return new DB2Scanner(content, scanInfo, getParserLanguage(), log, getScannerExtensionConfiguration(scanInfo),
				fcp);
	}

	@Override
	public String[] getKeywords() {
		String[] keywords = super.getKeywords();
		String[] additionalKeywords = new String[] { "EXEC", "SQL" };
		String[] result = new String[keywords.length + additionalKeywords.length];
		System.arraycopy(keywords, 0, result, 0, keywords.length);
		System.arraycopy(additionalKeywords, 0, result, keywords.length, additionalKeywords.length);
		return result;
	}
}
