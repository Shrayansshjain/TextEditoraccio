import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener{

    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;

    //File menu Items
    JMenuItem newFile, openFile, saveFile;
    //edit menu Items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor()     //constructor
    {
        //Initializing JFrame
        frame = new JFrame();

        //Initializing JMenuBar
        menuBar = new JMenuBar();

        //Initialize text area
        textArea = new JTextArea();

        //Initializing Menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize file menu items
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add file items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //add edit items to edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //set menuBar to frame
        frame.setJMenuBar(menuBar);

        /*//add text area
        frame.add(textArea);*/

        //create content panel to make it scrollable
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //Add text area to panel
        panel.add(textArea,BorderLayout.CENTER);

        //Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Add scroll pane to panel
        panel.add(scrollPane);

        //Add panel to frame
        frame.add(panel);

        //set dimensions of frame
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor 2.0");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override          //to perform actions on the options provided
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == cut)
        {
            textArea.cut();
        }
        if(actionEvent.getSource() == copy)
        {
            textArea.copy();
        }
        if(actionEvent.getSource() == paste)
        {
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll)
        {
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close)
        {
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile)
        {
            //open file choose
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we have clicked an open button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "" , output = "";
                    //read file line by line
                    while((intermediate = bufferedReader.readLine()) != null)
                    {
                        output += intermediate + "\n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //Create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+ ".txt");

                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of textArea to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile)
        {
            TextEditor newtextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

    }
}