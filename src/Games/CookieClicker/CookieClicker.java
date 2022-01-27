package Games.CookieClicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CookieClicker {

    JLabel counterLabel, perSecLabel;
    JButton button1, button2, button3, button4, cursorUpgradeButton, grandpaUpgradeButton, farmUpgradeButton, factoryUpgradeButton;
    int cookieCounter = 0,
            timerSpeed,
            cursorNumber = 0,
            cursorPrice = 20,
            grandpaNumber = 0,
            grandpaPrice = 100,
            farmNumber = 0,
            farmPrice = 1000,
            factoryNumber = 0,
            factoryPrice = 10000,
            cursorUpgradePrice = 250,
            grandpaUpgradePrice = 2500,
            farmUpgradePrice = 7500,
            factoryUpgradePrice = 50000;
    double perSecond = 0;
    boolean timerOn = false,
            grandpaUnlocked = false,
            farmUnlocked = false,
            factoryUnlocked = false,
            cursorUpgradeUnlocked,
            cursorUpgradeActive = false,
            grandpaUpgradeUnlocked,
            grandpaUpgradeActive = false,
            farmUpgradeUnlocked,
            farmUpgradeActive = false,
            factoryUpgradeUnlocked,
            factoryUpgradeActive = false;
    Font font1 = new Font("Comic Sans MS", Font.PLAIN, 32),
            font2 = new Font("Comic Sans MS", Font.PLAIN, 15);
    CookieHandler cHandler = new CookieHandler();
    Timer timer;
    JTextArea messageText;
    MouseHandler mHandler = new MouseHandler();
    ImageIcon cookie = new ImageIcon("src/resources/cookie.png");
    ImageIcon cursor = new ImageIcon("src/resources/cursor.jpg");
    ImageIcon grandpa = new ImageIcon("src/resources/grandpa.jpg");
    ImageIcon farm = new ImageIcon("src/resources/farm.jpg");
    ImageIcon factory = new ImageIcon("src/resources/factory.jpg");
    ImageIcon cursorCheck = new ImageIcon("src/resources/cursorCheck.jpg");
    ImageIcon grandpaCheck = new ImageIcon("src/resources/grandpaCheck.jpg");
    ImageIcon farmCheck = new ImageIcon("src/resources/farmCheck.jpg");
    ImageIcon factoryCheck = new ImageIcon("src.resources/factoryCheck.jpg");

    public static void main(String[] args) {
        new CookieClicker();
    }

    public CookieClicker() {
        createUI();
    }
    public void createUI() {

        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);


        JPanel cookiePanel = new JPanel();
        cookiePanel.setBounds(100,220,200,200);
        cookiePanel.setBackground(Color.black);
        window.add(cookiePanel);

        JButton cookieButton = new JButton();
        cookieButton.setBackground(Color.black);
        cookieButton.setFocusPainted(false);
        cookieButton.setBorder(null);
        cookieButton.setIcon(cookie);
        cookieButton.addActionListener(cHandler);
        cookieButton.setActionCommand("cookie");
        cookiePanel.add(cookieButton);


        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100,100,200,100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2,1));
        window.add(counterPanel);

        counterLabel = new JLabel(cookieCounter + " cookies");
        counterLabel.setForeground(Color.white);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        perSecLabel = new JLabel();
        perSecLabel.setForeground(Color.white);
        perSecLabel.setFont(font2);
        counterPanel.add(perSecLabel);


        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(500,170,250,250);
        itemPanel.setBackground(Color.black);
        itemPanel.setLayout(new GridLayout(4,1));
        window.add(itemPanel);

        button1 = new JButton("Cursor");
        button1.setFont(font1);
        button1.setFocusPainted(false);
        button1.addActionListener(cHandler);
        button1.setActionCommand("Cursor");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        button2 = new JButton("?");
        button2.setFont(font1);
        button2.setFocusPainted(false);
        button2.addActionListener(cHandler);
        button2.setActionCommand("Grandpa");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        button3 = new JButton("?");
        button3.setFont(font1);
        button3.setFocusPainted(false);
        button3.addActionListener(cHandler);
        button3.setActionCommand("Farm");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        button4 = new JButton("?");
        button4.setFont(font1);
        button4.setFocusPainted(false);
        button4.addActionListener(cHandler);
        button4.setActionCommand("Factory");
        button1.addMouseListener(mHandler);
        itemPanel.add(button4);


        JPanel upgradePanel = new JPanel();
        upgradePanel.setBounds(500, 435, 250, 100);
        upgradePanel.setBackground(Color.white);
        upgradePanel.setLayout(new GridLayout(2, 5));
        /*
          [1][3][5][7][9]
          [2][4][6][8][10]
         */
        cursorUpgradeButton = new JButton();
        cursorUpgradeButton.setFocusPainted(false);
        cursorUpgradeButton.setIcon(cursor);
        cursorUpgradeButton.addActionListener(cHandler);
        cursorUpgradeButton.setActionCommand("CursorUpgrade");
        cursorUpgradeButton.addMouseListener(mHandler);
        upgradePanel.add(cursorUpgradeButton);

        grandpaUpgradeButton = new JButton();
        grandpaUpgradeButton.setFocusPainted(false);
        grandpaUpgradeButton.setIcon(grandpa);
        grandpaUpgradeButton.addActionListener(cHandler);
        grandpaUpgradeButton.setActionCommand("GrandpaUpgrade");
        grandpaUpgradeButton.addMouseListener(mHandler);
        upgradePanel.add(grandpaUpgradeButton);

        farmUpgradeButton = new JButton();
        farmUpgradeButton.setFocusPainted(false);
        farmUpgradeButton.setIcon(farm);
        farmUpgradeButton.addActionListener(cHandler);
        farmUpgradeButton.setActionCommand("FarmUpgrade");
        farmUpgradeButton.addMouseListener(mHandler);
        upgradePanel.add(farmUpgradeButton);

        factoryUpgradeButton = new JButton();
        factoryUpgradeButton.setFocusPainted(false);
        factoryUpgradeButton.setIcon(factory);
        factoryUpgradeButton.addActionListener(cHandler);
        factoryUpgradeButton.setActionCommand("FactoryUpgrade");
        factoryUpgradeButton.addMouseListener(mHandler);
        upgradePanel.add(factoryUpgradeButton);

        window.add(upgradePanel);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(500,70,250,250);
        messagePanel.setBackground(Color.black);
        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(500,70,250,250);
        messageText.setForeground(Color.white);
        messageText.setBackground(Color.black);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);

        window.setVisible(true);
    }
    public void setTimer() {

        timer = new Timer(timerSpeed, e -> {

            cookieCounter++;
            counterLabel.setText(cookieCounter + " cookies");

            if(!grandpaUnlocked) {
                if(cookieCounter >= grandpaPrice) {
                    grandpaUnlocked = true;
                    button2.setText("Grandpa " + "(" + grandpaNumber + ")");
                }
            }
            if(!farmUnlocked) {
                if(cookieCounter >= farmPrice) {
                    farmUnlocked = true;
                    button3.setText("Farm " + "(" + farmNumber + ")");
                }
            }
            if(!factoryUnlocked) {
                if(cookieCounter >= factoryPrice) {
                    factoryUnlocked = true;
                    button4.setText("Factory " + "(" + factoryNumber + ")");
                }
            }
            if(!cursorUpgradeUnlocked) {
                if(cookieCounter >= cursorUpgradePrice) {
                    cursorUpgradeUnlocked = true;
                }
            }
            if(!grandpaUpgradeUnlocked) {
                if(cookieCounter >= grandpaUpgradePrice) {
                    grandpaUpgradeUnlocked = true;
                }
            }
            if(!farmUpgradeUnlocked) {
                if(cookieCounter >= farmUpgradePrice) {
                    farmUpgradeUnlocked = true;
                }
            }
            if(!factoryUpgradeUnlocked) {
                if(cookieCounter >= factoryUpgradePrice) {
                    factoryUpgradeUnlocked = true;
                }
            }
        });
    }
    public void timerUpdate() {

        if(!timerOn) {
            timerOn = true;
        }
        else {
            timer.stop();
        }

        double speed = 1/perSecond*1000;
        timerSpeed = (int)Math.round(speed);

        String s = String.format("%.1f", perSecond);
        perSecLabel.setText("per second: " + s);

        setTimer();
        timer.start();
    }


    public class CookieHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String action = event.getActionCommand();

            switch(action) {
                case "cookie":
                    if(cursorUpgradeActive) {
                        cookieCounter = cookieCounter + 2;
                    }
                    else {
                        cookieCounter++;
                    }
                    counterLabel.setText(cookieCounter + " cookies");
                    break;
                case "Cursor":
                    if(cookieCounter >= cursorPrice) {
                        cookieCounter = cookieCounter - cursorPrice;
                        cursorPrice = cursorPrice + 5;
                        counterLabel.setText(cookieCounter + " cookies");

                        cursorNumber++;
                        button1.setText("Cursor " + "(" + cursorNumber + ")");
                        if (cursorUpgradeActive) {
                            perSecond = 2 * (perSecond + 0.1);
                        } else {
                            perSecond = perSecond + 0.1;
                        }
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                    break;
                case "Grandpa":
                    if(cookieCounter >= grandpaPrice) {
                        cookieCounter = cookieCounter - grandpaPrice;
                        grandpaPrice = grandpaPrice + 25;
                        counterLabel.setText(cookieCounter + " cookies");

                        grandpaNumber++;
                        button2.setText("Grandpa " + "(" + grandpaNumber + ")");
                        if(grandpaUpgradeActive) {
                            perSecond = 2 * (perSecond + 1);
                        }
                        else {
                            perSecond = perSecond + 1;
                        }
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                    break;
                case "Farm" :
                    if(cookieCounter >= farmPrice) {
                        cookieCounter = cookieCounter - farmPrice;
                        farmPrice = farmPrice + 100;
                        counterLabel.setText(cookieCounter + " cookies");

                        farmNumber++;
                        button3.setText("Farm " + "(" + farmNumber + ")");
                        if(farmUpgradeActive) {
                            perSecond = 2 * (perSecond + 10);
                        }
                        else {
                            perSecond = perSecond + 10;
                        }
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                    break;
                case "Factory":
                    if(cookieCounter >= factoryPrice) {
                        cookieCounter = cookieCounter - factoryPrice;
                        factoryPrice = factoryPrice + 500;
                        counterLabel.setText(cookieCounter + " cookies");

                        factoryNumber++;
                        button4.setText("Factory " + "(" + factoryNumber + ")");
                        if(factoryUpgradeActive) {
                            perSecond = 2 * (perSecond + 50);
                        }
                        else {
                            perSecond = perSecond + 50;
                        }
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                    break;
                case "CursorUpgrade":
                    if(cookieCounter >= cursorUpgradePrice) {
                        cookieCounter = cookieCounter - cursorUpgradePrice;
                        counterLabel.setText(cookieCounter + " cookies");
                        cursorUpgradeActive = true;
                        cursorUpgradeButton.setIcon(cursorCheck);
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                    break;
                case "GrandpaUpgrade" :
                    if(cookieCounter >= grandpaUpgradePrice) {
                        cookieCounter = cookieCounter - grandpaUpgradePrice;
                        counterLabel.setText(cookieCounter + " cookies");
                        grandpaUpgradeActive = true;
                        grandpaUpgradeButton.setIcon(grandpaCheck);
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                    break;
                case "FarmUpgrade" :
                    if(cookieCounter >= farmUpgradePrice) {
                        cookieCounter = cookieCounter - farmUpgradePrice;
                        counterLabel.setText(cookieCounter + " cookies");
                        farmUpgradeActive = true;
                        farmUpgradeButton.setIcon(farmCheck);
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                case "FactoryUpgrade" :
                    if(cookieCounter >= factoryUpgradePrice) {
                        cookieCounter = cookieCounter - factoryUpgradePrice;
                        counterLabel.setText(cookieCounter + " cookies");
                        factoryUpgradeActive = true;
                        factoryUpgradeButton.setIcon(factoryCheck);
                        timerUpdate();
                    }
                    else {
                        messageText.setText("You need more cookies!");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + action);
            }
        }
    }

    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            JButton button = (JButton)e.getSource();

            if(button == button1) {
                messageText.setText("Cursor\n[price: " + cursorPrice + "]\nAuto-clicks once every 10 seconds.");
            }
            else if(button == button2) {
                if(!grandpaUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Grandpa\n[price: " + grandpaPrice + "]\nEach grandpa produces 1 cookie per second.");
                }
            }
            else if(button == button3) {
                if(!farmUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Farm\n[price: " + farmPrice + "]\nEach farm produces 10 cookies per second");
                }
            }
            else if(button == button4) {
                if(!factoryUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Factory\n[price: " + factoryPrice + "]\nEach factory produces 50 cookies per second");
                }
            }
            else if(button == cursorUpgradeButton) {
                if(!cursorUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Cursor Upgrade\n[price: " + cursorUpgradePrice + "]\nMake every click produce 2 cookies instead of 1");
                }
            }
            else if(button == grandpaUpgradeButton) {
                if(!grandpaUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Grandpa Upgrade\n[price: " + grandpaUpgradePrice + "]\nDoubles grandpa's cookie production rate.");
                }
            }
            else if(button == farmUpgradeButton) {
                if(!farmUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Farm Upgrade\n[price: " + farmUpgradePrice + "]\nDoubles farm's cookie production rate.");
                }
            }
            else if(button == factoryUpgradeButton) {
                if(!factoryUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Factory Upgrade\n[price: " + factoryUpgradePrice + "]\nDoubles factory's cookie production rate.");
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            JButton button = (JButton)e.getSource();

            if(button == button1) {
                messageText.setText("Cursor\n[price: " + cursorPrice + "]\nAuto-clicks once every 10 seconds.");
            }
            else if(button == button2) {
                if(!grandpaUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Grandpa\n[price: " + grandpaPrice + "]\nEach grandpa produces 1 cookie per second.");
                }
            }
            else if(button == button3) {
                if(!farmUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Farm\n[price: " + farmPrice + "]\nEach farm produces 10 cookies per second");
                }
            }
            else if(button == button4) {
                if(!factoryUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Factory\n[price: " + factoryPrice + "]\nEach factory produces 50 cookies per second");
                }
            }
            else if(button == cursorUpgradeButton) {
                if(!cursorUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Cursor Upgrade\n[price: " + cursorUpgradePrice + "]\nMake every click produce 2 cookies instead of 1");
                }
            }
            else if(button == grandpaUpgradeButton) {
                if(!grandpaUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Grandpa Upgrade\n[price: " + grandpaUpgradePrice + "]\nDoubles grandpa's cookie production rate.");
                }
            }
            else if(button == farmUpgradeButton) {
                if(!farmUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Farm Upgrade\n[price: " + farmUpgradePrice + "]\nDoubles farm's cookie production rate.");
                }
            }
            else if(button == factoryUpgradeButton) {
                if(!factoryUpgradeUnlocked) {
                    messageText.setText("This item is currently locked!!");
                }
                else {
                    messageText.setText("Factory Upgrade\n[price: " + factoryUpgradePrice + "]\nDoubles factory's cookie production rate.");
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            JButton button = (JButton)e.getSource();

            if (button == button1) {
                messageText.setText(null);
            }
            else if (button == button2) {
                messageText.setText(null);
            }
            else if (button == button3) {
                messageText.setText(null);
            }
            else if (button == button4) {
                messageText.setText(null);
            }
            else if (button == cursorUpgradeButton) {
                messageText.setText(null);
            }
            else if (button == grandpaUpgradeButton) {
                messageText.setText(null);
            }
            else if (button == farmUpgradeButton) {
                messageText.setText(null);
            }
            else if (button == factoryUpgradeButton) {
                messageText.setText(null);
            }
        }
    }
}