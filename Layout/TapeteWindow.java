package Layout;

import javax.swing.*;

public abstract class TapeteWindow {

    private static final String defaultModeloCarro = "Modelo do carro: ";
    private static final String defaultDonoCarro = "Dono do carro: ";
    protected static final String defaultTempoIniciar = "Tempo para iniciar lavagem: ";
    protected static final String defaultTempoAcabar = "Tempo para acabar lavagem: ";
    protected static final String defaultTempoAposTerminoLavagemCarro = "Saida do carro: ";

    protected JFrame janela;
    private JLabel jLabelTitulo;
    private JLabel jLabelModeloCarro;
    private JLabel jLabelDonoCarro;
    private JLabel jLabelTempo;
    private JLabel jLabelVazio;
    private JLabel jLabelEspera;
    private JLabel jLabelPardo;
    private JPanel panel;
    private JLabel imageCar;

    {
        this.panel = null;
        Layout.setLayoutLookAndFeel();
        this.initComponents();
    }

    protected void tapeteVazio() {
        Layout.clearPanel(this.panel);

        this.panel.add(this.jLabelTitulo);
        this.panel.add(this.jLabelVazio);

        Layout.refreshPanel(this.panel);
    }

    protected void tapeteParado() {
        Layout.clearPanel(this.panel);

        this.panel.add(this.jLabelTitulo);
        this.panel.add(this.jLabelPardo);

        Layout.refreshPanel(this.panel);
    }

    protected void tapeteEspera() {
        Layout.clearPanel(this.panel);

        this.panel.add(this.jLabelTitulo);
        this.panel.add(this.imageCar);
        this.panel.add(this.jLabelEspera);

        Layout.refreshPanel(this.panel);
    }

    protected void mudarTempo(int time, String tipo) {
        this.jLabelTempo.setText(tipo + time);
    }

    protected void tapeteEmUtilizacao(String modelo, String dono) {
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
        this.janela = Layout.makeFrame("Tapete", "./images/Icon.png", 300, 300);

        this.panel = Layout.createPanel();
        this.jLabelTitulo = Layout.createLabel("Tapete", 24);
        this.jLabelTitulo.setForeground(new java.awt.Color(102, 102, 102));
        this.jLabelVazio = Layout.createLabel("Sem carros para lavar", 16);
        this.jLabelModeloCarro = Layout.createLabel("", 16);
        this.jLabelDonoCarro = Layout.createLabel("", 16);
        this.jLabelTempo = Layout.createLabel("", 16);
        this.jLabelEspera = Layout.createLabel("Tapete em espera dos rolos, aspersores e secador.", 16);
        this.imageCar = Layout.createImage("./images/carwash.gif", 200, 150);
        this.janela.add(this.panel);

        this.tapeteVazio();
    }

    protected void VisibleFrame() {
        Layout.visibleFrame(janela);
    }
}
