/**
 * 
 */
package liu233w.marklang.formatter.txtformatter;

import liu233w.marklang.formatter.FormatDecorator;
import liu233w.marklang.marklangnode.MarklangNode;
import liu233w.marklang.marklangnode.MarklangQuote;

/**
 * 负责从 MarklangQuote 节点生成 TXT 格式的字符串。 如果当前节点为 MarklangQuote 将不理会内层装饰器的返回值。
 * 
 * @author Liu233w
 *
 */
public class QuoteDecorator extends FormatDecorator {

	/**
	 * 参见 {@linkplain liu233w.marklang.formatter.FormatDecorator
	 * FormatDecorator}
	 * 
	 * @param nextDecorator
	 *            下一个装饰器
	 */
	public QuoteDecorator(FormatDecorator nextDecorator) {
		super(nextDecorator);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * liu233w.marklang.formatter.FormatDecorator#FormatNode(java.lang.String,
	 * liu233w.marklang.marklangnode.MarklangNode)
	 */
	@Override
	public String FormatNode(String prevStr, MarklangNode node) {
		String innerResult = super.nextDecorator.FormatNode(prevStr, node);
		if (node instanceof MarklangQuote) {
			MarklangQuote workNode = (MarklangQuote) node;
			String result = "";
			for (String line : workNode.getQuoteStrings()) {
				result += "    " + line + "\n";
			}
			return result;
		} else {
			return innerResult;
		}
	}
}
