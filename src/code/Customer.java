package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author gengxuelong
 * @date 2021-11-19 00:58
 * <p>
 * 顾客类，可以浏览书库的书籍，并选择是否装进购物车和是否购买
 */
public class Customer extends JFrame {


    /**
     * 购物车列表
     * 应在初始时便赋予该引用变量一个具体对象，
     * 避免出现空指针异常
     */
    public static ArrayList<Book> shopCar = new ArrayList<>();

    /**
     * 展示列表
     */
    private ArrayList<Book> show;

    /*
    通过clone（） 的方式进行浅复制，因为用户无法直接删除或增加书库的容器，
    所以用浅复制无意料外风险且节省内存，
    利用代码块初始化
     */ {
        show = BookBase.getBooks_1();
    }

    /**
     * 用户属性
     */
    private int ID;
    private String passWord;
    private String name;

    /**
     * 用户属性的getter function
     *
     * @return
     */
    public int getId() {
        return ID;
    }

    public String getPassword() {
        return passWord;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * 界面长和高
     */
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 650;

    /**
     * 界面组件
     */

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    private JPanel peronPane = new JPanel() {

        public void paint(Graphics g) {

            Image image = null;
            try {
                image = ImageIO.read(new File("./src/images/img_1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, 30, 20, 100, 100, this);
            g.setFont(new Font(null, 1, 30));
            g.setColor(Color.BLACK);
            g.drawString(name, 150, 70);
        }
    };

    /**
     *
     * 其他页面对象
     */
    /**
     * 类型选择对象
     */
    private Variety variety;

    /**
     * 个人中心页面
     */
    private PersonFrame personFrame;


    /**
     * constructor function
     */
    public Customer(int Id, String name, String passWord) {

        /*
        构造器的赋值实现
         */
        this.ID = Id;
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
        JMenuBar menuBar = new JMenuBar();
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
        build scrollPane
         */
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane);
        scrollPane.setBounds(20, 90, 800, 540);
        scrollPane.setBorder(new TitledBorder("书店的书籍展示"));

        /*
        build personPane
        为显示用户信息的用户模块
         */
        contentPane.add(peronPane);
        peronPane.setBounds(830, 20, 350, 120);
        peronPane.setLayout(null);

        /*
        build northPane
        为界面上方长条状的模块
         */
        JPanel northPane = new JPanel();
        contentPane.add(northPane);
        northPane.setLayout(null);
        northPane.setBounds(20, 20, 800, 70);
        northPane.setBorder(new TitledBorder(""));

        /*
        build toolsPane
        为右下角的功能模块
         */
        JPanel toolsPane = new JPanel();
        contentPane.add(toolsPane);
        toolsPane.setBounds(830, 150, 350, 450);
        toolsPane.setBorder(new TitledBorder("功能区"));
        toolsPane.setLayout(null);


        /*
        decorate toolsPane
         */
        JButton button_1 = new JButton("个人中心");
        JButton button_2 = new JButton("分类");
        JButton button_3 = new JButton("客服咨询");
        JButton button_4 = new JButton("社区提问");
        JButton button_5 = new JButton("图书点评");
        toolsPane.add(button_1);
        toolsPane.add(button_2);
        toolsPane.add(button_3);
        toolsPane.add(button_4);
        toolsPane.add(button_5);
        button_1.setBounds(260, 10, 85, 30);
        button_2.setBounds(100, 50, 200, 50);
        button_3.setBounds(100, 150, 200, 50);
        button_4.setBounds(100, 250, 200, 50);
        button_5.setBounds(100, 350, 200, 50);

        /*
        decorate scrollPane
         */
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setRowHeight(100);
        LoadModel();

        /*
        decorate northPane
         */
        JButton b1 = new JButton("加入购物车");

        northPane.add(b1);
        b1.setBounds(500, 10, 100, 50);
        JButton b2 = new JButton("展示详细介绍");
        northPane.add(b2);
        b2.setBounds(150, 10, 130, 50);

        /*
        为button 添加监听者
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
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_b1();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_b2();
            }
        });
    }

    /**
     * b1按钮
     * 加入购物车按钮的实现
     */
    private void do_b1() {
        int[] indexS = table.getSelectedRows();
        /*
        如果未选择任何行，则indexS为空，应直接返回，避免空指针异常
         */
        if (indexS == null) return;
        /*
        将所选内容加载到shopCar容器
         */
        for (int i = 0; i < indexS.length; i++) {
            shopCar.add(show.get(indexS[i]));
        }
    }

    /**
     * b2按钮，
     * 展示详细信息按钮功能的实现
     */
    private void do_b2() {
        /*
        用于展示所选书籍的详细介绍，
        通过弹窗文字的模式进行
         */
        int index = table.getSelectedRow();
        String describe = show.get(index).getDescribe();
        JOptionPane.showMessageDialog(this, describe, null
                , JOptionPane.DEFAULT_OPTION);
    }

    /**
     * button_1按钮
     * 按钮一‘个人中心’功能的实现
     */
    private void do_button_1() {
        /*
        通过建立PersonFrame对象的方式打开另一个页面
         */
        personFrame = new PersonFrame(this.ID, this.name, this.passWord, this.shopCar);
    }


    /**
     * 按钮二分类功能的实现
     */
    private void do_button_2() {
         /*
        通过建立Variety对象的方式打开另一个页面
         */
        variety = new Variety();

    }

    /**
     * 按钮三客服功能的实现
     */
    private CallService callService;

    private void do_button_3() {
         /*
        通过建立CallService对象的方式打开另一个页面
         */
        callService = new CallService();
    }

    /**
     * 按钮四，社区提问功能的实现
     */
    private CommunityQuestion communityQuestion;

    private void do_button_4() {
        /*
        通过建立CommunityQuestion对象的方式打开另一个页面
         */
        communityQuestion = new CommunityQuestion();
    }

    /**
     * 按钮五，图书点评功能的实现
     */
    private BooksComment booksComment;

    private void do_button_5() {
        /*
        通过建立BooksComment对象的方式打开另一个页面
         */
        booksComment = new BooksComment();
    }

    /**
     * 装载列表数据
     */
    private void LoadModel() {
        /*
        这里给model赋值，可以起到清空旧数据的作用，增加该方法的使用范围
         */
        model = new DefaultTableModel(new String[]{"书名", "作者", "价格"}, 0);
        ArrayList<Book> books = show;
        for (int i = 0; i < books.size(); i++) {
            String[] temp = books.get(i).toString().split("-");
            model.addRow(temp);
        }
        table.setModel(model);
    }


    /**
     * 内部类。分类页面
     * 之所以将该类设置为内部类，是利用了内部类过得主类信息更便捷的优势，
     * 能更方便得操作主类的数据
     */
    private class Variety extends JFrame {

        /**
         * 单选框
         */
        private JRadioButton jr1;
        private JRadioButton jr2;
        private JRadioButton jr3;
        private JRadioButton jr4;
        private JRadioButton jr5;
        private JRadioButton jr6;
        private ButtonGroup buttonGroup;

        /**
         * constructor function
         */
        public Variety() {
        /*
         build frame
         */
            this.setSize(600, 400);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);

        /*
        build contentPane
         */
            JPanel contentPane = new JPanel();
            this.setContentPane(contentPane);
            contentPane.setLayout(null);

        /*
        build radioButton
         */
            jr1 = new JRadioButton("古代文学");
            jr2 = new JRadioButton("世界名著");
            jr3 = new JRadioButton("科幻");
            jr4 = new JRadioButton("哲学");
            jr5 = new JRadioButton("都市");
            jr6 = new JRadioButton("战争");

            buttonGroup = new ButtonGroup();
            buttonGroup.add(jr1);
            buttonGroup.add(jr2);
            buttonGroup.add(jr3);
            buttonGroup.add(jr4);
            buttonGroup.add(jr5);
            buttonGroup.add(jr6);

            contentPane.add(jr1);
            contentPane.add(jr2);
            contentPane.add(jr3);
            contentPane.add(jr4);
            contentPane.add(jr5);
            contentPane.add(jr6);

            jr1.setBounds(50, 30, 100, 50);
            jr2.setBounds(250, 30, 100, 50);
            jr3.setBounds(450, 30, 100, 50);
            jr4.setBounds(50, 180, 100, 50);
            jr5.setBounds(250, 180, 100, 50);
            jr6.setBounds(450, 180, 100, 50);

            jr1.setSelected(true);

        /*
        build button
         */
            JButton button = new JButton("确定");
            contentPane.add(button);
            button.setBounds(220, 300, 140, 50);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    do_button();
                }
            });
        }

        /**
         * 按钮功能的实现
         */
        private void do_button() {

            if (jr1.isSelected()) {
                show = BookBase.getBooks_1();
            }
            if (jr2.isSelected()) {
                show = BookBase.getBooks_2();
            }
            if (jr3.isSelected()) {
                show = BookBase.getBooks_3();
            }
            if (jr4.isSelected()) {
                show = BookBase.getBooks_4();
            }
            if (jr5.isSelected()) {
                show = BookBase.getBooks_5();
            }
            if (jr6.isSelected()) {
                show = BookBase.getBooks_6();
            }

            model = new DefaultTableModel(new String[]{"书名", "作者", "价格"}, 0);
            for (int i = 0; i < show.size(); i++) {
                String[] temp = show.get(i).toString().split("-");
                model.addRow(temp);
            }
            table.setModel(model);
            dispose();
        }

    }

}
