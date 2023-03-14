package ya_training.algo3_0.final_sprint.task1.gpt;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> train = new Stack<>();
        Map<String, Integer> cargoCount = new HashMap<>();

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String[] command = sc.nextLine().split(" ");

            switch (command[0]) {
                case "add":
                    int count = Integer.parseInt(command[1]);
                    String cargo = command[2];
                    for (int j = 0; j < count; j++) {
                        train.push(cargo);
                    }
                    cargoCount.put(cargo, cargoCount.getOrDefault(cargo, 0) + count);
                    break;

                case "delete":
                    int numToDelete = Integer.parseInt(command[1]);
                    for (int j = 0; j < numToDelete; j++) {
                        String removed = train.pop();
                        cargoCount.put(removed, cargoCount.get(removed) - 1);
                        if (cargoCount.get(removed) == 0) {
                            cargoCount.remove(removed);
                        }
                    }
                    break;

                case "get":
                    String targetCargo = command[1];
                    System.out.println(cargoCount.getOrDefault(targetCargo, 0));
                    break;

                default:
                    break;
            }
        }
    }
}
