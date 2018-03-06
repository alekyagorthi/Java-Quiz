import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/*<applet code="SplitPaneExp.java" width=615 height=525>
</applet>*/
public class SplitPaneExp extends JFrame implements ActionListener
{
String msg="";
int la;
char arr2[]= new char[15];
char arr1[]=new char[15];
int arr3[]=new int[15];
ButtonGroup bg[]=new ButtonGroup[15];
Random rn=new Random();
TextArea text;
int ba;
int ra=0;
String round[]={"Fest quiz.txt","java.txt","Fest quiz.txt","java.txt"};
public SplitPaneExp() 
{
setTitle("Example of Split Pane");
setSize(615, 525);                                //setting the size of the pane
setLayout(new BorderLayout());
Button sub=new Button("Submit");
sub.addActionListener(this);
Button b[]=new Button[15];                //creating 15 buttons
JPanel jsp1 = new JPanel();    
JPanel jsp2 = new JPanel();                   //creating 3 different  jpanels
JPanel jsp3 = new JPanel(); 
setResizable(false);                             //to stop the applet viewer from chainging its dimensions
JLabel j2 = new JLabel("OMR");
Label l[]=new Label[15];
for(int i=0;i<15;i++)                              //initialising the labels for the que no
l[i]=new Label(" "+(i+1));                            
JRadioButton rb[]=new JRadioButton[60];
for(int j=0;j<60;j+=4)
rb[j]=new JRadioButton("A");                //initialising the radio buutons like a,b,c,d for each que in the round
for(int j=1;j<60;j+=4)
rb[j]=new JRadioButton("B");
for(int j=2;j<60;j+=4)
rb[j]=new JRadioButton("C");
for(int j=3;j<60;j+=4)
rb[j]=new JRadioButton("D");
for(int i=0;i<15;i++)
{
arr1[i]='e';
arr2[i]='f';
}
int r=0;
for(int m=0;m<60;m++)
{
if(m%4==0)
{
jsp2.add(l[r]);                                     //jsp2 panel is for the radio buttons section
r++;                                                  
}
jsp2.add(rb[m]);
}
for(int i=0;i<15;i++)
b[i]=new Button(""+(i+1));                  //initialising the butons and adding the actions to it
for(int i=0;i<15;i++)
b[i].addActionListener(this);
for(int n=0;n<15;n++)
bg[n]=new ButtonGroup();
int k=0 ;
for(int i=0;i<60;i+=4)
{
for(int j=i;j<i+4;j++)
{
bg[k].add(rb[j]);                          //creating groups of the radio buttons such that only one among the group is selected at 
}                                                 //a time
k++;
}
jsp2.add(j2);                             //giving the label to the jsp2 panel as omr
text=new TextArea(35,54);
fileread();                                          //calling the function to get any 15 random que no
for(int m=0;m<15;m++)                 //adding the buttons to the jsp3 ie the 3rd panel
jsp3.add(b[m]);            
jsp3.add(sub);    
jsp1.add(text);                               //adding the text area to the jsp1 ie the 1st panel
add(jsp1,BorderLayout.WEST);               //placing the text area to the left of the pane
add(jsp2,BorderLayout.CENTER);          //placing the radio buttons to the center of the pane 
add(jsp3,BorderLayout.NORTH);            //placing the butons in the top of the pane
}
public void actionPerformed(ActionEvent ae)
{
if(la==-1)
{
System.exit(0);
}
String ch=ae.getActionCommand();
if(ch.equals("Submit"))
{
submit();                                                        //if we press the submit button then this will take to submit method
}
else
{
int que=Integer.parseInt(ae.getActionCommand());
int but;
char s;
but=0;
msg="";
try
{
FileInputStream f1=  new FileInputStream(round[ra]);
char q='@';                                                                              //if any question no is pressed
s=(char) f1.read(); 
while(but!=arr3[que-1]-1)                                                       //'but' variable is used to check in which que the  
{                                                                                             //the file pointer is pointing at
if(s==q)
but++;                                                                      //if @is encountered mens one que is over and the next que will start
s=(char) f1.read();
}                                                        //this loop will continue till it reaches the desired que 
s=(char) f1.read();
while(s!=q)                                  
{
msg+=s;
s=(char) f1.read();                                     //after reaching the que we store it in a string called msg
}
s=(char)f1.read();
arr1[que-1]=s;
System.out.println(s);
}
catch(IOException e)
{
}
text.setText(msg);                             //the string 'msg' is printed in the text area 
}
}
public void fileread()              //this function is for getting any 15 que no randomly
{
int arr[]=new int[50];
int h,a;
for(int x=0;x<15;x++)                //this loop is for no of questions
{
h=0;
a=0;
while(h!=1)                                 //to check wether we got the correct que no without repetion or not
{                                                     //if not then the loop will continue to get the correct que no
a=rn.nextInt(49);                     //this function is to get the no randomly from 1-50
if(arr[a]==0 && a!=0)      
{
h=1;
arr[a]=1;                                  //to check  wether the no is repeating or not
}
}
arr3[x]=a;                                 //put the no in the que no place 
}
}
 public String getSelectedButtonText(ButtonGroup buttonGroup) 
{
for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();)
{
AbstractButton button = buttons.nextElement();             //this function is to get the selected radio button
                                                                                         //name or the option associated in it 
if (button.isSelected()) 
{
return button.getText();
}
}
return null;
}
public void submit()                     //this function is to do the calculations  after the submit button is pressed
{
la=0;
for(int i=0;i<15;i++)
{
if(getSelectedButtonText(bg[i]) !=null)
arr2[i]=getSelectedButtonText(bg[i]).charAt(0);          //to get the selected option from that group 
}
for(int j=0;j<15;j++)
if(arr1[j]==arr2[j])                                     //to compare the given answer with the corrct answer
la++;
if(la>8 )
{
text.setText("You are pased to the next level with marks\n "+la);    
for(int i=0;i<15;i++)                                                                     
bg[i].clearSelection();                                                        //to clear the selections in the group
ra++;
if(ra<2)
fileread();                                          //to change the file and the que no
else
{
text.setText("You have completed all the levels successfully");     //if all the levels are over then
la=-1;
}
}
else
{
text.setText("sorry\n better luck next time\n"+la);                  //if the result is lessthan 8
la=-1;
}
}
public static void main(String[] args)
{
SplitPaneExp sp = new SplitPaneExp();                                         //main method to initiate the starting process
sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
sp.setVisible(true);
}
}