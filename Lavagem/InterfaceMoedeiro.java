package Lavagem;

import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import Enums.Estado;
import Enums.TipoBotao;

import java.awt.event.*;

import Layout.MoedeiroWindow;
import Utils.Configs;
import Utils.Utils;

public class InterfaceMoedeiro extends MoedeiroWindow implements Runnable {

    private final Semaphore semMoedeiro;
    private final Semaphore semMoedeiroMain;
    private final SharedObjMoedeiro sharedObjMoedeiro;
    private double dinheiroInserido;

    {
        this.dinheiroInserido = 0;
    }

    public InterfaceMoedeiro(Semaphore semMoedeiroMain, Semaphore semMoedeiro,
            SharedObjMoedeiro sharedObjMoedeiro) {
        this.semMoedeiroMain = semMoedeiroMain;
        this.semMoedeiro = semMoedeiro;
        this.sharedObjMoedeiro = sharedObjMoedeiro;
        super.jLabelPrecoLavagemValor
                .setText(Utils.doubleToStringTwoCase(Configs.getPreco()));
        super.jLabelEstadoSistemaValor.setText(Estado.ToEstadoString(this.sharedObjMoedeiro.getEstado()));
        this.VisibleFrame();
    }

    private void addDinheiro(double moneyToAdd) {
        this.jButtonCancelar.setEnabled(true);
        this.dinheiroInserido += moneyToAdd;
        super.jLabelValorInseridoValor.setText(Utils.doubleToStringTwoCase(this.dinheiroInserido) + " €");
    }

    private void limparJanela() {
        this.jButtonCancelar.setEnabled(false);

        super.jLabelValorInseridoValor.setText(this.emptyEuro);
        super.jTextFieldMarcaCarro.setText("");
        super.jTextFieldProprietarioCarro.setText("");

        this.limparSharedObjMoedeiro();
    }

    private void limparSharedObjMoedeiro() {
        this.dinheiroInserido = 0;

        this.sharedObjMoedeiro.setDinheiroInserido(0);
        this.sharedObjMoedeiro.setTroco(0);
        this.sharedObjMoedeiro.setNomeProprietario(null);
        this.sharedObjMoedeiro.setModeloCarro(null);
    }

    private void changeButtonsFecharLavagem() {
        super.jButtonEstadoAberto.setEnabled(true);

        super.jButton5Cents.setEnabled(false);
        super.jButton10Cents.setEnabled(false);
        super.jButton20Cents.setEnabled(false);
        super.jButton50Cents.setEnabled(false);
        super.jButton1Eur.setEnabled(false);
        super.jButton2Eur.setEnabled(false);
        super.jButton5Eur.setEnabled(false);
        super.jButton10Eur.setEnabled(false);
        super.jButton20Eur.setEnabled(false);
        super.jButton50Eur.setEnabled(false);
        super.jButtonCancelar.setEnabled(false);
        super.jButtonEmergencia.setEnabled(false);
        super.jButtonEstadoFechado.setEnabled(false);
        super.jButtonIniciar.setEnabled(false);
        super.jButtonReset.setEnabled(false);
        super.jTextFieldMarcaCarro.setEnabled(false);
        super.jTextFieldProprietarioCarro.setEnabled(false);
        
        if (this.sharedObjMoedeiro!=null && this.sharedObjMoedeiro.getBotaoSelecionado() != TipoBotao.Emergencia) {
            this.dinheiroInserido = 0;
            this.limparJanela();
        }

    }

    private void fecharLavagens() {
        this.sharedObjMoedeiro.setBotaoSelecionado(TipoBotao.Fechado);
        this.semMoedeiro.release();
    }

    private void changeButtonsAbrirLavagens() {
        super.jButtonEstadoAberto.setEnabled(false);
        super.jButton5Cents.setEnabled(true);
        super.jButton10Cents.setEnabled(true);
        super.jButton20Cents.setEnabled(true);
        super.jButton50Cents.setEnabled(true);
        super.jButton1Eur.setEnabled(true);
        super.jButton2Eur.setEnabled(true);
        super.jButton5Eur.setEnabled(true);
        super.jButton10Eur.setEnabled(true);
        super.jButton20Eur.setEnabled(true);
        super.jButton50Eur.setEnabled(true);
        super.jButtonCancelar.setEnabled(false);
        super.jButtonEmergencia.setEnabled(true);
        super.jButtonEstadoFechado.setEnabled(true);
        super.jButtonIniciar.setEnabled(true);
        super.jButtonReset.setEnabled(true);
        super.jTextFieldMarcaCarro.setEnabled(true);
        super.jTextFieldProprietarioCarro.setEnabled(true);
    }

    private void abrirLavagens() {
        this.sharedObjMoedeiro.setBotaoSelecionado(TipoBotao.Aberto);
        this.semMoedeiro.release();
    }

