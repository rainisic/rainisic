package project;



import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class ExampleFrame_01 extends JFrame {

	public static void main(String args[]) {
		ExampleFrame_01 frame = new ExampleFrame_01();
		frame.setVisible(true);
	}

	public ExampleFrame_01() {
		super();
		setTitle("�ָ����");
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JSplitPane hSplitPane = new JSplitPane();// ����һ��ˮƽ����ķָ����
		hSplitPane.setDividerLocation(40);// �ָ������Ŀ��Ϊ40����
		getContentPane().add(hSplitPane, BorderLayout.CENTER);// ��ӵ�ָ������

		hSplitPane.setLeftComponent(new JLabel("      1"));// ��ˮƽ���������һ����ǩ���

		final JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);// ����һ����ֱ����ķָ����
		vSplitPane.setDividerLocation(30);// �ָ����Ϸ��ĸ߶�Ϊ30����
		vSplitPane.setDividerSize(8);// �ָ����Ŀ��Ϊ8����
		vSplitPane.setOneTouchExpandable(true);// �ṩUIС����
		vSplitPane.setContinuousLayout(true);// �ڵ����ָ���λ��ʱ�����ػ淽ʽΪ��������
		hSplitPane.setRightComponent(vSplitPane);// ��ӵ�ˮƽ�����Ҳ�

		vSplitPane.setLeftComponent(new JLabel("      2"));// �ڴ�ֱ����Ϸ����һ����ǩ���

		vSplitPane.setRightComponent(new JLabel("      3"));// �ڴ�ֱ����·����һ����ǩ���
		//
	}

}
