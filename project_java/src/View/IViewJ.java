package View;

import java.util.List;

import javax.swing.JButton;

public interface IViewJ{

    public void print(Object o);
    public void printError(String msg);

    public static final String QUERY1 = "|      business_id       |";
    public static final String QUERY2 = "| num_reviews | num_users |";
    public static final String QUERY3 = "| month | num_reviews | distinct | average_stars |";
    public static final String QUERY4 = "| num_reviews | distinct | average_stars |";
    public static final String QUERY5 = "|      name      | num_reviews |";
    public static final String QUERY6 = "|      business_id       | num_reviews | distinct |";
    public static final String QUERY7 = "|      business_id       | num_reviews |";
    public static final String QUERY8 = "|        user_id         | num_reviews |";
    public static final String QUERY9 = "|        user_id         | num_reviews | average_stars |";
    public static final String QUERY10 = "|      business_id       | average_stars |";

    public static final String[] QUERIES = {QUERY1, QUERY2, QUERY3, QUERY4, QUERY5,
                                            QUERY6, QUERY7, QUERY8, QUERY9, QUERY10};

    public static final String SEP1 = "--------------------------";
    public static final String SEP2 = "---------------------------";
    public static final String SEP3 = "--------------------------------------------------";
    public static final String SEP4 = "------------------------------------------";
    public static final String SEP5 = "--------------------------------";
    public static final String SEP6 = "---------------------------------------------------";
    public static final String SEP7_8 = "----------------------------------------";
    public static final String SEP9 = "--------------------------------------------------------";
    public static final String SEP10 = "------------------------------------------";

    public static final String[] SEPS = {SEP1, SEP2, SEP3, SEP4, SEP5, 
                                         SEP6, SEP7_8, SEP7_8, SEP9, SEP10};

    public void showQuery(List<String> p, String msg, int query);

     /**
     * Getter do botão da query estatística 1
     * @return      Botão correspondente à query 1
     */
    public JButton getQueryE1Button();

    /**
     * Getter do botão da query estatística 2
     * @return      Botão correspondente à query 1
     */
    public JButton getQueryE2Button();

    /**
     * Getter do botão da query1
     * @return      Botão correspondente à query 1
     */
    
    public JButton getQuery1Button();
    /**
     * Getter do botão da query2
     * @return      Botão correspondente à query 2
     */
    public JButton getQuery2Button();

    /**
     * Getter do botão da query3
     * @return      Botão correspondente à query 3
     */
    public JButton getQuery3Button();

    /**
     * Getter do botão da query4
     * @return      Botão correspondente à query 4
     */
    public JButton getQuery4Button();

    /**
     * Getter do botão da query5
     * @return      Botão correspondente à query 5
     */
    public JButton getQuery5Button();

    /**
     * Getter do botão da query6
     * @return      Botão correspondente à query 6
     */
    public JButton getQuery6Button();

    /**
     * Getter do botão da query7
     * @return      Botão correspondente à query 7
     */
    public JButton getQuery7Button();

    /**
     * Getter do botão da query8
     * @return      Botão correspondente à query 8
     */
    public JButton getQuery8Button();

    /**
     * Getter do botão da query9
     * @return      Botão correspondente à query 9
     */
    public JButton getQuery9Button();

    /**
     * Getter do botão da query10
     * @return      Botão correspondente à query 10
     */
    public JButton getQuery10Button();

    /**
     * Getter do botão do load from disk
     * @return      Botão correspondente ao load from disk
     */
    public JButton getLoadFromDiskButton();

    /**
     * Getter do botão do save to disk
     * @return      Botão correspondente ao save to disk
     */
    public JButton getSaveToDiskButton();

    /**
     * Getter do botão do load
     * @return      Botão correspondente ao load
     */
    public JButton getLoadButton();

    /**
     * Getter do botão de exit
     * @return      Botão correspondente ao exit
     */
    public JButton getExitButton();

    /**
     * Função que determina se os botões das queries estão disponíveis ou não
     * @param bool      Booleano que defini disponibilidade das queries
     */
    public void setQueryButtonsEnable(boolean bool);

    /**
    * Método que determinar se os botões dos loads e save estão disponíveis
    * @param status      Booleano que indica se o model se encontra loaded
    */
    public void setLoadedStatus(boolean status);

}