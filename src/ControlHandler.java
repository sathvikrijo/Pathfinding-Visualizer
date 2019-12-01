import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class ControlHandler implements Styles{
    private Frame frame;
    private ArrayList<JLabel> labels;
    private ArrayList<JCheckBox> checkBoxes;
    private JCheckBox checkShowSteps, checkDiagonals;
    private JSlider speed;
    private JLabel speedText, speedCounter, openText, openCount, closedText, closedCount, pathText, pathCounter, noPath;

    ControlHandler(Frame F){
        this.frame = F;

        labels = new ArrayList<>();
        checkBoxes = new ArrayList<>();

        speedText = new JLabel("Speed: ");
        speedText.setName("speedText");
        speedText.setFont(Styles.numbers);
        speedText.setVisible(true);
        speedCounter = new JLabel("50");
        speedCounter.setName("speedCounter");
        speedCounter.setFont(Styles.numbers);
        speedCounter.setVisible(true);

        openText = new JLabel("Open: ");
        openText.setName("openText");
        openText.setFont(Styles.numbers);
        openText.setVisible(true);
        openCount = new JLabel("0");
        openCount.setName("openCount");
        openCount.setFont(Styles.numbers);
        openCount.setVisible(true);

        closedText = new JLabel("Closed: ");
        closedText.setName("closedText");
        closedText.setFont(Styles.numbers);
        closedText.setVisible(true);
        closedCount = new JLabel("0");
        closedCount.setName("closedCount");
        closedCount.setFont(Styles.numbers);
        closedCount.setVisible(true);

        noPath = new JLabel("NO PATH");
        noPath.setName("noPath");
        noPath.setForeground(Color.white);
        noPath.setFont(Styles.bigText);
        noPath.setVisible(true);

        labels.add(speedText);
        labels.add(speedCounter);
        labels.add(openText);
        labels.add(openCount);
        labels.add(closedText);
        labels.add(closedCount);
        labels.add(noPath);

        checkShowSteps = new JCheckBox();
        checkShowSteps.setName("checkShowSteps");
        checkShowSteps.setText("show Steps");
        checkShowSteps.setOpaque(false);
        checkShowSteps.setSelected(true);
        checkShowSteps.setFocusable(false);
        checkShowSteps.setVisible(true);

        checkDiagonals = new JCheckBox();
        checkDiagonals.setName("checkDiagonals");
        checkDiagonals.setText("Diagonals");
        checkDiagonals.setOpaque(false);
        checkDiagonals.setSelected(true);
        checkDiagonals.setFocusable(false);
        checkDiagonals.setVisible(true);

        checkBoxes.add(checkShowSteps);
        checkBoxes.add(checkDiagonals);

        speed = new JSlider();
        speed.setName("speed");
        speed.setOpaque(false);
        speed.setVisible(true);
        speed.setFocusable(false);
        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider slider = (JSlider) changeEvent.getSource();
                speed.setValue(slider.getValue());
                //frame.changeSpeed();
                frame.repaint();
            }
        });
    }
}
