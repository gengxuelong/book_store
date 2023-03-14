package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
 * @date 2021-11-19 13:27
 * <p>
 * 个人中心类，这里包含了用户的购物车展示，同时提供给用户处理购物车中购买项的接口
 */
public class PersonFrame extends JFrame {

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
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JPanel northPane;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JTable table_1;
    private DefaultTableModel model_1;
    private JPanel peronPane = new JPanel() {
        /*
        覆写paint方法
         */
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
     * 购物车共计金额
     */
    private static double numPrice = 0;

    /**
     * 可用金额
     */
    private static double freeMoney = 1000.0;

    /**
     * 已消费金额
     */
    private static double usedMoney;

    /**
     * 列表的数据
     */
    private static ArrayList<Book> shopCar;//购物车列表
    private static ArrayList<Book> hadBought = new ArrayList<>();//已购列表


    /**
     * constructor function
     */
    public PersonFrame(int Id, String name, String passWord, ArrayList<Book> shopCar) {

        this.ID = Id;
        this.name = name;
        this.passWord = passWord;
        this.shopCar = shopCar;

        /*
        计算购物车中书籍的总价格
         */
        loadNumPrice();

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
        build northPane
        frame上面部分的panel
         */
        northPane = new JPanel();
        contentPane.add(northPane);
        northPane.setLayout(null);
        northPane.setBounds(20, 10, 800, 50);
        northPane.setBorder(new TitledBorder(""));


         /*
        build southPane
        frame下面部分的panel
         */
        JPanel southPane = new JPanel();
        contentPane.add(southPane);
        southPane.setLayout(null);
        southPane.setBorder(new TitledBorder(""));
        southPane.setBounds(20, 60, 800, 540);

         /*
        build personPane
        展示个人信息的模块
         */
        peronPane.setBorder(new TitledBorder(""));
        contentPane.add(peronPane);
        peronPane.setBounds(830, 20, 350, 120);
        peronPane.setLayout(null);

        /*
        build boughtPane
        展示已购买轮滑框所在模块
         */
        JPanel boughtPane = new JPanel();
        contentPane.add(boughtPane);
        boughtPane.setBounds(830, 150, 350, 450);
        boughtPane.setBorder(new TitledBorder(""));
        boughtPane.setLayout(null);

        /*
        build scrollPane
        购物车轮滑框
         */
        scrollPane = new JScrollPane();
        southPane.add(scrollPane);
        scrollPane.setBounds(100, 10, 500, 400);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setBorder(new TitledBorder(""));

        /*
        build scrollPane_1
        已购买轮滑框
         */
        scrollPane_1 = new JScrollPane();
        boughtPane.add(scrollPane_1);
        scrollPane_1.setBounds(10, 10, 330, 300);
        scrollPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane_1.setBorder(new TitledBorder(""));


         /*
        decorate scrollPane
        加载购物车轮滑框中显示的数据
         */
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setRowHeight(50);
        LoadModel();

         /*
        decorate scrollPane_1
        加载已购买轮滑框中显示的数据
         */
        table_1 = new JTable();
        scrollPane_1.setViewportView(table_1);
        table_1.setRowHeight(50);
        LoadModel_1();

        /*
        decorate northPane
         */
        buildLabel_3("" + freeMoney);
        buildLabel_4("" + usedMoney);
        JButton button_4 = new JButton("充值");
        northPane.add(button_4);
        button_4.setBounds(600, 10, 100, 30);
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_4();
            }
        });

        /*
        decorate southPane
         */
        JLabel label_1 = new JLabel("购物车");
        southPane.add(label_1);
        label_2 = new JLabel("共计金额:" + numPrice + "元");
        southPane.add(label_2);

        JButton button_1 = new JButton("购买");
        JButton button_2 = new JButton("移除");
        southPane.add(button_1);
        southPane.add(button_2);

        label_1.setFont(new Font(null, 1, 40));
        label_1.setBounds(150, 410, 300, 150);
        label_2.setFont(new Font(null, 1, 20));
        label_2.setBounds(600, 480, 200, 50);

        button_1.setBounds(620, 50, 150, 70);
        button_2.setBounds(620, 250, 150, 70);
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


        /*
        decorate boughtPane
         */
        JLabel label_5 = new JLabel("已购买");
        boughtPane.add(label_5);
        label_5.setBounds(20, 320, 100, 50);
        label_5.setFont(new Font(null, 1, 20));

        JButton button_3 = new JButton("移除记录");
        boughtPane.add(button_3);
        button_3.setBounds(200, 340, 150, 50);
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                do_button_3();
            }
        });

    }

    /**
     * 充值功能的实现
     */
    private void do_button_4() {

        String ss = JOptionPane.showInputDialog("请输入充值金额");
        /*
        健壮性考虑， 防止用户未输入任何数字时的空指针异常
         */
        if (ss == null) return;
        try {
            Double.parseDouble(ss);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "请输入正确的格式");
            return;
        }
        freeMoney += Double.parseDouble(ss);
        /*
        重新设置总金额的显示
         */
        freeMoney = ((int) (freeMoney * 10)) / 10.0;
        label_3.setText("可用金额为:" + freeMoney + "元");

    }

    /**
     * 初始化numPrice
     */
    private void loadNumPrice() {

        /*
        遍历承载购物车表格数据的数组容器，并求价格总和
         */
        for (Book b : shopCar) {
            numPrice += b.getPrice();
        }
        /*
        通过截断的方式实现小数点后一位的保留
         */
        numPrice = ((int) (numPrice * 10)) / 10.0;
    }


    /**
     * button_1
     * 购买按钮的实现
     */
    private void do_button_1() {

        /*
        当余额小于零时，无法购买
         */
        if (freeMoney < 0) {
            JOptionPane.showMessageDialog(this, "购买失败，您的余额已透支，请充值");
            return;
        }
        int[] indexS = null;
        /*
        通过int数组储存选中的行的索引
         */
        indexS = table.getSelectedRows();
        /*
        健壮性考虑，当未选中任何行时，indexS为空
         */
        if (indexS == null) return;
        /*
        先通过中间容器记录选中的项
         */
        ArrayList<Book> temp = new ArrayList<>();
        for (Integer i : indexS) {
            temp.add(shopCar.get(i));
        }
        /*
        再根据中间容器temp的值做相应操作
         */
        for (Book b : temp) {
            shopCar.remove((b));
            hadBought.add(b);
            freeMoney -= b.getPrice();
            usedMoney += b.getPrice();
            numPrice -= b.getPrice();
        }
         /*
        重新加载轮滑框中的表格数据
         */
        LoadModel();
        LoadModel_1();
        /*
        重新设置相关记录的值，并通过截断的方式将double型数字记录保留以为小数
         */
        freeMoney = ((int) (freeMoney * 10)) / 10.0;
        usedMoney = ((int) (usedMoney * 10)) / 10.0;
        numPrice = ((int) (numPrice * 10)) / 10.0;
        /*
        消除因截断误差造成的显式影响
         */
        if (numPrice < 0.3) numPrice = 0.0;
        label_3.setText("可用金额为:" + freeMoney + "元");
        label_4.setText("已用金额为:" + usedMoney + "元");
        label_2.setText("总计金额:" + numPrice + "元");

        /*
        当余额小于零时，给出充值提醒
         */
        if (freeMoney < 0) {

            JOptionPane.showMessageDialog(this, "您的余额已透支，请充值");
        }

    }

    /**
     * button_2
     * 移除购物车购买项按钮的实现
     */
    private void do_button_2() {

        int[] indexS = null;
        /*
        通过int数组储存训中的行的索引
         */
        indexS = table.getSelectedRows();
        /*
        健壮性考虑，当未选中任何行时，indexS为空
         */
        if (indexS == null) return;
        /*
        先通过中间容器记录要删除的项
         */
        ArrayList<Book> temp = new ArrayList<>();
        for (int i : indexS) {
            temp.add(shopCar.get(i));
        }
        /*
        在根据中间容器temp的值将hadBought中响相应的项移除
        同时购物车记录总金额相应减少
         */
        for (Book b : temp) {
            shopCar.remove((b));
            numPrice -= b.getPrice();
        }
        LoadModel();
        /*
        通过截断的方式保留小数点后一位
         */
        numPrice = (int) (numPrice * 10) / 10.0;
        /*
        消除因截断误差造成的显式影响
         */
        if (numPrice < 0.3) numPrice = 0.0;
        label_2.setText("总计金额:" + numPrice + "元");

    }

    /**
     * button_3
     * 移除购买记录功能的实现
     */
    private void do_button_3() {

        int[] indexS = null;
        /*
        通过int数组储存训中的行的索引
         */
        indexS = table_1.getSelectedRows();
        /*
        健壮性考虑，当未选中任何行时，indexS为空
         */
        if (indexS == null) return;
        /*
        先通过中间容器记录要删除的项
         */
        ArrayList<Book> temp = new ArrayList<>();
        for (Integer i : indexS) {
            temp.add(hadBought.get(i));
        }
        /*
        在根据中间容器temp的值将hadBought相应的项移除
         */
        for (Book b : temp) {
            hadBought.remove((b));
        }
        LoadModel_1();

    }


    /**
     * buildLabel_3 function
     */
    public void buildLabel_3(String s) {

        label_3 = new JLabel("可用金额：" + s + "元");
        label_3.setFont(new Font(null, 1, 20));
        label_3.setText("可用金额：" + s + "元");
        label_3.setBounds(50, 10, 300, 50);
        northPane.add(label_3);
    }

    /**
     * buildLabel_4 function
     */
    public void buildLabel_4(String s) {

        label_4 = new JLabel();
        label_4.setFont(new Font(null, 1, 20));
        label_4.setText("已用金额:" + s + "元");
        label_4.setBounds(350, 10, 300, 50);
        northPane.add(label_4);
    }

    /**
     * 装载列表数据
     */
    private void LoadModel() {

        /*
        model 在这里赋值有更新数据的效果，避免过往数据的累计
         */
        model = new DefaultTableModel(new String[]{"书名", "作者", "价格"}, 0);
        if (shopCar.size() != 0)
            for (int i = 0; i < shopCar.size(); i++) {
                String[] temp = shopCar.get(i).toString().split("-");
                model.addRow(temp);
            }
        table.setModel(model);

    }

    /**
     * 装载列表数据_1
     */
    private void LoadModel_1() {

        /*
        model_1 在这里赋值有更新数据的效果，避免过往数据的累计
         */
        model_1 = new DefaultTableModel(new String[]{"书名", "作者", "价格"}, 0);
        if (hadBought.size() != 0)
            for (int i = 0; i < hadBought.size(); i++) {
                String[] temp = hadBought.get(i).toString().split("-");
                model_1.addRow(temp);
            }
        table_1.setModel(model_1);
    }
}
