package semestralniPrace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import net.miginfocom.swing.MigLayout;
import oponent.Factory;
import oponent.Oponent;


public class GameDesk extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static List<Pile> piles;
    private Oponent oponent;
    private final static Logger LOGGER = Logger.getLogger(GameDesk.class);

    public GameDesk() {
        LOGGER.debug("Inicializace tridy GameDesk.java");
        setLayout(new MigLayout());
        createGameDesk();
        add(addRemoveButton());
        oponent = new Factory().getOponent();
    }


    public void createGameDesk() {
        
        int countOfPile = Integer.parseInt(Helper.gameSetting.get(Helper.NUMBER_OF_PILE));
        LOGGER.debug("Vytvarim hraci plochu s " + countOfPile + " hromadkami.");
        piles = new ArrayList<>();
        for (int group = 0; group < countOfPile; group++) {
            Pile pile = new Pile(group);
            add(pile, "wrap");
            piles.add(pile);
            
        }

    }


    public JButton addRemoveButton() {
        JButton remove = new JButton("Remove");
        remove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {            
                        Helper.removeButton();
                        Thread thread = new Thread((Runnable)oponent);
                        thread.start();
                    }
        });
        return remove;
    }

}
