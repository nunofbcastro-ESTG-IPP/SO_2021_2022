package Layout;

import javax.swing.*;

public abstract class MoedeiroWindow {

    protected String emptyEuro = "0,00 €";

    protected JFrame janela;
    protected JButton jButton10Cents;
    protected JButton jButton10Eur;
    protected JButton jButton1Eur;
    protected JButton jButton20Cents;
    protected JButton jButton20Eur;
    protected JButton jButton2Eur;
    protected JButton jButton50Cents;
    protected JButton jButton50Eur;
    protected JButton jButton5Cents;
    protected JButton jButton5Eur;
    protected JButton jButtonCancelar;
    protected JButton jButtonEmergencia;
    protected JButton jButtonEstadoAberto;
    protected JButton jButtonEstadoFechado;
    protected JButton jButtonIniciar;
    protected JButton jButtonReset;
    private JLabel jLabelEstadoSistemaTexto;
    protected JLabel jLabelEstadoSistemaValor;
    private JLabel jLabelMarcaCarro;
    private JLabel jLabelPrecoLavagemTexto;
    protected JLabel jLabelPrecoLavagemValor;
    private JLabel jLabelProprietarioCarro;
    private JLabel jLabelTitulo;
    private JLabel jLabelValorInseridoTexto;
    protected JLabel jLabelValorInseridoValor;
    private JSeparator jSeparator1;
    protected JTextField jTextFieldMarcaCarro;
    protected JTextField jTextFieldProprietarioCarro;

    {
        Layout.setLayoutLookAndFeel();
        this.initComponents();
        this.addActionButtons();
    }

    private void initComponents() {
        this.janela = Layout.makeFrame("Moedeiro", "./images/Icon.png", 813, 553);

        this.jLabelTitulo = Layout.createLabel("Moedeiro", 24);
        this.jLabelTitulo.setForeground(new java.awt.Color(102, 102, 102));
        this.jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        this.jLabelValorInseridoTexto = Layout.createLabel("Valor inserido: ", 16);
        this.jLabelPrecoLavagemTexto = Layout.createLabel("Preço da lavagem: ", 16);

        this.jLabelMarcaCarro = Layout.createLabel("Marca do carro:", 16);
        this.jLabelEstadoSistemaTexto = Layout.createLabel("Estado do sistema:", 16);
        this.jLabelProprietarioCarro = Layout.createLabel("Proprietário do carro:", 16);
        this.jLabelEstadoSistemaValor = Layout.createLabel("Aberto", 14);
        this.jLabelPrecoLavagemValor = Layout.createLabel(this.emptyEuro, 14);
        this.jLabelValorInseridoValor = Layout.createLabel(this.emptyEuro, 14);

        this.jTextFieldMarcaCarro = new JTextField();
        this.jTextFieldProprietarioCarro = new JTextField();
        this.jSeparator1 = new JSeparator();

        this.jButtonIniciar = Layout.createButton("Iniciar");
        this.jButtonCancelar = Layout.createButton("Cancelar");
        this.jButtonEmergencia = Layout.createButton("Emergência");
        this.jButtonReset = Layout.createButton("Reset");
        this.jButtonEstadoAberto = Layout.createButton("Aberto");
        this.jButtonEstadoFechado = Layout.createButton("Fechado");

        this.jButton5Cents = Layout.createButton("0,05 €");
        this.jButton10Cents = Layout.createButton("0,10 €");
        this.jButton20Cents = Layout.createButton("0,20 €");
        this.jButton50Cents = Layout.createButton("0,50 €");
        this.jButton1Eur = Layout.createButton("1 €");
        this.jButton2Eur = Layout.createButton("2 €");
        this.jButton5Eur = Layout.createButton("5 €");
        this.jButton10Eur = Layout.createButton("10 €");
        this.jButton20Eur = Layout.createButton("20 €");
        this.jButton50Eur = Layout.createButton("50 €");

        GroupLayout layout = new GroupLayout(this.janela.getContentPane());
        this.janela.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabelPrecoLavagemTexto)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabelPrecoLavagemValor,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        115,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabelEstadoSistemaTexto)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabelEstadoSistemaValor,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        115,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabelMarcaCarro)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTextFieldMarcaCarro,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        182,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabelProprietarioCarro)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTextFieldProprietarioCarro,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        182,
                                                                        GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jButton5Cents,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        86,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jButton10Cents,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        86,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jButton20Cents,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        86,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jButton50Cents,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        86,
                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jButton1Eur,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        86,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(
                                                                                        GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(
                                                                                                        jButton20Eur,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        86,
                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        jButton50Eur,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        86,
                                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(
                                                                                                        jButton2Eur,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        86,
                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        jButton5Eur,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        86,
                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        jButton10Eur,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        86,
                                                                                                        GroupLayout.PREFERRED_SIZE)))))
                                                                .addGap(60, 60, 60)
                                                                .addComponent(jButtonIniciar)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                        GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jButtonCancelar)
                                                                                .addPreferredGap(
                                                                                        LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jButtonEmergencia)
                                                                                .addPreferredGap(
                                                                                        LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jButtonReset))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jButtonEstadoAberto)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jButtonEstadoFechado))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabelValorInseridoTexto)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabelValorInseridoValor,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        115,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED)))
                                                .addContainerGap(23,
                                                        Short.MAX_VALUE)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTitulo, GroupLayout.PREFERRED_SIZE,
                                        54,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelEstadoSistemaTexto)
                                        .addComponent(jLabelEstadoSistemaValor,
                                                GroupLayout.PREFERRED_SIZE,
                                                22,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPrecoLavagemTexto)
                                        .addComponent(jLabelPrecoLavagemValor,
                                                GroupLayout.PREFERRED_SIZE,
                                                22,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE,
                                        10,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelMarcaCarro,
                                                GroupLayout.PREFERRED_SIZE,
                                                33,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldMarcaCarro,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelProprietarioCarro,
                                                GroupLayout.PREFERRED_SIZE,
                                                33,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldProprietarioCarro,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelValorInseridoTexto)
                                        .addComponent(jLabelValorInseridoValor,
                                                GroupLayout.PREFERRED_SIZE,
                                                22,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(
                                                GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton5Cents,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        55,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton10Cents,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        55,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton20Cents,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        55,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton50Cents,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        55,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButtonIniciar)
                                                        .addComponent(jButtonCancelar)
                                                        .addComponent(jButtonEmergencia)
                                                        .addComponent(jButtonReset))
                                                .addPreferredGap(
                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButtonEstadoAberto)
                                                        .addComponent(jButtonEstadoFechado))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1Eur,
                                                GroupLayout.PREFERRED_SIZE,
                                                55,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2Eur,
                                                GroupLayout.PREFERRED_SIZE,
                                                55,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton5Eur,
                                                GroupLayout.PREFERRED_SIZE,
                                                55,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton10Eur,
                                                GroupLayout.PREFERRED_SIZE,
                                                55,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton20Eur,
                                                GroupLayout.PREFERRED_SIZE,
                                                55,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton50Eur,
                                                GroupLayout.PREFERRED_SIZE,
                                                55,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(67, Short.MAX_VALUE)));

    }

    abstract protected void addActionButtons();

    protected void VisibleFrame() {
        Layout.visibleFrame(janela);
    }
}
