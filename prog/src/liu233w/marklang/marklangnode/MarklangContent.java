/**
 * 
 */
package liu233w.marklang.marklangnode;

import liu233w.marklang.formatter.Formatter;

/**
 * 表示包含的普通内容，一行文字为一个 MarklangContent对象
 * 
 * @author Liu233w
 *
 */
public class MarklangContent extends MarklangNode {

	/**
	 * 包含的内容，不能含有换行符
	 */
	private String content;

	/**
	 * 默认的无参构造函数
	 */
	public MarklangContent() {
		this.content = "";
	}

	/**
	 * 有参构造函数
	 * 
	 * @param content
	 *            包含的内容，不能含有换行符
	 */
	public MarklangContent(String content) {
		this.content = content;
	}

	/**
	 * @return content, 包含的内容，不能含有换行符
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 content ,包含的内容，不能含有换行符
	 */
	public void setContent(String content) {
		this.content = content;
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
