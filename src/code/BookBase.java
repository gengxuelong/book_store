package code;

import java.io.*;
import java.util.ArrayList;

/**
 * @author gengxuelong
 * @date 2021-11-19 14:10
 * <p>
 * 书库类，用以提供图书数据和与文件的交互
 */
public class BookBase {

    private static final ArrayList<Book> books_1 = new ArrayList<>();
    private static final ArrayList<Book> books_2 = new ArrayList<>();
    private static final ArrayList<Book> books_3 = new ArrayList<>();
    private static final ArrayList<Book> books_4 = new ArrayList<>();
    private static final ArrayList<Book> books_5 = new ArrayList<>();
    private static final ArrayList<Book> books_6 = new ArrayList<>();

    /**
     *
     * 获得数据
     * 从文件中加载数据至ArrayList中
     */
    static {
        BookBase.read();
    }

    /**
     * getter function
     *
     * @return
     */
    public static ArrayList<Book> getBooks_1() {
        return books_1;
    }

    public static ArrayList<Book> getBooks_2() {
        return books_2;
    }

    public static ArrayList<Book> getBooks_3() {
        return books_3;
    }

    public static ArrayList<Book> getBooks_4() {
        return books_4;
    }

    public static ArrayList<Book> getBooks_5() {
        return books_5;
    }

    public static ArrayList<Book> getBooks_6() {
        return books_6;
    }

    /**
     * 储存数据
     * 将上述ArrayList中的数据储存到文件中
     */
    public static void write() {

        try {

            /*
            构建输出流
             */
            DataOutputStream data1 = new DataOutputStream(new FileOutputStream(new File("./src/book-txt/book1.txt")));
            DataOutputStream data2 = new DataOutputStream(new FileOutputStream(new File("./src/book-txt/book2.txt")));
            DataOutputStream data3 = new DataOutputStream(new FileOutputStream(new File("./src/book-txt/book3.txt")));
            DataOutputStream data4 = new DataOutputStream(new FileOutputStream(new File("./src/book-txt/book4.txt")));
            DataOutputStream data5 = new DataOutputStream(new FileOutputStream(new File("./src/book-txt/book5.txt")));
            DataOutputStream data6 = new DataOutputStream(new FileOutputStream(new File("./src/book-txt/book6.txt")));

            /*
            向文件中写入Array List中的数据
             */
            for (Book b : BookBase.getBooks_1()) {

                data1.writeUTF(b.toString());
                data1.writeUTF("\n");
            }
            for (Book b : BookBase.getBooks_2()) {

                data2.writeUTF(b.toString());
                data2.writeUTF("\n");
            }
            for (Book b : BookBase.getBooks_3()) {

                data3.writeUTF(b.toString());
                data3.writeUTF("\n");
            }
            for (Book b : BookBase.getBooks_4()) {

                data4.writeUTF(b.toString());
                data4.writeUTF("\n");
            }
            for (Book b : BookBase.getBooks_5()) {

                data5.writeUTF(b.toString());
                data5.writeUTF("\n");
            }
            for (Book b : BookBase.getBooks_6()) {

                data6.writeUTF(b.toString());
                data6.writeUTF("\n");
            }

            /*
            关闭数据流
             */
            data1.close();
            data2.close();
            data3.close();
            data4.close();
            data5.close();
            data6.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据
     */
    public static void read() {

        try {
            String str = null;
            BufferedReader data1 = new BufferedReader(new FileReader(new File("./src/book-txt/book1.txt")));
            while ((str = data1.readLine()) != null) {
                String[] strings = str.split("-");
                String name = strings[0];
                String author = strings[1];
                double price = Double.parseDouble(strings[2]);
                String describe = strings[3];
                Book b = new Book(name, author, price, describe);
                BookBase.getBooks_1().add(b);
            }

            BufferedReader data2 = new BufferedReader(new FileReader(new File("./src/book-txt/book2.txt")));
            str = null;
            while ((str = data2.readLine()) != null) {
                String[] strings = str.split("-");
                String name = strings[0];
                String author = strings[1];
                double price = Double.parseDouble(strings[2]);
                String describe = strings[3];
                Book b = new Book(name, author, price, describe);
                BookBase.getBooks_2().add(b);
            }

            BufferedReader data3 = new BufferedReader(new FileReader(new File("./src/book-txt/book3.txt")));
            str = null;
            while ((str = data3.readLine()) != null) {
                String[] strings = str.split("-");
                String name = strings[0];
                String author = strings[1];
                double price = Double.parseDouble(strings[2]);
                String describe = strings[3];
                Book b = new Book(name, author, price, describe);
                BookBase.getBooks_3().add(b);
            }

            BufferedReader data4 = new BufferedReader(new FileReader(new File("./src/book-txt/book4.txt")));
            str = null;
            while ((str = data4.readLine()) != null) {
                String[] strings = str.split("-");
                String name = strings[0];
                String author = strings[1];
                double price = Double.parseDouble(strings[2]);
                String describe = strings[3];
                Book b = new Book(name, author, price, describe);
                BookBase.getBooks_4().add(b);
            }

            BufferedReader data5 = new BufferedReader(new FileReader(new File("./src/book-txt/book5.txt")));
            str = null;
            while ((str = data5.readLine()) != null) {
                String[] strings = str.split("-");
                String name = strings[0];
                String author = strings[1];
                double price = Double.parseDouble(strings[2]);
                String describe = strings[3];
                Book b = new Book(name, author, price, describe);
                BookBase.getBooks_5().add(b);
            }

            BufferedReader data6 = new BufferedReader(new FileReader(new File("./src/book-txt/book6.txt")));
            str = null;
            while ((str = data6.readLine()) != null) {
                String[] strings = str.split("-");
                String name = strings[0];
                String author = strings[1];
                double price = Double.parseDouble(strings[2]);
                String describe = strings[3];
                Book b = new Book(name, author, price, describe);
                BookBase.getBooks_6().add(b);
            }

            data1.close();
            data2.close();
            data3.close();
            data4.close();
            data5.close();
            data6.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
