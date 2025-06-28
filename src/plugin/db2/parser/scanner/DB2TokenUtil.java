package plugin.db2.parser.scanner;

import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.internal.core.parser.scanner.TokenUtil;
import org.eclipse.cdt.internal.core.parser.scanner.TokenWithImage;

@SuppressWarnings("restriction")
public class DB2TokenUtil {

	private DB2TokenUtil() {
	}

	public static IToken createIdentifierToken(DB2Scanner scanner, IToken createFrom, String name) {
		return new TokenWithImage(IToken.tIDENTIFIER, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				name.toCharArray());
	}

	public static IToken createStructToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.t_struct, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.t_struct));
	}

	public static IToken createAssignToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.tASSIGN, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.tASSIGN));
	}

	public static IToken createSemiToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.tSEMI, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.tSEMI));
	}

	public static IToken createLiteralToken(DB2Scanner scanner, IToken createFrom, String value) {
		return new TokenWithImage(IToken.tSTRING, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				value.toCharArray());
	}

	public static IToken createIntegerToken(DB2Scanner scanner, IToken createFrom, String value) {
		return new TokenWithImage(IToken.tINTEGER, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				value.toCharArray());
	}

	public static IToken createLBraceToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.tLBRACE, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.tLBRACE));
	}

	public static IToken createRBraceToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.tRBRACE, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.tRBRACE));
	}

	public static IToken createTypedefToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.t_typedef, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.t_typedef));
	}

	public static IToken createUnsignedToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.t_unsigned, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.t_unsigned));
	}

	public static IToken createCharToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.t_char, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.t_char));
	}

	public static IToken createShortToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.t_short, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.t_short));
	}

	public static IToken createIntToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.t_int, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.t_int));
	}

	public static IToken createLongToken(DB2Scanner scanner, IToken createFrom) {
		return new TokenWithImage(IToken.t_long, scanner, createFrom.getOffset(), createFrom.getEndOffset(),
				TokenUtil.getImage(IToken.t_long));
	}

	public static void addSqlcaTokens(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createStructToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlca"));
		scanner.tokens().add(createLBraceToken(scanner, createFrom));
		addSqlcaSqlcaid(scanner, createFrom);
		addSqlcaSqlabc(scanner, createFrom);
		addSqlcaSqlcode(scanner, createFrom);
		addSqlcaSqlerrml(scanner, createFrom);
		addSqlcaSqlerrmc(scanner, createFrom);
		addSqlcaSqlerrp(scanner, createFrom);
		addSqlcaSqlerrd(scanner, createFrom);
		addSqlcaSqlwarn(scanner, createFrom);
		addSqlcaSqlstate(scanner, createFrom);
		scanner.tokens().add(createRBraceToken(scanner, createFrom));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlcaid(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlcaid"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlabc(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createLongToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlabc"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlcode(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createLongToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlcode"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlerrml(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createLongToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlerrml"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlerrmc(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlerrmc"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlerrp(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlerrp"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlerrd(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createLongToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlerrd"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlwarn(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlwarn"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlcaSqlstate(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlstate"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	public static void addGlobalVariableTokens(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createLongToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "SQLCODE"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "SQLSTATE"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	public static void addTypeDefs(DB2Scanner scanner, IToken createFrom) {
		addSqlint8(scanner, createFrom);
		addSqluint8(scanner, createFrom);
		addSqlint16(scanner, createFrom);
		addSqluint16(scanner, createFrom);
		addSqlint32(scanner, createFrom);
		addSqluint32(scanner, createFrom);
		addSqlint64(scanner, createFrom);
		addSqluint64(scanner, createFrom);
	}

	private static void addSqlint8(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlint8"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqluint8(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createUnsignedToken(scanner, createFrom));
		scanner.tokens().add(createCharToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqluint8"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlint16(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createShortToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlint16"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqluint16(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createUnsignedToken(scanner, createFrom));
		scanner.tokens().add(createShortToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqluint16"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlint32(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createIntToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlint32"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqluint32(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createUnsignedToken(scanner, createFrom));
		scanner.tokens().add(createIntToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqluint32"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqlint64(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createLongToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqlint64"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}

	private static void addSqluint64(DB2Scanner scanner, IToken createFrom) {
		scanner.tokens().add(createTypedefToken(scanner, createFrom));
		scanner.tokens().add(createUnsignedToken(scanner, createFrom));
		scanner.tokens().add(createLongToken(scanner, createFrom));
		scanner.tokens().add(createIdentifierToken(scanner, createFrom, "sqluint64"));
		scanner.tokens().add(createSemiToken(scanner, createFrom));
	}
}
