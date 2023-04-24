import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RandProductMaker extends JFrame{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel displayPnl;
    JPanel searchPnl;
    JLabel titleLbl;
    JLabel nameLbl;
    JLabel dscrptLbl;
    JLabel idLbl;
    JLabel costLbl;

    JButton addNewItem;
    JTextArea getName;
    JTextArea getDescription;
    JTextArea getID;
    JTextArea getCost;
    JButton quitBtn;

    JButton createRAF;

    static JTextArea displayTA;
    static JTextArea displayOG;
    JScrollPane scroller;
    String nameString;
    String dscrptString;
    String idNum;
    double costNum;
    Boolean fileChosen = false;

    public RandProductMaker()
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

        searchPnl.setLayout(new GridLayout(11, 1));

        nameLbl = new JLabel("Name:");
        getName = new JTextArea(1, 25);

        dscrptLbl = new JLabel("Desciption:");
        getDescription = new JTextArea(1, 25);

        idLbl = new JLabel("ID:");
        getID = new JTextArea(1, 8);

        costLbl = new JLabel("Cost:");
        getCost = new JTextArea(1, 8);

        addNewItem = new JButton("Add New Item");
        addNewItem.addActionListener((ActionEvent ae) ->{
            nameString = getName.getText();
            dscrptString = getDescription.getText();
            idNum = getID.getText();
            costNum = Double.parseDouble(getCost.getText());
            if(nameString.length() <= 35){
                if(dscrptString.length() <= 75){
                    if(idNum.length() <= 6) {
                        FileMaster.setRAF(nameString, dscrptString, idNum, costNum);
                    }else {
                        displayTA.append("ID should be 6 characters or less");
                    }
                }else {
                    displayTA.append("Description is too long");
                }
            }else {
                displayTA.append("Name is too long");
            }
            getName.setText("");
            getDescription.setText("");
            getID.setText("");
            getCost.setText("");
            if(FileMaster.getRAF()){
                displayTA.append("Object added to File");
            }else{
                displayTA.append("Error. File NOT written. Retry");
            }

        });


        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        searchPnl.add(nameLbl);
        searchPnl.add(getName);
        searchPnl.add(dscrptLbl);
        searchPnl.add(getDescription);
        searchPnl.add(idLbl);
        searchPnl.add(getID);
        searchPnl.add(costLbl);
        searchPnl.add(getCost);
        searchPnl.add(addNewItem);
        searchPnl.add(quitBtn);
    }
}
