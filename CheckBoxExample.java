import java.awt.*;
import javax.swing.*; 
import java.awt.event.*;

class CheckBoxExample {

    public static void main(String[] args) {

	cBLFrame window = new cBLFrame("Check Box Listener Example"); 
	window.init();
    }
}

class cBLFrame extends JFrame {

    public cBLFrame(String s){
	super(s);
    }

    public void init(){

	JCheckBox red = new JCheckBox("Red");
	JCheckBox bold = new JCheckBox("Bold");
	JLabel out = new JLabel("    The message    ");

	Container panel = this.getContentPane();
	panel.setLayout(new GridLayout(3,1));
	panel.add(red);
	panel.add(bold);
	panel.add(out);
	
	red.addItemListener( new redListener(out) );
        bold.addItemListener( new boldListener(out) );

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	
	this.setSize(300,100); 
	this.setVisible(true); 
    }
}

class redListener implements ItemListener {

    JLabel out;
    public redListener(JLabel out){
	this.out = out;
    }

    public void itemStateChanged(ItemEvent e){
	if (e.getStateChange() == ItemEvent.SELECTED){
	    out.setForeground(Color.red);
	}
	else out.setForeground(Color.black);
    }

}

class boldListener implements ItemListener {

    JLabel out;
    public boldListener(JLabel out){
	this.out = out;
    }


    public void itemStateChanged(ItemEvent e){
	if (e.getStateChange() == ItemEvent.SELECTED){
	    out.setFont(out.getFont().deriveFont(Font.BOLD));
	}
	else  out.setFont(out.getFont().deriveFont(Font.PLAIN));
    }

}