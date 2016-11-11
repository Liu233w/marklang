/**
 * 
 */
package liu233w.marklang;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import liu233w.marklang.formatter.Formatter;
import liu233w.marklang.formatter.htmlformatter.HtmlFormatter;
import liu233w.marklang.formatter.markdownformatter.MarkdownFormatter;
import liu233w.marklang.formatter.txtformatter.TxtFormatter;
import liu233w.marklang.formatter.marklangformatter.MarklangFormatter;
import liu233w.marklang.marklangnode.*;

/**
 * 驱动类，用于测试 Marklang 组件和提供命令行接口
 * 
 * @author Liu233w
 *
 */
public class Driver {

	static MarklangRoot ast;

	static final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	static final PrintWriter stdOut = new PrintWriter(new OutputStreamWriter(System.out));

	@SuppressWarnings("serial")
	static final ArrayList<Tuple<String, Formatter>> formatterList = new ArrayList<Tuple<String, Formatter>>() {
		{
			add(new Tuple<String, Formatter>("Marklang", MarklangFormatter.getInstance()));
			add(new Tuple<String, Formatter>("Markdown", MarkdownFormatter.getInstance()));
			add(new Tuple<String, Formatter>("TXT", TxtFormatter.getInstance()));
			add(new Tuple<String, Formatter>("HTML", HtmlFormatter.getInstance()));
		}
	};

	/**
	 * 程序的入口点。请注意程序的输出为UTF-8，在不支持UTF-8的控制台上可能会有乱码。
	 * 
	 * @param args
	 *            不会对参数进行处理
	 */
	public static void main(String[] args) {
		stdOut.println("Marklang v1.0 作者：14011501 刘书敏 2015303087\n");

		readAstFromFile();

		do {
			stdOut.println("0 : 退出程序");
			for (int i = 0; i < formatterList.size(); ++i) {
				stdOut.printf("%d : %s\n", i + 1, formatterList.get(i).first);
			}

			int index = readIndex();

			if (index == 0) {
				stdOut.println("\n感谢使用本程序");
				break;
			} else {
				handleOutput(formatterList.get(index - 1).second);
			}
		} while (true);
	}

	static void readAstFromFile() {
		do {
			try {
				stdOut.print("请输入Marklang源码文件名：");
				stdOut.flush();

				String file = readLine();

				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

				String str = "";
				String line = in.readLine();
				while (line != null) {
					str += line + "\n";
					line = in.readLine();
				}

				in.close();

				ast = Parser.parseStringToMarklangNode(str);
				return;

			} catch (IOException e) {
				System.err.println("无法读取！请重新输入");
			}
		} while (true);
	}

	static String readLine() {
		do {
			try {
				return stdIn.readLine();
			} catch (IOException e) {
				System.err.println("IO错误！请重新输入");
			}
		} while (true);
	}

	static int readIndex() {
		do {
			try {
				stdOut.print("请输入序号以选择输出的格式：");
				stdOut.flush();

				String line = readLine();

				int index = Integer.parseInt(line);
				if (index < 0 || index > formatterList.size()) {
					System.err.println("数字不在正确的范围内！请重新输入");
					continue;
				} else {
					return index;
				}
			} catch (NumberFormatException e) {
				System.err.println("不是一个合法的数字！请重新输入");
			}
		} while (true);
	}

	static void handleOutput(Formatter formatter) {
		do {
			try {
				stdOut.print("请输入文件名（包括后缀名），留空表示输出到控制台：");
				stdOut.flush();

				String file = readLine();
				String outputStr = ast.FormatNode(formatter);

				if (file.equals("")) {
					stdOut.println(outputStr);
				} else {
					FileWriter out = new FileWriter(file);
					out.write(outputStr);
					out.close();
				}

				return;
			} catch (IOException e) {
				System.err.println("找不到文件或文件无法读取！请重新输入");
			}
		} while (true);
	}

	/**
	 * 测试函数，生成一个 MarklangRoot 对象，其内容是硬编码的。
	 * 
	 * @return 生成的对象
	 */
	public static MarklangRoot TEST_generateNode() {
		return new MarklangRoot("标题",
				new ArrayList<MarklangNode>(
						Arrays.asList(
								new MarklangTitle("第一节", 1,
										new ArrayList<MarklangNode>(
												Arrays.asList(new MarklangContent("正文 1"), new MarklangContent("正文 2"),
														new MarklangQuote(new ArrayList<String>(
																Arrays.asList("引用 1", "引用 2")))))),
								new MarklangTitle("第二节", 1,
										new ArrayList<MarklangNode>(Arrays.asList(
												new MarklangTitle("2-1", 2,
														new ArrayList<MarklangNode>(
																Arrays.asList(new MarklangContent("正文 3")))),
												new MarklangTitle("2-2", 2, new ArrayList<MarklangNode>(
														Arrays.asList(new MarklangContent("正文 4"))))))))));
	}

	/**
	 * 生成一个Marklang的字符串，其内容是硬编码的
	 * 
	 * @return 生成的字符串
	 */
	public static String TEST_generateMarklangString() {
		return "标题\n" + "#第一节\n" + "正文 1\n" + "正文 2\n" + "```\n" + "引用 1\n" + "引用 2\n" + "```\n" + "#第二节\n" + "##2-1\n"
				+ "正文 3\n" + "##2-2\n" + "正文 4";
	}
}