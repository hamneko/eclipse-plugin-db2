package plugin.db2;

import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.IncludeFileContentProvider;

import plugin.db2.parser.scanner.DB2Scanner;

public class DB2Language extends GCCLanguage {

	@Override
	public String getId() {
		return "plugin.db2.editor";
	}

	@Override
	protected IScanner createScanner(FileContent content, IScannerInfo scanInfo, IncludeFileContentProvider fcp,
			IParserLogService log) {
		return new DB2Scanner(content, scanInfo, getParserLanguage(), log, getScannerExtensionConfiguration(scanInfo),
				fcp);
	}

}
