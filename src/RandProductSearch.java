import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandProductSearch extends JFrame{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel displayPnl;
    JPanel searchPnl;
    JLabel titleLbl;
    JLabel searchLbl;

    JButton searchRAF;
    JTextArea getSearch;
    JButton quitBtn;
    static JTextArea displayTA;
    JScrollPane scroller;
    String searchedString;
    static String resultSearch = "";
    static File objFile = new File("C:\\Users\\legol\\IdeaProjects\\FileStreams\\src\\product.txt");
    static RandomAccessFile randAccFile;

    public RandProductSearch()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createSearchPanel();
        mainPnl.add(searchPnl, BorderLayout.CENTER);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createTitlePanel()
    {
        titlePnl = new JPanel();
        titlePnl.setLayout(new GridLayout(2, 1));
        titleLbl = new JLabel("Search", JLabel.CENTER);
        titleLbl.setFont(new Font("Courier", Font.BOLD,30));
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);


        titlePnl.add(titleLbl);

    }

    private void createDisplayPanel()
    {
        displayPnl = new JPanel();

        displayPnl.setLayout(new GridLayout(1, 1));
        displayTA = new JTextArea(10, 80);
        displayTA.setEditable(false);
        displayTA.setFont(new Font("Courier New", Font.PLAIN, 12));
        scroller = new JScrollPane(displayTA);

        displayPnl.add(scroller);

    }
    private void createSearchPanel()
    {
        searchPnl = new JPanel();

        searchPnl.setLayout(new GridLayout(4, 1));

        searchLbl = new JLabel("Name:");
        getSearch = new JTextArea(4, 25);


        searchRAF = new JButton("Search File");
        searchRAF.addActionListener((ActionEvent ae) ->{
            searchedString = getSearch.getText();
            getSearchedString(searchedString);
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        searchPnl.add(searchLbl);
        searchPnl.add(getSearch);
        searchPnl.add(searchRAF);
        searchPnl.add(quitBtn);
    }
    public static void getSearchedString(String searchedString)
    {
        try {
            randAccFile = new RandomAccessFile(objFile, "r");
            randAccFile.seek(randAccFile.length());

            try (Stream<String> lines = Files.lines(Paths.get(String.valueOf("C:\\Users\\legol\\IdeaProjects\\FileStreams\\src\\product.txt"))))
            {
                List<Object> examples = lines
                        .filter(w -> !w.endsWith("."))
                        .filter(w -> w.toLowerCase().contains(searchedString))
                        .collect(Collectors.toList());
                for(int i = 0; i<examples.size();i++) {
                    String stringLines = (String) examples.get(i);
                    displayTA.append(resultSearch + stringLines +"\n" +"\n");
                }
                randAccFile.close();



            }
            catch (IOException e) {
                e.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
