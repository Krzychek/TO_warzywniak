package to_shop.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

abstract public class CLIView implements Runnable {
    private String header;
    protected boolean running = true;
    protected List<Option> optionList = new ArrayList<>();

    protected class Option implements Runnable {
        private String name;
        private Callable<Boolean> callBack;

        Option(String name, Callable<Boolean> callBack) {
            this.name = name;
            this.callBack = callBack;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public void run() {
            try {
                running = callBack.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public CLIView(String header) {
        this();
        this.header = header;
    }

    public CLIView() {
        optionList.add(new Option("exit", () -> false));
        setUpOptions();
    }


    public void run() {
        while (running) {
            if(this.header != null)
                System.out.println(this.header);
            StringBuilder str = new StringBuilder();
            str.append("\n");
            for(int i=0; i < optionList.size(); i++) {
                str.append("(");
                str.append(i);
                str.append(") ");
                str.append(optionList.get(i));
                str.append("\n");
            }
            str.append("Action: ");
            System.out.print(str.toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                processAnswer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void processAnswer(String answer) {
        int optionNr = Integer.parseInt(answer);
        if(optionNr >= 0 && optionNr < optionList.size()) {
            optionList.get(optionNr).run();
        }
    }

    protected void setUpOptions() {}
}