    public void manageTroco() {
        if (!this.sharedObjMoedeiro.isDadosValidos()) {
            JOptionPane.showMessageDialog(super.janela,
                    "Os dados inseridos são invalidos. O modelo do carro deve ter no minimo 2 carateres e o nome no minimo 3 carateres.",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
        } else if (this.sharedObjMoedeiro.getTroco() >= 0) {
            if (this.sharedObjMoedeiro.getTroco() > 0) {
                JOptionPane.showMessageDialog(super.janela,
                        "A retirar o troco... "
                                + Utils.doubleToStringTwoCase(
                                        this.sharedObjMoedeiro.getTroco())
                                + " €",
                        "Alerta", JOptionPane.WARNING_MESSAGE);
            }
            this.limparJanela();
            this.limparSharedObjMoedeiro();
        } else {
            JOptionPane.showMessageDialog(super.janela,
                    "Não tem dinheiro suficiente para realizar a lavagem", "Alerta",
                    JOptionPane.WARNING_MESSAGE);
        }
        this.sharedObjMoedeiro.setDadosValidos(false);
        this.jButtonCancelar.setEnabled(false);
    }

    public void setActionBtnIniciar() {
        this.sharedObjMoedeiro.setBotaoSelecionado(TipoBotao.Iniciar);
        this.sharedObjMoedeiro.setDinheiroInserido(this.dinheiroInserido);
        this.sharedObjMoedeiro.setModeloCarro(super.jTextFieldMarcaCarro.getText());
        this.sharedObjMoedeiro.setNomeProprietario(super.jTextFieldProprietarioCarro.getText());
        this.semMoedeiro.release();
    }

    public void setActionBtnCancelar() {
        this.sharedObjMoedeiro.setBotaoSelecionado(TipoBotao.Cancelar);
        this.sharedObjMoedeiro.setDinheiroInserido(this.dinheiroInserido);
        this.semMoedeiro.release();
    }

    private void manageCancel() {
        JOptionPane.showMessageDialog(super.janela,
                "A retirar o dinheiro inserido... "
                        + Utils.doubleToStringTwoCase(this.sharedObjMoedeiro.getTroco())
                        + " €",
                "Alerta", JOptionPane.WARNING_MESSAGE);
        this.limparJanela();
        this.limparSharedObjMoedeiro();
    }

    public void setActionBtnEmergencia() {
        this.sharedObjMoedeiro.setBotaoSelecionado(TipoBotao.Emergencia);
        this.semMoedeiro.release();
    }

    public void setActionBtnReset() {
        this.sharedObjMoedeiro.setBotaoSelecionado(TipoBotao.Reset);
        this.semMoedeiro.release();
    }

    @Override
    protected void addActionButtons() {
        this.changeButtonsFecharLavagem();

        this.janela.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent ke) {
                switch (ke.getKeyChar()) {
                    case 'A':
                    case 'a':
                        abrirLavagens();
                        break;
                    case 'F':
                    case 'f':
                        fecharLavagens();
                        break;
                }
            }

            public void keyPressed(KeyEvent ke) {
            }

            public void keyReleased(KeyEvent ke) {
            }
        });

        super.janela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                janela.requestFocus();
            }
        });

        super.jButton5Cents.addActionListener(e -> {
            this.addDinheiro(0.05);
        });

        super.jButton10Cents.addActionListener(e -> {
            this.addDinheiro(0.1);
        });

        super.jButton20Cents.addActionListener(e -> {
            this.addDinheiro(0.2);
        });

        super.jButton50Cents.addActionListener(e -> {
            this.addDinheiro(0.5);
        });

        super.jButton1Eur.addActionListener(e -> {
            this.addDinheiro(1.0);
        });

        super.jButton2Eur.addActionListener(e -> {
            this.addDinheiro(2.0);
        });

        super.jButton5Eur.addActionListener(e -> {
            this.addDinheiro(5.0);
        });

        super.jButton10Eur.addActionListener(e -> {
            this.addDinheiro(10.0);
        });

        super.jButton20Eur.addActionListener(e -> {
            this.addDinheiro(20.0);
        });

        super.jButton50Eur.addActionListener(e -> {
            this.addDinheiro(50.0);
        });

        super.jButtonEstadoFechado.addActionListener(e -> {
            this.fecharLavagens();
        });

        super.jButtonEstadoAberto.addActionListener(e -> {
            this.abrirLavagens();
        });

        super.jButtonIniciar.addActionListener(e -> {
            this.setActionBtnIniciar();
        });

        super.jButtonCancelar.addActionListener(e -> {
            this.setActionBtnCancelar();
        });

        super.jButtonEmergencia.addActionListener(e -> {
            this.setActionBtnEmergencia();
        });

        super.jButtonReset.addActionListener(e -> {
            this.setActionBtnReset();
        });
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.semMoedeiroMain.acquire();
                switch (this.sharedObjMoedeiro.getEstado()) {
                    case Livre:
                        super.jLabelEstadoSistemaValor.setText(Estado
                                .ToEstadoString(this.sharedObjMoedeiro.getEstado()));
                        this.changeButtonsAbrirLavagens();
                        break;
                    case Fechado:
                        super.jLabelEstadoSistemaValor.setText(Estado
                                .ToEstadoString(this.sharedObjMoedeiro.getEstado()));
                        this.changeButtonsFecharLavagem();
                        break;
                    case Ocupado:
                        super.jLabelEstadoSistemaValor
                                .setText(Estado.ToEstadoString(
                                        this.sharedObjMoedeiro.getEstado()));
                        this.changeButtonsAbrirLavagens();
                        break;
                    default:
                        break;
                }
                switch (this.sharedObjMoedeiro.getBotaoSelecionado()) {
                    case Cancelar:
                        this.manageCancel();
                        break;
                    case Iniciar:
                        this.manageTroco();
                        break;
                    case Emergencia:
                        if (this.sharedObjMoedeiro.getEstado() == Estado.Fechado) {
                            super.jButtonEstadoAberto.setEnabled(false);
                            super.jButtonEmergencia.setEnabled(true);

                        } else if (this.dinheiroInserido != 0) {
                            super.jButtonCancelar.setEnabled(true);
                        }
                        break;
                    default:
                        break;
                }
                this.sharedObjMoedeiro.setBotaoSelecionado(TipoBotao.NaoSelecionado);
            } catch (InterruptedException e) {
            }
        }
    }

}
