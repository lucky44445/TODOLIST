import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать задачи");
            System.out.println("3.Удалить задачу");
            System.out.println("4.Удалить файл с задачами");
            System.out.println("5.Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите новую задачу: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("Задача добавлена");
                    break;

                case 2:
                    System.out.println("Ваши задачи: ");
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + "." + tasks.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Введите индекс удаляемого задачи: ");
                    int removeTask = scanner.nextInt();
                    if (removeTask > 0 && removeTask <= tasks.size()) {
                        tasks.remove(removeTask - 1);
                        System.out.println("задача " + " удалена");
                    } else {
                        System.out.println("некоректный номер задачи!");
                    }
                    break;
                case 4:
                    System.out.println("Вы деиствительно хотите удалить задачу? Y/N?");
                    String accept  = scanner.nextLine();
                    if (accept.equalsIgnoreCase("y")) {
                        DeleteToFile();
                        System.out.println("ваша задача удалена");
                    } else if (accept.equalsIgnoreCase("n")){
                        System.out.println("Удаление отменено");
                    } else {
                        System.out.println("Некорректный ответ.Попробуйте снова");
                    }
//                    DeleteToFile();
                    break;
                case 5:
                    saveTasksToFile(tasks);
                    System.out.println("Программа завершена! все данные сохранены в файл " + "tasks.txt" );
                    return;
            }
        }

    }

    private static void saveTasksToFile(ArrayList<String> tasks) {
        try {
            FileWriter writer = new FileWriter("tasks.txt");
            for (String taskItem : tasks) {
                writer.write(taskItem + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }

    private static void DeleteToFile () {
        String fileName = "tasks.txt";
        File file = new File (fileName);
        if(file.delete()) {
            System.out.println("Файл " + fileName +  " Удален");
        }
        else {
            System.out.println("Ошибка при удалений файла" + " ");
        }
    }
}

