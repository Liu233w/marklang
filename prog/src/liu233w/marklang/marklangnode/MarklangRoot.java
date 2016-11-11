/**
 * 
 */
package liu233w.marklang.marklangnode;

import java.util.ArrayList;

import liu233w.marklang.formatter.Formatter;

/**
 * 表示语法树的根节点的类
 * 
 * @author Liu233w
 *
 */
public class MarklangRoot extends MarklangNode {

	/**
	 * 文档的标题
	 */
	private String title;
	/**
	 * 文档的内容
	 */
	private ArrayList<MarklangNode> contents;

	/**
	 * 默认的无参构造函数
	 */
	public MarklangRoot() {
		this.title = "";
		this.contents = new ArrayList<MarklangNode>();
	}

	/**
	 * 有参数的构造函数
	 * 
	 * @param title
	 *            文档的标题
	 * @param contents
	 *            文档的内容
	 */
	public MarklangRoot(String title, ArrayList<MarklangNode> contents) {
		this.title = title;
		this.contents = contents;
	}

	/**
	 * @return title 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return contents 内容
	 */
	public ArrayList<MarklangNode> getContents() {
		return contents;
	}

	/**
	 * @param title
	 *            要设置的标题
	 */
	public void setTitle(String title) {
		this.title = title;
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
		String extraStr = "";
		for (MarklangNode node : this.contents) {
			extraStr += node.FormatNode(formatter);
		}

		String result = formatter.FormatNode(extraStr, this);
		return result;
	}

}
