package ru.avalon.java.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends AbstractFrame{


    @Override
    protected void onCreate() {
        setTitle("Калькулятор");
        CalculatorPanel panel = new CalculatorPanel();
        add(panel);
        pack();
    }
}

class CalculatorPanel extends JPanel{

    private JButton display;
    private JPanel panel;

    //переменная, используется для проверки
    //учитывает начало работы в калькуляторе
    private boolean startStatus = true;

    //начальные условия работы
    private double result = 0;
    private String lastInstruction = "=";

    //формирование панели калькулятора
    public CalculatorPanel(){

        //разметка панели
        setLayout(new BorderLayout());

        //дисплей калькулятора из неактивной кнопки
        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);

        //слушатели кнопок ввода и действий
        ActionListener input = new InputAction();
        ActionListener instruction = new InstructionAction();

        //панель управления и разметка 4х4
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        //строки калькулятора
        addButton("7", input);
        addButton("8", input);
        addButton("9", input);
        addButton("+", instruction);

        addButton("4", input);
        addButton("5", input);
        addButton("6", input);
        addButton("-", instruction);

        addButton("1", input);
        addButton("2", input);
        addButton("3", input);
        addButton("*", instruction);

        addButton("0", input);
        addButton(".", input);
        addButton("/", instruction);
        addButton("=", instruction);

        add(panel, BorderLayout.CENTER);
    }

    //создание кнопки со встраиванием слушателя
    private void addButton(String text, ActionListener listener) {

        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    //слушатель ввода цифр
    private class InputAction implements ActionListener {
        public void actionPerformed(ActionEvent event){

            String input = event.getActionCommand();

            if(startStatus) {
                display.setText("");
                startStatus = false;
            }
            display.setText(display.getText() + input);
        }
    }

    //слушатель ввода команд
    private class InstructionAction implements ActionListener {
        public void actionPerformed(ActionEvent event){

            String instruction = event.getActionCommand();

            if(startStatus) {
                if (instruction.equals("")) {
                    display.setText("");
                    startStatus = false;
                }
                else lastInstruction = instruction;
            }
            else {
                calculate(Double.parseDouble(display.getText()));
                lastInstruction = instruction;
                startStatus = true;
            }
        }
    }

    //калькуляция
    private void calculate(double x) {

        if(lastInstruction.equals("+")) result += x;
        else if (lastInstruction.equals("-")) result -= x;
        else if (lastInstruction.equals("*")) result *= x;
        else if (lastInstruction.equals("/")) result /= x;
        else if (lastInstruction.equals("=")) result = x;

        display.setText("" + result);
    }
}