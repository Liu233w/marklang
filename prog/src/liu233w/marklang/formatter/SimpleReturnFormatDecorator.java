/**
 * 
 */
package liu233w.marklang.formatter;

import liu233w.marklang.marklangnode.MarklangNode;

/**
 * 这个类不做生成工作，只是单纯地返回空字符串，用于装饰链条的最内层
 * 
 * @author Liu233w
 *
 */
public class SimpleReturnFormatDecorator extends FormatDecorator {

	public SimpleReturnFormatDecorator() {
		super(null);
		// 什么都不做
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
		return "";
	}

}
