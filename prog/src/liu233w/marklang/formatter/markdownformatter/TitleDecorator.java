/**
 * 
 */
package liu233w.marklang.formatter.markdownformatter;

import liu233w.marklang.formatter.FormatDecorator;
import liu233w.marklang.marklangnode.MarklangNode;
import liu233w.marklang.marklangnode.MarklangTitle;

/**
 * 负责从 MarklangTitle 节点生成 Markdown 格式的字符串。 如果当前节点为 MarklangTitle 将不理会内层装饰器的返回值。
 * 
 * @author Liu233w
 *
 */
public class TitleDecorator extends FormatDecorator {

	/**
	 * 参见 {@linkplain liu233w.marklang.formatter.FormatDecorator
	 * FormatDecorator}
	 * 
	 * @param nextDecorator
	 *            下一个装饰器
	 */
	public TitleDecorator(FormatDecorator nextDecorator) {
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
		if (node instanceof MarklangTitle) {
			MarklangTitle workNode = (MarklangTitle) node;
			String result = "";
			for (int i = -1; i < workNode.getTitleLevel(); ++i) {
				result += '#';
			}
			result += " " + workNode.getTitle() + "\n" + prevStr;
			return result;
		} else {
			return innerResult;
		}
	}

}
