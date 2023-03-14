package code;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gengxuelong
 * @date 2021-11-18 22:39
 * <p>
 * 书店类，为整个程序的入口，让用户或管理用登录，让新人注册自己的账号
 */
public class BookStore extends JFrame {

    /**
     * 界面长和高
     */
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 650;

    /**
     * 界面组件
     */
    private JPanel leftPane;
    private JPanel rightPane;
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JTextField id;
    private JPasswordField password;
    private ButtonGroup buttonGroup;
    private JRadioButton jr1;
    private JRadioButton jr2;

    /**
     * 用户的账号数据
     */
    public static Map map;

    static {
        map = new HashMap<Integer, String[]>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/user.txt")));
            String str = null;
            while ((str = br.readLine()) != null) {
                String[] strings = str.split("-");
                int id = Integer.parseInt(strings[0]);
                String password = strings[1];
                String name = strings[2];
                map.put(id, new String[]{password, name});
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Map map_manager;

    static {
        map_manager = new HashMap<Integer, String[]>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/manager.txt")));
            String str = null;
            while ((str = br.readLine()) != null) {
                String[] strings = str.split("-");
                int id = Integer.parseInt(strings[0]);
                String password = strings[1];
                String name = strings[2];
                map_manager.put(id, new String[]{password, name});
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 其他界面的对象
     */
    private Customer customer;
    private Manager manager;
    private ZhuCe zhuCe;

    /**
     * constructor function
     */
    public BookStore() {

        /*
        build frame
         */
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("欢迎来到雪龙书店应用程序");

        /*
        build contentPane
         */
        contentPane = new JPanel();
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        /*
        build menuBar
         */
        menuBar = new JMenuBar();
        JMenu m1 = new JMenu("设置");
        JMenu m2 = new JMenu("工具");
        JMenu m3 = new JMenu("帮助");
        menuBar.add(m1);
        menuBar.add(m2);
        menuBar.add(m3);
        contentPane.add(menuBar);
        menuBar.setBounds(0, 0, 1200, 20);
        JMenuItem mi1 = new JMenuItem("null");
        JMenuItem mi2 = new JMenuItem("null");
        JMenuItem mi3 = new JMenuItem("null");
        m2.add(mi2);
        m3.add(mi3);
        m1.add(mi1);

        /*
        build rightPane
        既用户登录/新人注册的模块
         */
        rightPane = new JPanel();
        contentPane.add(rightPane);
        rightPane.setLayout(null);
        rightPane.setBounds(800, 20, 400, 600);
        rightPane.setPreferredSize(new Dimension(400, 600));
        rightPane.setBorder(new TitledBorder("用户登录/新人注册"));

        /*
        build leftPane
        既在页面左端的模块
         */
        leftPane = new JPanel();
        contentPane.add(leftPane);
        leftPane.setLayout(null);
        leftPane.setBounds(0, 20, 800, 600);
        leftPane.setPreferredSize(new Dimension(800, 620));

        /*
        decorate left
         */
        JLabel label = new JLabel();
        leftPane.add(label);
        JLabel label_1 = new JLabel("雪龙书店欢迎您");
        leftPane.add(label_1);
        label_1.setBounds(100, 0, 600, 150);
        label_1.setFont(new Font(null, 1, 50));
        ImageIcon icon = new ImageIcon("./src/images/beijing.jpg");
        label.setIcon(icon);
        label.setBounds(0, 150, 800, 400);
        leftPane.setPreferredSize(new Dimension(800, 600));

        /*
        decorate right
         */
        JLabel lb1 = new JLabel("账号:");
        JLabel lb2 = new JLabel("密码:");
        id = new JTextField(10);
        password = new JPasswordField(10);
        id.setFont(new Font(null, 1, 20));
        password.setFont(new Font(null, 1, 20));

        rightPane.add(lb1);
        rightPane.add(lb2);
        rightPane.add(id);
        rightPane.add(password);

        lb1.setFont(new Font(null, 1, 20));
        lb2.setFont(new Font(null, 1, 20));
        lb1.setBounds(10, 50, 100, 60);
        lb2.setBounds(10, 200, 100, 60);
        id.setBounds(100, 50, 230, 60);
        password.setBounds(100, 200, 230, 60);

        JButton button_1 = new JButton("登录");
        JButton button_2 = new JButton("注册");
        rightPane.add(button_1);
        rightPane.add(button_2);
        button_1.setBounds(100, 320, 200, 50);
        button_2.setBounds(100, 420, 200, 50);
        /*
        给按钮添加监听事件
         */
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_1_actionPerformed();
            }
        });
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_2_actionPerformed();
            }
        });
        /*
        构建分类单选
         */
        jr1 = new JRadioButton("用户登录");
        jr2 = new JRadioButton("管理员登录");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jr1);
        buttonGroup.add(jr2);
        rightPane.add(jr1);
        rightPane.add(jr2);
        jr1.setBounds(10, 20, 100, 20);
        jr2.setBounds(200, 20, 100, 20);
        jr1.setSelected(true);
    }

    /**
     * 登录按钮功能的实现
     */
    private void do_button_1_actionPerformed() {

        String id_info = id.getText();
        String pw_info = password.getText();

        /*
        健壮性考虑，如果有未填信息，则弹出提醒弹窗，避免
        可能的空指针异常
         */
        if (jr1.isSelected()) {
            if (id_info == null || pw_info == null) {
                JOptionPane.showMessageDialog(this, "请填写完整信息", null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            /*
            由于希望用户将账号设置位数在十位数以下以方便用户的记忆，这里使用int数据类型作为账号信息的承载形式，
            若用户输入信息超出十位，则会有弹窗跳出提示
             */
            if (id_info.length() > 10 || Long.parseLong(id_info) > Integer.MAX_VALUE) {
                JOptionPane.showMessageDialog(this, "账号最多10位哟,,且十位时首位不能大于2，为2时次位不能大于0，建议使用自己的学号注册哦",
                        null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idNum = Integer.parseInt(id_info);
            String[] value = null;
            if (map.containsKey(idNum)) {
                value = (String[]) map.get(idNum);
                if ((value[0].equals(pw_info))) {
                    String name = value[1];
                    /*
                    通过建立Customer 对象的方式新建一个页面
                     */
                    customer = new Customer(idNum, name, value[0]);
                    /*
                    弹出欢迎弹窗
                     */
                    JOptionPane.showMessageDialog(customer, "欢迎用户:" + name, null, JOptionPane.DEFAULT_OPTION);
                } else {
                    JOptionPane.showMessageDialog(this, "很遗憾，密码错误",
                            null, JOptionPane.WARNING_MESSAGE);
                    password.selectAll();
                }
            } else {
                JOptionPane.showMessageDialog(this, "抱歉，您的账号尚未注册请先注册吧");
            }
        }
        if (jr2.isSelected()) {

            /*
            健壮性考虑，如果有未填信息，则弹出提醒弹窗，避免
             可能的空指针异常
             */
            if (id_info == null || pw_info == null) {
                JOptionPane.showMessageDialog(this, "请填写完整信息",
                        null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idNum = Integer.parseInt(id_info);
            String[] value = null;
            /*
            由于希望用户将账号设置位数在十位数以下以方便用户的记忆，这里使用int数据类型作为账号信息的承载形式，
            若用户输入信息超出十位，则会有弹窗跳出提示
             */
            if (id_info.length() > 10) {
                JOptionPane.showMessageDialog(this, "账号最多10位哟,,为10位数时，首位不能大于2，为2时次位不能大于0，强建议使用自己的学号注册哦",
                        null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (map_manager.containsKey(idNum)) {
                System.out.println("账号正确");
                value = (String[]) map_manager.get(idNum);
                if ((value[0].equals(pw_info))) {
                    /*
                    通过建立Customer 对象的方式新建一个页面
                     */
                    manager = new Manager(idNum, value[1], value[0]);
                    String name = value[1];
                    /*
                    弹出欢迎弹窗
                     */
                    JOptionPane.showMessageDialog(customer, "欢迎管理员:" + name,
                            null, JOptionPane.DEFAULT_OPTION);
                } else {
                    /*
                    若账号正确，密码错误，则弹出密码错误提示弹窗
                     */
                    JOptionPane.showMessageDialog(this, "很遗憾，密码错误",
                            null, JOptionPane.WARNING_MESSAGE);
                    /*
                    此时全选密码输入框的内容，方便用户再次输入密码
                     */
                    password.selectAll();
                }
            } else {
                JOptionPane.showMessageDialog(this, "抱歉，您的管理员账号输入错误");
            }
        }
    }

    /**
     * 注册按钮的实现
     */
    private void do_button_2_actionPerformed() {
        /*
        通过新建一个ZhuCe 按钮的形式打卡另一个页面
         */
        zhuCe = new ZhuCe();
    }

    /**
     * main function
     * 程序的入口
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    /*
                    通过新建一个BookStore 对象的形式进入开始界面
                     */
                    BookStore bookStore = new BookStore();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
