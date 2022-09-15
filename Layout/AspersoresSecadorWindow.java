package Layout;

import javax.swing.*;

public abstract class AspersoresSecadorWindow {

    private static final String defaultModeloCarro = "Modelo do carro: ";
    private static final String defaultDonoCarro = "Dono do carro: ";
    protected static final String defaultSecador = "Tempo para acabar secador: ";
    protected static final String defaultAspersores = "Tempo para acabar aspersores: ";

    protected JFrame janela;
    private JLabel jLabelTitulo;
    private JLabel jLabelModeloCarro;
    private JLabel jLabelDonoCarro;
    private JLabel jLabelTempo;
    private JLabel jLabelParados;
    private JPanel panel;
    private JLabel imageCar;

    {
        this.panel = null;
        Layout.setLayoutLookAndFeel();
        this.initComponents();
    }

    protected void mudarTempo(int time, String tipo) {
        this.jLabelTempo.setText(tipo + time);
    }

    protected void aspersoresSecadorParados() {
        Layout.clearPanel(this.panel);

        this.panel.add(this.jLabelTitulo);
        this.panel.add(this.jLabelParados);

        this.janela.add(this.panel);

        Layout.refreshPanel(this.panel);
    }

    protected void aspersoresSecadorEmUtilizacao(String modelo, String dono) {
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
        this.janela = Layout.makeFrame("Aspersores e Secadores", "./images/Icon.png", 300, 300);

        this.panel = Layout.createPanel();
        this.jLabelTitulo = Layout.createLabel("Aspersores e Secadores", 24);
        this.jLabelTitulo.setForeground(new java.awt.Color(102, 102, 102));
        this.jLabelParados = Layout.createLabel("Aspersores e Secadores parados", 16);
        this.jLabelModeloCarro = Layout.createLabel("", 16);
        this.jLabelDonoCarro = Layout.createLabel("", 16);
        this.jLabelTempo = Layout.createLabel("", 16);
        this.imageCar = Layout.createImage("./images/carwash.gif", 200, 150);
        this.janela.add(this.panel);

        this.aspersoresSecadorParados();
    }

    protected void VisibleFrame() {
        Layout.visibleFrame(janela);
    }

}
