/**
 * 
 */
package liu233w.marklang.marklangnode;

import java.util.ArrayList;

import liu233w.marklang.formatter.Formatter;

/**
 * 表示小标题的类
 * 
 * @author Liu233w
 *
 */
public class MarklangTitle extends MarklangNode {

	/**
	 * 标题内容
	 */
	private String title;
	/**
	 * 包含的内容
	 */
	private ArrayList<MarklangNode> contents;
	/**
	 * 标题的级别
	 */
	private int titleLevel;

	/**
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return 包含的内容
	 */
	public ArrayList<MarklangNode> getContents() {
		return contents;
	}

	/**
	 * @return 标题级别
	 */
	public int getTitleLevel() {
		return titleLevel;
	}

	/**
	 * @param title
	 *            要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param titleLevel
	 *            要设置的 titleLevel
	 */
	public void setTitleLevel(int titleLevel) {
		this.titleLevel = titleLevel;
	}

	/**
	 * 默认的无参构造函数
	 */
	public MarklangTitle() {
		this.title = "";
		this.titleLevel = 1;
		this.contents = new ArrayList<MarklangNode>();
	}

	/**
	 * 有参构造函数
	 * 
	 * @param title
	 *            标题内容
	 * @param titleLevel
	 *            标题的级别
	 * @param contents
	 *            包含的内容
	 */
	public MarklangTitle(String title, int titleLevel, ArrayList<MarklangNode> contents) {
		this.title = title;
		this.contents = contents;
		this.titleLevel = titleLevel;
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
