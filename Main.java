import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        boolean running = true;
        String FinishProgramm = "";

        while(running) {

            System.out.println("Выберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать задачи");
            System.out.println("3. Удалить задачу");
            System.out.println("4.Cохранить файл с задачами");
            System.out.println("5. Удалить файл с задачами");
            System.out.println("6. Выход");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Введите новую задачу: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("Новая задача добавлена");
                    break;

                case 2:
                    if (tasks.isEmpty()) {
                        System.out.println("Cписок задач пуст! Добавьте новые задачи.");
                    } else {
                        System.out.println("Список задач: ");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + "." +  tasks.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.println("Введите индекс удаляемого задачи: ");
                    int removeTask = scanner.nextInt();

                    if(removeTask > 0 && removeTask <= tasks.size()) {
                        tasks.remove(removeTask - 1);
                        System.out.println("Задача " + removeTask + " удалена");
                    } else if(tasks.isEmpty()) {
                        System.out.println("Список задач пуст.");
                    }
                    else {
                        System.out.println("Несуществующий индекс.Повторите снова");
                        return;
                    }
                    break;

                case 4:

                    saveTasksFile(tasks);
                    break;
                    case 5:
deleteTasksFile();
break;

                case 6:
                    System.out.println("Вы деиствительно хотите завершить программу? Y/N: ");
                    FinishProgramm = scanner.nextLine().toUpperCase();
                    if(FinishProgramm.equals("Y")) {
                        System.out.println("Программа завершена");
                        running = false;
                    } else if (FinishProgramm.equals("N")) {
                        System.out.println("Отмена завершения");
                    } else {
                        System.out.println("Ошибка ввода!Повторите еще раз");
                        continue;
                    }
                    break;
                default:
                    System.out.println("Неправильный ввод! Повторите еще раз");
            }
        }
    }

    public static void deleteTasksFile () {
        String filename  = "tasks.txt";
        File file = new File(filename);

        if(file.exists()) {
            if(file.delete()) {
                System.out.println("Файл " + filename + " был удален");
            }
        } else {
            System.out.println("Файл не существует");
        }


    }

    public static void saveTasksFile(ArrayList<String> tasks) throws IOException {

        try {
            FileWriter writer = new FileWriter("tasks.txt");
            for (String taskItem : tasks) {
                writer.write(taskItem + "\n");
            }
            System.out.println("Файл сохранен");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }
}
