/**
 * 
 */
package liu233w.marklang.formatter;

import liu233w.marklang.marklangnode.MarklangNode;

/**
 * 用于输出代码的接口，处理节点输出的类必须实现此接口
 * 
 * @author Liu233w
 *
 */
public interface Formatter {

	/**
	 * 处理指定的节点（通常是用另一种标记语言来表示节点），返回处理结果
	 * 
	 * @param extraStr
	 *            一个额外的参数，其意义可能会改变
	 * @param node
	 *            要输出的节点
	 * @return 输出结果
	 */
	String FormatNode(String extraStr, MarklangNode node);
}
