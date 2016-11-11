/**
 * 
 */
package liu233w.marklang.formatter.markdownformatter;

import liu233w.marklang.formatter.FormatDecorator;
import liu233w.marklang.marklangnode.MarklangContent;
import liu233w.marklang.marklangnode.MarklangNode;

/**
 * 负责从 MarklangContent 节点生成 Markdown 格式的字符串。 如果当前节点为 MarklangContent
 * 将不理会内层装饰器的返回值。
 * 
 * @author Liu233w
 *
 */
public class ContentDecorator extends FormatDecorator {

	/**
	 * 参见 {@linkplain liu233w.marklang.formatter.FormatDecorator
	 * FormatDecorator}
	 * 
	 * @param nextDecorator
	 *            下一个装饰器
	 */
	public ContentDecorator(FormatDecorator nextDecorator) {
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
		if (node instanceof MarklangContent) {
			return ((MarklangContent) node).getContent() + "\n\n";
		} else {
			return innerResult;
		}
	}

}
