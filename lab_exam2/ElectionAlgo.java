package lab_exam2;


public class ElectionAlgo {


    int TotalProcess;
    Processes[] processes;
    public ElectionAlgo() { }
    public void initialiseProgram()
    {
        System.out.println("No of processes 5");
        TotalProcess = 5;
        processes = new Processes[TotalProcess];
        int i = 0;
        while (i < processes.length) {
            processes[i] = new Processes(i);
            i++;
        }
    }
    public void Election()
    {
        System.out.println("Process no " + processes[FetchMaximum()].id + " fails");
        processes[FetchMaximum()].act = false;
        System.out.println("Election Initiated by 2");
        int initializedProcess = 2;

        int old = initializedProcess;
        int newpro = old + 1;

        while (true) {
            if (processes[newpro].act) {
                System.out.println(
                        "Process " + processes[old].id
                                + " pass Election(" + processes[old].id
                                + ") to" + processes[newpro].id);
                old = newpro;
            }

            newpro = (newpro + 1) % TotalProcess;
            if (newpro == initializedProcess) {
                break;
            }
        }

        System.out.println("Process "
                + processes[FetchMaximum()].id
                + " becomes coordinator");
        int coord = processes[FetchMaximum()].id;

        old = coord;
        newpro = (old + 1) % TotalProcess;

        while (true) {

            if (processes[newpro].act) {
                System.out.println(
                        "Process " + processes[old].id
                                + " pass Coordinator(" + coord
                                + ") message to process "
                                + processes[newpro].id);
                old = newpro;
            }
            newpro = (newpro + 1) % TotalProcess;
            if (newpro == coord) {
                System.out.println("End Of Election ");
                break;
            }
        }
    }
    public int FetchMaximum()
    {
        int IntiId = 0;
        int maxId = -10000;
        int i = 0;
        while (i < processes.length) {
            if (processes[i].act && processes[i].id > maxId) {
                maxId = processes[i].id;
                IntiId = i;
            }
            i++;
        }
        return IntiId;
    }

    public static void main(String arg[])
    {
        ElectionAlgo object = new ElectionAlgo();
        object.initialiseProgram();
        object.Election();
    }


    class Processes {
        int id;
        boolean act;
        Processes(int id)
        {
            this.id = id;
            act = true;
        }
    }


}