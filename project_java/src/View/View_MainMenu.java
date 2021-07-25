package View;

import javax.swing.JButton;
import javax.swing.JFrame;



import java.util.*;

/**
 * Classe View_MainMenu Responsável pela view do MainMenu
 */
public class View_MainMenu implements IViewJ{
    private MainMenu window;

    /**
     * Construtor por omissão do View_MainMenu
     */
    public View_MainMenu(){
        window = new MainMenu("Main Menu");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500,500);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Getter do MainMenu
     * @return      MainMenu obtido
     */
    public MainMenu getWindow()
    {
        return window;
    }

    /**
     * Setter do MainMenu
     * @return      MainMenu a colocar
     */
    public void setWindow(MainMenu window)
    {
        this.window = window;
    }

    /**
     * Getter do botão da query estatística 1
     * @return      Botão correspondente à query 1
     */
    public JButton getQueryE1Button()
    {
        return window.getQueryE1Button();
    }

    /**
     * Getter do botão da query estatística 2
     * @return      Botão correspondente à query 1
     */
    public JButton getQueryE2Button()
    {
        return window.getQueryE2Button();
    }

    /**
     * Getter do botão da query1
     * @return      Botão correspondente à query 1
     */
    public JButton getQuery1Button()
    {
        return window.getQuery1Button();
    }

    /**
     * Getter do botão da query2
     * @return      Botão correspondente à query 2
     */
    public JButton getQuery2Button()
    {
        return window.getQuery2Button();
    }

    /**
     * Getter do botão da query3
     * @return      Botão correspondente à query 3
     */
    public JButton getQuery3Button()
    {
        return window.getQuery3Button();
    }

    /**
     * Getter do botão da query4
     * @return      Botão correspondente à query 4
     */
    public JButton getQuery4Button()
    {
        return window.getQuery4Button();
    }

    /**
     * Getter do botão da query5
     * @return      Botão correspondente à query 5
     */
    public JButton getQuery5Button()
    {
        return window.getQuery5Button();
    }

    /**
     * Getter do botão da query6
     * @return      Botão correspondente à query 6
     */
    public JButton getQuery6Button()
    {
        return window.getQuery6Button();
    }

    /**
     * Getter do botão da query7
     * @return      Botão correspondente à query 7
     */
    public JButton getQuery7Button()
    {
        return window.getQuery7Button();
    }

    /**
     * Getter do botão da query8
     * @return      Botão correspondente à query 8
     */
    public JButton getQuery8Button()
    {
        return window.getQuery8Button();
    }

    /**
     * Getter do botão da query9
     * @return      Botão correspondente à query 9
     */
    public JButton getQuery9Button()
    {
        return window.getQuery9Button();
    }

    /**
     * Getter do botão da query10
     * @return      Botão correspondente à query 10
     */
    public JButton getQuery10Button()
    {
        return window.getQuery10Button();
    }

    /**
     * Getter do botão do load from disk
     * @return      Botão correspondente ao load from disk
     */
    public JButton getLoadFromDiskButton()
    {
        return window.getLoadFromDiskButton();
    }

    /**
     * Getter do botão do save to disk
     * @return      Botão correspondente ao save to disk
     */
    public JButton getSaveToDiskButton()
    {
        return window.getSaveToDiskButton();
    }

    /**
     * Getter do botão do load
     * @return      Botão correspondente ao load
     */
    public JButton getLoadButton()
    {
        return window.getLoadButton();
    }

    /**
     * Getter do botão de exit
     * @return      Botão correspondente ao exit
     */
    public JButton getExitButton()
    {
        return window.getExitButton();
    }

    public void setQueryButtonsEnable(boolean bool){
        window.getQueryE1Button().setEnabled(bool);
        window.getQueryE2Button().setEnabled(bool);

        window.getQuery1Button().setEnabled(bool);
        window.getQuery2Button().setEnabled(bool);
        window.getQuery3Button().setEnabled(bool);
        window.getQuery4Button().setEnabled(bool);
        window.getQuery5Button().setEnabled(bool);
        window.getQuery6Button().setEnabled(bool);
        window.getQuery7Button().setEnabled(bool);
        window.getQuery8Button().setEnabled(bool);
        window.getQuery9Button().setEnabled(bool);
        window.getQuery10Button().setEnabled(bool);
    }

