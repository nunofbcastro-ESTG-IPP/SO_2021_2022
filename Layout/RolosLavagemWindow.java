package Layout;

import javax.swing.*;

public abstract class RolosLavagemWindow {

    private static final String defaultModeloCarro = "Modelo do carro: ";
    private static final String defaultDonoCarro = "Dono do carro: ";

    protected JFrame janela;
    private JLabel jLabelTitulo;
    private JLabel jLabelModeloCarro;
    private JLabel jLabelDonoCarro;
    private JLabel jLabelTempo;
    private JLabel jLabelParado;
    private JPanel panel;
    private JLabel imageCar;

    {
        this.panel = null;
        Layout.setLayoutLookAndFeel();
        this.initComponents();
    }

    protected void mudarTempo(int time) {
        this.jLabelTempo.setText("Tempo para acabar rolos: " + time);
    }

    protected void rolosLavagemParado() {
        Layout.clearPanel(this.panel);

        this.panel.add(this.jLabelTitulo);
        this.panel.add(this.jLabelParado);

        this.janela.add(this.panel);

        Layout.refreshPanel(this.panel);
    }

    protected void rolosLavagemEmUtilizacao(String modelo, String dono) {
        Layout.clearPanel(this.panel);

        this.jLabelModeloCarro.setText(defaultModeloCarro + modelo);
        this.jLabelDonoCarro.setText(defaultDonoCarro + dono);
        this.jLabelTempo.setText("");

        this.panel.add(this.jLabelTitulo);
        this.panel.add(this.imageCar);
        this.panel.add(this.jLabelModeloCarro);
        this.panel.add(this.jLabelDonoCarro);
        this.panel.add(this.jLabelTempo);

        Layout.refreshPanel(this.panel);
    }

    private void initComponents() {
        this.janela = Layout.makeFrame("Rolos", "./images/Icon.png", 300, 300);

        this.panel = Layout.createPanel();
        this.jLabelTitulo = Layout.createLabel("Rolos lavagem", 24);
        this.jLabelTitulo.setForeground(new java.awt.Color(102, 102, 102));
        this.jLabelParado = Layout.createLabel("Rolos parados", 16);
        this.jLabelModeloCarro = Layout.createLabel("", 16);
        this.jLabelDonoCarro = Layout.createLabel("", 16);
        this.jLabelTempo = Layout.createLabel("", 16);
        this.imageCar = Layout.createImage("./images/carwash.gif", 200, 150);
        this.janela.add(this.panel);

        this.rolosLavagemParado();
    }

    protected void VisibleFrame() {
        Layout.visibleFrame(janela);
    }

}
