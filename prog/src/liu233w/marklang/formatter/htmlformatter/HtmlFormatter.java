/**
 * 
 */
package liu233w.marklang.formatter.htmlformatter;

import liu233w.marklang.formatter.FormatDecorator;
import liu233w.marklang.formatter.Formatter;
import liu233w.marklang.formatter.SimpleReturnFormatDecorator;
import liu233w.marklang.marklangnode.MarklangNode;

/**
 * 将语法树格式化成 HTML 格式的字符串并输出，使用了单例模式
 * 
 * @author Liu233w
 *
 */
public class HtmlFormatter implements Formatter {

	/**
	 * 装饰器，用于输出对应的字符串
	 */
	private FormatDecorator formatDecorator;

	/**
	 * 类的实例
	 */
	private static HtmlFormatter instance;

	private HtmlFormatter() {
		formatDecorator = new TitleDecorator(
				new ContentDecorator(new QuoteDecorator(new RootDecorator(new SimpleReturnFormatDecorator()))));
	}

	/**
	 * 获取当前类型的实例
	 * 
	 * @return 类的实例
	 */
	public static HtmlFormatter getInstance() {
		if (instance == null)
			instance = new HtmlFormatter();
		return instance;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see liu233w.marklang.formatter.Formatter#FormatNode(java.lang.String,
	 * liu233w.marklang.marklangnode.MarklangNode)
	 */
	@Override
	public String FormatNode(String extraStr, MarklangNode node) {
		return formatDecorator.FormatNode(extraStr, node);
	}

}
