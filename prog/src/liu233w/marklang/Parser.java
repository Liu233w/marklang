/**
 * 
 */
package liu233w.marklang;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import liu233w.marklang.marklangnode.*;

/**
 * 用于解析Marklang字符串的类。
 * 
 * @author Liu233w
 *
 */
public class Parser {
	/**
	 * 从字符串生成Marklang的语法树，注意本解析器不负责检测语法错误，请用户保证语法正确。如果语法产生错误，函数将返回已经处理好的部分。
	 * 
	 * TODO：使用状态模式处理语法解析
	 * 
	 * TODO：使程序可以处理语法错误
	 * 
	 * @param str
	 *            Marklang 的源代码
	 * @return Marklang 的语法树
	 */
	public static MarklangRoot parseStringToMarklangNode(String str) {
		// 目前使用的是递归来处理

		StringTokenizer token = new StringTokenizer(str, "\n");
		MarklangRoot root = new MarklangRoot();

		try {
			root.setTitle(token.nextToken());
			// 将标题当做一个 Title 类来处理
			Tuple<MarklangTitle, Tuple<String, Integer>> fakeTitleTup = handleTitle(token,
					new Tuple<String, Integer>("", 0));
			root.getContents().addAll(fakeTitleTup.first.getContents());
			assert fakeTitleTup.second == null;
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return root;
		}

		return root;
	}

	static MarklangQuote handleQuote(StringTokenizer token) throws NoSuchElementException {
		MarklangQuote node = new MarklangQuote();

		String str = token.nextToken();
		while (!str.equals("```")) {
			node.getQuoteStrings().add(str);
			str = token.nextToken();
		}

		return node;
	}

	static Tuple<String, Integer> getTitleAndLevel(String titleLine) {
		int level = 0;
		while (titleLine.charAt(level) == '#') {
			++level;
		}
		String title = titleLine.substring(level);

		return new Tuple<String, Integer>(title, level);
	}

	static Tuple<MarklangTitle, Tuple<String, Integer>> handleTitle(StringTokenizer token,
			Tuple<String, Integer> thisTitleAndLevel) throws NoSuchElementException {

		MarklangTitle node = new MarklangTitle();
		node.setTitle(thisTitleAndLevel.first);
		node.setTitleLevel(thisTitleAndLevel.second);

		while (token.hasMoreElements()) {
			String str = token.nextToken();
			if (str.startsWith("#")) {
				Tuple<String, Integer> nextTitleAndLevel = getTitleAndLevel(str);
				do { // 由于当前标题内部的标题很可能处理到和当前标题同级甚至外部的标题，因此加一个循环来处理此情况

					if (nextTitleAndLevel.second <= thisTitleAndLevel.second) {
						// 下一行的标题比当前标题靠外，因此返回上层来处理
						return new Tuple<MarklangTitle, Tuple<String, Integer>>(node, nextTitleAndLevel);
					} else {
						Tuple<MarklangTitle, Tuple<String, Integer>> innerResult = handleTitle(token,
								nextTitleAndLevel);
						node.getContents().add(innerResult.first);
						nextTitleAndLevel = innerResult.second;
					}

				} while (nextTitleAndLevel != null); // 为null说明解析到了字符串的结尾

			} else if (str.startsWith("```")) {
				node.getContents().add(handleQuote(token));
			} else { // 内容
				node.getContents().add(new MarklangContent(str));
			}
		}

		// 下一行没有内容了，说明解析到了字符串的结尾
		return new Tuple<MarklangTitle, Tuple<String, Integer>>(node, null);
	}
}

class Tuple<T, U> {
	public Tuple(T f, U s) {
		first = f;
		second = s;
	}

	public T first;
	public U second;
}
