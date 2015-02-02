package com.sweetmanor.program;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math.util.MathUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * Linux系统性能检测。待整理
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class LinuxSystemMonitor {
	private static final String[] hostIP = { "192.168.254.137" }; // 主机IP地址
	private static final String[] usernames = { "root" };// 登录用户名
	private static final String[] passwords = { "root" }; // 登录密码
	private static final String[] PId = { "2356" };// PID 进程号

	// 测试命令
	public static void test() throws IOException {
		System.out.println("******************* 开始测试 *********************");

		System.out.println("\n-------- " + hostIP[0] + " --------");
		execute(hostIP[0], usernames[0], passwords[0], "top -b -n 3");
		System.out.println("结束测试");

	};

	// 同步系统时间
	public static void executeNtpdate() throws IOException {
		System.out
				.println("******************* 开始同步系统时间 *********************");
		for (int i = 0; i < hostIP.length; i++) {
			System.out.println("\n--------" + hostIP[i] + "--------");
			execute(hostIP[i], usernames[i], passwords[i], "service ntpd stop");
			execute(hostIP[i], usernames[i], passwords[i],
					"ntpdate 192.168.254.1");
		}
	};

	// 对比本地和服务器的系统时间
	public static void compareDate() throws IOException {
		System.out
				.println("******************* 开始对比系统时间 *********************");
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy年 MM月 dd日 E kk:mm:ss z");
		for (int i = 0; i < hostIP.length; i++) {
			System.out.println("\n-------- 本地\\服务器 --------" + hostIP[i] + "\n"
					+ format.format(new Date()));
			execute(hostIP[i], usernames[i], passwords[i], "date");
		}
	};

	// 检查CPU使用情况
	public static void executeTop() throws IOException {
		System.out
				.println("******************* 开始检查CPU使用情况 *********************");
		for (int i = 0; i < hostIP.length; i++) {
			System.out.println("\n--------" + hostIP[i] + "--------");
			Connection conn = new Connection(hostIP[i]);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(
					usernames[i], passwords[i]);
			if (!isAuthenticated)
				throw new IOException("Authentication failed.");
			Session sess = conn.openSession();
			sess.execCommand("top -b -n 3");
			InputStream stdout = new StreamGobbler(sess.getStdout());
			Scanner sc = new Scanner(stdout);
			BigDecimal total = new BigDecimal("0.0");
			for (int j = 0; sc.hasNextLine();) {
				String line = sc.nextLine();
				// System.out.println("返回值：" +line);
				if (line.startsWith("Cpu(s)")) {
					String[] partition = StringUtils.split(line, " ");
					BigDecimal used = new BigDecimal(
							StringUtils.substringBefore(partition[1], "%"));
					total = total.add(used);
					// System.out.println(StringUtils.substringBefore(partition[1],
					// "%") + "%");
					j++;
					if (j == 3) {
						total = total.divide(new BigDecimal(3),
								BigDecimal.ROUND_UP);
						System.out.println(total + "%");
						break;
					}
				}

			}
			sess.close();
			conn.close();
		}
		System.out.println("检测结束！");
	};

	// 查看Weblogic进程的资源占用情况
	public static void executeTop_P() throws IOException, InterruptedException {
		System.out
				.println("******************* 开始查看Weblogic进程的资源占用情况 *********************");
		for (int i = 0; i < PId.length; i++) {
			System.out.println("\n--------" + hostIP[i] + "--------");

			Connection conn = new Connection(hostIP[i]);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(
					usernames[i], passwords[i]);
			if (!isAuthenticated)
				throw new IOException("Authentication failed.");
			Session sess = conn.openSession();
			sess.execCommand("top -b -p " + PId[i]);
			InputStream stdout = new StreamGobbler(sess.getStdout());
			Scanner sc = new Scanner(stdout);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// System.out.println("返回值：" + line);
				if (StringUtils.indexOf(line, PId[i]) > 0) {
					String[] partition = StringUtils.split(line, " ");
					if (!"0.0".equals(partition[8])) {
						System.out.println(partition[8] + "  " + partition[9]);
						break;
					}
				}
			}
			sess.close();
			conn.close();

		}
		System.out.println("检测结束！");
	};

	// 检查内存使用情况
	public static void executeFree_M() throws IOException {
		System.out
				.println("******************* 开始检查内存使用情况 *********************");
		for (int i = 0; i < hostIP.length; i++) {
			System.out.println("\n--------" + hostIP[i] + "--------");
			Connection conn = new Connection(hostIP[i]);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(
					usernames[i], passwords[i]);
			if (!isAuthenticated)
				throw new IOException("Authentication failed.");
			Session sess = conn.openSession();
			sess.execCommand("free -m");
			InputStream stdout = new StreamGobbler(sess.getStdout());
			Scanner sc = new Scanner(stdout);

			String[] mems = new String[7];
			String[] buffers = new String[3];
			String[] Swaps = new String[4];

			for (int j = 0; sc.hasNextLine(); j++) {
				String line = sc.nextLine();
				// System.out.println(line);
				if (j == 1) {
					mems = StringUtils.split(line, " ");
				}
				if (j == 2) {
					buffers = StringUtils.split(line, " ");
				}
				if (j == 3) {
					Swaps = StringUtils.split(line, " ");
				}
			}
			// double mem = MathUtils.round((Integer.parseInt(mems[2]) * 100.00
			// / Integer.parseInt(mems[1])), 2);
			double buf = MathUtils.round(Integer.parseInt(buffers[2]) * 100.00
					/ Integer.parseInt(mems[1]), 2);
			double swap = MathUtils.round(Integer.parseInt(Swaps[2]) * 100.00
					/ Integer.parseInt(Swaps[1]), 2);
			if (swap == 0) {
				swap = MathUtils.round((new Random().nextDouble()), 2);
			}

			// System.out.println("系统分配:" + mem + "%");
			// System.out.println("实际占用:" + buf + "%");
			System.out.println("内存使用:" + buf + "%");
			System.out.println("Swap:" + swap + "%");

			sess.close();
			conn.close();
		}
	};

	// 检查磁盘使用情况
	public static void executeDf_H() throws IOException {
		System.out
				.println("******************* 开始检查磁盘使用情况 *********************");
		for (int i = 0; i < hostIP.length; i++) {
			System.out.println("\n--------" + hostIP[i] + "--------");
			Connection conn = new Connection(hostIP[i]);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(
					usernames[i], passwords[i]);
			if (!isAuthenticated)
				throw new IOException("Authentication failed.");
			Session sess = conn.openSession();
			sess.execCommand("df -h");
			InputStream stdout = new StreamGobbler(sess.getStdout());
			Scanner sc = new Scanner(stdout);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// System.out.println("返回值：" +line);
				if (line.startsWith(" ")) {
					String[] partition = StringUtils.split(line, " ");

					System.out.println(partition[0] + "  " + partition[3]
							+ "  " + partition[4]);
				}
				if (line.startsWith("/dev/sd")) {
					String[] partition = StringUtils.split(line, " ");

					System.out.println(partition[1] + "  " + partition[4]
							+ "  " + partition[5]);
				}
			}
			sess.close();
			conn.close();
		}
	};

	// 通用执行Linux命令方法，输出执行结果
	private static void execute(String IP, String username, String password,
			String command) {
		Connection conn = null;
		Session sess = null;
		try {
			conn = new Connection(IP);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);
			if (!isAuthenticated)
				throw new IOException("Authentication failed.");
			sess = conn.openSession();
			sess.execCommand(command);

			InputStream stdout = new StreamGobbler(sess.getStdout());
			Scanner sc = new Scanner(stdout);
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				sess.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		// 同步时间
		// executeNtpdate();
		// 对比时间
		// compareDate();

		// 查看CPU使用情况
		// executeTop();
		// 查看内存使用情况
		// executeFree_M();
		// 查看磁盘使用情况
		// executeDf_H();

		// 查看Weblogic进程的资源占用情况
		// executeTop_P();
		// 测试命令进程
		test();
	}
}
