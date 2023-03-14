package code;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gengxuelong
 * @date 2021-11-19 13:35
 * <p>
 * 图书评论类，提供用户提交对莫一本图书评论的接口
 */
public class BooksComment extends JFrame {

    /**
     * 提交的文字
     * 先初始化防止用户未输入导致的空指针异常
     */
    private String text = "";


    /**
     * 页面组件
     */
    private JPanel panel;
    private JComboBox kindsComboBox;
    private JComboBox booksComboBox;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    /**
     * 各个类型的书名数组集合
     */
    private String[] strings_1;
    private String[] strings_2;
    private String[] strings_3;
    private String[] strings_4;
    private String[] strings_5;
    private String[] strings_6;

    /*
    利用代码块给上述数组初始化
     */ {
        strings_1 = new String[BookBase.getBooks_1().size()];
        int i = 0;
        for (Book b : BookBase.getBooks_1()) {
            strings_1[i++] = b.getName();
        }

        strings_2 = new String[BookBase.getBooks_2().size()];
        i = 0;
        for (Book b : BookBase.getBooks_2()) {
            strings_2[i++] = b.getName();
        }

        strings_3 = new String[BookBase.getBooks_3().size()];
        i = 0;
        for (Book b : BookBase.getBooks_3()) {
            strings_3[i++] = b.getName();
        }

        strings_4 = new String[BookBase.getBooks_4().size()];
        i = 0;
        for (Book b : BookBase.getBooks_4()) {
            strings_4[i++] = b.getName();
        }

        strings_5 = new String[BookBase.getBooks_5().size()];
        i = 0;
        for (Book b : BookBase.getBooks_5()) {
            strings_5[i++] = b.getName();
        }

        strings_6 = new String[BookBase.getBooks_6().size()];
        i = 0;
        for (Book b : BookBase.getBooks_6()) {
            strings_6[i++] = b.getName();
        }
    }

    /**
     * 类型与书名数组对应的map
     */
    private Map<String, String[]> model = new LinkedHashMap();

    /*
    通过代码块给map初始化
     */ {
        model.put("古代文学", strings_1);
        model.put("世界名著", strings_2);
        model.put("科幻", strings_3);
        model.put("哲学", strings_4);
        model.put("都市", strings_5);
        model.put("战争", strings_6);
    }

    /**
     * constructor function
     */
    public BooksComment() {

        /*
        默认展示种类
         */
        String kinds = (String) getKinds()[0];

        /*
        frame
         */
        this.setSize(800, 500);
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
        build panel
        下拉框选择要评价的书籍的模块
         */
        panel = new JPanel();
        panel.setBounds(20, 50, 720, 100);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "请选择要评价的书籍", TitledBorder
                .DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));


        /*
        build labels
         */
        JLabel label = new JLabel();
        label.setText("种类");
        label.setBounds(200, 20, 100, 50);
        panel.add(label);

        JLabel label_1 = new JLabel();
        label_1.setText("书籍名称");
        label_1.setBounds(510, 20, 100, 50);
        panel.add(label_1);

        /*
       build kinds ComboBox
         */
        kindsComboBox = new JComboBox();
        kindsComboBox.setBounds(40, 20, 150, 50);
        panel.add(kindsComboBox);


        /*
        build booksComboBox
         */
        booksComboBox = new JComboBox();
        booksComboBox.setBounds(350, 20, 150, 50);
        panel.add(booksComboBox);
        booksComboBox.setModel(new DefaultComboBoxModel(getBooks(kinds)));

        /*
          给kindsComboBox添加变换项目监听器，
        从而让booksComboBox的内容随着kindsComboBox的变化而变化
         */
        kindsComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) { // 选项状态更改事件
                itemChange();
            }
        });
        kindsComboBox.setModel(new DefaultComboBoxModel(getKinds()));


        /*
        构建评论输入框所在的轮滑框
         */
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane);
        scrollPane.setBounds(20, 150, 720, 200);
        scrollPane.setBorder(new TitledBorder("请输入您的评论"));

         /*
        构建评论输入框
         */
        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setFont(new Font(null, 2, 20));

        /*
        构建提交和清空按钮
         */
        JButton button_1 = new JButton("提交");
        JButton button_2 = new JButton("清空评论框");
        contentPane.add(button_1);
        contentPane.add(button_2);
        button_1.setBounds(230, 370, 100, 40);
        button_2.setBounds(430, 370, 100, 40);

        /*
        为按钮添加监听事件
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
    }

    /**
     * button_1
     * 提交按钮的实现
     */
    private void do_button_1() {

        text = textArea.getText();
        /*
        健壮性考虑，当用户未输入内容时，弹出提醒弹窗
         */
        if (text.equals("")) {
            JOptionPane.showMessageDialog(this, "评论内容不能为空");
            return;
        }

        JOptionPane.showMessageDialog(this, "评论成功，感谢您的评论与分享");

    }

    /**
     * button_2
     * 清空按钮的实现
     */
    private void do_button_2() {

        /*
        当评论框内无内容时，直接返回，不执行后续操作
         */
        if (text.equals("")) {
            return;
        }
        textArea.setText("");
    }


    /**
     * 获取书籍种类
     *
     * @return
     */
    public Object[] getKinds() {

        Map<String, String[]> map = model;
        Set<String> set = map.keySet();
        Object[] province = set.toArray();
        return province;
    }

    /**
     * 获取种类对应的书籍名称
     *
     * @param selectKinds
     * @return
     */
    public String[] getBooks(String selectKinds) {

        String[] arrBooks = model.get(selectKinds); // 获取指定键的值
        return arrBooks;
    }

    /**
     * 种类下拉框项目变化监听者动作的实现，
     *
     * @return
     */
    private void itemChange() {

        String selectKinds = (String) kindsComboBox.getSelectedItem();
        /*
        清空booksComboBox的内容
         */
        booksComboBox.removeAllItems();
        String[] arrBooks = getBooks(selectKinds);
        booksComboBox.setModel(new DefaultComboBoxModel(arrBooks));
    }
}
