package code;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * @author gengxuelong
 * @date 2021-11-19 01:20
 * <p>
 * 注册的页面实现，仅支持用户的注册
 * 内含验证码机制
 */
public class ZhuCe extends JFrame {

    /**
     * 界面长和高
     */
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 640;

    /**
     * 界面组件
     */

    private JPanel contentPane;
    private String text = "ASRE";
    private JLabel yanzhen;
    private TextField f1 = new TextField();
    private TextField f2 = new TextField();
    private JPasswordField f3 = new JPasswordField();
    private JPasswordField f4 = new JPasswordField();
    private JTextField f5 = new JTextField();

    /**
     * constructor function
     */
    public ZhuCe() {

         /*
        build frame
         */
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("欢迎来到雪龙书店应用程序");

        /*
        build contentPane
         */
        contentPane = new JPanel();
        this.setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.orange);

        /*
        build Panel
         */
        JPanel Panel = new JPanel();
        contentPane.add(Panel);
        Panel.setBounds(100, 50, 1000, 500);
        Panel.setLayout(null);
        Panel.setBorder(new TitledBorder("注册页面"));

        /*
        decorate Panel
         */
        /*
        创建标签
         */
        JLabel label_1 = new JLabel("账号:");
        JLabel label_2 = new JLabel("用户名:");
        JLabel label_3 = new JLabel("密码:");
        JLabel label_4 = new JLabel("确认密码:");
        JLabel label_5 = new JLabel("验证码:");

        /*
        加入输入框和标签
         */
        Panel.add(f1);
        Panel.add(f2);
        Panel.add(f3);
        Panel.add(f4);
        Panel.add(f5);
        Panel.add(label_1);
        Panel.add(label_2);
        Panel.add(label_3);
        Panel.add(label_4);
        Panel.add(label_5);
        /*
        标签和输入框的具体设计
         */
        label_1.setBounds(50, 20, 100, 60);
        label_2.setBounds(50, 125, 100, 60);
        label_3.setBounds(50, 250, 100, 60);
        label_4.setBounds(50, 375, 100, 60);
        label_5.setBounds(550, 20, 100, 60);

        f1.setFont(new Font(null, 1, 40));
        f2.setFont(new Font(null, 1, 40));
        f3.setFont(new Font(null, 1, 40));
        f4.setFont(new Font(null, 1, 40));
        f5.setFont(new Font(null, 1, 40));

        f1.setBounds(150, 20, 300, 60);
        f2.setBounds(150, 125, 300, 60);
        f3.setBounds(150, 250, 300, 60);
        f4.setBounds(150, 375, 300, 60);
        f5.setBounds(640, 20, 250, 60);

        /*
        起到展示验证码的标签
         */
        yanzhen = new JLabel(text);

        Panel.add(yanzhen);
        yanzhen.setBounds(550, 125, 200, 100);
        yanzhen.setBackground(Color.yellow);
        yanzhen.setFont(new Font(null, 1, 50));
        yanzhen.setOpaque(true);

        /*
        按钮的构造
         */
        JButton button_1 = new JButton("看不清？换一张");
        Panel.add(button_1);
        button_1.setBounds(800, 135, 150, 80);
        JButton button_2 = new JButton("注册");
        Panel.add(button_2);
        button_2.setBounds(620, 275, 300, 100);
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_1();
            }
        });
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_2();
            }
        });
    }

    /**
     * 按钮一功能的实现
     */
    private void do_button_1() {
        text = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char temp = (char) (random.nextInt(26) + 65);
            text = text + temp;
        }
        yanzhen.setText(text);
    }

    /**
     * 注册按钮功能的实现
     */
    private void do_button_2() {
        String id = f1.getText();
        String name = f2.getText();
        String password = f3.getText();
        String password_2 = f4.getText();
        /*
        健壮性的考虑
         */
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "账号信息格式不对哦亲");
            return;
        }
        if (id.length() > 10 || Long.parseLong(id) > Integer.MAX_VALUE) {
            JOptionPane.showMessageDialog(this, "账号不能超过十位,十位时前两位应不超过20哦，这样更方便记忆呢！！",
                    null, JOptionPane.DEFAULT_OPTION);
            return;
        }
        /*
        当设置的密码和确认密码栏内容不一致时弹出提示框
         */
        if (!(password.equals(password_2))) {
            JOptionPane.showMessageDialog(this, "两次输入的密码不一样呀亲！！",
                    null, JOptionPane.DEFAULT_OPTION);
            return;
        }
        if (f5.getText().equals(text) && !((f1.getText()).equals("")) && !((f2.getText()).equals("")) && !((f3.getText()).equals("")) && !((f4.getText()).equals(""))) {
            JOptionPane.showMessageDialog(this, "恭喜！注册成功，请去登录吧！！",
                    null, JOptionPane.DEFAULT_OPTION);
            BookStore.map.put(Integer.parseInt(id), new String[]{password, name});
            PrintStream p = null;
            try {
                p = new PrintStream(new FileOutputStream(new File("./src/user.txt"), true));
                p.println(id + "-" + password + "-" + name);
                p.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            /*
            当未填写正确的验证码时
             */
            if (!(f5.getText().equals(text)))
                JOptionPane.showMessageDialog(this, "请填写正确的验证码！！",
                        null, JOptionPane.DEFAULT_OPTION);
                /*
                当用户未将信息填写全时
                 */
            else {
                JOptionPane.showMessageDialog(this, "请填写有效信息！！",
                        null, JOptionPane.DEFAULT_OPTION);
            }
        }

    }
}
