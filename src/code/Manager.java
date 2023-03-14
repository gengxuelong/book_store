package code;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author gengxuelong
 * @date 2021-11-19 09:55
 * <p>
 * 管理员类，可以直接添加或删除书库的书籍，同时监控书籍的邮寄情况
 */
public class Manager extends JFrame {

    /**
     * 待发货书籍
     */
    private ArrayList<Book> notSends;

    /*
    利用代码块，对可变数组noteSends 通过深复制的方式初始化
     */ {
        notSends = new ArrayList<>();
        for (int i = 0; i < BookBase.getBooks_1().size(); i++) {
            notSends.add(BookBase.getBooks_1().get(i));
        }
    }


    /**
     * 已发货书籍
     */
    private ArrayList<Book> hadSends;

    /*
    利用代码块，对可变数组hadSends 通过深复制的方式初始化
     */ {
        hadSends = new ArrayList<>();
        for (int i = 0; i < BookBase.getBooks_2().size(); i++) {
            hadSends.add(BookBase.getBooks_2().get(i));
        }
    }


    /**
     * 查看全部书籍(分种类）
     * 通过浅复制，可以透过allBooks引用 直接作用于书库
     */
    private ArrayList<Book> allBooks
            = (ArrayList<Book>) BookBase.getBooks_1();

    /**
     * 界面长和高
     */
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 650;

    /**
     * 界面组件
     */
    private JPanel contentPane;
    private JMenuBar menuBar;

    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;

    private JTable table_1;
    private JTable table_2;
    private JTable table_3;

    private DefaultTableModel model_1;
    private DefaultTableModel model_2;
    private DefaultTableModel model_3;

    /**
     * JRadioButtons
     */
    private JRadioButton jr1;
    private JRadioButton jr2;
    private JRadioButton jr3;
    private JRadioButton jr4;
    private JRadioButton jr5;
    private JRadioButton jr6;
    private ButtonGroup buttonGroup;


    /**
     * 管理员属性
     */
    private int ID;
    private String name;
    private String passWord;

    /**
     * 管理员标识模块
     */
    private JPanel panel = new JPanel();


    /**
     * constructor function
     */
    public Manager(int id, String name, String passWord) {

        this.ID = id;
        this.name = name;
        this.passWord = passWord;

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
        panel的创建， 用于显示管理员的信息
         */
        panel.setBorder(new TitledBorder(""));
        contentPane.add(panel);
        panel.setBounds(830, 30, 350, 120);
        panel.setLayout(null);
        JLabel label_1 = new JLabel("管理员：" + name);
        panel.add(label_1);
        label_1.setFont(new Font(null, 1, 40));
        label_1.setBounds(10, 10, 300, 100);


        /*
        scrollPane_1创建
         */
        scrollPane_1 = new JScrollPane();
        contentPane.add(scrollPane_1);
        scrollPane_1.setBounds(10, 20, 300, 500);
        scrollPane_1.setBorder(new TitledBorder("未邮寄书籍"));
         /*
        scrollPane_2的创建
         */
        scrollPane_2 = new JScrollPane();
        contentPane.add(scrollPane_2);
        scrollPane_2.setBounds(350, 20, 300, 500);
        scrollPane_2.setBorder(new TitledBorder("已邮寄书籍"));
        /*
        scrollPane_3的创建
         */
        scrollPane_3 = new JScrollPane();
        contentPane.add(scrollPane_3);
        scrollPane_3.setBounds(830, 160, 350, 400);
        scrollPane_3.setBorder(new TitledBorder("全部书籍展示"));
        /*
        button 的创建
         */
        JButton button_1 = new JButton("邮寄所选");
        JButton button_2 = new JButton("新增书籍");
        JButton button_3 = new JButton("删除书籍");
        JButton button_4 = new JButton("删除所选记录");
        JButton button_5 = new JButton("清空");

        contentPane.add(button_1);
        contentPane.add(button_2);
        contentPane.add(button_3);
        contentPane.add(button_4);
        contentPane.add(button_5);
        button_1.setBounds(50, 530, 180, 40);
        button_2.setBounds(680, 35, 130, 40);
        button_3.setBounds(680, 105, 130, 40);
        button_4.setBounds(350, 530, 130, 40);
        button_5.setBounds(510, 530, 100, 40);

        /*
        分类模块
         */
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        panel_1.setBorder(new TitledBorder("图书分类"));
        panel_1.setBounds(680, 160, 150, 400);
        /*
        单选按钮的创建
         */
        jr1 = new JRadioButton("古代文学");
        jr2 = new JRadioButton("世界名著");
        jr3 = new JRadioButton("科幻");
        jr4 = new JRadioButton("哲学");
        jr5 = new JRadioButton("都市");
        jr6 = new JRadioButton("战争");
        /*
        通过buttonGroup 实现真正的单选效果
         */
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jr1);
        buttonGroup.add(jr2);
        buttonGroup.add(jr3);
        buttonGroup.add(jr4);
        buttonGroup.add(jr5);
        buttonGroup.add(jr6);

