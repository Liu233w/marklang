/**
 * 
 */
package liu233w.marklang.formatter;

import liu233w.marklang.marklangnode.MarklangNode;

/**
 * 用于装饰器的抽象类
 * 
 * @author Liu233w
 *
 */
public abstract class FormatDecorator {
	/**
	 * 下一个装饰器
	 */
	protected FormatDecorator nextDecorator;

	/**
	 * 构造函数，类必须接受一个 FormatDecorator 作为下一个装饰器。 在装饰链条的最后可以使用
	 * {@linkplain liu233w.marklang.formatter.SimpleReturnFormatDecorator
	 * SimpleReturnFormatDecorator} 来结束。
	 * 
	 * @param nextDecorator
	 *            下一个装饰器
	 */
	public FormatDecorator(FormatDecorator nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	/**
	 * 接收下一个装饰器的处理结果、处理节点并返回本装饰器的处理结果。
	 * 
	 * 所有派生类都应该遵循的处理顺序：
	 * 
	 * 1. 接收 prevStr、node
	 * 
	 * 2. 调用 nextDecorator.FormatNode(prevStr, node)，接收其返回值
	 * 
	 * 3. 处理 node 节点，将处理结果与第二步的返回值附加到一起
	 * 
	 * 4. 返回处理结果
	 * 
	 * 因此总的处理顺序是从后往前（从里往外）的.
	 * 
	 * @param prevStr
	 *            上一个装饰器传过来的字符串，这个字符串可以用于处理一些额外的信息， 不能用于传递上个装饰器的处理结果。
	 * @param node
	 *            要被处理的节点
	 * @return 节点的处理结果
	 */
	public abstract String FormatNode(String prevStr, MarklangNode node);
}
