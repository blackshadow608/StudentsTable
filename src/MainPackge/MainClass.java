package MainPackge;
import Model.StudentList;

//import MainPackge.MainView;

/**
 * Created by USER on 30.03.15.
 */
public class MainClass {
    public static void main(String [] args){

        StudentList studentList = new StudentList();
        Controller controller = new Controller(studentList);
        MainView view = new MainView(controller);


    }
}