        panel_1.add(jr1);
        panel_1.add(jr2);
        panel_1.add(jr3);
        panel_1.add(jr4);
        panel_1.add(jr5);
        panel_1.add(jr6);

        jr1.setBounds(10, 40, 85, 50);
        jr2.setBounds(10, 140, 85, 50);
        jr3.setBounds(10, 240, 85, 50);
        jr4.setBounds(95, 40, 65, 50);
        jr5.setBounds(95, 140, 65, 50);
        jr6.setBounds(95, 240, 65, 50);
        jr1.setSelected(true);

        JButton button_6 = new JButton("确定");
        panel_1.add(button_6);
        button_6.setBounds(20, 320, 100, 40);

        /*
        给button添加事件
         */
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
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_3();
            }
        });
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_4();
            }
        });
        button_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_5();
            }
        });
        button_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_6();
            }
        });

        /*
        给各个轮滑窗面添加列表数据
         */
        table_1 = new JTable();
        table_2 = new JTable();
        table_3 = new JTable();
        scrollPane_1.setViewportView(table_1);
        scrollPane_2.setViewportView(table_2);
        scrollPane_3.setViewportView(table_3);
        loadModel_1();
        loadModel_2();
        loadModel_3();
    }


    /**
     * 给未邮寄轮滑框表格加载数据
     */
    private void loadModel_1() {
        /*
        model在这里新建对象，可以起到清空旧数据模型的作用，避免了信息的叠加
         */
        model_1 = new DefaultTableModel(new String[]{"书名", "作者", "价格"}, 0);
        for (int i = 0; i < notSends.size(); i++) {
            String[] temps = notSends.get(i).toString().split("-");
            model_1.addRow(temps);
        }
        table_1.setModel(model_1);
    }

    /**
     * 给已邮寄轮滑框表格加载数据
     */
    private void loadModel_2() {
        /*
        model在这里新建对象，可以起到清空旧数据模型的作用，避免了信息的叠加
         */
        model_2 = new DefaultTableModel(new String[]{"书名", "作者", "价格"}, 0);
        for (int i = 0; i < hadSends.size(); i++) {
            String[] temps = hadSends.get(i).toString().split("-");
            model_2.addRow(temps);
        }
        table_2.setModel(model_2);
    }

    /**
     * 给展示所有书籍轮滑框表格加载数据
     */
    private void loadModel_3() {
        /*
        model在这里新建对象，可以起到清空旧数据模型的作用，避免了信息的叠加
         */
        model_3 = new DefaultTableModel(new String[]{"书名", "作者", "价格"}, 0);
        for (int i = 0; i < allBooks.size(); i++) {
            String[] temps = allBooks.get(i).toString().split("-");
            model_3.addRow(temps);
        }
        table_3.setModel(model_3);
    }

    /**
     * 邮寄按钮功能的实现
     */
    private void do_button_1() {
        int[] indexS = table_1.getSelectedRows();
        /*
        如果未选中则indexS 为空数组，直接返回
         */
        if (indexS == null) return;
        /*
        通过中间list记录需要转移的项，记录完成后统一转移
         */
        ArrayList<Book> temp = new ArrayList<>();
        for (int i : indexS) {
            temp.add(notSends.get(i));
        }
        /*
        实质上的转移步骤
         */
        for (Book b : temp) {
            notSends.remove(b);
            hadSends.add(b);
        }
        /*
        储存数据的数组内容已被改变，
        重新加载数据
         */
        loadModel_1();
        loadModel_2();
    }

    /**
     * 为给书库增加书籍的按钮实现功能
     */
    private void do_button_2() {
        /*
        防止什么也不输入导致的空指针异常,如果info为null，则直接返回
         */
        String info = null;
        info = JOptionPane.showInputDialog("请输入要增加的书籍，形如：三国演义-吴承恩-30.0-我国伟大的一部历史题材的小说著作");
        if (info == null) return;
        String[] temp = info.split("-");
        /*
        健壮性考虑，如果管理员输入的格式不对或内容不匹配，
        将给出弹窗提醒，同时避免了因格式不对而导致的程序中断
         */
        if (temp.length == 4) {
            try {
                Double.parseDouble(temp[2]);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "请输入正确的格式");
                return;
            }
            Book book = new Book(temp[0], temp[1], Double.parseDouble(temp[2]), temp[3]);
            allBooks.add(book);
            /*
            将对数组容器的操作传递到文件
             */
            BookBase.write();
            /*
            重新加载数据以显示改变后的数据列表
             */
            loadModel_3();
        } else {
            JOptionPane.showMessageDialog(this, "请输入正确的格式");
        }
    }

    /**
     * 为给书库删除书籍的按钮实现功能
     */
    private void do_button_3() {
        int[] indexS = table_3.getSelectedRows();
        /*
        如果未选中则indexS 为空数组，直接返回
         */
        if (indexS == null) return;
        /*
        通过中间List记录需要转移的项，记录完成后统一转移
         */
        ArrayList<Book> temp = new ArrayList<>();
        for (int i : indexS) {
            temp.add(allBooks.get(i));
        }
        for (Book b : temp) {
            allBooks.remove(b);
        }
        /*
         将对数组容器的操作传递到文件
         */
        BookBase.write();
        /*
        储存数据的数组内容已被改变，利用loadModel函数重新加载数据
         */
        loadModel_3();
    }

    /**
     * 为删除已邮寄栏记录的按钮实现功能
     */
    private void do_button_4() {
        int[] indexS = table_2.getSelectedRows();
        /*
        如果未选中则indexS 为空数组，直接返回
         */
        if (indexS == null) return;
        /*
        通过中间List记录需要转移的项，记录完成后统一转移
         */
        ArrayList<Book> temp = new ArrayList<>();
        for (int i : indexS) {
            temp.add(hadSends.get(i));
        }
        for (Book b : temp) {
            hadSends.remove(b);
        }
        /*
        储存数据的数组内容已被改变，利用loadModel函数重新加载数据
         */
        loadModel_2();
    }

    /**
     * 为清空已邮寄栏的按钮实现功能
     */
    private void do_button_5() {
        /*
        通过重新赋值快速将hadSends容器清空
         */
        hadSends = new ArrayList<>();
        /*
        通过loadModel_2 方法将对容器的作用传递到table_2
         */
        loadModel_2();
    }

    /**
     * 实现更换种类的按钮的功能
     */
    private void do_button_6() {
        /*
        根据单选按钮的不同将allBooks引用指向不同的书库容器
         */
        if (jr1.isSelected()) {
            allBooks = BookBase.getBooks_1();
        }
        if (jr2.isSelected()) {
            allBooks = BookBase.getBooks_2();
        }
        if (jr3.isSelected()) {
            allBooks = BookBase.getBooks_3();
        }
        if (jr4.isSelected()) {
            allBooks = BookBase.getBooks_4();
        }
        if (jr5.isSelected()) {
            allBooks = BookBase.getBooks_5();
        }
        if (jr6.isSelected()) {
            allBooks = BookBase.getBooks_6();
        }
        /*
        通过loadModel_3 方法将对容器的作用传递到table_3
         */
        loadModel_3();
    }
}
