/**
 * 
 */
package liu233w.marklang.marklangnode;

import java.util.ArrayList;

import liu233w.marklang.formatter.Formatter;

/**
 * 表示引用内容的节点类，可以包含多行
 * 
 * @author Liu233w
 *
 */
public class MarklangQuote extends MarklangNode {

	/**
	 * 要引用的文字
	 */
	private ArrayList<String> quoteStrings;

	/**
	 * 默认的无参构造函数
	 */
	public MarklangQuote() {
		this.quoteStrings = new ArrayList<String>();
	}

	/**
	 * 有参构造函数
	 * 
	 * @param quoteStrings
	 *            要引用的文字
	 */
	public MarklangQuote(ArrayList<String> quoteStrings) {
		this.quoteStrings = quoteStrings;
	}

	/**
	 * @return quoteStrings, 要引用的文字
	 */
	public ArrayList<String> getQuoteStrings() {
		return quoteStrings;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * liu233w.marklang.marklangnode.MarklangNode#FormatNode(liu233w.marklang.
	 * formatter.Formatter)
	 */
	@Override
	public String FormatNode(Formatter formatter) {
		return formatter.FormatNode("", this);
	}

}
