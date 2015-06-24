package mainPackage; /**
 * Created by USER on 06.04.15.
 */

import model.Student;
import model.StudentList;

import myFileChooser.ChooseDialog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private DefaultTableModel model = new DefaultTableModel();
    private StudentList studentList;
    private String dateFormat;
    private String separator;
    private ArrayList<Integer> numbersOfSearchElements;
    private DefaultTableModel modelOfSearchTable;

    public Controller(StudentList studentList){
        this.separator = ":";
        this.dateFormat = "dd" + separator + "MM" + separator + "y";

        this.studentList = studentList;
        model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");

    }

    public void addNewStudent (String firstName, String lastName,String secondName, Date birthday, Date enterDate, Date finishDate){
        studentList.addStudent(firstName,secondName,lastName,birthday,enterDate,finishDate);
    }

    public void delStudents(){
        if(numbersOfSearchElements.size() > 0){
        for (int number = numbersOfSearchElements.size() - 1; number >= 0 ; number--){
            studentList.deleteStudent(numbersOfSearchElements.get(number));
        }
        }
        numbersOfSearchElements = null;
    }

    public void save() {
//        JFileChooser fileChooser = new JFileChooser();
        int returnVal =  0;
//        FileNameExtensionFilter filter;
//        filter = new FileNameExtensionFilter("xml","XML");
//        fileChooser.addChoosableFileFilter(filter);
        ChooseDialog chooseDialog = new ChooseDialog(null,System.getProperty("user.home"));
        File file;
        if(returnVal == 1){
            file = new File("D:/studentsTable.xml");
        }else{
            file = new File(chooseDialog.getSelectedFile().getAbsolutePath());
        }


        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("studentList");
            doc.appendChild(root);

            for (int currentStudent = 0; currentStudent < studentList.size(); currentStudent++) {
                Student student = studentList.getStudent(currentStudent);
                Element studentElement = doc.createElement("student");
                root.appendChild(studentElement);

                createFieldElement(doc, studentElement, "firstName", student.getFirstName());
                createFieldElement(doc, studentElement, "lastName", student.getLastName());
                createFieldElement(doc, studentElement, "secondName", student.getSecondName());
                DateFormat format = new SimpleDateFormat("y-MM-dd");
                createFieldElement(doc, studentElement, "birthday",format.format(student.getBirthday()));
                createFieldElement(doc, studentElement, "enterDate", format.format(student.getEnterDate()));
                createFieldElement(doc, studentElement, "finishDate",format.format(student.getFinishDate()));
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(domSource, result);

    } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void createFieldElement(Document doc, Element parent, String field, String value) {
        Element element = doc.createElement(field);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    public void open() {
        //("D:/таблица.xml");
//        JFileChooser fileChooser = new JFileChooser();
//
//        int returnVal =  fileChooser.showDialog(null, "Открыть");
//        FileNameExtensionFilter filter;
//        filter = new FileNameExtensionFilter("xml","XML");
//        fileChooser.addChoosableFileFilter(filter);
//        if(returnVal == 1) return;
//        File file;
//        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
       // File file = new File("D:/таблица.xml");
        studentList.deleteAll();
        ChooseDialog dialog = new ChooseDialog(new String[]{"xml"},"d:/Универ");
        File file = dialog.getSelectedFile();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root = (Element) doc.getElementsByTagName("studentList").item(0);
        NodeList  students = root.getElementsByTagName("student");


        for(int currentNode = 0;currentNode < students.getLength(); currentNode++){
            Student student  = new Student();

            Element studentNode = (Element) students.item(currentNode);
            student.setName(studentNode.getElementsByTagName("lastName").item(0).getTextContent(),
                    studentNode.getElementsByTagName("firstName").item(0).getTextContent(),
                    studentNode.getElementsByTagName("secondName").item(0).getTextContent());
            DateFormat format = new SimpleDateFormat("y-MM-dd");
            String birthday = studentNode.getElementsByTagName("birthday").item(0).getTextContent();
            Date date = null;
            try {
                date = format.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            student.setBirthday(date);
            String enterDate = studentNode.getElementsByTagName("enterDate").item(0).getTextContent();
            try {
                date = format.parse(enterDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            student.setEnterDate(date);
            String finishDate = studentNode.getElementsByTagName("finishDate").item(0).getTextContent();
            try {
                date = format.parse(finishDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            student.setFinishDate(date);

            studentList.addStudent(student);
        }
    }

    public List<String[]> getRows( int TypeOfRows){
        List<String[]> rowData = new ArrayList<String[]>() ;
        Student student;

        model = null;
        model = new DefaultTableModel();
        model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");
//        for (int rowCount = 0; rowCount < model.getRowCount(); rowCount++){
//            model.removeRow(rowCount);
//        }
        SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
        if(TypeOfRows == 0){
            for (int row = 0 ; row < studentList.size(); row++){
                student = studentList.getStudent(row);
            String [] rData = {student.getName(), format1.format(student.getBirthday()),
                    format1.format(student.getEnterDate()), format1.format(student.getFinishDate())};
            // model.addRow(rowData);
            rowData.add(rData);
        }
        }
        if(TypeOfRows == 1){
            if(numbersOfSearchElements != null){
                for (Integer numbersOfSearchElement : numbersOfSearchElements) {
                    student = studentList.getStudent(numbersOfSearchElement);
                    String[] rData = {student.getName(),
                            format1.format(student.getBirthday()),
                            format1.format(student.getEnterDate()),
                            format1.format(student.getFinishDate())};
                    rowData.add(rData);
                }
            }else{return null;}
        }

        return rowData;
    }


    public void findWithNameAndBDay(int numberSearch, String name, int date){
        List<String> names = studentList.getNames();
        numbersOfSearchElements = new ArrayList<Integer>();
        int birthdaySearch = 1;
        int enterDateSearch = 2;
        int finishDateSearch = 3;
        Student student;
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getBirthday().getDate()
                        && name.equals(student.getName())){
                    numbersOfSearchElements.add(currentStudent);
                }
            }

        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getEnterDate().getDate() && name.equals(student.getName())){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getFinishDate().getDate() && name.equals(student.getName())){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }

    }

    public void findStudentWithDate(int numberSearch, int date, int month){
        numbersOfSearchElements = new ArrayList<Integer>();
        int birthdaySearch = 1;
        int enterDateSearch = 2;
        int finishDateSearch = 3;
        Student student;
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
               if(date == student.getBirthday().getDate())
                    if( month == student.getBirthday().getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getEnterDate().getDate() && month == student.getEnterDate().getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getFinishDate().getDate() && month == student.getFinishDate().getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
    }
    public void findStudentWithDate(int numberSearch, int date, int yearFrom, int yearTo){
        numbersOfSearchElements = new ArrayList<Integer>();
        int birthdaySearch = 1;
        int enterDateSearch = 2;
        int finishDateSearch = 3;
        Student student;
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getBirthday().getDate() && yearFrom <= student.getBirthday().getYear()
                        && student.getBirthday().getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getEnterDate().getDate() && yearFrom <=  student.getEnterDate().getYear()
                        &&  student.getEnterDate().getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getFinishDate().getDate() && yearFrom <= student.getFinishDate().getYear()
                        && student.getFinishDate().getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
    }
}