    public void setLoadedStatus(boolean status){
        window.getLoadButton().setEnabled(!status);
        window.getLoadFromDiskButton().setEnabled(!status);
        window.getSaveToDiskButton().setEnabled(status);
    }
    
    public void print(Object o){
        System.out.print(o);
    }

    public void printError(String msg){
        System.err.println("Error: "+msg);
    }

    public static char proxPag(){
        char ch;
        Scanner sc = new Scanner(System.in);

        System.out.print("\033[1mPagina Seguinte       (P)\n\033[0m");
        System.out.print("\033[1mPagina Anterior       (A)\n\033[0m");
        System.out.print("\033[1mVoltar ao Menu        (M)\n\033[0m");
        System.out.print("\033[1mIr Para a Pagina      (E)\n\033[0m");
        System.out.print("\033[1m>\033[0m");

        do{
            ch = Character.toUpperCase(sc.nextLine().charAt(0));
        } while((ch!='P')&&(ch!='A')&&(ch!='M')&&(ch!='E'));
        
        sc.close();
        return ch;
    }

    public static void clear(){
        System.out.print("\033\143");
    }
    
    public static void printLinha(int i){
        System.out.printf("\t       %s\t\n", IViewJ.SEPS[i]);
    }

    public void listDivider (List<String> l, String message, String header, int q) {
        System.out.print("\033[2J");

        boolean b = true;
        Scanner sc = new Scanner(System.in);

        int N = l.size();
        int pages = N/10;
        if ((N%10)!=0) 
            pages++;

        int j=1, k=0, t=0, temp1=0, temp2=0, n;
        char ch;
        
        while(j<=pages&&t<N && b){

            while(true){
                temp1=t;
                System.out.printf("\033[1m%s\n\n\033[0m", message);
                printLinha(q);
                
                System.out.printf("\033[1m\t       %s\n\033[0m", header);            
                for(n=0; n<10; n++, t++){
                    if(k+n>=N) {b=false; break;}
                    else{
                        printLinha(q);
                        System.out.printf("\t%0"+5+"d. %s\n", n+k+1, l.get(k+n));
                    }
                }
                printLinha(q);
                temp2=k;
                k+=10;
                System.out.printf("\033[1m\nPage <%d/%d> \n\n\033[0m",j ,pages);
                j++; 
                
                ch = proxPag();

                if(ch=='P'){
                    if(j==pages+1){
                        j=1; t=0; k=0;
                        System.out.printf("You are already on the last page!\nPress C to return to the first page\n> ");
                        while (Character.toUpperCase(sc.nextLine().charAt(0)) !='C');
                        System.out.printf("\033[2J");
                    }
                    else System.out.printf("\033[2J");
                }

                else if(ch=='M') 
                    {b=false; break;}
            
                else if(ch=='A'){

                    if(j!=2){
                        System.out.print ("\033[2J");
                        k = temp2-10; j = j-2; t = temp1-10;
                    }
                    else {
                        System.out.print("You are already on the first page!\nPress C to continue\n> "); 
                        while (Character.toUpperCase(sc.nextLine().charAt(0))!='C');
                        System.out.print("\033[2J");
                    }
                }

                else if(ch=='E'){
                    System.out.print("Escolha o numero da página:\n> ");
                    int f;
                    do{
                        f = sc.nextInt(); sc.nextLine();

                        if(f<1 || f>pages)
                            System.out.printf("Página Inválida. Insira uma pagina entre 1 e %d.\n",pages);
                        else{
                            k=(f*10)-10;
                            j = f;
                            t = f*10;
                            System.out.print("\033[2J");
                        }
                    } while(f<1 || f>pages);
                }
            }
        }
        sc.close();
    }

    public void showQuery(List<String> p, String msg, int query){
        String head = IViewJ.QUERIES[query];
        this.listDivider(p, msg, head, query);
    }
}
