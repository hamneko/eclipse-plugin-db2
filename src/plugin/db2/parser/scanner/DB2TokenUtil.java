package plugin.db2.parser.scanner;

import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.internal.core.parser.scanner.TokenUtil;
import org.eclipse.cdt.internal.core.parser.scanner.TokenWithImage;

@SuppressWarnings("restriction")
public class DB2TokenUtil {
	public static void addSqlcaTokens(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_struct, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_struct));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlca".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tLBRACE, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tLBRACE));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		addSqlcaSqlcaid(scanner, t);
		addSqlcaSqlabc(scanner, t);
		addSqlcaSqlcode(scanner, t);
		addSqlcaSqlerrml(scanner, t);
		addSqlcaSqlerrmc(scanner, t);
		addSqlcaSqlerrp(scanner, t);
		addSqlcaSqlerrd(scanner, t);
		addSqlcaSqlwarn(scanner, t);
		addSqlcaSqlstate(scanner, t);
		IToken t4 = new TokenWithImage(IToken.tRBRACE, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tRBRACE));
		IToken t5 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t4);
		scanner.getTokens().add(t5);
	}

	private static void addSqlcaSqlcaid(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlcaid".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(), "sqlcaid".toCharArray());
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlabc(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_long, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlabc".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tLBRACE));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlcode(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_long, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlcode".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tLBRACE));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlerrml(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_long, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlerrml".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tLBRACE));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlerrmc(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlerrmc".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				"sqlerrmc".toCharArray());
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlerrp(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlerrp".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(), "sqlerrp".toCharArray());
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlerrd(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_long, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlerrd".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tLBRACE));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlwarn(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlwarn".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(), "sqlwarn".toCharArray());
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	private static void addSqlcaSqlstate(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlstate".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				"sqlstate".toCharArray());
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
	}

	public static void addGlobalVariableTokens(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_long, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
		IToken t2 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"SQLCODE".toCharArray());
		IToken t3 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		IToken t4 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t5 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"SQLSTATE".toCharArray());
		IToken t6 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
		scanner.getTokens().add(t5);
		scanner.getTokens().add(t6);
	}

	public static void addTypeDefs(DB2Scanner scanner, IToken t) {
		addSqlint8(scanner, t);
		addSqluint8(scanner, t);
		addSqlint16(scanner, t);
		addSqluint16(scanner, t);
		addSqlint32(scanner, t);
		addSqluint32(scanner, t);
		addSqlint64(scanner, t);
		addSqluint64(scanner, t);
	}

	private static void addSqlint8(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t3 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlint8".toCharArray());
		IToken t4 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
	}

	private static void addSqluint8(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_unsigned, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_unsigned));
		IToken t3 = new TokenWithImage(IToken.t_char, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
		IToken t4 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqluint8".toCharArray());
		IToken t5 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
		scanner.getTokens().add(t5);
	}

	private static void addSqlint16(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_short, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_short));
		IToken t3 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlint16".toCharArray());
		IToken t4 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
	}

	private static void addSqluint16(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_unsigned, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_unsigned));
		IToken t3 = new TokenWithImage(IToken.t_short, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_short));
		IToken t4 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqluint16".toCharArray());
		IToken t5 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
		scanner.getTokens().add(t5);
	}

	private static void addSqlint32(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_int, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_int));
		IToken t3 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlint32".toCharArray());
		IToken t4 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
	}

	private static void addSqluint32(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_unsigned, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_unsigned));
		IToken t3 = new TokenWithImage(IToken.t_int, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_int));
		IToken t4 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqluint32".toCharArray());
		IToken t5 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
		scanner.getTokens().add(t5);
	}

	private static void addSqlint64(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_long, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
		IToken t3 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqlint64".toCharArray());
		IToken t4 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
	}

	private static void addSqluint64(DB2Scanner scanner, IToken t) {
		IToken t1 = new TokenWithImage(IToken.t_typedef, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
		IToken t2 = new TokenWithImage(IToken.t_unsigned, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_unsigned));
		IToken t3 = new TokenWithImage(IToken.t_long, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
		IToken t4 = new TokenWithImage(IToken.tIDENTIFIER, scanner, t.getOffset(), t.getEndOffset(),
				"sqluint64".toCharArray());
		IToken t5 = new TokenWithImage(IToken.tSEMI, scanner, t.getOffset(), t.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
		scanner.getTokens().add(t1);
		scanner.getTokens().add(t2);
		scanner.getTokens().add(t3);
		scanner.getTokens().add(t4);
		scanner.getTokens().add(t5);
	}
}
