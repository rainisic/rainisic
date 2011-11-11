package project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExampleFrame_03 extends JFrame {

	JDesktopPane desktopPane = null;// ����һ������������

	InternalFrame pInFrame = null;// ����һ�����¹����ڲ��������

	InternalFrame rInFrame = null;// ����һ�����׹����ڲ��������

	InternalFrame tInFrame = null;// ����һ�����������ڲ��������

	public static void main(String args[]) {
		ExampleFrame_03 frame = new ExampleFrame_03();
		frame.setVisible(true);
	}

	public ExampleFrame_03() {
		super();
		setTitle("��ҵ���¹���ϵͳ");
		setBounds(100, 100, 570, 470);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �����������
		desktopPane = new JDesktopPane();// ��������������
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);// �����ڲ�������϶�ģʽ
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		// Ϊ���������ӱ���ͼƬ
		final JLabel backLabel = new JLabel(); // ����һ����ǩ�������
		URL resource = this.getClass().getResource("/back.JPG"); // ��ñ���ͼƬ��·��
		ImageIcon icon = new ImageIcon(resource); // ��������ͼƬ����
		backLabel.setIcon(icon); // ���ǩ�����ʾ����ͼƬ
		backLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // �����������ʾλ�ü���С
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE)); // ����ǩ�����ӵ�ָ������λ��

		// ��Ӱ�ť
		final JPanel panel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setLayout(flowLayout);
		getContentPane().add(panel, BorderLayout.NORTH);

		final JButton personnelButton = new JButton();
		personnelButton.setText("���¹���");
		personnelButton.addActionListener(new BAListener(pInFrame, "���¹���"));
		panel.add(personnelButton);

		final JButton reckoningButton = new JButton();
		reckoningButton.setText("���׹���");
		reckoningButton.addActionListener(new BAListener(rInFrame, "���׹���"));
		panel.add(reckoningButton);

		final JButton treatmentButton = new JButton();
		treatmentButton.setText("��������");
		treatmentButton.addActionListener(new BAListener(tInFrame, "��������"));
		panel.add(treatmentButton);
		//
	}

	private class BAListener implements ActionListener {

		InternalFrame inFrame;

		String title;

		public BAListener(InternalFrame inFrame, String title) {
			this.inFrame = inFrame;
			this.title = title;
		}

		public void actionPerformed(ActionEvent e) {
			if (inFrame == null || inFrame.isClosed()) {
				JInternalFrame[] allFrames = desktopPane.getAllFrames();// �����������е������ڲ�����
				int titleBarHight = 30 * allFrames.length;// ������������ӵ���ڲ����������
				int x = 10 + titleBarHight, y = x;// ���ô������ʾλ��
				int width = 250, height = 180;// ���ô���Ĵ�С
				inFrame = new InternalFrame(title);// ����ָ��������ڲ�����
				inFrame.setBounds(x, y, width, height);// ���ô������ʾλ�ü���С
				inFrame.setVisible(true);// ���ô���ɼ�
				desktopPane.add(inFrame);// ��������ӵ����������
			}
			try {
				inFrame.setSelected(true);// ѡ�д���
			} catch (PropertyVetoException propertyVetoE) {
				propertyVetoE.printStackTrace();
			}
		}
	}

	private class InternalFrame extends JInternalFrame {

		public InternalFrame(String title) {
			super();
			setTitle(title);// �����ڲ�����ı���
			setResizable(true);// �����������ɵ�����С
			setClosable(true);// �����ṩ�رհ�ť
			setIconifiable(true);// �����ṩͼ�껯��ť
			setMaximizable(true);// �����ṩ��󻯰�ť

			URL resource = this.getClass().getResource("/in_frame.JPG"); // ��ñ���ͼƬ��·��
			ImageIcon icon = new ImageIcon(resource); // ��������ͼƬ����
			setFrameIcon(icon);
			//
		}
	}

}
