/**
 * 
 */
package liu233w.marklang.marklangnode;

import liu233w.marklang.formatter.Formatter;

/**
 * 所有节点类的基类
 * 
 * @author Liu233w
 *
 */
public abstract class MarklangNode {

	/*
	 * * 按照指定转换格式输出当前节点及其子节点。会把当前的节点作为node参数调用接口中的FormatNode方法。
	 * 如果此节点不含子节点，extraStr参数将为空；如果含有子节点，将首先对每个子节点调用其
	 * FormatNode方法，将结果按照顺序串连起来作为extraStr的实参。
	 * 
	 * @param formatter 用于处理节点的类
	 * 
	 * @return 节点的处理结果，通常是使用某种标记语言来表示的字符串
	 */
	public abstract String FormatNode(Formatter formatter);
}
