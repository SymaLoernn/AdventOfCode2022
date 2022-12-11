package model;

import java.util.ArrayList;
import java.util.List;

public class MonkeyDay11 {

    int id;
    public long inspectionAmount = 0;
    List<Long> items = new ArrayList<>();

    // If not sum, then multiply
    boolean sum = true;

    boolean self = false;
    int valueOperation = 0;

    public long criticalStress = 0;

    public int test = 0;

    MonkeyDay11 targetTrue = null;
    MonkeyDay11 targetFalse = null;

    public MonkeyDay11(int id) {
        this.id = id;
    }

    public void init (String items, String operation, String test, String ifTrue, String ifFalse, List<MonkeyDay11> monkeys) {

        for (String item : items.substring(18).split(", ")) {
            this.items.add(Long.parseLong(item));
        }

        String[] operations = operation.substring(23).split(" ");
        if(operations[0].equals("+")) {
            this.sum = true;
        } else {
            this.sum = false;
        }

        if (operations[1].equals("old")) {
            this.self = true;
        } else {
            this.valueOperation = Integer.parseInt(operations[1]);
        }

        this.test = Integer.parseInt(test.substring(21));

        this.targetTrue = monkeys.get(Integer.parseInt(ifTrue.substring(29)));
        this.targetFalse = monkeys.get(Integer.parseInt(ifFalse.substring(30)));
    }

    public void processTurn () {
        for (long item : items) {
            inspectionAmount++;

            if (this.sum) {
                if (this.self) {
                    item = item + item;
                } else {
                    item = item + valueOperation;
                }
            } else {
                if (this.self) {
                    item = item * item;
                } else {
                    item = item * valueOperation;
                }
            }
            if (criticalStress == 0) {
                item = item / 3;
            } else {
                item = item%criticalStress;
            }


            if (item%test == 0) {
                this.throwItem(targetTrue, item);
            } else {
                this.throwItem(targetFalse, item);
            }
        }

        this.items = new ArrayList<>();
    }


    private void throwItem(MonkeyDay11 recipient, long item) {
        recipient.items.add(item);
    }

}
